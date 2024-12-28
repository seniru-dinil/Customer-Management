package controller;

import dbc.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class customerFormController implements Initializable {

    @FXML
    private TableView<Customer> tbl;

    @FXML
    private TableColumn<?, ?> txtAddress;

    @FXML
    private TableColumn<?, ?> txtId;

    @FXML
    private TableColumn<?, ?> txtName;

    @FXML
    private TableColumn<?, ?> txtSalary;

    public void loadTable() throws SQLException {
        Connection dbc = DBConnection.getInstance().getConnection();
        String stm = "SELECT * FROM customer";
        PreparedStatement preparedStatement = dbc.prepareStatement(stm);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        txtId.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtName.setCellValueFactory(new PropertyValueFactory<>("name"));
        txtAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        txtSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        while (resultSet.next()) {
            observableList.add(new Customer(resultSet.getString(2), resultSet.getString(1), resultSet.getString(3), resultSet.getDouble(4)));
        }
        tbl.setItems(observableList);
    }

    public void btnLoadDataOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/addCustomerForm.fxml"))));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteCustomerOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/deleteCustomerForm.fxml"))));
        stage.show();
    }

    public void btnUpdateCustomerOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/updateCustomerForm.fxml"))));
        stage.show();
    }

    public void btnReloadTableOnClick(ActionEvent actionEvent) {
        try {
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
