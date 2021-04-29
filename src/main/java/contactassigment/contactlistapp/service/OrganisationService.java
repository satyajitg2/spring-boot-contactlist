package contactassigment.contactlistapp.service;

import java.util.List;

import contactassigment.contactlistapp.dto.ContactSearchCriteriaDTO;
import contactassigment.contactlistapp.dto.OrganisationDTO;

public interface OrganisationService
{
  List<OrganisationDTO> listAll();
  List<OrganisationDTO> listAll(String string);
  List<OrganisationDTO> listByNameContainingIgnoreCase(ContactSearchCriteriaDTO criteria);
}
