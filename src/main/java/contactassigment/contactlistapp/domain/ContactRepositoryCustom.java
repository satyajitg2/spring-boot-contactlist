package contactassigment.contactlistapp.domain;

import java.util.List;

public interface ContactRepositoryCustom
{
  List<Contact> searchByNamesFetchOrganisation(String contactName, String lastName, String organisationName);
  List<Contact> searchByOrganisationNameFetchName(String organisationName);
}
