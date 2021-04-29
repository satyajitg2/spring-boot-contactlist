package contactassigment.contactlistapp.domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Organisation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Access(AccessType.PROPERTY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String abn;
	  
	@Column(name = "startdate")
	private Date dateoperation = new java.sql.Date(new java.util.Date().getTime());

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
		StringBuilder sb = new StringBuilder();
		sb.append(abn.substring(0, 2));
		sb.append(" " + abn.subSequence(2, 5));
		sb.append(" " + abn.subSequence(5, 8));
		sb.append(" " + abn.substring(8, 11));
		return "ABN " + sb.toString();
	}

	public void setAbn(String abn) {
		StringBuilder sb = new StringBuilder();
		sb.append(abn.substring(0, 2));
		sb.append(" " + abn.subSequence(2, 5));
		sb.append(" " + abn.subSequence(5, 8));
		sb.append(" " + abn.substring(8, 11));
		this.abn = "ABN " + sb.toString();
	}
	
	public Date getDate() {
		return dateoperation;
	}

	public void setDate(Date date) {
		this.dateoperation = date;
	}
}
