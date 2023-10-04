package example.myapp1;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import example.myapp1.service.DatebaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PictureController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label AppName;

    @FXML
    private TextField deleteIdField;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Label errorLabel;

    @FXML
    private AnchorPane imageButtonPicture;

    @FXML
    private ImageView imageButtonPicture1;

    @FXML
    private Label inscriptionText;


    @FXML
    void initialize()
    {
        assert imageButtonPicture != null : "fx:id=\"imageButtonPicture\" was not injected: check your FXML file 'picture.fxml'.";

        deleteUserButton.setOnAction(actionEvent ->
        {
            String input = deleteIdField.getText();

            if(isNumeric(input))
            {
                int userId = Integer.parseInt(input.trim());

                try
                {
                    boolean flag = checkIfUserExist(userId);
                    if (flag)
                    {
                        DatebaseHandler db = new DatebaseHandler();
                        db.deleteUser(userId);
                        setErrorLabelText("Пользователь успешно удален! ");
                        errorLabel.setVisible(true);
                    }
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
                catch (ClassNotFoundException e)
                {
                    throw new RuntimeException(e);
                }
            }
            else
            {
                errorLabel.setVisible(true);
                errorLabel.setText("Вы ввели не число! Введите число:");
            }
            });
    }

    public boolean checkIfUserExist(int id) throws SQLException, ClassNotFoundException
    {
        try
        {
            DatebaseHandler db = new DatebaseHandler();
            Connection connection = db.getDbConnection();
            String query = "SELECT * FROM users WHERE idusers = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                return true;
            }
            else
            {
                setErrorLabelText("Пользователя с таким id в базе нет! ");
                errorLabel.setVisible(true);
                return false;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isNumeric(String input)
    {
        try
        {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }


    public void setErrorLabelText(String  text)
    {
        errorLabel.setText(text);
    }
}
