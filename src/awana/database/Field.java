package awana.database;

/**
 *
 * @author Justin VanDeBrake
 */
public class Field {
	private String name;
	private String data;
	private String type;
	private String storageType;
	private int displayLength;

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
}
