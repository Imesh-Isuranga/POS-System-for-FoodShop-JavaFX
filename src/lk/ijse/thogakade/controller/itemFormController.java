package lk.ijse.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.thogakade.DataBaseAccessCode;
import lk.ijse.thogakade.dto.itemDTO;
import lk.ijse.thogakade.view.TM.itemTM;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class itemFormController {
    public TableView tblItem;
    public AnchorPane itemContext;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQTYOnHand;
    public TextField txtSearchHere;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQTYOnHand;
    public TableColumn colOption;
    public JFXButton btnSaveItem;

    public void initialize(){

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("decription"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTYOnHand.setCellValueFactory(new PropertyValueFactory<>("QTYOnHand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllItems("");

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setData((itemTM) newValue);
            }
        });

        txtSearchHere.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllItems(newValue);
        });
    }

    private void setData(itemTM newValue) {
        btnSaveItem.setText("Update Item");
        txtCode.setText(newValue.getCode());
        txtDescription.setText(newValue.getDescription());
        txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
        txtQTYOnHand.setText(String.valueOf(newValue.getQTYOnHand()));
    }

    private void loadAllItems(String text) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Item WHERE code LIKE ? OR description LIKE ? OR unitPrice LIKE ? OR QTYOnHand LIKE ?");
            stm.setObject(1,"%"+text+"%");
            stm.setObject(2,"%"+text+"%");
            stm.setObject(3,"%"+text+"%");
            stm.setObject(4,"%"+text+"%");
            ResultSet rst = stm.executeQuery();

            ObservableList<itemTM> itemTMList = FXCollections.observableArrayList();

            for (itemDTO itemDTO:new DataBaseAccessCode().getAllItem("%"+text+"%")
                 ) {
                Button btn = new Button("Delete");
                itemTM itemTM = new itemTM(
                        itemDTO.getCode(),
                        itemDTO.getDescription(),
                        itemDTO.getUnitPrice(),
                        itemDTO.getQTYOnHand(),
                        btn
                );

                itemTMList.add(itemTM);

                btn.setOnAction(event -> {
                    try {
                        Alert comAlert1 = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure",ButtonType.YES,ButtonType.CANCEL);
                        Optional<ButtonType> result = comAlert1.showAndWait();
                        if(result.get().equals(ButtonType.YES)){
                            if(new DataBaseAccessCode().deleteItem(itemTM.getCode())){
                                Alert comAlert2 = new Alert(Alert.AlertType.CONFIRMATION,"Item Deleted",ButtonType.OK);
                                comAlert2.show();
                                loadAllItems("");
                            }else{
                                Alert comAlert3 = new Alert(Alert.AlertType.WARNING,"Something went wrong",ButtonType.CANCEL);
                                comAlert3.show();
                            }
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
            tblItem.setItems(itemTMList);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void backToHomeonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) itemContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashBoardForm.fxml"))));
    }

    public void newItemOnAction(ActionEvent actionEvent) {
        btnSaveItem.setText("Save Item");
        clearFields();
    }

    private void clearFields() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQTYOnHand.clear();
    }


    public void saveItemOnAction(ActionEvent actionEvent) {
        try {
            if(btnSaveItem.getText().equalsIgnoreCase("Save Item")){
                if(new DataBaseAccessCode().saveItem(new itemDTO(txtCode.getText(),txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQTYOnHand.getText())))){
                    Alert comAlert = new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved", ButtonType.OK);
                    comAlert.show();
                    loadAllItems("");
                }else{
                    Alert comAlert = new Alert(Alert.AlertType.WARNING,"Something went wrong", ButtonType.CANCEL);
                    comAlert.show();
                }
            }else{
                if(new DataBaseAccessCode().updateItem(new itemDTO(txtCode.getText(),txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQTYOnHand.getText())))){
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated", ButtonType.OK);
                    alert1.show();
                    loadAllItems("");
                }else{
                    Alert alert2 = new Alert(Alert.AlertType.WARNING,"Something went wrong", ButtonType.CANCEL);
                    alert2.show();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
