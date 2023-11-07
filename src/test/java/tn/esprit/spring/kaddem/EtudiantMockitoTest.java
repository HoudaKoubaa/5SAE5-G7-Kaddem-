package tn.esprit.spring.kaddem;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EtudiantMockitoTest {
    @Mock
    EtudiantRepository etudiantRepository;
    @InjectMocks
    EtudiantServiceImpl etudiantService;


    Etudiant etudiant = new Etudiant("korbi","seif");

    @org.junit.jupiter.api.Test
    @Order(1)
    public void testRetrieveEtudiant() {
        Mockito.when(etudiantRepository.findById(2)).thenReturn(Optional.of(etudiant));
        Etudiant etudiant1 = etudiantService.retrieveEtudiant(2);
        Assertions.assertNotNull(etudiant1);
    }


    @org.junit.jupiter.api.Test
    @Order(0)
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        List<Etudiant> LEtudiant = new ArrayList<>();
        etudiant.setNomE("bouatay");
        etudiant.setPrenomE("sameh");
        Etudiant dd=etudiantRepository.save(etudiant);
        LEtudiant.add(dd);
        assertEquals(1,LEtudiant.size());
    }

    @org.junit.jupiter.api.Test
    @Order(3)
    void deleteAll() {
        etudiantRepository.deleteAll();
        assertEquals(0,etudiantRepository.findAll().spliterator().estimateSize());
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    void retrieveEtudiant() {
        Mockito.when(etudiantRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(etudiant))
        ;
        Etudiant etudiant1 = etudiantService.retrieveEtudiant(2);
        Assertions.assertNotNull(etudiant1);

    }

    @org.junit.jupiter.api.Test
    @Order(4)
    void getEtudiant(){
        Iterable<Etudiant> LEtudiant = etudiantRepository.findAll();
        Assertions.assertNotNull(LEtudiant);
    }
}
