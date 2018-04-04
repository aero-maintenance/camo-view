package com.camo.customer;


import java.io.IOException;

import com.camo.MainApp;
import com.camo.entities.Utilisateur;

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
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomerOverviewController {
	
	@FXML
	private TextField researshField;
	@FXML
    private TableView<Utilisateur> customerTable;
    @FXML
    private TableColumn<Utilisateur, String> NameColumn;
    @FXML
    private TableColumn<Utilisateur, String> AddressColumn;
    @FXML
    private TableColumn<Utilisateur, String> VilleColumn;
    @FXML
    private TableColumn<Utilisateur, String> CPColumn;
    @FXML
    private TableColumn<Utilisateur, String> EmailColumn;
    @FXML
    private TableColumn<Utilisateur, String> TelColumn;
    
    private ObservableList<Utilisateur> 	userData;
    
    private MainApp mainApp;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public CustomerOverviewController() {
    }
    
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        NameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getnom_aeroclub()));
        AddressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse()));
        VilleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVille()));
        CPColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode_postale()));
        EmailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        TelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelephone()));
        
        
        //Recherche automatique
        researshField.textProperty().addListener((obs, oldText, newText) -> {
            
            if(newText.length()==0) {
            	//System.out.println("Text changed from "+oldText+" to "+newText);
            	userData=FXCollections.observableArrayList(mainApp.getUtilisateurDao().lister());
            	customerTable.getItems().clear();
            	customerTable.setItems(userData);
            	customerTable.refresh();
            }else {
            	//System.out.println("Text changed from "+oldText+" to "+newText);
            	userData=FXCollections.observableArrayList(mainApp.getUtilisateurDao().rechercher(newText));
            	customerTable.getItems().clear();
            	customerTable.setItems(userData);
            	customerTable.refresh();
            }
        });
        
        
        customerTable.setRowFactory(tv -> {
            TableRow<Utilisateur> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Utilisateur rowData = row.getItem();
                    System.out.println("Double click on: "+rowData.getnom_aeroclub());
                    //Ajouter l'ouverture de onglet avec la liste des avions de cet utilisateur
                }
            });
            return row ;
        });
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewUser() {
        Utilisateur tempUser = new Utilisateur();
        boolean okClicked = showCustomerEditDialog(tempUser);
        if (okClicked) {
        	mainApp.getUtilisateurDao().creer(tempUser);
        	userData.add(tempUser);
        }
    }
    
    @FXML
    private void handleEditUser() {
        Utilisateur selectedUser = customerTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            boolean okClicked = showCustomerEditDialog(selectedUser);
            if (okClicked) {
            	 mainApp.getUtilisateurDao().saveUtilisateur(selectedUser);
            	 userData=FXCollections.observableArrayList(mainApp.getUtilisateurDao().lister());
            	 customerTable.refresh();
            	       	 
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No customer Selected");
            alert.setContentText("Please select a customer in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();
        Utilisateur selectedUser = customerTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
        	Alert alert = new Alert(AlertType.CONFIRMATION, "Supprimer l'utilisateur ?", ButtonType.YES,  ButtonType.CANCEL);
        	alert.showAndWait();

        	if (alert.getResult() == ButtonType.YES) {
        		customerTable.getItems().remove(selectedIndex);
                mainApp.getUtilisateurDao().supprimer(selectedUser);
                customerTable.refresh();
                customerTable.getSelectionModel().clearSelection();
        	}
              
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No customer Selected");
            alert.setContentText("Please select a customer in the table.");

            alert.showAndWait();
        }
    }
    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

     // Add observable list data to the table
        customerTable.setItems(userData);
    }
    
    
    public boolean showCustomerEditDialog(Utilisateur user) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("customer/CustomerEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CustomerEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    public ObservableList<Utilisateur> getUserData() {
		return userData;
	}
	
	public void setUserData(ObservableList<Utilisateur> userData) {
		this.userData = userData;
	}


}
