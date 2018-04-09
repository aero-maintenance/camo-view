package com.camo;

import java.io.IOException;

import javax.persistence.EntityManager;

import com.camo.root.RootLayoutController;
import com.camo.dao.AircraftDAO;
import com.camo.dao.EntityManagerUtil;
import com.camo.dao.UtilisateurDAO;
import com.camo.dao.VolDAO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private EntityManager 					em = EntityManagerUtil.getEntityManager();
	//private ObservableList<Utilisateur> 	userData;// = FXCollections.observableArrayList();
    private static Stage 					primaryStage;
    private BorderPane 				rootLayout;
    private TabPane 				tabPane;
    
    private UtilisateurDAO utilisateurDao = new UtilisateurDAO(em);
    private AircraftDAO aircraftDao = new AircraftDAO(em);
    private VolDAO volDao = new VolDAO(em);
    
    /**
     * The data as an observable list of Persons.
     */
    //private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
    	
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    /*public ObservableList<Person> getPersonData() {
        return personData;
    }*/

    @Override
    public void start(Stage primaryStage) {
    	    	
        
        
        
        
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("Softw'Air Solution");
        
        

        // Set the application icon.
        MainApp.primaryStage.getIcons().add(new Image("file:resources/images/if_plane_40952.png"));

        initRootLayout();
        
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("root/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            SplitPane splitPane= (SplitPane) rootLayout.getCenter();
            tabPane = (TabPane) splitPane.getItems().get(1);
            
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newTab (String name, Node value) {
    	boolean tmp=true;
    	Tab tab = new Tab();
    	//Test si l'onglet existe déjà
    	for (Tab tb : tabPane.getTabs()){
    		if(tb.getText().equals(name)) {
    			tmp=false;
    			tab=tb;
    		}
    			
    	}
    	//si l'onglet n'existe alors il est cree
    	if(tmp) {
            tab.setText(name);
            tab.setContent(value);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().selectLast();
    	} else {
    		tabPane.getSelectionModel().select(tab);
    	}
    }
    


    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public UtilisateurDAO getUtilisateurDao() {
    	return utilisateurDao;
    }
    
    public EntityManager getEntityManager() {
		return em;
	}

    public static void main(String[] args) {
        launch(args);
    }

	public AircraftDAO getAircraftDao() {
		return aircraftDao;
	}

	public VolDAO getVolDao() {
		return volDao;
	}

	public void setVolDao(VolDAO volDao) {
		this.volDao = volDao;
	}
}