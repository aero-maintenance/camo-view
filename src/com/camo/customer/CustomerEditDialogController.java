package com.camo.customer;

import com.camo.entities.Utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
	
	public class CustomerEditDialogController {
		@FXML
	    private TextField nomField;
	    @FXML
	    private TextField adresseField;
	    @FXML
	    private TextField villeField;
	    @FXML
	    private TextField postalCodeField;
	    @FXML
	    private TextField emailField;
	    @FXML
	    private TextField passwordField;
	    @FXML
	    private TextField telField;
	
	
	    private Stage dialogStage;
	    private Utilisateur user;
	    private boolean okClicked = false;
	
	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    }
	    
	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     *
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }
	
	    /**
	     * Sets the stage of this dialog.
	     *
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	    
	    @FXML
	    private void handleOk() {
	    	if (isInputValid()) {
	            user.setnom_aeroclub(nomField.getText());
	            user.setAdresse(adresseField.getText());
	            user.setVille(villeField.getText());
	            user.setCode_postale(postalCodeField.getText());
	            user.setEmail(emailField.getText());
	            user.setTelephone(telField.getText());
	            user.setPassword(passwordField.getText());

	            okClicked = true;
	            dialogStage.close();
	        }
	    }

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }
	    
	    public void setUser(Utilisateur user) {
	        this.user = user;

	        nomField.setText(user.getnom_aeroclub());
	        adresseField.setText(user.getAdresse());
	        villeField.setText(user.getVille());
	        postalCodeField.setText(user.getCode_postale());
	        emailField.setText(user.getEmail());
	        passwordField.setText(user.getPassword());
	        telField.setText(user.getTelephone());
	    }
	    
	    private boolean isInputValid() {
	        String errorMessage = "";
	        
	        if (nomField.getText() == null || nomField.getText().length() == 0) {
	            errorMessage += "No valid name!\n";
	        }
	        if (adresseField.getText() == null || adresseField.getText().length() == 0) {
	            errorMessage += "No valid address!\n";
	        }
	        if (villeField.getText() == null || villeField.getText().length() == 0) {
	            errorMessage += "No valid city!\n";
	        }
	        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
	            errorMessage += "No valid postal code!\n";
	        }
	        if (emailField.getText() == null || emailField.getText().length() == 0) {
	            errorMessage += "No valid email!\n";
	        }
	        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
	            errorMessage += "No valid password!\n";
	        }
	        if (telField.getText() == null || telField.getText().length() == 0) {
	            errorMessage += "No valid telephone!\n";
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

}
