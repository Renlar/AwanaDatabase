package com.liddev.awanadatabase;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

/**
 *
 * @author Renlar <liddev.com>
 */
public class Section implements ItemListener{

	private final String name;
	private boolean completed;
	private String completionDate;
	private JCheckBox checkBox;

	public Section(String name, boolean completed, String completionDate) {
		this.name = name;
		this.completed = completed;
		this.completionDate = completionDate;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
		if (completed == true) {
			completionDate = Record.calculateCompletionDate();
		} else {
			completionDate = null;
		}
		if(checkBox != null){
		checkBox.setSelected(completed);
		}
	}

	public boolean isCompleted() {
		return completed;
	}

	public String getName() {
		return name;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public JPanel getRenderable() {
		JPanel group = new JPanel();
		group.setName(getName());
		checkBox = new JCheckBox(getName(), isCompleted());
		checkBox.addItemListener(this);
		group.add(checkBox);
		JFormattedTextField date = new JFormattedTextField(new SimpleDateFormat("mm/dd/yyyy"));
		date.setEditable(false);
		if (getCompletionDate() != null) {
			date.setText(completionDate + "");
		}
		group.add(date);
		return group;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		setCompleted(checkBox.isSelected());
		//TODO: update date box when checked.
	}

	@Override
	public String toString(){
		return "Name:" + getName() + "; Completed:" + isCompleted();
	}
        
        /**
         * 
         * @param o the object to check for structural equivalence.
         * @return true if objects the same or structurally equivalent.
         */
        @Override
        public boolean equals(Object o){
            if(o == null){
                return false;
            }else if(o.getClass().equals(this.getClass())){
                Section s = (Section) o;
                if(s.getName().equals(this.getName()) 
                        && s.getCompletionDate().equals(this.getCompletionDate()) 
                        && s.isCompleted() == this.isCompleted()){
                    return true;
                }
            }
            return false;
        }
}