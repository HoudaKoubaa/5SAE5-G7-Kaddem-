package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import tn.esprit.spring.kaddem.entities.Universite;

import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UniversiteServiceImplTest {

    @Autowired
    IUniversiteService us;
    @Test
    @Order(1)
    public void retrieveAllUniversites(){
        List<Universite> listUniversite = us.retrieveAllUniversites();
        Assertions.assertEquals(0, listUniversite.size());
    }
}
