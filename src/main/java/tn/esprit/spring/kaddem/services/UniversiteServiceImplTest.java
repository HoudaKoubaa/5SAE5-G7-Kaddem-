package tn.esprit.spring.kaddem.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.List;

public class UniversiteServiceImplTest {

    @Test
    public void testRetrieveAllUniversites() {
        // Arrange
        UniversiteServiceImpl universiteService = new UniversiteServiceImpl(); // You may need to inject your repositories or use mocks here
        Universite universite1 = new Universite(/* Initialize with data */);
        Universite universite2 = new Universite(/* Initialize with data */);

        // Act
        List<Universite> universites = universiteService.retrieveAllUniversites();

        // Assert
        Assertions.assertEquals(2, universites.size()); // Adjust based on the number of universities retrieved
        // You can add more specific assertions to verify the contents of the list.
    }
}
