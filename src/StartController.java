import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StartController implements Initializable {

    @FXML
    private Label label_sname;
    @FXML
    private Label label_dept;
    @FXML
    private Label label_sem_year;
    @FXML
    private Button button_start;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event){
                DButils.changeScene(event,"Form","form.fxml",null,null,1);
                
            }
        });
    }
    
    public void setUserInformation(String PRN,String Password){
        try {
            int row = DButils.row;
            ArrayList<List<String>> data= DButils.Data();
            label_sname.setText(data.get(row).get(1));
            label_dept.setText("AIML");
            label_sem_year.setText("III / 2nd");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
