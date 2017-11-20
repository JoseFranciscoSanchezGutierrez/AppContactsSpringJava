package com.desa.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.desa.backend.component.ContactConverter;
import com.desa.backend.entity.ContactEntity;
import com.desa.backend.model.ContactModel;
import com.desa.backend.repository.ContactRepository;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {

		ContactEntity contactEntity = contactConverter.convertModelToEntity(contactModel);
		contactRepository.save(contactEntity);
		return contactConverter.convertEntityToModel(contactEntity);
	}

	@Override
	public List<ContactModel> listAllContact() {
		
		List<ContactEntity> listContactEntity = contactRepository.findAll();
		List<ContactModel> contactModel = new ArrayList<ContactModel>();
		
		for(ContactEntity contactEntity : listContactEntity) {
			contactModel.add(contactConverter.convertEntityToModel(contactEntity));
		}
		
		return contactModel;
	}

	@Override
	public ContactEntity findContactEntityById(int id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public ContactModel findContactModelById(int id) {
		return contactConverter.convertEntityToModel(findContactEntityById(id));
	}

	@Override
	public void removeContact(int id) {
		
		ContactEntity contactEntity = findContactEntityById(id);
		
		if (contactEntity != null) {
		contactRepository.delete(contactEntity);
		}
	}

}
