package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.services.IEquipeService;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EquipeJUnitTest {

    @Autowired
    IEquipeService iEquipeService;

    @Test
    public void testAddEquipe(){
        Equipe e =new Equipe();
        e.setNomEquipe("ESS");
        e.setNiveau(Niveau.JUNIOR);
        iEquipeService.addEquipe(e);
        System.out.println("Equipe nom test =>" +e.getNomEquipe());
        assertNotNull(e.getIdEquipe());
        assertNotNull(e.getNiveau());
        iEquipeService.deleteEquipe(e.getIdEquipe());
    }

    @Test
    public void testRetrieveAllEquipes()
    {
        List<Equipe> equipes= iEquipeService.retrieveAllEquipes();
        int expected = equipes.size();
        Equipe e = new Equipe();
        e.setNomEquipe("testRetrieve");
        e.setNiveau(Niveau.JUNIOR);
        iEquipeService.addEquipe(e);
        assertEquals(expected+1, iEquipeService.retrieveAllEquipes().size());
    }

    @Test
    public void testDeleteEquipe()
    {
        Equipe e= new Equipe();
        e.setNomEquipe("testDelete");
        e.setNiveau(Niveau.JUNIOR);
        iEquipeService.addEquipe(e);
        System.out.println("Equipe Test => "+e);
        iEquipeService.deleteEquipe(e.getIdEquipe());
    }


    @Test
    public void testUpdateEquipe()
    {
        Equipe e= new Equipe();
        e.setNomEquipe("testUpdate");
        e.setNiveau(Niveau.JUNIOR);
        iEquipeService.addEquipe(e);
        Equipe eq= iEquipeService.retrieveEquipe(e.getIdEquipe());
        eq.setNiveau(Niveau.SENIOR);
        iEquipeService.updateEquipe(eq);
        assertEquals(Niveau.SENIOR,eq.getNiveau());
        log.info("test update =>" + eq.getNiveau());

    }


}
