package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.DTO.EquipeDTO;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.services.IEquipeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {
	IEquipeService equipeService;
	// http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
	@GetMapping("/retrieve-all-equipes")
	public List<Equipe> getEquipes() {
		return equipeService.retrieveAllEquipes();
	}
	// http://localhost:8089/Kaddem/equipe/retrieve-equipe/8
	@GetMapping("/retrieve-equipe/{equipe-id}")
	public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
		return equipeService.retrieveEquipe(equipeId);
	}

	// http://localhost:8089/Kaddem/equipe/add-equipe
	@PostMapping("/add-equipe")
	public Equipe addEquipe(@RequestBody EquipeDTO equipeDTO) {
		Equipe equipe = convertToEquipeEntity(equipeDTO);
		return equipeService.addEquipe(equipe);
	}


	// http://localhost:8089/Kaddem/equipe/remove-equipe/1
	@DeleteMapping("/remove-equipe/{equipe-id}")
	public void removeEquipe(@PathVariable("equipe-id") Integer equipeId) {
		equipeService.deleteEquipe(equipeId);
	}

	// http://localhost:8089/Kaddem/equipe/update-equipe
	@PutMapping("/update-equipe/{idEquipe}")
	public Equipe updateEquipe(@PathVariable("idEquipe") Integer idEquipe, @RequestBody EquipeDTO equipeDTO) {
		Equipe existingEquipe = equipeService.findById(idEquipe);
		if (existingEquipe == null) {
			// Handle not found case
			// You can throw an exception or return an appropriate response
		}

		// Update the existing Equipe entity with the DTO data
		updateEquipeFromDTO(existingEquipe, equipeDTO);

		return equipeService.updateEquipe(existingEquipe);
	}
	private Equipe convertToEquipeEntity(EquipeDTO equipeDTO) {
		Equipe equipe = new Equipe();
		equipe.setNomEquipe(equipeDTO.getNomEquipe());
		equipe.setNiveau(equipeDTO.getNiveau());
		// Set other fields as needed
		return equipe;
	}

	private void updateEquipeFromDTO(Equipe equipe, EquipeDTO equipeDTO) {
		equipe.setNomEquipe(equipeDTO.getNomEquipe());
		equipe.setNiveau(equipeDTO.getNiveau());
		// Update other fields as needed
	}

	@Scheduled(cron="0 0 13 * * *")
	@PutMapping("/faireEvoluerEquipes")
	public void faireEvoluerEquipes() {
		 equipeService.evoluerEquipes() ;
	}
}


