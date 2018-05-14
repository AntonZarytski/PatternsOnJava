package com.zaritsky.controllers;

import com.zaritsky.models.CollectionAddressBook;
import com.zaritsky.models.Person;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;


public class MainController {

    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tableAddressBook;
    @FXML
    private TableColumn<Person, String> columnFIO;
    @FXML
    private TableColumn<Person, String> columnPhone;
    @FXML
    private Label labelCount;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;

    @FXML
    private void initialize() {
        columnFIO.setCellValueFactory(new PropertyValueFactory<>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        addressBookImpl.getPersonList().addListener((ListChangeListener<Person>) c -> updateCountLabel());

        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());

        try {
            fxmlLoader.setLocation(getClass().getResource("../views/edit.views"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: " + addressBookImpl.getPersonList().size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (!(source instanceof Button)) {
            return;
        }
        Button clickedButton = (Button) source;
        Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editDialogController.setPerson(selectedPerson);

        switch (clickedButton.getId()) {
            case "btnAdd":
                showDialog(parentWindow);
                break;
            case "btnEdit":
                showDialog(parentWindow);
                break;
            case "btnDelete":
                break;
        }
    }

    private void showDialog(Window parentWindow) {

        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(parentWindow);
        }
//      editDialogStage.showAndWait(); // для ожидания закрытия окна
        editDialogStage.show();

    }


}
