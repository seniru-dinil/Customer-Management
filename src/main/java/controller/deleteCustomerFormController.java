package controller;

import com.jfoenix.controls.JFXTextField;
import dbc.DBConnection;
import interfacePackage.myClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class deleteCustomerFormController extends myClass {

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnDeleteCustomerOnClick(ActionEvent event) throws SQLException {
        Connection dbc = DBConnection.getInstance().getConnection();
        Customer customer = searchCustomer(txtId.getText());
        if(customer==null)return;
        String id = customer.getId();
        String stmt ="DELETE FROM customer WHERE id ='"+id+"';";
        PreparedStatement preparedStatement = dbc.prepareStatement(stmt);
        boolean resultSet = preparedStatement.executeUpdate()>0;
        if(resultSet) System.out.println("customer deleted");
        emptyFlds();
    }

    public void emptyFlds(){
        txtSalary.setText("");
        txtAddress.setText("");
        txtId.setText("");
        txtName.setText("");
    }

    @FXML
    void btnSearchCustomerOnClick(ActionEvent event) throws SQLException {
        Customer customer = searchCustomer(txtId.getText());
        if(customer==null)return;
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(customer.getSalary()+"");
    }

    

}
