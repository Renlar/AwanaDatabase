package awana.database;

/**
 *
 * @author Renlar <liddev.com>
 */
public class Shutdown implements Runnable{
	private DirectoryPage page;
	private DatabaseWrapper databaseWrapper;

	Shutdown(DirectoryPage page, DatabaseWrapper databaseWrapper) {
		this.page = page;
		this.databaseWrapper = databaseWrapper;
	}

	@Override
	public void run() {
		page.saveCurrentRecord();
		databaseWrapper.closeDatabase();
	}

}
