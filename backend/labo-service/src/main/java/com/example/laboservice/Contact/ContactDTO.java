package com.example.laboservice.Contact;

import lombok.Data;

@Data
public class ContactDTO {
    private Long id;
    private String numTel;
    private String fax;
    private String email;

    public ContactDTO(long l, long l1, String number, String number1, String mail) {
        this.id = l;
        this.numTel = number;
        this.fax = number1;
        this.email = mail;
    }
}