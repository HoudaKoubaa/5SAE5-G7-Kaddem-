package tn.esprit.spring.kaddem.services;

import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UniversiteServiceImpl implements IUniversiteService{

    UniversiteRepository universiteRepository;

    DepartementRepository departementRepository;
    public UniversiteServiceImpl() {
        // This class relies on the default constructor provided by Java.
        // Custom initialization is not required at this time.
    }

  public   List<Universite> retrieveAllUniversites(){
return (List<Universite>) universiteRepository.findAll();
    }

 public    Universite addUniversite (Universite  u){
return  (universiteRepository.save(u));
    }

 public    Universite updateUniversite (Universite  u){
     return  (universiteRepository.save(u));
    }

    public Universite retrieveUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);

        if (optionalUniversite.isPresent()) {
            return optionalUniversite.get();
        } else {
            // Handle the case where the value is not present, for example, return null or throw an exception.
            // You might want to decide how to handle this situation.
            return null; // Or throw an exception if appropriate.
        }
    }

    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement) {
        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        Departement d = departementRepository.findById(idDepartement).orElse(null);

        if (u != null && d != null) {
            u.getDepartements().add(d);
            universiteRepository.save(u);
        } else {
            // Handle the case where either u or d is null, for example, log an error or throw an exception.
            // You might want to decide how to handle this situation.
        }
    }


    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Universite u = universiteRepository.findById(idUniversite).orElse(null);

        if (u != null) {
            return u.getDepartements();
        } else {
            // Handle the case where 'u' is null, for example, return an empty set or throw an exception.
            // You might want to decide how to handle this situation.
            return Collections.emptySet(); // Return an empty set as a default, or throw an exception if appropriate.
        }
    }

}
