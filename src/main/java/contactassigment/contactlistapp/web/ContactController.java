package contactassigment.contactlistapp.web;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import contactassigment.contactlistapp.dto.ContactDTO;
import contactassigment.contactlistapp.dto.ContactSearchCriteriaDTO;
import contactassigment.contactlistapp.dto.OrganisationDTO;
import contactassigment.contactlistapp.service.ContactService;
import contactassigment.contactlistapp.service.OrganisationService;
import contactassigment.contactlistapp.web.validators.ContactDTOValidator;
import contactassigment.contactlistapp.web.validators.ContactSearchCriteriaDTOValidator;

@Controller
public class ContactController {

	private final ContactService contactService;
	private final OrganisationService organisationService;
	private final ContactDTOValidator contactDTOValidator;
	private final ContactSearchCriteriaDTOValidator contactSearchValidator;

	@Autowired
	public ContactController(ContactService contactService, OrganisationService organisationService,
			ContactDTOValidator contactDTOValidator,ContactSearchCriteriaDTOValidator contactSearchValidator) {
		this.contactService = contactService;
		this.organisationService = organisationService;
		this.contactDTOValidator = contactDTOValidator;
		this.contactSearchValidator = contactSearchValidator;
	}
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String listContacts(@ModelAttribute("searchCriteria") ContactSearchCriteriaDTO contactSearchCriteria,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		
		//TODO: Check fields for X-site scripting
		contactSearchValidator.validate(contactSearchCriteria, result);
		
/*
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError o: errors)
			{
				message.setMessage(o.toString());
			}
			model.addAttribute("message", message);

			return "/contact/list";
		} 
*/	
		List<ContactDTO> contacts = new ArrayList<ContactDTO>();
		contacts = contactService.listByCriteriaFetchOrganisation(contactSearchCriteria);

		model.addAttribute("contacts", contacts);
		model.addAttribute("searchCriteria", contactSearchCriteria);
		if (contacts.size() == 1) {
			ContactDTO contact = contacts.get(0);
			model.addAttribute("contact", contact);
			return "/contact/view";
		}
		return "/contact/list";
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public String updateContact(@ModelAttribute("contact") ContactDTO contactDTO, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		contactDTOValidator.validate(contactDTO, result);
		if (result.hasErrors()) {
			model.addAttribute("contact", contactDTO);
			model.addAttribute("organisations", organisationService.listAll());
			return "/contact/edit";
		} else {
			contactService.updateByDTO(contactDTO);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Contact has been updated successfully!");
			return "redirect:/contacts/" + contactDTO.getId();
		}
	}

	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Integer id, Model model) {
		ContactDTO contact = contactService.findByIdFetchOrganisation(id);
		model.addAttribute("contact", contact);
		return "/contact/view";
	}

	@RequestMapping(value = "/contacts/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		ContactDTO contact = contactService.findByIdFetchOrganisation(id);
		List<OrganisationDTO> organisations = organisationService.listAll("abn");

		model.addAttribute("contact", contact);
		model.addAttribute("organisations", organisations);

		return "/contact/edit";
	}
}