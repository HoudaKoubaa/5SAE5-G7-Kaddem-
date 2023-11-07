package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UniversiteTest {

    @Autowired
    IUniversiteService us;
    @Test
    @Order(1)
    public void retrieveAllUniversites(){
        List<Universite> listUniversite = us.retrieveAllUniversites();
        Assertions.assertEquals(0, listUniversite.size());
    }
}
