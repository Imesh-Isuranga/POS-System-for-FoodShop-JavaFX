package lk.ijse.thogakade.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class orderController {
    public TableView tblOrder;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQTY;
    public TableColumn colTotal;
    public TableColumn colOption;
    public Label lblOrderId;
    public Label lblDate;
    public Label lblTime;
    public Label lblTotal;
    public AnchorPane orderContext;

    public void backToHomeonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) orderContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashBoardForm.fxml"))));
    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
    }

    public void txtId(ActionEvent actionEvent) {
    }

    public void txtName(ActionEvent actionEvent) {
    }

    public void txtAddress(ActionEvent actionEvent) {
    }

    public void txtSalary(ActionEvent actionEvent) {
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
    }

    public void txtCode(ActionEvent actionEvent) {
    }

    public void txtDescription(ActionEvent actionEvent) {
    }

    public void txtUnitPrice(ActionEvent actionEvent) {
    }

    public void txtQTYOnHand(ActionEvent actionEvent) {
    }

    public void txtQTY(ActionEvent actionEvent) {
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }
}
