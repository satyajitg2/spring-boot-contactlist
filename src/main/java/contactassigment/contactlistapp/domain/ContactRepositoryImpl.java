package contactassigment.contactlistapp.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ContactRepositoryImpl implements ContactRepositoryCustom {
	Logger logger = LoggerFactory.getLogger(ContactRepositoryImpl.class);

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Contact> searchByNamesFetchOrganisation(String contactName, String lastName, String organisationName) {
		StringBuilder sbuilder = new StringBuilder("SELECT c FROM Contact c LEFT JOIN FETCH c.organisation o ");
		if (StringUtils.hasText(contactName)) {
			sbuilder.append("AND UPPER(c.name) like CONCAT('%',UPPER(:contactNamePattern),'%') ");
		}
		if (StringUtils.hasText(lastName)) {
			sbuilder.append("AND UPPER(c.lastname) like CONCAT('%',UPPER(:lastNamePattern),'%') ");
		}
		if (StringUtils.hasText(organisationName)) {
			sbuilder.append("AND UPPER(o.name) like CONCAT('%',UPPER(:organisationNamePattern),'%') ");
		}

		String queryHQL = sbuilder.toString().replaceFirst("AND", "WHERE").trim();
		logger.debug("Query HQL: " + queryHQL);
		javax.persistence.Query q = em.createQuery(queryHQL);

		if (StringUtils.hasText(contactName)) {
			q.setParameter("contactNamePattern", contactName.replace("*", "%"));
		}
		if (StringUtils.hasText(lastName)) {
			q.setParameter("lastNamePattern", lastName.replace("*", "%"));
		}

		if (StringUtils.hasText(organisationName)) {
			q.setParameter("organisationNamePattern", organisationName.replace("*", "%"));
		}

		return q.getResultList();
	}

	@Override
	public List<Contact> searchByOrganisationNameFetchName(String organisationName) {
		TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c LEFT JOIN FETCH c.organisation o WHERE UPPER(o.name) LIKE CONCAT('%',UPPER(:oname),'%') ",
				Contact.class);
		query.setParameter("oname", organisationName);
		List<Contact> resultList = query.getResultList();
		return resultList;
	}
}
