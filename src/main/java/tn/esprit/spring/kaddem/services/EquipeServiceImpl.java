package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService{
	EquipeRepository equipeRepository;


	public List<Equipe> retrieveAllEquipes(){
	return  (List<Equipe>) equipeRepository.findAll();
	}
	public Equipe addEquipe(Equipe e){
		return (equipeRepository.save(e));
	}

	public  void deleteEquipe(Integer idEquipe){
		Equipe e=retrieveEquipe(idEquipe);
		equipeRepository.delete(e);
	}

	public Equipe retrieveEquipe(Integer equipeId) {
		Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);

		if (optionalEquipe.isPresent()) {
			return optionalEquipe.get();
		} else {
			// Handle the case where the value is not present, for example, return null or throw an exception.
			// You might want to decide how to handle this situation.
			return null; // Or throw an exception if appropriate.
		}
	}


	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	public void evoluerEquipes() {
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();

		for (Equipe equipe : equipes) {
			if (shouldEvoluer(equipe)) {
				evoluerEquipe(equipe);
			}
		}
	}

	private boolean shouldEvoluer(Equipe equipe) {
		if (equipe.getNiveau() == Niveau.JUNIOR || equipe.getNiveau() == Niveau.SENIOR) {
			int activeContractsCount = countActiveContracts(equipe);
			return activeContractsCount >= 3;
		}
		return false;
	}

	private int countActiveContracts(Equipe equipe) {
		int activeContractsCount = 0;

		for (Etudiant etudiant : equipe.getEtudiants()) {
			activeContractsCount += countActiveContractsForEtudiant(etudiant);
			if (activeContractsCount >= 3) {
				break;
			}
		}

		return activeContractsCount;
	}

	private int countActiveContractsForEtudiant(Etudiant etudiant) {
		int activeContractsCount = 0;

		for (Contrat contrat : etudiant.getContrats()) {
			if (isContractActive(contrat)) {
				activeContractsCount++;
			}
			if (activeContractsCount >= 3) {
				break;
			}
		}

		return activeContractsCount;
	}

	private boolean isContractActive(Contrat contrat) {
		Date currentDate = new Date();
		long difference_in_time = currentDate.getTime() - contrat.getDateFinContrat().getTime();
		long difference_in_years = (difference_in_time / (1000l * 60 * 60 * 24 * 365));
		return contrat.getArchive() && difference_in_years > 1;
	}

	private void evoluerEquipe(Equipe equipe) {
		if (equipe.getNiveau() == Niveau.JUNIOR) {
			equipe.setNiveau(Niveau.SENIOR);
		} else if (equipe.getNiveau() == Niveau.SENIOR) {
			equipe.setNiveau(Niveau.EXPERT);
		}

		equipeRepository.save(equipe);
	}

}