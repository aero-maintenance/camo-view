package com.camo.entities;

import java.sql.Date;

public class Kardex_pot {
	
	private String ATA;
	private String designation;
	private String PartNumber;
	private String SerialNumber;
	private String operation;
	private String onAircraft;
	private Date dates_onAircraft;
	private String heures_onAircraft;
	private String cycles_onAircraft;
	private String potentiel;
	private Date   dates_potentiel;
	private String heures_potentiel;
	private String cycles_potentiel;
	private String butee;
	private Date dates_butee;
	private String heures_butee;
	private String cycles_butee;

	public String getATA() {
		return ATA;
	}

	public void setATA(String aTA) {
		ATA = aTA;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPartNumber() {
		return PartNumber;
	}

	public void setPartNumber(String partNumber) {
		PartNumber = partNumber;
	}

	public String getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOnAircraft() {
		return onAircraft;
	}

	public void setOnAircraft(String onAircraft) {
		this.onAircraft = onAircraft;
	}

	public Date getDates_onAircraft() {
		return dates_onAircraft;
	}

	public void setDates_onAircraft(Date dates_onAircraft) {
		this.dates_onAircraft = dates_onAircraft;
	}

	public String getHeures_onAircraft() {
		return heures_onAircraft;
	}

	public void setHeures_onAircraft(String heures_onAircraft) {
		this.heures_onAircraft = heures_onAircraft;
	}

	public String getCycles_onAircraft() {
		return cycles_onAircraft;
	}

	public void setCycles_onAircraft(String cycles_onAircraft) {
		this.cycles_onAircraft = cycles_onAircraft;
	}

	public String getPotentiel() {
		return potentiel;
	}

	public void setPotentiel(String potentiel) {
		this.potentiel = potentiel;
	}

	public Date getDates_potentiel() {
		return dates_potentiel;
	}

	public void setDates_potentiel(Date dates_potentiel) {
		this.dates_potentiel = dates_potentiel;
	}

	public String getHeures_potentiel() {
		return heures_potentiel;
	}

	public void setHeures_potentiel(String heures_potentiel) {
		this.heures_potentiel = heures_potentiel;
	}

	public String getCycles_potentiel() {
		return cycles_potentiel;
	}

	public void setCycles_potentiel(String cycles_potentiel) {
		this.cycles_potentiel = cycles_potentiel;
	}

	public String getButee() {
		return butee;
	}

	public void setButee(String butee) {
		this.butee = butee;
	}

	public Date getDates_butee() {
		return dates_butee;
	}

	public void setDates_butee(Date dates_butee) {
		this.dates_butee = dates_butee;
	}

	public String getHeures_butee() {
		return heures_butee;
	}

	public void setHeures_butee(String heures_butee) {
		this.heures_butee = heures_butee;
	}

	public String getCycles_butee() {
		return cycles_butee;
	}

	public void setCycles_butee(String cycles_butee) {
		this.cycles_butee = cycles_butee;
	}

}
