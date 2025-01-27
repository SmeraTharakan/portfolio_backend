package com.example.portfolio.controller;

import com.example.portfolio.entity.Contact;
import com.example.portfolio.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*") // Allow requests from frontend
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // POST endpoint to save a message
    @PostMapping
    public ResponseEntity<Contact> sendMessage(@RequestBody Contact contact) {
        Contact savedContact = contactService.saveContact(contact);
        return ResponseEntity.ok(savedContact);
    }

    // GET endpoint to retrieve all messages
    @GetMapping
    public ResponseEntity<List<Contact>> getAllMessages() {
        List<Contact> messages = contactService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
}
