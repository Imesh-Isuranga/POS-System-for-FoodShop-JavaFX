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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.thogakade.view.TM.customerTM;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class customerFormController {
    public AnchorPane customerContext;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXButton saveCustomerbtn;
    public TextField txtSearch;

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllCustomers("");

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setData((customerTM) newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllCustomers(newValue);
        });
    }

    private void setData(customerTM newValue) {
        saveCustomerbtn.setText("Update Customer");
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
    }


    private void loadAllCustomers(String searchText) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Customer WHERE id LIKE ? OR name LIKE ? OR address LIKE ?");
            stm.setObject(1,"%"+searchText+"%");
            stm.setObject(2,"%"+searchText+"%");
            stm.setObject(3,"%"+searchText+"%");
            ResultSet rst = stm.executeQuery();

            ObservableList<customerTM> customerObList = FXCollections.observableArrayList();
            while(rst.next()){
                Button btn = new Button("Delete");
                customerTM customerTM = new customerTM(
                                rst.getString("id"),
                                rst.getString("name"),
                                rst.getString("address"),
                                Double.parseDouble(rst.getString("salary")),
                                btn
                );

                customerObList.add(customerTM);

                btn.setOnAction(event -> {
                    try {
                        Alert comfirmation = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure?",ButtonType.YES,ButtonType.CANCEL);
                        Optional<ButtonType> result = comfirmation.showAndWait();
                        if(result.get().equals(ButtonType.YES)){
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
                            PreparedStatement stm1 = connection1.prepareStatement("DELETE FROM Customer WHERE id = ?");
                            stm1.setObject(1,customerTM.getId());
                            boolean isDeleted = stm1.executeUpdate() > 0;
                            if(isDeleted){
                                Alert comAlert1 = new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted",ButtonType.OK);
                                comAlert1.show();
                                loadAllCustomers("");
                            }else{
                                Alert comAlert2 = new Alert(Alert.AlertType.WARNING,"Something went wrong",ButtonType.CANCEL);
                                comAlert2.show();
                            }
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
            tblCustomer.setItems(customerObList);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void backToHomeonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) customerContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashBoardForm.fxml"))));
    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        saveCustomerbtn.setText("Save Customer");
        clearFields();
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSearch.clear();
    }

    public void saveCustomerOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        if(saveCustomerbtn.getText().equalsIgnoreCase("Save Customer")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Customer values(?,?,?,?)");
            stm.setObject(1, txtId.getText());
            stm.setObject(2, txtName.getText());
            stm.setObject(3, txtAddress.getText());
            stm.setObject(4, txtSalary.getText());
            boolean isSaved = stm.executeUpdate() > 0;
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved", ButtonType.OK).show();
                loadAllCustomers("");
            } else {
                Alert comAlert = new Alert(Alert.AlertType.WARNING, "Something went wrong", ButtonType.CANCEL);
                comAlert.show();
            }
        }else{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
            PreparedStatement stm = connection.prepareStatement("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?");
            stm.setObject(4, txtId.getText());
            stm.setObject(1, txtName.getText());
            stm.setObject(2, txtAddress.getText());
            stm.setObject(3, Double.parseDouble(txtSalary.getText()));
            boolean isUpdated = stm.executeUpdate() > 0;
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated", ButtonType.OK).show();
                loadAllCustomers("");
            } else {
                Alert comAlert = new Alert(Alert.AlertType.WARNING, "Something went wrong", ButtonType.CANCEL);
                comAlert.show();
            }
        }
    }
}
