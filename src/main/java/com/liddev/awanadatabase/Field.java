package com.liddev.awanadatabase;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Renlar <liddev.com>
 */
public class Field implements DocumentListener{
	private final String name;
	private String data;
	private final String type;
	private String storageType;
	private final int displayLength;
	private JTextField textField;

	public Field(String name, String data, String type){
		this.name = name;
		this.data = data;
		this.type = type;
		this.displayLength = Record.getDisplayLengthByType(type);
	}

	public Field(String name, String data, String type, int displayLength){
		this.name = name;
		this.data = data;
		this.type = type;
		this.displayLength = displayLength;
	}

	public Field(String name, String data, String type, String storageType){
		this.name = name;
		this.data = data;
		this.type = type;
		this.storageType = storageType;
		this.displayLength = Record.getDisplayLengthByType(type);
	}

	public Field(String name, String data, String type, String storageType, int displayLength){
		this.name = name;
		this.data = data;
		this.type = type;
		this.storageType = storageType;
		this.displayLength = displayLength;
	}

	public String getName(){
		return name;
	}

	public String getData(){
		return data;
	}

	public String getType(){
		return type;
	}

	public String getStorageType(){
		return storageType;
	}

	public int getDisplayLength(){
		return displayLength;
	}

	public JPanel getRenderable(){
			JPanel group = new JPanel();
			JLabel label = new JLabel();
			label.setText(getName());
			group.add(label);
			textField = new JTextField();
			textField.setText(getData());
			textField.setName(getName());
			textField.setPreferredSize(new Dimension(getDisplayLength() * 8, 25));
			textField.getDocument().addDocumentListener(this);
			group.add(textField);
			group.setSize(label.getSize().width + 5 + textField.getSize().width, 5 + textField.getSize().height);
		return group;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		onUpdate(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		onUpdate(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		onUpdate(e);
	}

	private void onUpdate(DocumentEvent e){
		try {
			data = e.getDocument().getText(0, e.getDocument().getLength());
		} catch (BadLocationException ex) {
			Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

        @Override
	public String toString(){
		return "Name:" + getName() + "; Data:" + getData() + "\n";
	}
        
        @Override
        public boolean equals(Object o){
            if(o == null){
                return false;
            }
            if(o.getClass().equals(this.getClass())){
                Field f = (Field) o;
                if(f.getName().equals(this.getName())
                        && f.getData().equals(this.getData())
                        && f.getDisplayLength() == this.getDisplayLength() 
                        && f.getStorageType().equals(this.getStorageType()) 
                        && f.getType().equals(this.getType())){
                    return true;
                }
            }
            return false;
        }
}
