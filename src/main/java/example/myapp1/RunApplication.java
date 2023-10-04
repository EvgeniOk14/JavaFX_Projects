package example.myapp1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 700);
        stage.setTitle("MyApp1");
        stage.setScene(scene);
        stage.show();
        /*fxmlLoader.setController(new RegistrationController());*/
        /*fxmlLoader.setController(new InputUserInDbController());*/
    }

    public static void main(String[] args)
    {
        launch();
    }
}