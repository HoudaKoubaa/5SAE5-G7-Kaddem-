package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.DTO.EtudiantDTO;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {
	IEtudiantService etudiantService;
	// http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
	@GetMapping("/retrieve-all-etudiants")
	public List<Etudiant> getEtudiants() {
		return etudiantService.retrieveAllEtudiants();
	}
	// http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
	@GetMapping("/retrieve-etudiant/{etudiant-id}")
	public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		return etudiantService.retrieveEtudiant(etudiantId);
	}

	// http://localhost:8089/Kaddem/etudiant/add-etudiant
	@PostMapping("/add-etudiant")
	public EtudiantDTO addEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		// Convert EtudiantDTO to Etudiant entity and then add it
		Etudiant newEtudiant = convertToEtudiantEntity(etudiantDTO);
		newEtudiant = etudiantService.addEtudiant(newEtudiant);
		return convertToEtudiantDTO(newEtudiant);
	}


	// http://localhost:8089/Kaddem/etudiant/remove-etudiant/1
	@DeleteMapping("/remove-etudiant/{etudiant-id}")
	public void removeEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		etudiantService.removeEtudiant(etudiantId);
	}

	// http://localhost:8089/Kaddem/etudiant/update-etudiant
	@PutMapping("/update-etudiant")
	public EtudiantDTO updateEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		// Convert EtudiantDTO to Etudiant entity, perform the update, and then convert it back to DTO
		Etudiant updatedEtudiant = convertToEtudiantEntity(etudiantDTO);
		updatedEtudiant = etudiantService.updateEtudiant(updatedEtudiant);
		return convertToEtudiantDTO(updatedEtudiant);
	}


	//@PutMapping("/affecter-etudiant-departement")
	@PutMapping(value="/affecter-etudiant-departement/{etudiantId}/{departementId}")
	public void affecterEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId, @PathVariable("departementId")Integer departementId){
		etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
    }
//addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe)
    /* Ajouter un étudiant tout en lui affectant un contrat et une équipe */
@PostMapping("/add-assign-Etudiant")
@ResponseBody
public Etudiant addEtudiantWithEquipeAndContract(
		@RequestBody EtudiantDTO etudiantDTO,
		@RequestParam("idContrat") Integer idContrat,
		@RequestParam("idEquipe") Integer idEquipe) {
	// Convert EtudiantDTO to Etudiant and assign it to an Equipe with the given Contrat
	Etudiant EtudiantDTO = null;
	return etudiantService.addAndAssignEtudiantToEquipeAndContract(EtudiantDTO, idContrat, idEquipe);
}


	@GetMapping(value = "/getEtudiantsByDepartement/{idDepartement}")
	public List<Etudiant> getEtudiantsParDepartement(@PathVariable("idDepartement") Integer idDepartement) {

		return etudiantService.getEtudiantsByDepartement(idDepartement);
	}

	private Etudiant convertToEtudiantEntity(EtudiantDTO etudiantDTO) {
		Etudiant etudiant = new Etudiant();
		etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant());
		etudiant.setNomE(etudiantDTO.getNomE());
		etudiant.setPrenomE(etudiantDTO.getPrenomE());
		etudiant.setOp(etudiantDTO.getOp());
		return etudiant;
	}

	private EtudiantDTO convertToEtudiantDTO(Etudiant etudiant) {
		EtudiantDTO etudiantDTO = new EtudiantDTO();
		etudiantDTO.setIdEtudiant(etudiant.getIdEtudiant());
		etudiantDTO.setNomE(etudiant.getNomE());
		etudiantDTO.setPrenomE(etudiant.getPrenomE());
		etudiantDTO.setOp(etudiant.getOp());
		return etudiantDTO;
	}

}


