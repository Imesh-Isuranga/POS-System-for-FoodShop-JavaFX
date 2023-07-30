package lk.ijse.thogakade.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class dashBoardFormController {
    public AnchorPane dashBoardContext;

    public void itemsOnClick(MouseEvent mouseEvent) throws IOException {
        setUi("item");
    }

    public void customerOnClick(MouseEvent mouseEvent) throws IOException {
        setUi("customer");
    }

    public void orderOnClick(MouseEvent mouseEvent) throws IOException {
        setUi("order");
    }

    public void orderDetailsOnClick(MouseEvent mouseEvent) throws IOException {
        setUi("orderDetail");
    }

    private void setUi(String text) throws IOException {
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+text+"Form.fxml"))));
    }
}
