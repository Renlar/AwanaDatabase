package awana.database;

import java.util.ArrayList;

/**
 *
 * @author Renlar
 */
public class Student {
    public static final String[][] studentFieldTableColumns = {
		{"firstName", "lastName", "studentEmail", "legalGuardianName",
		"motherName", "motherCell", "fatherName", "fatherCell", "parentEmail",
		"addressLine1", "addressLine2", "city", "stateOrProvince", "zip",
		"country", "county", "emergencyContactName", "emergencyContactPhone"},

		{"VARCHAR(100)", "VARCHAR(100)", "VARCHAR(100)", "VARCHAR(100)",
			"VARCHAR(100)", "BIGINT", "VARCHAR(100)", "BIGINT", "VARCHAR(100)",
			"VARCHAR(100)", "VARCHAR(100)", "VARCHAR(100)", "VARCHAR(100)", "INTEGER",
			"VARCHAR(100)", "VARCHAR(100)", "VARCHAR(100)", "BIGINT"}
	};

	private int ID;
	private String firstName;
	private String lastName;
	private EmailAddress studentEmail;
	private String legalGuardianName;
	private String motherName;
	private PhoneNumber motherCell;
	private String fatherName;
	private PhoneNumber fatherCell;
	private EmailAddress parentEmail;
	private Address address;
	private String emergencyContactName;
	private PhoneNumber emergencyContactPhone;
	private ArrayList<Book> books;

	@Override
	public boolean equals(Object o) {
		if (o.getClass() == this.getClass()) {
			Student s = (Student) o;
			if (s.getID() == this.getID()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 79 * hash + this.ID;
		return hash;
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

	public EmailAddress getStudentEmail() {
		return studentEmail;
	}

	public String getLegalGuardianName() {
		return legalGuardianName;
	}

	public String getMotherName() {
		return motherName;
	}

	public PhoneNumber getMotherCell() {
		return motherCell;
	}

	public String getFatherName() {
		return fatherName;
	}

	public PhoneNumber getFatherCell() {
		return fatherCell;
	}

	public EmailAddress getParentEmail() {
		return parentEmail;
	}

	public Address getAddress() {
		return address;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public PhoneNumber getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	public ArrayList<Book> getBookList(){
		return (ArrayList<Book>)books.clone();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setStudentEmail(EmailAddress studentEmail) {
		this.studentEmail = studentEmail;
	}

	public void setLegalGuardianName(String legalGuardianName) {
		this.legalGuardianName = legalGuardianName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public void setMotherCell(PhoneNumber motherCell) {
		this.motherCell = motherCell;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public void setFatherCell(PhoneNumber fatherCell) {
		this.fatherCell = fatherCell;
	}

	public void setParentEmail(EmailAddress parentEmail) {
		this.parentEmail = parentEmail;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public void setEmergencyContactPhone(PhoneNumber emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}

	public void setBookList(ArrayList<Book> books){
		this.books = books;
	}

	public Record createRecord() {
		return new Record(ID, firstName, lastName);
	}
}