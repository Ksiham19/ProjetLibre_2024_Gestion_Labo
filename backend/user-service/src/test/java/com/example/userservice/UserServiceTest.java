package com.example.userservice;
import com.example.userservice.Entities.Utilisateur;
import com.example.userservice.Repositories.UtilisateurRepository;
import com.example.userservice.Services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUtilisateurs() {
        Utilisateur user1 = Utilisateur.builder().id(1).email("user1@example.com").nomComplet("User One").build();
        Utilisateur user2 = Utilisateur.builder().id(2).email("user2@example.com").nomComplet("User Two").build();
        when(utilisateurRepository.findAll()).thenReturn(List.of(user1, user2));

        List<Utilisateur> result = utilisateurService.getAllUtilisateurs();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(utilisateurRepository, times(1)).findAll();
    }

    @Test
    public void testGetUtilisateurById_Found() {
        Utilisateur user = Utilisateur.builder().id(1).email("user1@example.com").nomComplet("User One").build();
        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(user));

        Utilisateur result = utilisateurService.getUtilisateurById(1);

        assertNotNull(result);
        assertEquals("user1@example.com", result.getEmail());
        assertEquals("User One", result.getNomComplet());
        verify(utilisateurRepository, times(1)).findById(1);
    }

    @Test
    public void testGetUtilisateurById_NotFound() {
        when(utilisateurRepository.findById(1)).thenReturn(Optional.empty());

        Utilisateur result = utilisateurService.getUtilisateurById(1);

        assertNull(result);
        verify(utilisateurRepository, times(1)).findById(1);
    }

    @Test
    public void testCreateUtilisateur() {
        Utilisateur user = Utilisateur.builder().email("user1@example.com").nomComplet("User One").build();
        when(utilisateurRepository.save(user)).thenReturn(user);

        Utilisateur result = utilisateurService.createUtilisateur(user);

        assertNotNull(result);
        assertEquals("user1@example.com", result.getEmail());
        assertEquals("User One", result.getNomComplet());
        verify(utilisateurRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUtilisateurById_Success() {
        when(utilisateurRepository.existsById(1)).thenReturn(true);

        utilisateurService.deleteUtilisateurById(1);

        verify(utilisateurRepository, times(1)).existsById(1);
        verify(utilisateurRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteUtilisateurById_NotFound() {
        when(utilisateurRepository.existsById(1)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            utilisateurService.deleteUtilisateurById(1);
        });

        assertEquals("Utilisateur not found with ID: 1", exception.getMessage());
        verify(utilisateurRepository, times(1)).existsById(1);
        verify(utilisateurRepository, never()).deleteById(1);
    }
}
