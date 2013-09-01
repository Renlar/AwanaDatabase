package awana.database;

import java.util.Date;

/**
 *
 * @author Renlar
 */
public class Section {
	private String name;
	private boolean completed;
	private Date completionDate;

	public Section(String name, boolean completed, Date completionDate){
		this.name = name;
		this.completed = completed;
		this.completionDate = completionDate;
	}

	public void setCompleted(boolean completed){
		this.completed = completed;
		if(completed == true){
			completionDate = new Date();
		}else{
			completionDate = null;
		}
	}

	public boolean isCompleted() {
		return completed;
	}

	public String getName(){
		return name;
	}

	public Date getCompletionDate(){
		return completionDate;
	}
}