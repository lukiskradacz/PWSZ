package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    RadioButton celciusFrom;
    @FXML
    RadioButton fahrenheitFrom;
    @FXML
    RadioButton kelvinFrom;
    @FXML
    RadioButton celciusTo;
    @FXML
    RadioButton fahrenheitTo;
    @FXML
    RadioButton kelvinTo;
    @FXML
    TextField output;
    @FXML
    TextField valueTextField;

    @FXML
private void initialize() {
        selectFirstValue();
        initActions();
    }

private void selectFirstValue() {
        celciusTo.setSelected(true);
        celciusFrom.setSelected(true);

        valueTextField.setText("0");
        output.setText("0");
    }

private void initActions() {
        valueTextField.setOnKeyReleased(event -> {
            convertData();
        });

        celciusTo.setOnMouseClicked(event -> convertData());
        celciusFrom.setOnMouseClicked(event -> convertData());
        kelvinTo.setOnMouseClicked(event -> convertData());
        kelvinFrom.setOnMouseClicked(event -> convertData());
        fahrenheitTo.setOnMouseClicked(event -> convertData());
        fahrenheitFrom.setOnMouseClicked(event -> convertData());
        
    }

private void convertData() {
        try {
            double degreeFrom = Double.valueOf(valueTextField.getText());
            double degreeTo = 0;

            if ((celciusFrom.isSelected() && celciusTo.isSelected()) ||
                    (kelvinFrom.isSelected() && kelvinTo.isSelected()) ||
                    (fahrenheitFrom.isSelected() && fahrenheitTo.isSelected())) {
                output.setText(String.valueOf(degreeFrom));
            } else {

                if (celciusFrom.isSelected() && kelvinTo.isSelected()) {
                    degreeTo = degreeFrom + 273.15;
                } else if (celciusFrom.isSelected() && fahrenheitTo.isSelected()) {
                    degreeTo = (degreeFrom * (9 / 5)) + 32;
                } else if (kelvinFrom.isSelected() && celciusTo.isSelected()) {
                    degreeTo = degreeFrom - 273.15;
                } else if (kelvinFrom.isSelected() && fahrenheitTo.isSelected()) {
                    degreeTo = (degreeFrom * (9 / 5)) - 459.67;
                } else if (fahrenheitFrom.isSelected() && celciusTo.isSelected()) {
                    degreeTo = (degreeFrom - 32) / 1.8;
                } else if (fahrenheitFrom.isSelected() && kelvinTo.isSelected()) {
                    degreeTo = (degreeFrom + 459.67) / 1.8;
                }

                output.setText(String.valueOf(degreeTo));
            }
        } catch (Exception ex) {
            output.setText("Nieprawidlowe dane!");
        }
    }

}
