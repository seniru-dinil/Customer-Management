package controller;

import dbc.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class viewCustomerFormController implements Initializable {

    public TableView tbl;
    @FXML
    private TableColumn<?, ?> tblAddress;

    @FXML
    private TableColumn<?, ?> tblId;

    @FXML
    private TableColumn<?, ?> tblName;

    @FXML
    private TableColumn<?, ?> tblSalary;

    public void loadTable() throws SQLException {
        Connection dbc = DBConnection.getInstance().getConnection();
        String stm = "SELECT * FROM customer";
        PreparedStatement preparedStatement = dbc.prepareStatement(stm);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        while (resultSet.next()) {
            observableList.add(new Customer(resultSet.getString(2), resultSet.getString(1), resultSet.getString(3), resultSet.getDouble(4)));
        }
        tbl.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
