package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService{
	@Autowired
	EtudiantRepository etudiantRepository ;
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EquipeRepository equipeRepository;
    @Autowired
    DepartementRepository departementRepository;
	public List<Etudiant> retrieveAllEtudiants(){

		return (List<Etudiant>) etudiantRepository.findAll();
	}

	public Etudiant addEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}

	public Etudiant updateEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}

	public Etudiant retrieveEtudiant(Integer idEtudiant) {
		Optional<Etudiant> etudiantOptional = etudiantRepository.findById(idEtudiant);

		if (etudiantOptional.isPresent()) {
			return etudiantOptional.get(); // Accédez à la valeur si elle est présente
		} else {
			// Gérez le cas où l'étudiant n'a pas été trouvé, par exemple en lançant une exception, en renvoyant null ou en effectuant une autre action appropriée.
			throw new RuntimeException("L'étudiant avec l'ID " + idEtudiant + " n'a pas été trouvé.");
		}
	}

	public void removeEtudiant(Integer idEtudiant){
	Etudiant e=retrieveEtudiant(idEtudiant);
	etudiantRepository.delete(e);
	}

	public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
		Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
		Optional<Departement> departementOptional = departementRepository.findById(departementId);

		if (etudiantOptional.isPresent() && departementOptional.isPresent()) {
			Etudiant etudiant = etudiantOptional.get();
			Departement departement = departementOptional.get();

			etudiant.setDepartement(departement);
			etudiantRepository.save(etudiant);
		} else {
			// Gérer le cas où l'étudiant ou le département n'est pas trouvé
			// Vous pouvez lancer une exception, afficher un message d'erreur, ou prendre une autre action appropriée.
			throw new RuntimeException("L'étudiant ou le département n'a pas été trouvé.");
		}
	}

	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe){
		Contrat c=contratRepository.findById(idContrat).orElse(null);
		Equipe eq=equipeRepository.findById(idEquipe).orElse(null);
		c.setEtudiant(e);
		eq.getEtudiants().add(e);
return e;
	}

	public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){
return  etudiantRepository.findEtudiantsByDepartement_IdDepart((idDepartement));
	}
}
