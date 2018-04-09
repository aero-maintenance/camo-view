package com.camo.flight;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.camo.MainApp;
import com.camo.entities.Vol;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FlightOverviewController {

	@FXML
    private TableView<Vol> flightsTable;
	@FXML
    private TableColumn<Vol, String> dateColumn;
	@FXML
    private TableColumn<Vol, Long> flightNumberColumn;
	@FXML
    private TableColumn<Vol, String> immatriculationColumn;
	@FXML
    private TableColumn<Vol, String> modelColumn;
	@FXML
    private TableColumn<Vol, String> pilotColumn;
	@FXML
    private TableColumn<Vol, Float> FHColumn;
	@FXML
    private TableColumn<Vol, Integer> FCColumn;
	@FXML
    private TableColumn<Vol, Integer> fuelColumn;
	@FXML
    private TableColumn<Vol, Integer> oilColumn;
	@FXML
    private TableColumn<Vol, String> noteColumn;
	
	private ObservableList<Vol> 	flightsData;
	
	private MainApp mainApp;
	
	public FlightOverviewController() {
		
	}
	
	@FXML
    private void initialize() {
    	
    	DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    	dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(fmt.print(cellData.getValue().getDate_heure())));
    	flightNumberColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getFlightNumber()).asObject());
    	immatriculationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAircraft().getImmatriculation()));
    	modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAircraft().getModele()));
    	pilotColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPilote()));
    	FHColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getFH()).asObject());
    	FCColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFC()).asObject());
    	fuelColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCarburant()).asObject());
    	oilColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHuile()).asObject());
    	noteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRemarque()));
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

     // Add observable list data to the table
        flightsTable.setItems(getFlightsData());
    }

	public ObservableList<Vol> getFlightsData() {
		return flightsData;
	}

	public void setFlightsData(ObservableList<Vol> flightsData) {
		this.flightsData = flightsData;
	}
	
}
