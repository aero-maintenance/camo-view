package com.camo.aircraft;

import java.sql.Date;
import java.util.List;

import com.camo.entities.Aircraft;
import com.camo.entities.Utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class AircraftEditDialogController {
	
	private Stage dialogStage;
	private Aircraft aircraft;
	private boolean okClicked = false;
	    
	@FXML
	private TextField manufacturerField;
	@FXML
	private TextField modelField;
	@FXML
	private TextField immatriculationField;
	@FXML
	private TextField totalFHField;
	@FXML
	private TextField totalFCField;
	@FXML
    private TextField MSNField;
    @FXML
    private TextField statusField;
    @FXML
    private DatePicker dateKardexPicker;
    @FXML
    private ComboBox<Utilisateur> ownerBox;
    
    private List<Utilisateur> userList;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	ownerBox.setButtonCell(new SimpleOwnerListCell());
    	ownerBox.setCellFactory(listView -> new SimpleOwnerListCell());
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        
        ownerBox.getSelectionModel().select(aircraft.getProprietaire());
        manufacturerField.setText(aircraft.getConstructeur());
        modelField.setText(aircraft.getModele());
        immatriculationField.setText(aircraft.getImmatriculation());
        totalFHField.setText(aircraft.getTotal_FH()==null?null:Double.toString(aircraft.getTotal_FH()));
        totalFCField.setText(aircraft.getTotal_FC()==null?null:Long.toString(aircraft.getTotal_FC()));
        MSNField.setText(aircraft.getMsn()==null?null:Long.toString(aircraft.getMsn()));
        statusField.setText(aircraft.getStatut());
        dateKardexPicker.setValue(aircraft.getDate_Kardex()==null?null:aircraft.getDate_Kardex().toLocalDate());
    }
    
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    @FXML
    private void handleOk() {
    	if (isInputValid()) {
    		aircraft.setProprietaire(ownerBox.getSelectionModel().getSelectedItem());
    		aircraft.setConstructeur(manufacturerField.getText());
	        aircraft.setModele(modelField.getText());
	        aircraft.setMsn(Long.parseLong(MSNField.getText()));
	        aircraft.setImmatriculation(immatriculationField.getText());
	        aircraft.setTotal_FC(Long.parseLong(totalFCField.getText()));
	        aircraft.setTotal_FH(Double.parseDouble(totalFHField.getText()));
	        aircraft.setStatut(statusField.getText());
	        aircraft.setDate_Kardex(Date.valueOf(dateKardexPicker.getValue()));
	        
	        okClicked = true;
            dialogStage.close();
        }
    }
    
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (manufacturerField.getText() == null || manufacturerField.getText().length() == 0) {
            errorMessage += "No valid manufacturer!\n";
        }
        if (modelField.getText() == null || modelField.getText().length() == 0) {
            errorMessage += "No valid model!\n";
        }
        if (immatriculationField.getText() == null || immatriculationField.getText().length() == 0) {
            errorMessage += "No valid immatriculation!\n";
        }
        if (totalFCField.getText() == null || totalFCField.getText().length() == 0) {
            errorMessage += "No valid FC!\n";
        }
        if (totalFHField.getText() == null || totalFHField.getText().length() == 0) {
            errorMessage += "No valid FH!\n";
        }
        if (statusField.getText() == null || statusField.getText().length() == 0) {
            errorMessage += "No valid status!\n";
        }
       	                
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
	            alert.showAndWait();
	            return false;
        }
    }

	public List<Utilisateur> getUserList() {
		return userList;
	}

	public void setUserList(List<Utilisateur> userList) {
		this.userList = userList;
		for(Utilisateur user : userList) {
    		ownerBox.getItems().add(user);
    	}
	}
	
	public class SimpleOwnerListCell extends ListCell<Utilisateur> { 
		  
	    @Override 
	    protected void updateItem(Utilisateur item, boolean empty) { 
	        super.updateItem(item, empty); 
	        setText(null); 
	        if (!empty && item != null) { 
	            final String text = String.format("%s", item.getnom_aeroclub()); 
	            setText(text); 
	        } 
	    } 
	}
    
}
