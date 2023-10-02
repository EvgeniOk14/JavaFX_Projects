package example.myapp1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PictureController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane imageButtonPicture;

    @FXML
    private ImageView imageButtonPicture1;

    @FXML
    void initialize()
    {
        assert imageButtonPicture != null : "fx:id=\"imageButtonPicture\" was not injected: check your FXML file 'picture.fxml'.";
    }

}
