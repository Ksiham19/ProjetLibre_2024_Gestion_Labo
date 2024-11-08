package com.example.laboservice;


import com.example.laboservice.Contact.ContactDTO;
import com.example.laboservice.Contact.ContactFeignClient;
import com.example.laboservice.Entities.Laboratoire;
import com.example.laboservice.Repositories.LaboratoreRepository;
import com.example.laboservice.Services.LaboratoireService;
import com.example.laboservice.link.FullLaboResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LaboServiceTest {
    @Mock
    private LaboratoreRepository laboratoreRepository;

    @Mock
    private UtilisateurClient utilisateurClient;

    @Mock
    private ContactFeignClient contactFeignClient;

    @InjectMocks
    private LaboratoireService laboratoireService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllLaboratoires() {
        Laboratoire labo1 = Laboratoire.builder().id(1).nom("Labo1").build();
        Laboratoire labo2 = Laboratoire.builder().id(2).nom("Labo2").build();
        when(laboratoreRepository.findAll()).thenReturn(List.of(labo1, labo2));

        List<Laboratoire> result = laboratoireService.getAllLaboratoires();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(laboratoreRepository, times(1)).findAll();
    }

    @Test
    public void testGetLaboratoireById_Found() {
        Laboratoire labo = Laboratoire.builder().id(1).nom("Labo1").build();
        when(laboratoreRepository.findById(1)).thenReturn(Optional.of(labo));

        Laboratoire result = laboratoireService.getLaboratoireById(1);

        assertNotNull(result);
        assertEquals("Labo1", result.getNom());
        verify(laboratoreRepository, times(1)).findById(1);
    }

    @Test
    public void testGetLaboratoireById_NotFound() {
        when(laboratoreRepository.findById(1)).thenReturn(Optional.empty());

        Laboratoire result = laboratoireService.getLaboratoireById(1);

        assertNull(result);
        verify(laboratoreRepository, times(1)).findById(1);
    }

    @Test
    public void testCreateLaboratoire() {
        Laboratoire labo = Laboratoire.builder().nom("Labo1").build();
        when(laboratoreRepository.save(labo)).thenReturn(labo);

        Laboratoire result = laboratoireService.createLaboratoire(labo);

        assertNotNull(result);
        assertEquals("Labo1", result.getNom());
        verify(laboratoreRepository, times(1)).save(labo);
    }

    @Test
    public void testDeleteLaboratoireById_Found() {
        when(laboratoreRepository.existsById(1)).thenReturn(true);

        laboratoireService.deleteLaboratoireById(1);

        verify(laboratoreRepository, times(1)).existsById(1);
        verify(laboratoreRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteLaboratoireById_NotFound() {
        when(laboratoreRepository.existsById(1)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            laboratoireService.deleteLaboratoireById(1);
        });

        assertEquals("Laboratoire not found with ID: 1", exception.getMessage());
        verify(laboratoreRepository, times(1)).existsById(1);
        verify(laboratoreRepository, never()).deleteById(1);
    }

}
