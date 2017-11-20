package com.desa.backend.component;

import org.springframework.stereotype.Component;

import com.desa.backend.entity.ContactEntity;
import com.desa.backend.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	public ContactEntity convertModelToEntity(ContactModel contactModel) {
		
		ContactEntity contactEntity = new ContactEntity();
		contactEntity.setId(contactModel.getId());
		contactEntity.setFirstname(contactModel.getFirstname());
		contactEntity.setLastname(contactModel.getLastname());
		contactEntity.setTelephone(contactModel.getTelephone());
		contactEntity.setCity(contactModel.getCity());
		return contactEntity;
	}

	public ContactModel convertEntityToModel(ContactEntity contactEntity) {
		
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contactEntity.getId());
		contactModel.setFirstname(contactEntity.getFirstname());
		contactModel.setLastname(contactEntity.getLastname());
		contactModel.setTelephone(contactEntity.getTelephone());
		contactModel.setCity(contactEntity.getCity());
		return contactModel;
	}
}
