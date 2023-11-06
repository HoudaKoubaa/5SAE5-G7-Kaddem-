package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.DTO.UniversiteDTO;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {

	IUniversiteService universiteService;
	// http://localhost:8089/Kaddem/universite/retrieve-all-universites
	@GetMapping("/retrieve-all-universites")
	public List<Universite> getUniversites() {
	return universiteService.retrieveAllUniversites();
	}
	// http://localhost:8089/Kaddem/universite/retrieve-universite/8
	@GetMapping("/retrieve-universite/{universite-id}")
	public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
		return universiteService.retrieveUniversite(universiteId);
	}

	// http://localhost:8089/Kaddem/universite/add-universite
	@PostMapping("/add-universite")
	public UniversiteDTO addUniversite(@RequestBody UniversiteDTO universiteDTO) {
		// Convert UniversiteDTO to Universite entity and then add it
		Universite newUniversite = convertToUniversiteEntity(universiteDTO);
		newUniversite = universiteService.addUniversite(newUniversite);
		return convertToUniversiteDTO(newUniversite);
	}


	// http://localhost:8089/Kaddem/universite/remove-universite/1
	@DeleteMapping("/remove-universite/{universite-id}")
	public void removeUniversite(@PathVariable("universite-id") Integer universiteId) {
		universiteService.deleteUniversite(universiteId);
	}

	// http://localhost:8089/Kaddem/universite/update-universite
	@PutMapping("/update-universite")
	public UniversiteDTO updateUniversite(@RequestBody UniversiteDTO universiteDTO) {
		// Convert UniversiteDTO to Universite entity, perform the update, and then convert it back to DTO
		Universite updatedUniversite = convertToUniversiteEntity(universiteDTO);
		updatedUniversite = universiteService.updateUniversite(updatedUniversite);
		return convertToUniversiteDTO(updatedUniversite);
	}


	//@PutMapping("/affecter-etudiant-departement")
	@PutMapping(value="/affecter-universite-departement/{universiteId}/{departementId}")
	public void affectertUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId, @PathVariable("departementId")Integer departementId){
		universiteService.assignUniversiteToDepartement(universiteId, departementId);
	}

	@GetMapping(value = "/listerDepartementsUniversite/{idUniversite}")
	public Set<Departement> listerDepartementsUniversite(@PathVariable("idUniversite") Integer idUniversite) {

		return universiteService.retrieveDepartementsByUniversite(idUniversite);
	}
	private Universite convertToUniversiteEntity(UniversiteDTO universiteDTO) {
		Universite universite = new Universite();
		universite.setIdUniv(universiteDTO.getIdUniv());
		universite.setNomUniv(universiteDTO.getNomUniv());
		return universite;
	}

	private UniversiteDTO convertToUniversiteDTO(Universite universite) {
		UniversiteDTO universiteDTO = new UniversiteDTO();
		universiteDTO.setIdUniv(universite.getIdUniv());
		universiteDTO.setNomUniv(universite.getNomUniv());
		return universiteDTO;
	}


}


