package contactassigment.contactlistapp.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import contactassigment.contactlistapp.domain.Contact;
import contactassigment.contactlistapp.domain.Organisation;

public class ContactDTO {

	public static ContactDTO createBy(Contact contact) {
		return new ContactDTO(contact, false);
	}
	

	public static ContactDTO createByView(Contact contact) {
		boolean typeView = true;
		return new ContactDTO(contact, typeView);
	}


	public static List<ContactDTO> createListBy(List<Contact> contacts) {
		List<ContactDTO> contactDTOs = new ArrayList<ContactDTO>(contacts.size());
		for (Contact c : contacts) {
			contactDTOs.add(ContactDTO.createBy(c));
		}
		return contactDTOs;
	}

	private Integer id;

	private String name;
	private String lastName;
	private String userdate;

	private OrganisationDTO organisation;

	public ContactDTO() {
	}

	public ContactDTO(Contact contact) {
		setId(contact.getId());
		setName(contact.getName());
		setLastName(contact.getLastName());
		setUserdate(contact.getDateUser().toString());
		Organisation org = contact.getOrganisation();
		if (org != null) {
			setOrganisation(new OrganisationDTO(contact.getOrganisation()));
		}
	}

	public ContactDTO(Contact contact, boolean viewType) {
		setId(contact.getId());
		setName(contact.getName());
		setLastName(contact.getLastName());
		setUserdate(contact.getDateUser().toString());
		Organisation org = contact.getOrganisation();
		if (org != null) {
			setOrganisation(new OrganisationDTO(contact.getOrganisation(), viewType));
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public OrganisationDTO getOrganisation() {
		return organisation;
	}

	public void setOrganisation(OrganisationDTO organisation) {
		this.organisation = organisation;
	}
	
	public String getUserdate() {
		return userdate;
	}


	public void setUserdate(String userdate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = new Date();
		try {
			date = format.parse (userdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		DateFormat destDf = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = destDf.format(date);
		this.userdate = dateStr;
	}


	public String getOrganisationInfo() {
		OrganisationDTO org = getOrganisation();
		if (org != null) {
			String abn = org.getAbn();
			return org.getName() + " ( " + org.getAbn() + " )";
		} else {
			return Constants.EMPTY_STRING;
		}
	}

	public String getAbn() {
		OrganisationDTO org = getOrganisation();
		if (org != null) {
			return org.getAbn();
		} else {
			return Constants.EMPTY_STRING;
		}
	}

	public String getOrganisationName() {
		OrganisationDTO org = getOrganisation();
		if (org != null) {
			return org.getName();
		} else {
			return Constants.EMPTY_STRING;
		}
	}
	
	public String getOrganisationDate() {
		OrganisationDTO org = getOrganisation();
		if (org != null) {
			return org.getDate().toString();
		} else {
			return Constants.EMPTY_STRING;
		}
	}
}
