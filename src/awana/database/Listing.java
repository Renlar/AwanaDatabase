package awana.database;

/**
 *
 * @author Renlar
 */
public class Listing {

	private int ID;
	private String firstName;
	private String lastName;

	public Listing(int ID, String firstName, String lastName) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getID() {
		return ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullNameLastFirst() {
		return lastName + ", " + firstName;
	}

	public int compairName(Listing l) {
		int test = compairNameIgnoreCase(this.lastName, l.lastName);
		if(test != 0){
			return test;
		}
		test = compairNameIgnoreCase(this.firstName, l.firstName);
		return test;
	}

	public int compairID(Listing r){
		if(this.ID > r.ID){
			return 1;
		}
		if(this.ID < r.ID){
			return -1;
		}
		return 0;
	}

	private int compairNameIgnoreCase(String a, String b) {
		if(a == null && b == null){
			return 0;
		}
		if(a == null){
			return -1;
		}
		if(b == null){
			return 1;
		}
		a = a.toLowerCase();
		b = b.toLowerCase();
		if (a.compareTo(b) == 0) {
			return 0;
		}
		for (int i = 0; i < a.length() && i < b.length(); i++) {
			if (a.charAt(i) > b.charAt(i)) {
				return 1;
			}
			if (a.charAt(i) < b.charAt(i)) {
				return -1;
			}
		}
		return 0;
	}

	@Override
	public String toString(){
		return getFullNameLastFirst() + " (" + getID() + ")";
	}

	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(o.getClass() == this.getClass()){
			Listing r = (Listing) o;
			if(this.ID == r.getID()){
				return true;
			}
		}
		return false;
	}
}
