package awana.database;

/**
 *
 * @author Renlar
 */
public class Record {

	private int ID;
	private String firstName;
	private String lastName;

	public Record(int ID, String firstName, String lastName) {
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

	public int compairName(Record r) {
		int test = compairNameIgnoreCase(this.lastName, r.lastName);
		if(test != 0){
			return test;
		}
		test = compairNameIgnoreCase(this.firstName, r.firstName);
		if(test != 0){
			return test;
		}
		return 0;
	}

	public int compairID(Record r){
		if(this.ID > r.ID){
			return 1;
		}
		if(this.ID < r.ID){
			return -1;
		}
		return 0;
	}

	private int compairNameIgnoreCase(String a, String b) {
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
		if(o.getClass() == this.getClass()){
			Record r = (Record) o;
			if(this.ID == r.ID){
				return true;
			}
		}
		return false;
	}
}
