package awana.database;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author Renlar
 */
public class DirectoryPage extends javax.swing.JFrame{

	private static Vector<Listing> recordListings;
	private static DatabaseWrapper databaseWrapper;
	private Vector<Listing> displayList;
	private Record selectedRecord;
	Shutdown s = new Shutdown(this);

	/**
	 * Creates new form DirectoryPage
	 */
	public DirectoryPage() {
		initComponents();
		Record.loadMasterData(); //do not remove temporary record load fix will be replaced with dynamic loading once variable yml field loading is supproted
		databaseWrapper = new DatabaseWrapper();
		loadSortAndDisplayListings();
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        recordData = new javax.swing.JTabbedPane();
        recordScrollPane = new javax.swing.JScrollPane();
        recordItemList = new javax.swing.JList();
        newRecord = new javax.swing.JButton();
        deleteRecord = new javax.swing.JButton();
        searchBox = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        recordScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        recordScrollPane.setName(""); // NOI18N

        recordItemList.setModel(getListItems());
        recordItemList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        recordItemList.setName(""); // NOI18N
        recordItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listingSelected(evt);
            }
        });
        recordItemList.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                listingSelected(evt);
            }
        });
        recordScrollPane.setViewportView(recordItemList);

        newRecord.setText("New");
        newRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newRecordHandler(evt);
            }
        });

        deleteRecord.setText("Delete");
        deleteRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteRecordHandler(evt);
            }
        });

        searchBox.setForeground(new java.awt.Color(150, 150, 150));
        searchBox.setText("Search");
        searchBox.setName(""); // NOI18N
        searchBox.setPreferredSize(new java.awt.Dimension(200, 25));
        searchBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBoxFocusGainedHandler(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBoxFocusLostHandler(evt);
            }
        });
        searchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchBoxKeyTypedHandler(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recordScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(recordData, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recordData)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recordScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newRecord)
                            .addComponent(deleteRecord))))
                .addContainerGap())
        );

        recordScrollPane.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newRecordHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newRecordHandler
		saveCurrentRecord();
		newRecord();
		updateRecordData();
    }//GEN-LAST:event_newRecordHandler

    private void deleteRecordHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteRecordHandler
		String msgNoRecordSelected = "No Record Selected.";
		Listing delete = getSelectedListing();
		if (delete == null) {
			JOptionPane.showMessageDialog(this, msgNoRecordSelected,
					"Null",
					JOptionPane.YES_NO_OPTION);
			return;
		}
		String[] confirmDeleteOptions = {"Delete", "Cancel"};
		String msgConfirmDelete = "<html>Are You sure you want to delete,</html>\n<html>"
				+ delete.getFullNameLastFirst()
				+ "</html>.\n<html><b>This can not be undone.</b></html>";
		int choice = JOptionPane.showOptionDialog(this, msgConfirmDelete,
				"Confirm Delete",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				confirmDeleteOptions,
				confirmDeleteOptions[1]);
		if (choice == JOptionPane.YES_OPTION) {
			databaseWrapper.deleteListing(delete);
			removeAndUpdateListing(delete);
		}
		clearRecordData();
    }//GEN-LAST:event_deleteRecordHandler

    private void searchBoxFocusGainedHandler(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusGainedHandler
		searchBox.setForeground(java.awt.Color.BLACK);
		searchBox.setText("");
    }//GEN-LAST:event_searchBoxFocusGainedHandler

    private void searchBoxKeyTypedHandler(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBoxKeyTypedHandler
		/*
		char pressed = evt.getKeyChar();
		if (pressed == '\b' || pressed == (char) 127) {
			displayList = searchRecords(recordListings, searchBox.getText());
		} else {
			displayList = searchRecords(displayList, searchBox.getText());
		}
		recordItemList.removeAll();
		updateListingDisplay();
		*/
    }//GEN-LAST:event_searchBoxKeyTypedHandler

    private void searchBoxFocusLostHandler(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusLostHandler
		searchBox.setForeground(Color.GRAY);
		searchBox.setText("Search");
    }//GEN-LAST:event_searchBoxFocusLostHandler

    private void listingSelected(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listingSelected
		if (recordItemList.getSelectedValue() != null) {
			Listing newSelection = (Listing) recordItemList.getSelectedValue();
			if (selectedRecord == null || selectedRecord.getID() != newSelection.getID()) {
				saveCurrentRecord();
				updateRecordData();
			}
		}
    }//GEN-LAST:event_listingSelected

	public void saveCurrentRecord() {
		if (selectedRecord != null) {
			databaseWrapper.saveRecord(selectedRecord);
			updateListings();
		}
	}

	public void updateListings(){
		recordListings.remove(selectedRecord.createListing());
		recordListings.add(selectedRecord.createListing());
		sortAndDisplayListings();
	}

	public void updateRecordData() {
		clearRecordData();
		selectedRecord = databaseWrapper.getRecord(((Listing) recordItemList.getSelectedValue()).getID());
		selectedRecord.draw(recordData);
	}

	public void clearRecordData() {
		recordData.removeAll();
		recordData.validate();
	}

	public void removeAndUpdateListing(Listing r) {
		recordListings.remove(r);
		sortAndDisplayListings();
	}

	public void newRecord() {
		Record s = databaseWrapper.newRecord();
		Listing l = s.createListing();
		addAndDisplayListing(l);
		selectListing(l);
	}

	public void addAndDisplayListing(Listing r) {
		recordListings.add(r);
		sortAndDisplayListings();
	}

	public void loadSortAndDisplayListings() {
		recordListings = databaseWrapper.getRecordListingsAsVector();
		sortAndDisplayListings();
	}

	public void sortAndDisplayListings() {
		displayList = (Vector<Listing>) recordListings.clone();
		SortRecordsAlphabeticlyQuickSort(displayList);
		recordItemList.setListData(displayList);
		//updateListingDisplay();
	}
	private void SortRecordsAlphabeticlyQuickSort(Vector<Listing> list) {
		if (!recordListings.isEmpty()) {
			quickSortAlphabeticly(list, 0, list.size() - 1);
		}
	}

	private void quickSortAlphabeticly(Vector<Listing> list, int left, int right) {
		int index = partition(list, left, right);
		if (left < index - 1) {
			quickSortAlphabeticly(list, left, index - 1);
		}
		if (index < right) {
			quickSortAlphabeticly(list, index, right);
		}
	}

	private int partition(Vector<Listing> list, int left, int right) {
		int i = left, j = right;
		Listing tmp1, tmp2;
		Listing pivot = list.get((left + right) / 2);

		while (i <= j) {
			while (list.get(i).compairName(pivot) == -1) {
				i++;
			}
			while (list.get(i).compairName(pivot) == 1) {
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

	public void updateListingDisplay() {
		recordItemList.validate();  //TODO: find out which of the following lines are necessary
		recordItemList.repaint();
		recordScrollPane.validate();
		recordScrollPane.repaint();
	}

	public void selectListing(Listing l) {
		recordItemList.setSelectedValue(l, true);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(DirectoryPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		DirectoryPage page;
				page = new DirectoryPage();
				page.setVisible(true);
		Thread t = new Thread(page.s);
		Runtime.getRuntime().addShutdownHook(t);
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteRecord;
    private javax.swing.JButton newRecord;
    private javax.swing.JTabbedPane recordData;
    private javax.swing.JList recordItemList;
    private javax.swing.JScrollPane recordScrollPane;
    private javax.swing.JTextField searchBox;
    // End of variables declaration//GEN-END:variables


	private ListModel getListItems() {
		DefaultListModel list = new DefaultListModel();
		return list;
	}

	private Listing getSelectedListing() {
		return (Listing) recordItemList.getSelectedValue();
	}

	private Vector<Listing> searchRecords(Vector<Listing> searchSet, String text) {
		Vector<Listing> resultSet = new Vector<Listing>();
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
	//TODO: put searching entries and loading data in seperate threads from application to eliminate temperary locking of application.
	public void onClose(){
		saveCurrentRecord();
		databaseWrapper.closeDatabase();
	}
}