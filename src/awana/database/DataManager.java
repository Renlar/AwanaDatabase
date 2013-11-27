package awana.database;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Renlar <liddev.com>
 */
public class DataManager implements Runnable, Shutdown {

	private static DataManager dataManager;
	private static SynchronousQueue<Execute> execute;
	private static boolean run;
	private static DefaultListModel<Listing> masterListModel;
	private static DefaultListModel<Listing> displayListModel;
	private Record currentSelection;

	private DataManager() {
		execute = new SynchronousQueue<>();
		run = true;
	}

	@Override
	public void run() {
		while (run || !execute.isEmpty()) {
			if (!execute.isEmpty()) {
				execute.poll().execute();
			}else{
				/*try {
					Thread.currentThread().wait(1000);
				} catch (InterruptedException ex) {
					Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
				}*/
			}
		}
	}

	@Override
	public void stop() {
		run = false;
	}

	public static synchronized DataManager get() {
		if (dataManager == null) {
			dataManager = new DataManager();
		}
		return dataManager;
	}

	static void search(KeyEvent evt) {
		execute.add(new SearchRecords(evt));
	}

	public void newRecord() {
		execute.add(new NewRecord());
	}

	public void deleteRecord() {
		execute.add(new DeleteRecord());
	}

	public void selectionChanged() {
		execute.add(new SelectionChanged());
	}

	public DefaultListModel<Listing> getList() {
		execute.add(new ListModel());
		return displayListModel;
	}

	private static class SearchRecords implements Execute {

		KeyEvent evt;
		String s;

		public SearchRecords(KeyEvent evt) {
			this.evt = evt;
			s = DirectoryPage.get().getSearchText();
		}

		public String execute() {
			char pressed = evt.getKeyChar();
			if (pressed == '\b' || pressed == (char) 127 || s.equalsIgnoreCase(null) || s.isEmpty()) {  //TODO: improve efficiency
				displayListModel.removeAllElements();
				for(Listing l : searchRecords(masterListModel, s)){
					displayListModel.addElement(l);
				}
			} else {
				ArrayList<Listing> results = searchRecords(displayListModel, s);
				displayListModel.removeAllElements();
				for(Listing l : results){
					displayListModel.addElement(l);
				}
			}
			return null;  //TODO: Say something interesting
		}

		private ArrayList<Listing> searchRecords(DefaultListModel<Listing> searchSet, String text) {
			ArrayList<Listing> resultSet = new ArrayList<Listing>();
			if (searchSet.isEmpty()) {
				return resultSet;
			}
			int counter = 0;
			while (counter < searchSet.size()) {
				Listing testee = searchSet.get(counter);
				if (testee.getFirstName() != null && testee.getLastName() != null && (testee.getLastName().contains(text) || testee.getFirstName().contains(text))) {
					resultSet.add(testee);
				}
				counter++;
			}
			return resultSet;
		}
	}

	private static class ListModel implements Execute {

		public ListModel() {
			displayListModel = new DefaultListModel<>();
			displayListModel.addListDataListener(DirectoryPage.get());//TODO: check if this is necessary
		}

		@Override
		public String execute() {
			masterListModel = DatabaseWrapper.get().getRecordListingsAsDefaultListModel();
			SortRecordsAlphabeticlyQuickSort(masterListModel);
			return null; //TODO: say something interesting.
		}
	}

	private class SelectionChanged implements Execute {

		private Listing newSelection;
		private Record record;

		public SelectionChanged() {
			newSelection = DirectoryPage.get().getSelectedListing();
			record = currentSelection;
		}

		@Override
		public String execute() {
			if (record != null) {
				if (newSelection == null || record.getID() != newSelection.getID()) {
					saveRecord(record);
				}
			}
			updateRecordData(newSelection);
			return null; //TODO: say something useful.
		}
	}

	private class DeleteRecord implements Execute {

		private Listing l;

		public DeleteRecord() {
			l = DirectoryPage.get().getSelectedListing();
		}

		@Override
		public String execute() {
			removeListing(l);  //TODO: consider moving into constructor
			DatabaseWrapper.get().deleteListing(l);
			return null;
		}
	}

	private class NewRecord implements Execute {

		private Record saveRecord;

		public NewRecord() {
			saveRecord = currentSelection;
		}

		@Override
		public String execute() {
			saveCurrentAndCreateNewRecord();
			return null;  //TODO: provide helpful message about new record and success state.
		}

		private void saveCurrentAndCreateNewRecord() {
			saveRecord(saveRecord);
			Record s = newRecord();
			updateRecordData(s);
		}

		private Record newRecord() {
			Record s = DatabaseWrapper.get().newRecord();
			Listing l = s.createListing();
			addListing(l);
			DirectoryPage.get().selectListing(l);
			return s;
		}
	}

	private void updateRecordData(Listing newSelection) {
		clearRecordData();
		currentSelection = DatabaseWrapper.get().getRecord(newSelection.getID());
		currentSelection.draw(DirectoryPage.get().recordData());//TODO: change so this method updates a pregenerated tab set instead of making a new one.
	}

	private void updateRecordData(Record newSelection) {
		clearRecordData();
		currentSelection = newSelection;
		currentSelection.draw(DirectoryPage.get().recordData());//TODO: change so this method updates a pregenerated tab set instead of making a new one.
	}

	private void clearRecordData() {//TODO: replace with data loading into current tabs not clear and regenerate just remove from display when no record is selected
		currentSelection = null;
		DirectoryPage.get().removeAllRecordData();
	}

	private void saveRecord(Record r) {
		if (r != null) {
			DatabaseWrapper.get().saveRecord(r);
			updateListings();
		}
	}

	public static boolean stringsEqual(String a, String b) {
		if (b == null && a == null) {
			return true;
		} else if (b == null || a == null) {
			return false;
		} else {
			return a.equals(b);
		}
	}

	public void updateListings() {
		int index = masterListModel.indexOf(currentSelection.createListing());
		if (nameChanged()) {
			masterListModel.remove(index);
			addListing(currentSelection.createListing());
		}
	}

	public boolean nameChanged() {
		int index = masterListModel.indexOf(currentSelection.createListing());
		Listing l = masterListModel.get(index);
		if (!stringsEqual(l.getFirstName(), currentSelection.get("First Name").getData())) {
			return true;
		} else if (!stringsEqual(l.getLastName(), currentSelection.get("Last Name").getData())) {
			return true;
		}
		return false;
	}

	public static void removeListing(Listing r) {
		masterListModel.removeElement(r);
	}

	public static void addListing(Listing listing) {
		int insertLocation = getInsertLocation(listing);
		masterListModel.insertElementAt(listing, insertLocation);
		displayListModel = masterListModel;
	}

	public static int getInsertLocation(Listing listing) {
		int loc = 0;
		boolean notFound = true;
		if (masterListModel.size() > 0) {
			loc = (masterListModel.size() - 1) / 2;
			int increment = loc;
			while (notFound) {
				increment /= 2;
				if (increment == 0) {
					increment++;
				}

				int compair = masterListModel.get(loc).compairName(listing);
				int compairBelow = -1;

				if (loc > 0 && loc < masterListModel.size()) {
					compairBelow = masterListModel.get(loc - 1).compairName(listing);
				}
				if (compair == 0) {
					notFound = false;
				} else if (compair == 1 && compairBelow == -1) {
					notFound = false;
				} else if (compair == 1) {
					loc -= increment;
				} else if (compair == -1) {
					loc += increment;
				}
				if (loc == masterListModel.size()) {
					notFound = false;
				}
			}
		}
		return loc;
	}

	public static void SortRecordsAlphabeticlyQuickSort(DefaultListModel<Listing> list) {
		if (!list.isEmpty()) {
			quickSortAlphabeticly(list, 0, list.size() - 1);
		}
	}

	private static void quickSortAlphabeticly(DefaultListModel<Listing> list, int left, int right) {
		int partition = quickSortPartition(list, left, right);
		if (left < partition - 1) {
			quickSortAlphabeticly(list, left, partition - 1);
		}
		if (partition < right) {
			quickSortAlphabeticly(list, partition, right);
		}
	}

	private static int quickSortPartition(DefaultListModel<Listing> list, int left, int right) {
		int i = left, j = right;
		Listing tmp1, tmp2;
		Listing pivot = list.get((left + right) / 2);

		while (i <= j) {
			while (list.get(i).compairName(pivot) == -1) {
				i++;
			}
			while (list.get(j).compairName(pivot) == 1) {
				j--;
			}
			if (i <= j) {
				tmp1 = list.get(i);
				tmp2 = list.get(j);
				list.remove(i);
				list.add(i, tmp2);
				list.remove(j);
				list.add(j, tmp1);
				i++;
				j--;
			}
		}

		return i;
	}
}
