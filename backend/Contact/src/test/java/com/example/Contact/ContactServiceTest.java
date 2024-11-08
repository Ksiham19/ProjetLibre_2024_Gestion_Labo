package com.example.Contact;

import com.example.Contact.Entity.Contact;
import com.example.Contact.Repository.ContactRepository;
import com.example.Contact.Service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // test for creating new contact
    @Test
    public void testSaveContact() {
        Contact contact = Contact.builder()
                .id(1L)
                .laboratoireId(100L)
                .numTel("123456789")
                .fax("987654321")
                .email("john.doe@example.com")
                .build();

        when(contactRepository.save(contact)).thenReturn(contact);

        Contact savedContact = contactService.save(contact);

        assertNotNull(savedContact);
        assertEquals("123456789", savedContact.getNumTel());
        assertEquals("john.doe@example.com", savedContact.getEmail());
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    public void testFindAllContacts() {
        Contact contact1 = Contact.builder()
                .id(1L)
                .laboratoireId(100L)
                .numTel("123456789")
                .fax("987654321")
                .email("john.doe@example.com")
                .build();
        Contact contact2 = Contact.builder()
                .id(2L)
                .laboratoireId(101L)
                .numTel("234567890")
                .fax("876543210")
                .email("jane.doe@example.com")
                .build();

        List<Contact> contacts = Arrays.asList(contact1, contact2);
        when(contactRepository.findAll()).thenReturn(contacts);

        List<Contact> result = contactService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("jane.doe@example.com", result.get(1).getEmail());
        verify(contactRepository, times(1)).findAll();
    }

    @Test
    public void testFindContactById() {
        Contact contact = Contact.builder()
                .id(1L)
                .laboratoireId(100L)
                .numTel("123456789")
                .fax("987654321")
                .email("john.doe@example.com")
                .build();
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        Optional<Contact> foundContact = contactService.findById(1L);

        assertTrue(foundContact.isPresent());
        assertEquals("123456789", foundContact.get().getNumTel());
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteContactById() {
        when(contactRepository.existsById(1L)).thenReturn(true);

        contactService.deleteById(1L);

        verify(contactRepository, times(1)).existsById(1L);
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteContactByIdNotFound() {
        when(contactRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            contactService.deleteById(1L);
        });

        assertEquals("Contact not found with ID: 1", exception.getMessage());
        verify(contactRepository, times(1)).existsById(1L);
        verify(contactRepository, never()).deleteById(1L);
    }
}
