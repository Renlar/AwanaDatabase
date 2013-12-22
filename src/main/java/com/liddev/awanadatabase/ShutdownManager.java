package com.liddev.awanadatabase;

/**
 *
 * @author Renlar <liddev.com>
 */
public class ShutdownManager implements Runnable{
	private DirectoryPage page;
	private DatabaseWrapper databaseWrapper;

	ShutdownManager(DirectoryPage page, DatabaseWrapper databaseWrapper) {
		this.page = page;
		this.databaseWrapper = databaseWrapper;
	}

	@Override
	public void run() {

	}

}
