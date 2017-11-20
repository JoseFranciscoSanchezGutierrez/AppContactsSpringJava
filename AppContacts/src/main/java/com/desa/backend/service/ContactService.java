package com.desa.backend.service;

import java.util.List;

import com.desa.backend.entity.ContactEntity;
import com.desa.backend.model.ContactModel;

public interface ContactService {
	
	public abstract ContactModel addContact(ContactModel contactModel);

	public abstract List<ContactModel> listAllContact();
	
	public abstract ContactEntity findContactEntityById(int id);
	
	public abstract ContactModel findContactModelById(int id);
	
	public abstract void removeContact(int id);
	
}
