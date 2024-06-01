package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class StartController implements Initializable {
    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
        // print test
        System.out.println("StartController initialized");
    }
}
