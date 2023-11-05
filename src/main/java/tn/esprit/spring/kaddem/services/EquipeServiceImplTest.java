package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @Test
    public void testRetrieveAllEquipes() {
        // Créez des données de test
        Equipe equipe1 = new Equipe();
        Equipe equipe2 = new Equipe();
        // Définissez le comportement simulé du repository
        when(equipeRepository.findAll()).thenReturn(List.of(equipe1, equipe2));

        // Appelez la méthode à tester
        EquipeServiceImpl equipeService = new EquipeServiceImpl(equipeRepository);
        List<Equipe> equipes = equipeService.retrieveAllEquipes();
        // Vérifiez le résultat
        assertEquals
                (2,
                equipes.size());
    }

}
