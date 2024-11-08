package com.example.laboservice.Contact;

import lombok.Data;

@Data
public class ContactDTO {
    private Long id;
    private String numTel;
    private String fax;
    private String email;
}