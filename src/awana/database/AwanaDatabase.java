/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package awana.database;

import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 *
 * @author Renlar <liddev.com>
 */
public class AwanaDatabase {


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
		Thread settings = new Thread(SettingsManager.getSettingsManager());
		settings.setPriority(Thread.MAX_PRIORITY);
		settings.start();

		/**Start the backup system*/
		Thread backup = new Thread(BackupSystem.getBackupSystem());
		settings.setPriority(Thread.NORM_PRIORITY);
		backup.start();

		Record.loadMasterData(); //do not remove temporary record load fix will be replaced with dynamic loading once variable yml field loading is supproted
		DatabaseWrapper databaseWrapper = new DatabaseWrapper();  //must call Record.loadMasterData() before creating a DatabaseWrapper

		/* Create and display the form */
		DirectoryPage page;
		page = new DirectoryPage(databaseWrapper);
		page.setVisible(true);

		/*create the shutdown hook*/
		Thread shutdown = new Thread(new ShutdownManager(page, databaseWrapper));
		Runtime.getRuntime().addShutdownHook(shutdown);
	}
}
