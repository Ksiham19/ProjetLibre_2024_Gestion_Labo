package com.example.Contact.Service;

import com.example.Contact.Entity.Contact;
import com.example.Contact.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    public void deleteById(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found with ID: " + id);
        }
        contactRepository.deleteById(id);
    }
}
