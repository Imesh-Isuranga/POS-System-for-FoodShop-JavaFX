package lk.ijse.thogakade.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class itemFormController {
    public TableView tblItem;
    public AnchorPane itemContext;

    public void backToHomeonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) itemContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashBoardForm.fxml"))));
    }

    public void newItemOnAction(ActionEvent actionEvent) {
    }

    public void txtCode(ActionEvent actionEvent) {
    }

    public void txtDescription(ActionEvent actionEvent) {
    }

    public void txtUnitPrice(ActionEvent actionEvent) {
    }

    public void txtQTYOnHand(ActionEvent actionEvent) {
    }

    public void txtSearchHere(ActionEvent actionEvent) {
    }

    public void saveItemOnAction(ActionEvent actionEvent) {
    }
}
