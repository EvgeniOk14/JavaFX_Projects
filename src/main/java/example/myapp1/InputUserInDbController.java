package example.myapp1;


import java.net.URL;
import java.util.ResourceBundle;

import example.myapp1.service.DatebaseHandler;
import example.myapp1.service.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class InputUserInDbController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSighInButton;

    @FXML
    private TextField loging_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private AnchorPane signUpButton;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private TextField signUpCountry;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpName;

    @FXML
    void initialize() {
        authSighInButton.setOnAction(actionEvent ->
        {
            signUpNewUser();
        });
    }

    private void signUpNewUser()
    {
        DatebaseHandler dbHandler = new DatebaseHandler();
        String firstName =  signUpName.getText();
        String lastName = signUpLastName.getText();
        String userName = signUpName.getText();
        String password = password_field.getText();
        String location = signUpCountry.getText();
        String gender = "";
        if(signUpCheckBoxMale.isSelected())
        {gender = "Мужской";}
        else
        {gender = "Женский";}
        User user = new User(firstName, lastName, userName, password, location, gender);
        dbHandler.signUpUser(user);

    }

    }

