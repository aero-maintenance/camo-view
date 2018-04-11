package com.camo.aircraft;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.camo.MainApp;
import com.camo.entities.Aircraft;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AircraftOverviewController {
	
	@FXML
	private TextField researchField;
	@FXML
    private TableView<Aircraft> aircraftTable;
	@FXML
    private TableColumn<Aircraft, String> manufacturerColumn;
	@FXML
    private TableColumn<Aircraft, String> modelColumn;
	@FXML
    private TableColumn<Aircraft, String> immatriculationColumn;
	@FXML
    private TableColumn<Aircraft, Long> masterSerialNumberColumn;
	@FXML
    private TableColumn<Aircraft, Double> totalFlightHourColumn;
	@FXML
    private TableColumn<Aircraft, Long> totalFightCycleColumn;
	@FXML
    private TableColumn<Aircraft, String> statusColumn;
	@FXML
    private TableColumn<Aircraft, String> dateKardexColumn;
	
	private ObservableList<Aircraft> 	aircraftData;
    
    private MainApp mainApp;
       
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AircraftOverviewController() {
    }
    
    @FXML
    private void initialize() {
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // Initialize the person table with the two columns.
    	manufacturerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getConstructeur()));
    	modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModele()));
    	immatriculationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImmatriculation()));
    	masterSerialNumberColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getMsn()).asObject());
    	totalFlightHourColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotal_FH()).asObject());
    	totalFightCycleColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getTotal_FC()).asObject());
    	statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatut()));
    	dateKardexColumn.setCellValueFactory(cellData -> new SimpleStringProperty(df.format(cellData.getValue().getDate_Kardex())));
        
        
        //Recherche automatique
        researchField.textProperty().addListener((obs, oldText, newText) -> {
            
            if(newText.length()==0) {
            	System.out.println("Text changed from "+oldText+" to "+newText);
            	setAircraftData(FXCollections.observableArrayList(mainApp.getAircraftDao().lister()));
            	//aircraftTable.getItems().clear();
            	aircraftTable.setItems(getAircraftData());
            	aircraftTable.refresh();
            }else {
            	System.out.println("Text changed from "+oldText+" to "+newText);
            	setAircraftData(FXCollections.observableArrayList(mainApp.getAircraftDao().rechercher(newText)));
            	//aircraftTable.getItems().clear();
            	aircraftTable.setItems(getAircraftData());
            	aircraftTable.refresh();
            }
        });
        
        
        aircraftTable.setRowFactory(tv -> {
            TableRow<Aircraft> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Aircraft rowData = row.getItem();
                    System.out.println("Double click on: "+rowData.getImmatriculation());
                    //Ajouter l'ouverture de onglet avec le kardex de l'avion sélectionné
                    
                 // Load  kardex overview.
                    try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("kardex/kardex_overview.fxml"));
                    AnchorPane kardex_overview = (AnchorPane) loader.load();            
                    
                    mainApp.newTab("Kardex "+rowData.getImmatriculation(), kardex_overview);
                 } catch (IOException e) {
                    e.printStackTrace();
                }
                    
            }});
            return row ;
        });
    }
    
    @FXML
    private void handleNewAircraft() {
    	Aircraft tempaircraft = new Aircraft();
        boolean okClicked = showAircraftEditDialog(tempaircraft);
        if (okClicked) {
        	mainApp.getAircraftDao().creer(tempaircraft);
        	aircraftData.add(tempaircraft);
        }
    }
    
    @FXML
    private void handleDeleteAircraft() {
    	
    }
    
    @FXML
    private void handleEditAircraft() {
    	Aircraft selectedAircraft= aircraftTable.getSelectionModel().getSelectedItem();
        if (selectedAircraft != null) {
            boolean okClicked = showAircraftEditDialog(selectedAircraft);
            if (okClicked) {
            	mainApp.getAircraftDao().saveAircraft(selectedAircraft);
            	aircraftData=FXCollections.observableArrayList(mainApp.getAircraftDao().lister());
            	aircraftTable.refresh();	       	 
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Aircraft Selected");
            alert.setContentText("Please select a customer in the table.");

            alert.showAndWait();
        }
    }
    
    public boolean showAircraftEditDialog(Aircraft aircraft) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("aircraft/AircraftEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit aircraft");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AircraftEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAircraft(aircraft);
            controller.setUserList(mainApp.getUtilisateurDao().lister());
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

     // Add observable list data to the table
        aircraftTable.setItems(aircraftData);
    }

	public ObservableList<Aircraft> getAircraftData() {
		return aircraftData;
	}

	public void setAircraftData(ObservableList<Aircraft> aircraftData) {
		this.aircraftData = aircraftData;
	}


}
