package tn.esprit.spring.kaddem.dto;

import tn.esprit.spring.kaddem.entities.Niveau;

public class EquipeDTO {
    private String nomEquipe;
    private Niveau niveau;
    // Add any other fields as needed

    // Getters and setters

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
}
