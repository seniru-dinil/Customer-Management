package controller;

import com.jfoenix.controls.JFXTextField;
import dbc.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addCustomerFormController {

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddCustomerOnClick(ActionEvent event) throws SQLException {
        Connection dbc = DBConnection.getInstance().getConnection();
        String stm = "INSERT INTO CUSTOMER VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = dbc.prepareStatement(stm);
        preparedStatement.setString(1, txtId.getText());
        preparedStatement.setString(2, txtName.getText());
        preparedStatement.setString(3, txtAddress.getText());
        preparedStatement.setString(4, String.valueOf(Double.parseDouble(txtSalary.getText())));
        boolean rowsInserted = preparedStatement.executeUpdate()>0;
        if(rowsInserted) System.out.println("customer inserted successfully");
        emptyFlds();
    }

    public void emptyFlds(){
        txtSalary.setText("");
        txtAddress.setText("");
        txtId.setText("");
        txtName.setText("");
    }

}
