package jpokebattle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class JPokeBattle extends Application {
	
	private static Stage stage = null;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	stage = primaryStage;

        stage.setTitle("PokeBattle - Lorenzo Cipolletti");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("resources/sprites/pokemons/3.png")));
		stage.setResizable(false);
        
        setView("start");
    }
    
    public void setView(String view) throws IOException
    {
    	Parent root = FXMLLoader.load(getClass().getResource("view/" + view + ".fxml"));
    	Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("resources/styles.css").toExternalForm());
    	
    	stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}