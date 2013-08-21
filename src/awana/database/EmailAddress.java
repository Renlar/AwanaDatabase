package awana.database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Renlar
 */
public class EmailAddress {

	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private String emailAddress;
	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	private Matcher matcher;

	public EmailAddress(String address) {
		emailAddress = address;
	}

	public boolean validateEmail() {

		matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}

	public String getEmailAddress(){
		return emailAddress;
	}
}
