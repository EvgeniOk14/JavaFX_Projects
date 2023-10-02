package example.myapp1;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import example.myapp1.animations.Shake;
import example.myapp1.service.DatebaseHandler;
import example.myapp1.service.User;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSighInButton; // кнопка Войти (после ввода пароля и логина)

    @FXML
    private Button loginSignUpButton; // кнопка зарегистрироваться (переход на регистрацию)

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize()
    {

        authSighInButton.setOnAction(actionEvent ->
        {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals(""))
            {
                Task<Void> loginTask = new Task<Void>()
                {
                    @Override
                    protected Void call() throws Exception
                    {
                        loginUser(loginText, loginPassword);
                        return null;
                    }
                };
                Thread loginThread = new Thread(loginTask);
                loginThread.start();
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
        Task<Void> loginTask = new Task<Void>()
        {
            @Override
            protected Void call() throws Exception
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
                        Platform.runLater(() -> openNewScene("picture.fxml"));
                    }
                    else
                    {
                        Platform.runLater(() ->
                        {
                            Shake userLoginAnim = new Shake(login_field);
                            Shake userPassAnim = new Shake(password_field);
                            userLoginAnim.playAnim();
                            userPassAnim.playAnim();
                        });
                    }
                    return null;
            }
        };

        Thread loginThread = new Thread(loginTask);
        loginThread.start();
    }


    private void openNewScene(String fxmlFile)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}





