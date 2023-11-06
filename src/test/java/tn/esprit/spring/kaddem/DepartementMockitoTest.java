package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DepartementMockitoTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllDepartements() {
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement());
        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();

        assertEquals(1, result.size());
    }

    @Test
    public void testAddDepartement() {
        Departement departement = new Departement();
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        Departement result = departementService.addDepartement(departement);

        assertNotNull(result);
    }

    @Test
    public void testUpdateDepartement() {
        Departement departement = new Departement();
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        Departement result = departementService.updateDepartement(departement);

        assertNotNull(result);
    }

    @Test
    public void testRetrieveDepartement() {
        Departement departement = new Departement();
        when(departementRepository.findById(any(Integer.class))).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(1);

        assertNotNull(result);
    }

    @Test
    public void testDeleteDepartement() {
        Departement departement = new Departement();
        when(departementRepository.findById(any(Integer.class))).thenReturn(Optional.of(departement));

        departementService.deleteDepartement(1);

        verify(departementRepository, times(1)).delete(eq(departement));
    }
}
