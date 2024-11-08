package com.example.laboservice.Services;
import com.example.laboservice.Contact.ContactDTO;
import com.example.laboservice.Contact.ContactFeignClient;
import com.example.laboservice.UtilisateurClient;
import com.example.laboservice.link.FullLaboResponse;
import com.example.laboservice.Entities.Laboratoire;
import com.example.laboservice.Repositories.LaboratoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaboratoireService {

    private final LaboratoreRepository laboratoreRepository;
    private final UtilisateurClient client;
    @Autowired
    private ContactFeignClient contactFeignClient;

    public List<ContactDTO> getContactsForLaboratoire(Long laboratoireId) {
        return contactFeignClient.getContactsByLaboratoireId(laboratoireId);
    }

    public List<Laboratoire> getAllLaboratoires() {
        return laboratoreRepository.findAll();
    }

    public Laboratoire getLaboratoireById(Integer id) {
        return laboratoreRepository.findById(id).orElse(null);
    }

    public Laboratoire createUtilisateur(Laboratoire utilisateur) {
        return laboratoreRepository.save(utilisateur);
    }

    public FullLaboResponse findLaboratoireswithusers(Integer laboId ) {
        var laboratoitre = laboratoreRepository.findById(laboId)
                .orElse(
                        Laboratoire.builder()
                                .nom("NOT_FOUND")
                                .nrc("NOT_FOUND")
                                .logo("NOT_FOUND")
                                .active(false)
                                .build()
                );
        var utilisateurs = client.findAllUtilisateursByLaboratoire(laboId);
        return FullLaboResponse.builder()
                .nom(laboratoitre.getNom())
                .nrc(laboratoitre.getNrc())
                .active(laboratoitre.isActive())
                .logo(laboratoitre.getLogo())
                .utilisateurs(utilisateurs)
                .build();

    }
}
