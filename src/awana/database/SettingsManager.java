package awana.database;

/**
 *
 * @author Renlar <liddev.com>
 */
class SettingsManager implements Runnable, Shutdown {

	private static SettingsManager settingsManager;
	private SettingsManager() {
	}

	@Override
	public void run() {
		Record.loadMasterData();
	}

	@Override
	public int onStop() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	synchronized public static SettingsManager getSettingsManager(){
		if(settingsManager == null){
			settingsManager = new SettingsManager();
		}
		return settingsManager;
	}
}
