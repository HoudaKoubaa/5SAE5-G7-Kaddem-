package tn.esprit.spring.kaddem;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EtudiantTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Test
    @Order(1)
    public void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        Assertions.assertNotNull(etudiants);
        Assertions.assertEquals(0, etudiants.size());
    }






    @Test
    public void testAddEtudiant() {
        // Créez un étudiant factice pour les tests
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(111);

        // Configurez le comportement du repository pour retourner l'étudiant factice lorsque save est appelé
        when(etudiantRepository.save(eq(etudiant))).thenReturn(etudiant);

        // Appelez la méthode de service pour ajouter l'étudiant
        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        // Vérifiez que l'étudiant retourné par la méthode de service est le même que l'étudiant factice
        assertEquals(etudiant, addedEtudiant);

        // Vérifiez que le repository a été appelé une fois avec l'étudiant
        verify(etudiantRepository, times(1)).save(eq(etudiant));
        System.out.println("Ceci est un message dans la console IntelliJ IDEA.");

    }

    @Test
    public void testUpdateEtudiant() {
        // Créez un étudiant factice pour les tests
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(111);

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
        etudiant.setIdEtudiant(111);

        // Configurez le comportement du repository pour retourner l'étudiant factice lorsque findById est appelé avec l'ID 1
        when(etudiantRepository.findById(111)).thenReturn(Optional.of(etudiant));

        // Appelez la méthode de service pour supprimer l'étudiant
        etudiantService.removeEtudiant(111);

        // Vérifiez que le repository a été appelé une fois avec l'ID 1 pour la suppression
        verify(etudiantRepository, times(1)).delete(eq(etudiant));
    }


}
