package controller;

import com.jfoenix.controls.JFXTextField;
import dbc.DBConnection;
import interfacePackage.myClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updateCustomerFormController extends myClass {

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnSearchCustomerOnClick(ActionEvent event) throws SQLException {
        Customer customer = searchCustomer(txtId.getText());
        if(customer==null)return;
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(customer.getSalary()+"");
    }


    public void btnUpdateCustomerOnClick(ActionEvent actionEvent) throws SQLException {
        Connection dbc = DBConnection.getInstance().getConnection();
        Customer customer = searchCustomer(txtId.getText());
        String a = txtName.getText();
        String b = txtAddress.getText();
        String c = txtId.getText();
        double d = Double.parseDouble(txtSalary.getText());
        if(customer==null)return;
        String id = customer.getId();
        String stmt ="UPDATE customer SET name = ? , id = ?, address = ? , salary = ? WHERE id='"+id+"';";
        PreparedStatement preparedStatement = dbc.prepareStatement(stmt);
        preparedStatement.setString(1, a);
        preparedStatement.setString(2, c);
        preparedStatement.setString(3, b);
        preparedStatement.setDouble(4, d);
        boolean resultSet = preparedStatement.executeUpdate()>0;
        if(resultSet) System.out.println("customer updated");
        emptyFlds();
    }
    public void emptyFlds(){
        txtSalary.setText("");
        txtAddress.setText("");
        txtId.setText("");
        txtName.setText("");
    }
}
