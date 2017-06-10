package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Random;

public class Controller {

    @FXML
    Button runButton;

    @FXML
    private void initialize() {
        runButton.setOnMouseEntered(event -> changeThePositionOfButton());
    }

    private void changeThePositionOfButton() {
        Random random = new Random();
        runButton.setTranslateX(random.nextInt(600));
        runButton.setTranslateY(random.nextInt(450));
    }
}
