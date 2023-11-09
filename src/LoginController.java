import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private TextField tf_prn;
    
    @FXML
    private TextField tf_password;

    @FXML
    private Button button_login;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        button_login.setOnAction(new EventHandler<ActionEvent>() {  //Event listener

            @Override
            public void handle(ActionEvent event) {
                try {
                    DButils.login(event, tf_prn.getText(), tf_password.getText(), DButils.Data());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }});
    }
    
}
