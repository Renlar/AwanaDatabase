package awana.database;

import awana.database.Record;
import awana.database.Shutdown;

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
	public void stop() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public static synchronized SettingsManager get(){
		if(settingsManager == null){
			settingsManager = new SettingsManager();
		}
		return settingsManager;
	}
}
