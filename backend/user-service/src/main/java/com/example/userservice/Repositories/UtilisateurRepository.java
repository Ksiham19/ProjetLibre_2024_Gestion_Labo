package com.example.userservice.Repositories;

import com.example.userservice.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

    List<Utilisateur> findAllUtilisateursByFkIdLaboratoire(Integer fkIdLaboratoire);
}