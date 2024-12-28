package interfacePackage;

import dbc.DBConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class myClass {
    public Customer searchCustomer(String id) throws SQLException {
        Connection dbc = DBConnection.getInstance().getConnection();
        String stm = "SELECT * FROM customer";
        PreparedStatement preparedStatement = dbc.prepareStatement(stm);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String custId = resultSet.getString(1);
            if (custId.equals(id)) {
                return new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4));
            }
        }
        return
        null;
    }
}
