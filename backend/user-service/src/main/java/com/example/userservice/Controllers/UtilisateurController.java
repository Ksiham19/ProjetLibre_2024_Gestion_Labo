package com.example.userservice.Controllers;


import com.example.userservice.Entities.Utilisateur;
import com.example.userservice.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable Integer id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    @GetMapping("/laboratoire/{labo-id}")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs(
            @PathVariable("labo-id") Integer fkIdLaboratoire
    ) {
        return ResponseEntity.ok((List<Utilisateur>) utilisateurService.getAllUtilisateursByLabo(fkIdLaboratoire));
    }
}
