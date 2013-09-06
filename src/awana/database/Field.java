package awana.database;

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
	private String name;
	private String data;
	private String type;
	private String storageType;
	private int displayLength;
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

	public String toString(){
		return "Name:" + getName() + "; Data:" + getData() + "\n";
	}
}
