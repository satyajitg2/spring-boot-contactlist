package contactassigment.contactlistapp.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import contactassigment.contactlistapp.domain.Organisation;

public class OrganisationDTO {

	public static OrganisationDTO createBy(Organisation organisation) {
		return new OrganisationDTO(organisation);
	}

	public static OrganisationDTO createBy(Organisation organisation, String str) {
		return new OrganisationDTO(organisation, str);
	}

	public static List<OrganisationDTO> createListBy(List<Organisation> organisations) {
		List<OrganisationDTO> organisationDTOs = new ArrayList<OrganisationDTO>(organisations.size());
		for (Organisation o : organisations) {
			organisationDTOs.add(OrganisationDTO.createBy(o));
		}
		return organisationDTOs;
	}

	public static List<OrganisationDTO> createListBy(List<Organisation> organisations, String str) {
		List<OrganisationDTO> organisationDTOs = new ArrayList<OrganisationDTO>(organisations.size());
		for (Organisation o : organisations) {
			organisationDTOs.add(OrganisationDTO.createBy(o, str));
		}
		return organisationDTOs;
	}

	private Integer id;
	private String name;
	private String abn;
	private String startdate;

	public OrganisationDTO() {
	}

	public OrganisationDTO(Organisation organisation) {
		setId(organisation.getId());
		setName(organisation.getName());
		setAbn(organisation.getAbn());
		setDate(organisation.getDate().toString());
	}
	
	public OrganisationDTO(Organisation organisation, String str) {
		setId(organisation.getId());
		if(str == "abn")
		{
			setName(organisation.getName() + " (" + organisation.getAbn() + ")" );
		}
		setAbn(organisation.getAbn());
		setDate(organisation.getDate().toString());
	}

	public OrganisationDTO(Organisation organisation, boolean viewType) {
		setId(organisation.getId());
		setName(organisation.getName());
		setAbn(organisation.getAbn());
		setDate(organisation.getDate().toString(), viewType);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) {
		this.abn = abn;
	}
	
	public String getDate() {
		return startdate;
	}
	
	public void setDate(String date) {
		this.startdate = date;
	}
	
	public void setDate(String datestr, boolean viewType) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = new Date();
		try {
			date = format.parse (datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		DateFormat destDf = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = destDf.format(date);
		this.startdate = dateStr;
	}
	
}
