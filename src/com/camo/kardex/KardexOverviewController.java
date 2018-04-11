package com.camo.kardex;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.camo.MainApp;
import com.camo.entities.Kardex_pot;
import com.camo.entities.Kardex_reg;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class KardexOverviewController {
	
	@FXML
	private Accordion accordion;
	@FXML
    private TableView<Kardex_pot> Kardex_pot_Table;
	@FXML
    private TableColumn<Kardex_pot, String> ATAColumn;
	@FXML
    private TableColumn<Kardex_pot, String> designationColumn;
	@FXML
    private TableColumn<Kardex_pot, String> PartNumberColumn;
	@FXML
    private TableColumn<Kardex_pot, String> SerialNumberColumn;
	@FXML
    private TableColumn<Kardex_pot, String> operationColumn;
	@FXML
    private TableColumn<Kardex_pot, String> onAircraftColumn;
	@FXML
    private TableColumn<Kardex_pot, String> dates_onAircraftColumn;
	@FXML
    private TableColumn<Kardex_pot, String> heures_onAircraftColumn;
	@FXML
	private TableColumn<Kardex_pot, String> cycles_onAircraftColumn;
	@FXML
	private TableColumn<Kardex_pot, String> potentielColumn;
	@FXML
	private TableColumn<Kardex_pot, String> dates_potentielColumn;
	@FXML
	private TableColumn<Kardex_pot, String> heures_potentielColumn;
	@FXML
	private TableColumn<Kardex_pot, String> cycles_potentielColumn;
	@FXML
	private TableColumn<Kardex_pot, String> buteeColumn;
	@FXML
	private TableColumn<Kardex_pot, String> dates_buteeColumn;
	@FXML
	private TableColumn<Kardex_pot, String> heures_buteeColumn;
	@FXML
	private TableColumn<Kardex_pot, String> cycles_buteeColumn;
	@FXML
    private TableView<Kardex_reg> Kardex_reg_Table;
	@FXML
    private TableColumn<Kardex_reg, String> reference_docColumn;
	@FXML
    private TableColumn<Kardex_reg, String> typeColumn;
	@FXML
    private TableColumn<Kardex_reg, String> emetteurColumn;
	@FXML
    private TableColumn<Kardex_reg, String> emissionColumn;
	@FXML
    private TableColumn<Kardex_reg, String> repetitionColumn;
	@FXML
    private TableColumn<Kardex_reg, String> faitLeColumn;
	@FXML
    private TableColumn<Kardex_reg, String> butee_regColumn;
	
    public KardexOverviewController() {
    	
    }
    	private void initialize() {
        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            // Initialize the person table with the two columns.
        	ATAColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getATA()));
        	designationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDesignation()));
        	PartNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPartNumber()));
        	SerialNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSerialNumber()));
        	operationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOperation()));
        	onAircraftColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOnAircraft()));
        	dates_onAircraftColumn.setCellValueFactory(cellData -> new SimpleStringProperty(df.format(cellData.getValue().getDates_onAircraft())));
        	heures_onAircraftColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeures_onAircraft()));
        	cycles_onAircraftColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCycles_onAircraft()));
        	potentielColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPotentiel()));
        	dates_potentielColumn.setCellValueFactory(cellData -> new SimpleStringProperty(df.format(cellData.getValue().getDates_potentiel())));
        	heures_potentielColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeures_potentiel()));
        	cycles_potentielColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCycles_potentiel() ));
        	buteeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getButee()));
        	dates_buteeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(df.format(cellData.getValue().getDates_butee())));
        	heures_buteeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeures_butee()));
        	cycles_buteeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCycles_butee()));
        	        	
        	reference_docColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReference_doc()));
        	typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        	emetteurColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmetteur()));
        	emissionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(df.format(cellData.getValue().getEmission())));
        	repetitionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRepetition()));
        	faitLeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(df.format(cellData.getValue().getFaitLe())));
        	butee_regColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getButee()));      	      	
        	
    }
	
    private MainApp mainApp;

}
