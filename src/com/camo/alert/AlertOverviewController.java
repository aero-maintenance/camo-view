package com.camo.alert;

import com.camo.MainApp;
import com.camo.entities.Alerte;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AlertOverviewController {
	
	@FXML
	private TextField researchField;
	@FXML
    private TableView<Alerte> AlertTable;
	@FXML
    private TableColumn<Alerte, String> immatriculationColumn;
	@FXML
    private TableColumn<Alerte, String> modelColumn;
	@FXML
    private TableColumn<Alerte, String> designationColumn;
	@FXML
    private TableColumn<Alerte, String> limiteColumn;
	
	private ObservableList<Alerte> alertData;
	private MainApp mainApp;
	
	public AlertOverviewController(){
		
	}
	
	@FXML
	private void initialize() {
		immatriculationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImmat()));
		modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
		designationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDesignation()));
		limiteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLimite()));
		
		
	}
	
	public MainApp getMainApp() {
		return mainApp;
	}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		AlertTable.setItems(alertData);
	}
	public ObservableList<Alerte> getAlertData() {
		return alertData;
	}
	public void setAlertData(ObservableList<Alerte> alertData) {
		this.alertData = alertData;
	}
}
