package awana.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.jdbcx.JdbcDataSource;
//TODO: rewrite wrapper using http://iciql.com/ to simplify database connections.
/**
 *
 * @author Renlar
 */
public class DatabaseWrapper {

	public static final String delete = "DELETE ";
	public static final String create = "CREATE ";
	public static final String from = "FROM ";
	public static final String insert = "INSERT ";
	public static final String into = "INTO ";
	public static final String select = "SELECT ";
	public static final String set = "SET ";
	public static final String table = "TABLE ";
	public static final String update = "UPDATE ";
	public static final String where = "WHERE ";

	public static final String bool = "BOOLEAN ";
	public static final String date = "DATE ";
	public static final String varchar100 = "VARCHAR(100) ";

	private String dataTable = "directory";
	private Connection h2DatabaseConnection;

	public DatabaseWrapper() {
		createDatabaseConnection();
		//TODO: fix isDatabaseGenerated() so this test will work thereby eliminating gient error at beginning of code execution.
		//if (!isDatabaseGenerated()) {
			generateDatabase();
		//}
	}

	public Student getStudent(int ID) {
		try {
			executeQuery("");
		} catch (Exception ex) {
			Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public void saveStudent(Student s) {
		throw new UnsupportedOperationException();
	}

	public Student newStudent() {
		throw new UnsupportedOperationException();
		//TODO: create records;
	}

	public Vector<Record> getRecordsAsVector(){
		ResultSet resultSet;
		Vector<Record> records = new Vector<>();
		try{
			resultSet = executeQuery(select + Student.studentFieldTableColumns[0][0] + ", " + Student.studentFieldTableColumns[0][1]
					+ ", " + Student.studentFieldTableColumns[0][2] + " " + from + dataTable + ";");
		}
		catch(Exception ex){
			Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
			//TODO: figure out how to log this exception so it reports this location without createing a duplicate error log.
			return null;
		}
		try {
			Record r;
			while(resultSet.next()){
			 r = new Record(resultSet.getInt(Student.studentFieldTableColumns[0][0]),
					resultSet.getNString(Student.studentFieldTableColumns[0][1]),
					resultSet.getNString(Student.studentFieldTableColumns[0][2]));
				records.add(r);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
		}
		return records;
	}

	public void reconnectDatabase() {
		if (h2DatabaseConnection != null) {
			closeDatabase();
		}
		createDatabaseConnection();
	}

	private void createDatabaseConnection() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL("jdbc:h2:Data/AWANA");
		dataSource.setUser("sa");
		dataSource.setPassword("sa");
		connectToDatabase(dataSource);
	}

	private void connectToDatabase(JdbcDataSource ds) {
		try {
			h2DatabaseConnection = ds.getConnection();
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
			//System.err.println("DatabaseConnectionError: " + ex.getMessage());
		}
	}

	public void runStatement(String sqlstmt) {
		System.out.println(sqlstmt);
		Statement stmt;
		System.out.print("Status: ");
		try {
			stmt = h2DatabaseConnection.createStatement();
			stmt.executeUpdate(sqlstmt);
			stmt.close();
			System.out.println("Success");

		} catch (SQLException ex) {
			System.out.println("Failed");
			//System.err.println("SQLException: " + ex.getMessage());
			Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public ResultSet executeQuery(String sqlstmt) throws Exception {
		System.out.println(sqlstmt);
		System.out.print("Status: ");
		try {
			ResultSet result = queryDatabase(sqlstmt);
			System.out.println("Success");
			return result;
		} catch (Exception ex) {
			System.out.println("Failed");
			//System.err.println("SQLException: " + ex.getMessage());
			Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
			throw ex;
		}
	}

	private ResultSet queryDatabase(String sqlstmt) throws Exception {
		Statement select = h2DatabaseConnection.createStatement();
		ResultSet result = select.executeQuery(sqlstmt);
		return result;
	}

	public void printResultSet(ResultSet result) {
		try {
			printQueryResults(result);
			result.beforeFirst();
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void printQueryResults(ResultSet result) throws SQLException {
		int rownum = 0;
		ResultSetMetaData resultMetaData = result.getMetaData();
		int numberOfColumns = resultMetaData.getColumnCount();
		System.out.println("Got results:");
		while (result.next()) { // process results one row at a time
			System.out.print(" Row " + rownum + " | ");
			for (int i = 1; i <= numberOfColumns; i++) {
				System.out.print(resultMetaData.getColumnName(i) + " : " + result.getString(i));
				if (i < numberOfColumns) {
					System.out.print(", ");
				}
			}
			System.out.println();
			rownum++;
		}
	}

	public void closeDatabase() {
		try {
			h2DatabaseConnection.close();
			h2DatabaseConnection = null;
		} catch (SQLException ex) {
			//System.err.println("DatabaseCloseError: " + ex.getMessage());
			Logger.getLogger(DatabaseWrapper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private boolean isDatabaseGenerated() {
			String statement = select + "1 " + from + dataTable + ";";
			boolean empty = false;
			try {
				executeQuery(statement);
			} catch (Exception ex) {
				empty = true;
			}
			if(empty){
				return false;
			}
		return true;
	}

	private void generateDatabase() {
		String statement = create + table + dataTable + " ( ";
			statement = statement.concat("ID INTEGER NOT NULL PRIMARY KEY");
		for (int i = 0; i < Student.studentFieldTableColumns[0].length; i++) {
			statement = statement.concat(", \"" + Student.studentFieldTableColumns[0][i] + "\" ");
			statement = statement.concat(Student.studentFieldTableColumns[1][i]);
		}
		for (int i = 0; i < Book.bookNames.length; i++) {
			for (int j = 0; j < Book.bookSections[i].length; j++) {
				statement = statement.concat(", \"" + Book.bookNames[i] + "_");
				statement = statement.concat(Book.bookSections[i][j] + "\" ");
				statement = statement.concat(bool);
				statement = statement.concat(", \"" + Book.bookNames[i] + "_");
				statement = statement.concat(Book.bookSections[i][j] );
				statement = statement.concat("_date" + "\" ");
				statement = statement.concat(date);
				//System.out.println(statement);
			}
		}
		statement = statement.concat(" );");
		runStatement(statement);
	}
}
/*

	public void createTable(String collumnName) {
		throw new UnsupportedOperationException();
	}

	public void addCollumn(String collumnName) {
		throw new UnsupportedOperationException();
	}
	 public ArrayList<String> getStudentNames(){
	 throw new UnsupportedOperationException();
	 }
	 public ArrayList<Student> getStudents(){
	 throw new UnsupportedOperationException();
	 }
	 public void saveStudents(ArrayList<Student> students){
	 throw new UnsupportedOperationException();
	 }
	 public Book getStudentBook(int ID, String bookName){//TODO: seperate main table into sections using book name prefixes.
	 throw new UnsupportedOperationException();
	 }
	 public ArrayList<String> getCompletedBooks(String studentID){
	 throw new UnsupportedOperationException();
	 }

	public ArrayList<String> getBookList() {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Record> getRecordsAsArrayList() {
		throw new UnsupportedOperationException("Not yet implemented");
	}*/