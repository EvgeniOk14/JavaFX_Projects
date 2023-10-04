package example.myapp1;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import example.myapp1.service.DatebaseHandler;
import example.myapp1.service.User;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InputUserInDbController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox GenderFemale;

    @FXML
    private CheckBox GenderMale;

    @FXML
    private Button authSighInButton;

    @FXML
    private TextField login_field;

    @FXML
    private Button passageBD;

    @FXML
    private PasswordField password_field;

    @FXML
    private AnchorPane signUpButton;

    @FXML
    private TextField signUpCountry;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpName;


    @FXML
    void initialize()
    {
        authSighInButton.setOnAction(actionEvent ->
        {
            Task<Void> signUpTask = new Task<Void>()
            {
                @Override
                protected Void call() throws Exception
                {
                    signUpNewUser();
                    return null;
                }
        };

            Thread signUpThread = new Thread(signUpTask);
            signUpThread.start();
        });

        passageBD.setOnAction(actionEvent ->
        {
                openNewScene1("registration.fxml");
        });

    }

    private void signUpNewUser()
    {
        Task<Void> signUpTask = new Task<Void>()
        {
            @Override
            protected Void call() throws Exception
            {
                    DatebaseHandler dbHandler = new DatebaseHandler();
                    String firstName =  signUpName.getText();
                    String lastName = signUpLastName.getText();
                    String userName = login_field.getText();
                    String password = password_field.getText();
                    String location = signUpCountry.getText();
                    String gender = "";
                    if(GenderMale.isSelected())
                        {gender = "Мужской";}
                    else
                        {gender = "Женский";}
                    User user = new User(firstName, lastName, userName, password, location, gender);
                    dbHandler.signUpUser(user);
                return  null;
            }
        };
        Thread signUpThread = new Thread(signUpTask);
        signUpThread.start();
    }


    private void openNewScene1(String fxmlFile)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) authSighInButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

