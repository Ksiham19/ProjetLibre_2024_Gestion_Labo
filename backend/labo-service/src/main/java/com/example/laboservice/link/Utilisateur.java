package com.example.laboservice.link;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Utilisateur {

    private String email;
    private String nomComplet;
    private String profession;
    private String numTel;
    private String signature;
    private String role;
}
