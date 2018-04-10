package com.camo.entities;

public class Alerte {
	private String immatriculation;
	private String model;
	private String designation;
	private String limite;
	
	public Alerte(String immat, String model, String designation, String limite) {
		this.immatriculation = immat;
		this.model = model;
		this.designation = designation;
		this.limite = limite;
	}
	
	public String getImmat() {
		return immatriculation;
	}
	public void setImmat(String immat) {
		this.immatriculation = immat;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getLimite() {
		return limite;
	}
	public void setLimite(String limite) {
		this.limite = limite;
	}
}
