package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.DTO.DepartementDTO;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController {
	IDepartementService departementService;
	// http://localhost:8089/Kaddem/departement/retrieve-all-departements
	@GetMapping("/retrieve-all-departements")
	public List<Departement> getDepartements() {
		return departementService.retrieveAllDepartements();
	}
	// http://localhost:8089/Kaddem/departement/retrieve-departement/8
	@GetMapping("/retrieve-departement/{departement-id}")
	public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
		return departementService.retrieveDepartement(departementId);
	}

	// http://localhost:8089/Kaddem/departement/add-departement
	@PostMapping("/add-departement")
	public DepartementDTO addDepartement(@RequestBody DepartementDTO departementDTO) {
		// Convert DepartementDTO to Departement entity and then add it
		Departement newDepartement = convertToDepartementEntity(departementDTO);
		newDepartement = departementService.addDepartement(newDepartement);
		return convertToDepartementDTO(newDepartement);
	}


	// http://localhost:8089/Kaddem/departement/remove-departement/1
	@DeleteMapping("/remove-departement/{departement-id}")
	public void removeDepartement(@PathVariable("departement-id") Integer departementId) {
		departementService.deleteDepartement(departementId);
	}

	// http://localhost:8089/Kaddem/departement/update-departement
	@PutMapping("/update-departement")
	public DepartementDTO updateDepartement(@RequestBody DepartementDTO departementDTO) {
		// Convert DepartementDTO to Departement entity, perform the update, and then convert it back to DTO
		Departement updatedDepartement = convertToDepartementEntity(departementDTO);
		updatedDepartement = departementService.updateDepartement(updatedDepartement);
		return convertToDepartementDTO(updatedDepartement);
	}
	private Departement convertToDepartementEntity(DepartementDTO departementDTO) {
		Departement departement = new Departement();
		departement.setIdDepart(departementDTO.getIdDepart());
		departement.setNomDepart(departementDTO.getNomDepart());
		return departement;
	}

	private DepartementDTO convertToDepartementDTO(Departement departement) {
		DepartementDTO departementDTO = new DepartementDTO();
		departementDTO.setIdDepart(departement.getIdDepart());
		departementDTO.setNomDepart(departement.getNomDepart());
		return departementDTO;
	}


}


