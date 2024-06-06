package jpokebattle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main class of the JPokeBattle application.
 * This class extends the JavaFX Application class and serves as the entry point for the application.
 */
public class JPokeBattle extends Application {
	
	private static Stage stage = null;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

    private static JPokeBattle instance = null;

    /**
     * Returns the instance of the JPokeBattle class.
     * @return The instance of the JPokeBattle class.
     */
    public static JPokeBattle getInstance() {
        return instance;
    }

    /**
     * Constructs a new JPokeBattle object.
     * Sets the instance variable to the current instance of JPokeBattle.
     */
    public JPokeBattle() {
        super();
        instance = this;
    }

    /**
     * The entry point for the application.
     * Initializes the primary stage, sets the title, icon, and view, and shows the stage.
     * @param primaryStage The primary stage of the application.
     * @throws Exception if an error occurs during the initialization of the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
    	stage = primaryStage;

        stage.setTitle("PokeBattle - Lorenzo Cipolletti");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("resources/sprites/pokemons/3.png")));
		stage.setResizable(false);
        
        setView("start");
    }
    
    /**
     * Sets the view of the application.
     * Loads the FXML file for the specified view and sets it as the root of the scene.
     * @param view The name of the view to be set.
     */
    public void setView(String view)
    {
    	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/" + view + ".fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    	Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("resources/styles.css").toExternalForm());
    	
    	stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns the scene of the application.
     * @return The scene of the application.
     */
    public Scene getScene() {
        return stage.getScene();
    }

    /**
     * Returns the stage of the application.
     * @return The stage of the application.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * The main method of the application.
     * Launches the JavaFX application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}