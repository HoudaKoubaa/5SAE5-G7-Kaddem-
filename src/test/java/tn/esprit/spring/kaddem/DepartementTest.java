package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DepartementTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddDepartement() {
        Departement departementToSave = new Departement("Chimie");
        Departement departementSaved = new Departement(1, "Chimie");

        when(departementRepository.save(any(Departement.class))).thenReturn(departementSaved);

        Departement result = departementService.addDepartement(departementToSave);

        assertNotNull(result);
        assertEquals("Chimie", result.getNomDepart());

        assertEquals(1, result.getIdDepart().intValue());
    }

    @Test
    public void testRetrieveDepartement() {
        Departement departement = new Departement(1, "Informatique");


        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));


        Departement result = departementService.retrieveDepartement(1);


        assertNotNull(result);
        assertEquals(1, result.getIdDepart().intValue());
        assertEquals("Informatique", result.getNomDepart());
    }

    @Test
    public void testUpdateDepartement() {
        Departement departementToUpdate = new Departement(1, "Génie civil");
        Departement departementUpdated = new Departement(1, "Nouveau Génie civil");

        // Configuration du comportement du repository pour retourner le département mis à jour
        when(departementRepository.save(any(Departement.class))).thenReturn(departementUpdated);


        Departement result = departementService.updateDepartement(departementToUpdate);


        assertNotNull(result);
        assertEquals(1, result.getIdDepart().intValue());
        assertEquals("Nouveau Génie civil", result.getNomDepart());
    }

    @Test
    public void testDeleteDepartement() {
        Departement departementToDelete = new Departement(1, "Mathématiques");


        when(departementRepository.findById(1)).thenReturn(Optional.of(departementToDelete));


        departementService.deleteDepartement(1);


        verify(departementRepository, times(1)).delete(any());
    }
}
