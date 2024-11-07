package com.example.laboservice.Controllers;


import com.example.laboservice.link.FullLaboResponse;
import com.example.laboservice.Entities.Laboratoire;
import com.example.laboservice.Services.LaboratoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratoires")
@RequiredArgsConstructor
public class LaboratoireController {

    private final LaboratoireService laboratoireService;

    @GetMapping
    public List<Laboratoire> getAllLaboratoires() {
        return laboratoireService.getAllLaboratoires();
    }

    @GetMapping("/{id}")
    public Laboratoire getLaboratoireById(@PathVariable Integer id) {
        return laboratoireService.getLaboratoireById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Laboratoire createUtilisateur(@RequestBody Laboratoire utilisateur) {
        return laboratoireService.createUtilisateur(utilisateur);
    }


    @GetMapping("/with-users/{labo-id}")
    public ResponseEntity<FullLaboResponse> findAllLaboratoires(
            @PathVariable("labo-id") Integer laboId) {
        return ResponseEntity.ok(laboratoireService.findLaboratoireswithusers(laboId));
    }
}
