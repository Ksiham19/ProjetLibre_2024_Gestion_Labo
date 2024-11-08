package com.example.Contact.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "laboratoire_id", nullable = false)
    private Long laboratoireId; // Foreign key reference to Laboratoire

    @Column(name = "num_tel", nullable = false, length = 15)
    private String numTel;

    @Column(name = "fax", length = 15)
    private String fax;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
