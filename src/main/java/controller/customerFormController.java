package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

public class customerFormController {

    public Pane contentArea;
    public BorderPane mainContainer;

    public void btnLoadDataOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        loadUI("/addCustomerForm.fxml");
    }


    public void btnDeleteCustomerOnClick(ActionEvent actionEvent) throws IOException {
        loadUI("/deleteCustomerForm.fxml");
    }

    public void btnUpdateCustomerOnClick(ActionEvent actionEvent) throws IOException {
        loadUI("/updateCustomerForm.fxml");
    }

    public void btnReloadTableOnClick(ActionEvent actionEvent) {
        loadUI("/viewCustomerForm.fxml");
    }


    private void loadUI(String fxmlFile) {
        try {
            Object node = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentArea.getChildren().setAll((Node) node); // Replaces the content area with the loaded UI
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
