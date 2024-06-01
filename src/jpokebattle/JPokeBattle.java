package jpokebattle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.IOException;

public class JPokeBattle extends Application {
	
	private static Stage stage = null;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

    private static JPokeBattle instance = null;

    public static JPokeBattle getInstance() {
        return instance;
    }
    public JPokeBattle() {
        super();
        instance = this;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	stage = primaryStage;

        stage.setTitle("PokeBattle - Lorenzo Cipolletti");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("resources/sprites/pokemons/3.png")));
		stage.setResizable(false);
        
        setView("start");
    }
    
    public void setView(String view)
    {
    	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/" + view + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            root = new StackPane();
        }

    	Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("resources/styles.css").toExternalForm());
    	
    	stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return stage.getScene();
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}