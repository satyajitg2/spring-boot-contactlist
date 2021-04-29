package contactassigment.contactlistapp.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import contactassigment.contactlistapp.dto.ContactSearchCriteriaDTO;

@Component
public class ContactSearchCriteriaDTOValidator implements Validator {

	/**
	 * This Validator validates ContactSearchCriteria instances
	 */
	public boolean supports(Class clazz) {
		return ContactSearchCriteriaDTO.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		ContactSearchCriteriaDTO p = (ContactSearchCriteriaDTO) obj;
		String name = p.getName() + " " + p.getLastName();
		String regexName = "\\p{Upper}(\\p{Lower}+\\s?)";
		Pattern special = Pattern.compile("[!@#$%&()_+=|<>?{}\\[\\]~]");
		
		if(!name.isBlank())
		{
			if (special.matcher(p.getName()).find() || special.matcher(p.getLastName()).find()) {
				e.reject(null, "String contains special characters may not be valid inputs.");
			}

			if (p.getName().length() > 30) {
				e.rejectValue("name", "First Name too long");
			} else if ( p.getLastName().length() > 30) {
				e.rejectValue("lastame", "Last Name too long");
			}

			if (name.matches(regexName)) {
				e.reject(null, "Name is not a valid name.");
			}
		}
	}
}