package com.example.Contact.Service;

import com.example.Contact.Entity.Contact;
import com.example.Contact.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact save(Contact contact)
    {
        return contactRepository.save(contact);
    }

    public List<Contact> findAll()
    {
        return contactRepository.findAll();
    }
}
