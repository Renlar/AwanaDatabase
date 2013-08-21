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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed){
		this.completed = completed;
		if(completed == true){
			completionDate = new Date();
		}else{
			completionDate = null;
		}
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public Date getCompletionDate(){
		return (Date) completionDate.clone();
	}

}