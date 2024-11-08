package com.example.laboservice.Contact;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "Contact")
public interface ContactFeignClient {
    @GetMapping("/api/contacts/by-laboratoire")
    List<ContactDTO> getContactsByLaboratoireId(@RequestParam("laboratoireId") Long laboratoireId);
}
