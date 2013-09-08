package awana.database;

/**
 *
 * @author Renlar <liddev.com>
 */
public class DataManager implements Runnable, Shutdown {

	private static DataManager dataManager;

	private DataManager() {
	}

	@Override
	public void run() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public int onStop() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	synchronized public static DataManager getDataManager(){
		if(dataManager == null){
			dataManager = new DataManager();
		}
		return dataManager;
	}
}
