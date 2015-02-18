package com.example.gala_easy_event;

public class Etudiant {
	private String prenom;
	private String nom;
	private String email;
	private String prevente;
	private String validation;
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrevente() {
		return prevente;
	}

	public void setPrevente(String prevente) {
		this.prevente = prevente;
	}

	public String getValidation() {
		return this.validation;
	}
	
	public void setValidation(String validation){
		this.validation = validation;
	}
}
