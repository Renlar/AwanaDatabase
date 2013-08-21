package awana.database;

/**
 *
 * @author Renlar
 */
public class Address {
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String stateOrProvince;
	private int zip;
	private String country;
	private String county;

	public Address(){
	}

	public String getCity(){
		return city;
	}
	public String getStateOrProvince(){
		return stateOrProvince;
	}
	public int getZip(){
		return zip;
	}
	public String getCountry(){
		return country;
	}
	public String getCounty(){
		return county;
	}
	public String getAddressLine1(){
		return addressLine1;
	}
	public String getAddressLine2(){
		return addressLine2;
	}
	public void setCity(String city){
		this.city = city;
	}
	public void setStateOrProvince(String stateOrProvince){
		this.stateOrProvince = stateOrProvince;
	}
	public void setZip(int zip){
		this.zip = zip;
	}
	public void setCountry(String country){
		this.country = country;
	}
	public void setCounty(String county){
		this.county = county;
	}
	public void setAddressLine1(String addressLine1){
		this.addressLine1 = addressLine1;
	}
	public void setAddressLine2(String addressLine2){
		this.addressLine2 = addressLine2;
	}
	public String getMailingFormat(){
		String mailingAddress = addressLine1 + "\n" + addressLine2 + "\n" + city + ", " + stateOrProvince + " "+ zip;
		return mailingAddress;
	}
	public String getInternationalMailingFormat(){
		throw new UnsupportedOperationException("International mailing format is not yet supported.");
	}
}