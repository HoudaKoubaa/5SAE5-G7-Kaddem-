package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dto.ContratDTO;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.services.IContratService;

import java.util.Date;
import java.util.List;

@RestController


@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {
	IContratService contratService;
	// http://localhost:8089/Kaddem/contrat/retrieve-all-contrats


	@GetMapping("/retrieve-all-contrats")
	public List<Contrat> getContrats() {
		return contratService.retrieveAllContrats();

	}
	// http://localhost:8089/Kaddem/contrat/retrieve-contrat/8
	@GetMapping("/retrieve-contrat/{contrat-id}")
	public Contrat retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
		return contratService.retrieveContrat(contratId);
	}
	// Method to convert ContratDTO to Contrat entity
	private Contrat convertToContratEntity(ContratDTO contratDTO) {
		Contrat contrat = new Contrat();
		contrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
		contrat.setDateFinContrat(contratDTO.getDateFinContrat());
		contrat.setSpecialite(contratDTO.getSpecialite());
		contrat.setArchive(contratDTO.getArchive());
		contrat.setMontantContrat(contratDTO.getMontantContrat());
		// You may need to set other fields as well
		return contrat;
	}

	// Method to convert Contrat entity to ContratDTO
	private ContratDTO convertToContratDTO(Contrat contrat) {
		ContratDTO contratDTO = new ContratDTO();
		contratDTO.setDateDebutContrat(contrat.getDateDebutContrat());
		contratDTO.setDateFinContrat(contrat.getDateFinContrat());
		contratDTO.setSpecialite(contrat.getSpecialite());
		contratDTO.setArchive(contrat.getArchive());
		contratDTO.setMontantContrat(contrat.getMontantContrat());
		// You may need to map other fields as well
		return contratDTO;
	}


	// http://localhost:8089/Kaddem/econtrat/add-contrat
	@PostMapping("/add-contrat")
	public ContratDTO addContrat(@RequestBody ContratDTO contratDTO) {
		// Convert ContratDTO to Contrat entity and save it
		Contrat newContrat = convertToContratEntity(contratDTO);
		newContrat = contratService.addContrat(newContrat);
		return convertToContratDTO(newContrat);
	}


	// http://localhost:8089/Kaddem/contrat/remove-contrat/1
	@DeleteMapping("/remove-contrat/{contrat-id}")
	public void removeContrat(@PathVariable("contrat-id") Integer contratId) {
		contratService.removeContrat(contratId);
	}

	// http://localhost:8089/Kaddem/contrat/update-contrat
	@PutMapping("/update-contrat")
	public ContratDTO updateContrat(@RequestBody ContratDTO contratDTO) {
		// Convert ContratDTO to Contrat entity, perform the update, and then convert it back to DTO
		Contrat updatedContrat = convertToContratEntity(contratDTO);
		updatedContrat = contratService.updateContrat(updatedContrat);
		return convertToContratDTO(updatedContrat);
	}


	@PutMapping(value = "/assignContratToEtudiant/{idContrat}/{nomE}/{prenomE}")
	public Contrat assignContratToEtudiant (Integer idContrat, String nomE, String prenomE){
	//	Contrat c= contratService.affectContratToEtudiant()
		return 	(contratService.affectContratToEtudiant(idContrat, nomE, prenomE));
	}

	//The most common ISO Date Format yyyy-MM-dd — for example, "2000-10-31".
		@GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
		public Integer getnbContratsValides(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
										  @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

			return contratService.nbContratsValides(startDate, endDate);
		}

    //Only no-arg methods may be annotated with @Scheduled
    @Scheduled(cron="0 0 13 * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
	@PutMapping(value = "/majStatusContrat")
	public void majStatusContrat (){
		contratService.retrieveAndUpdateStatusContrat();

	}

	//public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate)

	@GetMapping("/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
	@ResponseBody
	public float calculChiffreAffaireEntreDeuxDates(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
	@PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

		return contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
	}
}


