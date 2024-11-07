package com.example.userservice.Entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String nomComplet;
    private String profession;
    private String numTel;
    private String signature;
    private String role;
    private Integer fkIdLaboratoire;
}
