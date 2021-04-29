package contactassigment.contactlistapp.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactSearchCriteriaDTO {

	private String name = Constants.EMPTY_STRING;
	private String lastName = Constants.EMPTY_STRING;
	private String organisationName = Constants.EMPTY_STRING;
	private String abn = Constants.EMPTY_STRING;
	private String organisationDate = Constants.EMPTY_STRING; 
	
	public String getOrganisationDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		String dateString = format.format(new Date());
		Date date = new Date();
		try {
			date = format.parse (organisationDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		DateFormat destDf = new SimpleDateFormat("MM-dd-yyyy");
		String dateStr = destDf.format(date);
		return dateStr;
	}

	public void setOrganisationDate(String organisationDate) {
		this.organisationDate = organisationDate;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) {
		this.abn = abn;
	}
	
}