package awana.database;

import java.math.BigInteger;

/**
 *
 * @author Renlar
 */
public class PhoneNumber {
	private short countryCode;
	private short areaCode;
	private int localPhoneNumber;

	public PhoneNumber(short countryCode, short areaCode, int localPhoneNumber) {
		if (!validatePhoneNumber(countryCode, areaCode, localPhoneNumber)) {
			throw new IllegalArgumentException("+" + countryCode + " (" + areaCode + ")" +localPhoneNumber + " is not a valid phone number");
		}
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.localPhoneNumber = localPhoneNumber;
	}


	public String getFormattedPhoneNumber(){
		return ("+" + countryCode + " (" + areaCode + ")" + (localPhoneNumber / 10000)
				+ "-" + (localPhoneNumber - ((localPhoneNumber / 10000) * 10000)));
	}

	public int getLocalPhoneNumber(){
		return localPhoneNumber;
	}

	public short getAreaCode(){
		return areaCode;
	}

	public short getCountryCode(){
		return countryCode;
	}

	public boolean validatePhoneNumber(short countryCode, short areaCode, int phoneNumber) {
		if(areaCode/100 >= 1 || phoneNumber/1000000 >= 1){
			return false;
		}
		return true;
		//TODO: more rigourus checks for valid phone number
	}
}
