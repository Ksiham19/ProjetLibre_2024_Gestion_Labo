package com.example.laboservice;

import com.example.laboservice.link.Utilisateur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "utilisateurs", url = "${application.config.utilisateurs-url}" )
public interface UtilisateurClient {

    @GetMapping("/laboratoire/{labo-id}")
    List<Utilisateur> findAllUtilisateursByLaboratoire(@PathVariable("labo-id") Integer laboId);

}
