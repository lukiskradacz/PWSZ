package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    Button editButton;
    @FXML
    TextField nameText;
    @FXML
    TextField numberText;
    @FXML
    TableView<Person> tableView;

    private ObservableList<Person> data;

    @FXML
    private void initialize() {
        addButton.setOnMouseClicked(event -> {
            data.add(new Person(nameText.getText(), numberText.getText()));
        });
        deleteButton.setOnMouseClicked(event -> {
            data.remove(tableView.getSelectionModel().getSelectedItem());
        });
        editButton.setOnMouseClicked(event -> {
            Person person = tableView.getSelectionModel().getSelectedItem();
            nameText.setText(person.getName());
            numberText.setText(person.getPhone());
            data.remove(person);
        });

        tableView.setEditable(true);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));

        TableColumn phoneCol = new TableColumn("Phone");
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Phone")
        );

        data = FXCollections.observableArrayList();
        tableView.setItems(data);

        tableView.getColumns().addAll(nameCol, phoneCol);

    }
}
