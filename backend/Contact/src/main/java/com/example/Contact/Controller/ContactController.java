package com.example.Contact.Controller;

import com.example.Contact.Entity.Contact;
import com.example.Contact.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public Contact addContact(@RequestBody Contact contact)
    {
        return contactService.save(contact);
    }

    @GetMapping
    public List<Contact> listContacts()
    {
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactService.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteById(id);
    }

    @GetMapping("/by-laboratoire")
    public List<Contact> getContactsByLaboratoireId(@RequestParam("laboratoireId") Long laboratoireId) {
        return contactService.findAll().stream()
                .filter(contact -> contact.getLaboratoireId().equals(laboratoireId))
                .collect(Collectors.toList());
    }
}
