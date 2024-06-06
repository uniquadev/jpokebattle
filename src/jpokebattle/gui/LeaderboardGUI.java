package jpokebattle.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import jpokebattle.model.PlayerScore;

public class LeaderboardGUI extends VBox {
    public LeaderboardGUI() {
        super();
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        setAlignment(Pos.TOP_LEFT);
        setSpacing(4);

        Label scoreLabel = new Label("CLASSIFICA:");
        scoreLabel.setAlignment(Pos.BOTTOM_LEFT);
        scoreLabel.setId("scoreLabel");

        // score table TODO: make it a custom class
        TableView<PlayerScore> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

         // Define columns
        TableColumn<PlayerScore, String> nickNameCol = new TableColumn<>("Nickname");
        nickNameCol.setReorderable(false);
        nickNameCol.setSortable(false);
        nickNameCol.setCellValueFactory(new PropertyValueFactory<>("nickname"));

        TableColumn<PlayerScore, Integer> scoreCol = new TableColumn<>("Score");
        scoreCol.setReorderable(false);
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Add columns to the table
        tableView.getColumns().addAll(nickNameCol, scoreCol);

        // Add data to the table
        tableView.getItems().addAll(
                new PlayerScore("John", 30),
                new PlayerScore("Jane", 25),
                new PlayerScore("Michael", 35)
        );

        getChildren().addAll(scoreLabel, tableView);
    }
}
