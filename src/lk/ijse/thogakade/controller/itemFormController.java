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
            while(rst.next()){
                Button btn = new Button("Delete");
                itemTM itemTM = new itemTM(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getDouble(3),
                        rst.getInt(4),
                        btn
                );

                itemTMList.add(itemTM);

                btn.setOnAction(event -> {
                    try {
                        Alert comAlert1 = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure",ButtonType.YES,ButtonType.CANCEL);
                        Optional<ButtonType> result = comAlert1.showAndWait();
                        if(result.get().equals(ButtonType.YES)){
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
                            PreparedStatement stm1 = connection1.prepareStatement("DELETE FROM Item WHERE code = ?");
                            stm1.setObject(1,itemTM.getCode());
                            boolean isDeleted = stm1.executeUpdate() > 0;
                            if(isDeleted){
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
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
                PreparedStatement stm = connection.prepareStatement("INSERT INTO Item VALUES(?,?,?,?)");
                stm.setObject(1,txtCode.getText());
                stm.setObject(2,txtDescription.getText());
                stm.setObject(3,Double.parseDouble(txtUnitPrice.getText()));
                stm.setObject(4,Integer.parseInt(txtQTYOnHand.getText()));
                boolean isSaved = stm.executeUpdate() > 0;
                if(isSaved){
                    Alert comAlert = new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved", ButtonType.OK);
                    comAlert.show();
                    loadAllItems("");
                }else{
                    Alert comAlert = new Alert(Alert.AlertType.WARNING,"Something went wrong", ButtonType.CANCEL);
                    comAlert.show();
                }
            }else{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
                PreparedStatement stm = connection.prepareStatement("UPDATE Item SET description=?,unitPrice=?,QTYOnHand=? WHERE Code=?");
                stm.setObject(4,txtCode.getText());
                stm.setObject(1,txtDescription.getText());
                stm.setObject(2,Double.parseDouble(txtUnitPrice.getText()));
                stm.setObject(3,Integer.parseInt(txtQTYOnHand.getText()));
                boolean isUpdated = stm.executeUpdate() > 0;
                if(isUpdated){
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
