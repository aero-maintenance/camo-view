package com.camo.root;

import java.io.IOException;

import com.camo.MainApp;
import com.camo.aircraft.AircraftOverviewController;
import com.camo.alert.AlertOverviewController;
import com.camo.customer.CustomerOverviewController;
import com.camo.entities.Alerte;
import com.camo.flight.FlightOverviewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class RootLayoutController {

	// Reference to the main application.
    private MainApp mainApp;
    
    public RootLayoutController() {
    	
    }

    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    }
    
    @FXML
    private void handleCustomerManagement() {
    	try {
    		
    		/**
    		 * TODO : récupérer la liste des clients dans la base de donnée
    		 */
            // Load customer overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("customer/CustomerOverview.fxml"));
            AnchorPane CustomerOverview = (AnchorPane) loader.load();            
            
            mainApp.newTab("Liste des clients", CustomerOverview);

            // Give the controller access to the main app.
            CustomerOverviewController controller = loader.getController();
            controller.setUserData(FXCollections.observableArrayList(mainApp.getUtilisateurDao().lister()));
            controller.setMainApp(mainApp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleAircraftManagement() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("aircraft/AircraftOverview.fxml"));
            AnchorPane CustomerOverview = (AnchorPane) loader.load();            
            
            mainApp.newTab("Liste des aéronefs", CustomerOverview);
            
            AircraftOverviewController controller = loader.getController();
            controller.setAircraftData(FXCollections.observableArrayList(mainApp.getAircraftDao().lister()));
            controller.setMainApp(mainApp);
    	} catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    /*@FXML
    private void handleStaffManagement() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("customer/CustomerOverview.fxml"));
            AnchorPane CustomerOverview = (AnchorPane) loader.load();            
            
            mainApp.newTab("Liste des clients", CustomerOverview);
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    @FXML
    private void handleFlightList() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("flight/FlightOverview.fxml"));
            AnchorPane CustomerOverview = (AnchorPane) loader.load();            
            
            mainApp.newTab("Liste des vols", CustomerOverview);
            
            FlightOverviewController controller = loader.getController();
            controller.setFlightsData(FXCollections.observableArrayList(mainApp.getVolDao().lister()));
            controller.setMainApp(mainApp);
            
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*@FXML
    private void handleStockManagement() {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("customer/CustomerOverview.fxml"));
        AnchorPane CustomerOverview = (AnchorPane) loader.load();            
        
        mainApp.newTab("Liste des clients", CustomerOverview);
    }*/
    
    @FXML
    private void handleAlertManagement() {
    	
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("alert/AlertOverview.fxml"));
	        AnchorPane AlertOverview;
			AlertOverview = (AnchorPane) loader.load();
			
			mainApp.newTab("Alertes", AlertOverview);
			AlertOverviewController controller = loader.getController();
			ObservableList<Alerte> alertData = FXCollections.observableArrayList();
			alertData.add(new Alerte("F-AAWY","Cessna 172 R","Visite 50H","6H"));
			alertData.add(new Alerte("F-AAWY","Cessna 172 R","Visite 100H","6H"));
			alertData.add(new Alerte("F-AAWY","Cessna 172 R","OVH helice","71H"));
			alertData.add(new Alerte("F-AAWY","Cessna 172 R","AD 2007-05-10","5J"));
			controller.setAlertData(alertData);
			controller.setMainApp(mainApp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}            
        
        
    }
    
    
}
