package awana.database;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 *
 * @author Renlar <liddev.com>
 */
public class AwanaDatabase {

	private static ArrayList<Shutdown> threads = new ArrayList<>();
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		LogManager.getLogManager().getLogger("").setLevel(Level.ALL);
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


		/**Start the settings manager*/
		SettingsManager sm = SettingsManager.get();
		threads.add(sm);
		Thread settings = new Thread(sm);
		settings.setPriority(Thread.MAX_PRIORITY);
		settings.start();

		/**Start the backup system*/
		BackupSystem bs = BackupSystem.get();
		threads.add(bs);
		Thread backup = new Thread(bs);
		backup.setPriority(Thread.MIN_PRIORITY);
		backup.start();

		/**Start the Data Manager*/
		DataManager dm = DataManager.get();
		threads.add(bs);
		Thread data = new Thread(dm);
		data.setPriority(Thread.NORM_PRIORITY);
		data.start();

		Record.loadMasterData(); //do not remove temporary record load fix will be replaced with dynamic loading once variable yml field loading is supproted
		DatabaseWrapper databaseWrapper = DatabaseWrapper.get();  //must call Record.loadMasterData() before creating a DatabaseWrapper

		/* Create and display the form */
		DirectoryPage page;
		page = DirectoryPage.get();
		page.setVisible(true);

		/*create the shutdown hook*/
		Thread shutdown = new Thread(new ShutdownManager(page, databaseWrapper));
		Runtime.getRuntime().addShutdownHook(shutdown);
	}
}
