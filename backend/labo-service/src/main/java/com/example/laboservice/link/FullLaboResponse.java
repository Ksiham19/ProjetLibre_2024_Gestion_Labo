package com.example.laboservice.link;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullLaboResponse {

    private String nom;
    private String logo;
    private String nrc;
    private boolean active;

    List<Utilisateur> utilisateurs;

}
