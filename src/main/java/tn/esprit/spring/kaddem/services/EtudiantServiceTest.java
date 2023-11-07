package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EtudiantServiceTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Test
    public void testRetrieveEtudiant() {
        // Créez un étudiant factice pour les tests
        Etudiant etudiant = new Etudiant();

        // Configurez le comportement du repository pour retourner l'étudiant factice lorsque findById est appelé avec l'ID 1
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        // Appelez la méthode de service
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1);

        // Vérifiez que l'étudiant retourné par la méthode de service est le même que l'étudiant factice
        assertEquals(etudiant, retrievedEtudiant);

        // Vérifiez que le repository a été appelé une fois avec l'ID 1
        verify(etudiantRepository, times(1)).findById(1);
    }
    @Test
    public void testAddEtudiant() {
        // Créez un étudiant factice pour les tests
        Etudiant etudiant = new Etudiant();

        // Configurez le comportement du repository pour retourner l'étudiant factice lorsque save est appelé
        when(etudiantRepository.save(eq(etudiant))).thenReturn(etudiant);

        // Appelez la méthode de service pour ajouter l'étudiant
        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        // Vérifiez que l'étudiant retourné par la méthode de service est le même que l'étudiant factice
        assertEquals(etudiant, addedEtudiant);

        // Vérifiez que le repository a été appelé une fois avec l'étudiant
        verify(etudiantRepository, times(1)).save(eq(etudiant));
    }

    @Test
    public void testUpdateEtudiant() {
        // Créez un étudiant factice pour les tests
        Etudiant etudiant = new Etudiant();

        // Configurez le comportement du repository pour retourner l'étudiant factice lorsque save est appelé
        when(etudiantRepository.save(eq(etudiant))).thenReturn(etudiant);

        // Appelez la méthode de service pour mettre à jour l'étudiant
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        // Vérifiez que l'étudiant retourné par la méthode de service est le même que l'étudiant factice
        assertEquals(etudiant, updatedEtudiant);

        // Vérifiez que le repository a été appelé une fois avec l'étudiant
        verify(etudiantRepository, times(1)).save(eq(etudiant));
    }

    @Test
    public void testRemoveEtudiant() {
        // Créez un étudiant factice pour les tests
        Etudiant etudiant = new Etudiant();

        // Configurez le comportement du repository pour retourner l'étudiant factice lorsque findById est appelé avec l'ID 1
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        // Appelez la méthode de service pour supprimer l'étudiant
        etudiantService.removeEtudiant(1);

        // Vérifiez que le repository a été appelé une fois avec l'ID 1 pour la suppression
        verify(etudiantRepository, times(1)).delete(eq(etudiant));
    }


}
