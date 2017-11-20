package com.desa.backend.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.desa.backend.constants.ViewConstants;
import com.desa.backend.model.ContactModel;
import com.desa.backend.service.ContactService;

@Controller("contactController")
@RequestMapping("/contacts")
public class ContactController {
	
	public static final Log LOG = LogFactory.getLog(ContactController.class);
	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name="id", required=false) int id, Model model) {
		
		ContactModel contactModel = new ContactModel();
		
		if(id != 0) {
			contactModel = contactService.findContactModelById(id);
		}
		
		model.addAttribute("contactModel", contactModel);
		return ViewConstants.CONTACT_FORM;
	}

	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contacModel") ContactModel contactModel,
			Model model) {
		
		LOG.info("METHOD: addContact() -- PARAMS: " + contactModel.toString());
		
		if(contactService.addContact(contactModel) != null) {
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}
		
		return "redirect:/contacts/showcontacts";
	}
	
	@PreAuthorize("permitAll()")
	@GetMapping("/showcontacts")
	public ModelAndView ShowContacts( ) {
		ModelAndView mav = new ModelAndView(ViewConstants.CONTACTS);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.listAllContact());
		return mav;
	}
	
	@GetMapping("/removecontact") 
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id) {
		contactService.removeContact(id);
		return ShowContacts();
	}
	
}
