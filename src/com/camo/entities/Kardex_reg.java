package com.camo.entities;

import java.sql.Date;

public class Kardex_reg {
	
	private String reference_doc;
	private String type;
	private String emetteur;
	private Date emission;
	private String repetition;
	private String OuiNon;
	private String intervalles;
	private Date FaitLe;
	private String butee;
	public String getReference_doc() {
		return reference_doc;
	}
	public void setReference_doc(String reference_doc) {
		this.reference_doc = reference_doc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(String emetteur) {
		this.emetteur = emetteur;
	}
	public Date getEmission() {
		return emission;
	}
	public void setEmission(Date emission) {
		this.emission = emission;
	}
	public String getRepetition() {
		return repetition;
	}
	public void setRepetition(String repetition) {
		this.repetition = repetition;
	}
	public String getOuiNon() {
		return OuiNon;
	}
	public void setOuiNon(String ouiNon) {
		OuiNon = ouiNon;
	}
	public String getIntervalles() {
		return intervalles;
	}
	public void setIntervalles(String intervalles) {
		this.intervalles = intervalles;
	}
	public Date getFaitLe() {
		return FaitLe;
	}
	public void setFaitLe(Date faitLe) {
		FaitLe = faitLe;
	}
	public String getButee() {
		return butee;
	}
	public void setButee(String butee) {
		this.butee = butee;
	}
	
}
