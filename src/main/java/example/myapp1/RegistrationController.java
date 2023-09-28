package example.myapp1;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import example.myapp1.animations.Shake;
import example.myapp1.service.DatebaseHandler;
import example.myapp1.service.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSighInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        authSighInButton.setOnAction(actionEvent ->
        {
            String logingText = login_field.getText().trim();
            String logingPassword = password_field.getText().trim();
            if(!logingText.equals("") && !logingPassword.equals(""))
            {
                loginUser(logingText, logingPassword);
            }
            else
            {
                System.out.println("Логин и пароль пустые!");
            }
        });


        loginSignUpButton.setOnAction(actionEvent ->
        {
            openNewScene("inputUserInDb.fxml");

        });
    }
    public void loginUser(String loginText, String loginPassword)
    {
        DatebaseHandler dbHandler = new DatebaseHandler();
        User user = new User();
        user.setUerName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int count = 0;
        try
        {
            while (result.next())
            {
                count++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        if (count >= 1)
        {
            /*System.out.println("Успех!");*/
            openNewScene("picture.fxml");
        }
        else
        {
            Shake userLoginAnim = new Shake(login_field);
            Shake userPassAnim = new Shake(password_field);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }

    public void openNewScene(String window)
    {
        loginSignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}





