package awana.database;

/**
 *
 * @author Justin VanDeBrake
 */
public class Shutdown implements Runnable{
	private DirectoryPage page;

	Shutdown(DirectoryPage page) {
		this.page = page;
	}

	@Override
	public void run() {
		page.onClose();
	}

}
