package awana.database;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Renlar
 */
public class Book {
	public static final String[] bookNames = {
		"T&T_Ultimate Adventure 1","T&T_Ultimate Adventure 2","T&T_Ultimate Challenge 1","T&T_Ultimate Challenge 2",
		"Treck_Treck Check","Treck_Roadsign Series","Treck_Dashboard Series","Treck_Billboard Series",
		"Journey_Faith Foundations", "Journey_Main Study 1", "Journey_Main Study 2", "Journey_Main Study 3", "Journey_Main Study 4",
		"Joureny_Elective 1", "Joureny_Elective 2", "Joureny_Elective 3", "Joureny_Elective 4", "Journey_Bible Reading"};
	public static final String[][] bookSections = {
		//1
		{"Discovery 1", "Discovery 2", "Discovery 3", "Discovery 4", "Discovery 5", "Discovery 6", "Discovery 7", "Discovery 8"},
		//2
		{"Discovery 1", "Discovery 2", "Discovery 3", "Discovery 4", "Discovery 5", "Discovery 6", "Discovery 7", "Discovery 8"},
		//3
		{"Discovery 1", "Discovery 2", "Discovery 3", "Discovery 4", "Discovery 5", "Discovery 6", "Discovery 7", "Discovery 8"},
		//4
		{"Discovery 1", "Discovery 2", "Discovery 3", "Discovery 4", "Discovery 5", "Discovery 6", "Discovery 7", "Discovery 8"},
		//5
		{""},
		//6
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Oasis 1",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Oasis 2"},
		//7
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Oasis 1",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Oasis 2"},
		//8
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Oasis 1",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Oasis 2"},
		//9
		{""},
		//10
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Review 1-6",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Review 7-12"},
		//11
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Review 1-6",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Review 7-12"},
		//12
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Review 1-6",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Review 7-12"},
		//13
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Review 1-6",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Review 7-12"},
		//14
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Review 1-6",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Review 7-12"},
		//15
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Review 1-6",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Review 7-12"},
		//16
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Review 1-6",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Review 7-12"},
		//17
		{"Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Review 1-6",
			"Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Review 7-12"},
		//18
		{"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges", "Ruth",
			"1 Samuel", "2 Samuel", "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra",
			"Nehemiah", "Esther", "Job", "Psalm", "Proverbs", "Ecclesiastes", "Song of Solomon",
			"Isaiah", "Jeremiah", "Lamentations", "Ezekiel", "Daniel", "Hosea", "Joel", "Amos",
			"Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah",
			"Malachi", "Matthew", "Mark", "Luke", "John", "Acts", "Romans", "1 Corinthians",
			"2 Corinthians", "Galatians", "Ephesians", "Philippians", "Colossians", "1 Thessalonians",
			"2 Thessalonians", "1 Timothy", "2 Timothy", "Titus", "Philemon", "Hebrews", "James",
			"1 Peter", "2 Peter", "1 John", "2 John", "3 John", "Jude", "Revelation"}
	};

	private ArrayList<Section> sections;
	private String group;
	private String name;
	private boolean completed;
	private Date completionDate;

	public Book(String groupAndName, ArrayList<Section> sections, boolean completed, Date completionDate){
		setGroupAndName(groupAndName);
		this.sections = sections;
		this.completed = completed;
		this.completionDate = completionDate;
	}

	private void setGroupAndName(String groupAndName){
		int index = groupAndName.indexOf('_');
		group = groupAndName.substring(0, index - 1);
		name = groupAndName.substring(index + 1);
	}

	public String getFullName(){
		return group + "_" + name;
	}

	public String getGroup(){
		return group;
	}

	public String getName(){
		return name;
	}

	public int getNumberOfSections(){
		return sections.size();
	}

	public boolean isCompleted(){
		return completed;
	}

	public void setCompleted(boolean completed){
		this.completed = completed;
		if(completed == true){
			completionDate = new Date();
			setAllSectionsCompleted();
		}else{
			completionDate = null;
		}
	}

	public ArrayList<Section> getCompletedSections(){
		ArrayList<Section> completedSections = new ArrayList<>();
		for(int i = 0; i < sections.size(); i++){
			Section test = sections.get(i);
			if(test.isCompleted()){
				completedSections.add(test);
			}
		}
		return completedSections;
	}

	public Date getCompletionDate(){
		return (Date) completionDate.clone();
	}

	private void setAllSectionsCompleted(){
			Section check;
		for(int i = 0; i < sections.size(); i++){
			check = sections.get(i);
			if(!check.isCompleted()){
				check.setCompleted(true);
			}
		}
	}

	public Section getSection(int i) {
		return sections.get(i);
	}
}
