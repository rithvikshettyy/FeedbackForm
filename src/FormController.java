
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;

public class FormController implements Initializable{

    @FXML
    private Label welcome;
    @FXML
    private Button button_logout;
    @FXML
    private Button next1,next2,next3,next4;
    @FXML
    private Button form_submit;
    @FXML
    private Tab tab1,tab2,tab3,tab4,tab5;
    @FXML
    private TabPane tabpane;
    @FXML 
    private ToggleGroup q02,q12,q22,q32,q42;
    @FXML
    private ToggleGroup q01,q03,q04,q05;
    @FXML
    private ToggleGroup q11,q13,q14,q15;
    @FXML
    private ToggleGroup q21,q23,q24,q25;
    @FXML
    private ToggleGroup q31,q33,q34,q35;
    @FXML
    private ToggleGroup q41,q43,q44,q45;

    public void setUserInformation(String PRN,String Password) throws Exception{
        ArrayList<List<String>> data= DButils.Data();
        welcome.setText("Welcome "+data.get(DButils.row).get(1));
    }

    public int check(String remark){
        if(remark.compareTo("Excellent")==0) return 5;
        else if(remark.compareTo("Good")==0) return 4;
        else if(remark.compareTo("Average")==0) return 3;
        else if(remark.compareTo("Poor")==0) return 2;
        if(remark.compareTo("Improved clarity in explanations")==0) return 5;
        else if(remark.compareTo("More engaging activities")==0) return 4;
        else if(remark.compareTo("Better communication with students")==0) return 3;
        else if(remark.compareTo("Stronger classroom discipline")==0) return 2;
        if(remark.compareTo("Yes, always")==0) return 5;
        else if(remark.compareTo("Sometimes")==0) return 4;
        else if(remark.compareTo("Rarely")==0) return 3;
        else if(remark.compareTo("No, never")==0) return 2;
        if(remark.compareTo("Very Approachable")==0) return 5;
        else if(remark.compareTo("Approachable")==0) return 4;
        else if(remark.compareTo("Somewhat Approachable")==0) return 3;
        else if(remark.compareTo("Not Very Approachable")==0) return 2;
        return 0;
    }
    
    public int[] rating(){
        int rating[] = new int[5];
        RadioButton SRB01 = (RadioButton)q01.getSelectedToggle();
        
        RadioButton SRB03 = (RadioButton)q03.getSelectedToggle();
        RadioButton SRB04 = (RadioButton)q04.getSelectedToggle();
        RadioButton SRB05 = (RadioButton)q05.getSelectedToggle();
        RadioButton SRB11 = (RadioButton)q11.getSelectedToggle();
        
        RadioButton SRB13 = (RadioButton)q13.getSelectedToggle();
        RadioButton SRB14 = (RadioButton)q14.getSelectedToggle();
        RadioButton SRB15 = (RadioButton)q15.getSelectedToggle();
        RadioButton SRB21 = (RadioButton)q21.getSelectedToggle();
        
        RadioButton SRB23 = (RadioButton)q23.getSelectedToggle();
        RadioButton SRB24 = (RadioButton)q24.getSelectedToggle();
        RadioButton SRB25 = (RadioButton)q25.getSelectedToggle();
        RadioButton SRB31 = (RadioButton)q31.getSelectedToggle();
        
        RadioButton SRB33 = (RadioButton)q33.getSelectedToggle();
        RadioButton SRB34 = (RadioButton)q34.getSelectedToggle();
        RadioButton SRB35 = (RadioButton)q35.getSelectedToggle();
        RadioButton SRB41 = (RadioButton)q41.getSelectedToggle();
        
        RadioButton SRB43 = (RadioButton)q43.getSelectedToggle();
        RadioButton SRB44 = (RadioButton)q44.getSelectedToggle();
        RadioButton SRB45 = (RadioButton)q45.getSelectedToggle();
        
        rating[0]=Math.round((check(SRB01.getText())+check(SRB03.getText())+check(SRB04.getText())+check(SRB05.getText()))/4);
        rating[1]=Math.round((check(SRB11.getText())+check(SRB13.getText())+check(SRB14.getText())+check(SRB15.getText()))/4);
        rating[2]=Math.round((check(SRB21.getText())+check(SRB23.getText())+check(SRB24.getText())+check(SRB25.getText()))/4);
        rating[3]=Math.round((check(SRB31.getText())+check(SRB33.getText())+check(SRB34.getText())+check(SRB35.getText()))/4);
        rating[4]=Math.round((check(SRB41.getText())+check(SRB43.getText())+check(SRB44.getText())+check(SRB45.getText()))/4);
        return rating;
    }
    public String[] remark(){
        String remark[]= new String[5];
        RadioButton SRB02 = (RadioButton)q02.getSelectedToggle();
        RadioButton SRB12 = (RadioButton)q12.getSelectedToggle();
        RadioButton SRB22 = (RadioButton)q22.getSelectedToggle();
        RadioButton SRB32 = (RadioButton)q32.getSelectedToggle();
        RadioButton SRB42 = (RadioButton)q42.getSelectedToggle();

        remark[0]=SRB02.getText();
        remark[1]=SRB12.getText();
        remark[2]=SRB22.getText();
        remark[3]=SRB32.getText();
        remark[4]=SRB42.getText();

        return remark;
    }
    public boolean submit(){
        try{
            DButils.AddRemark(rating(), remark());
            return true;
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Completely fill the form!");
            alert.show();
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                DButils.changeScene(event, "Login", "login.fxml", null, null, 1);
            }
        });

        next1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                tabpane.getSelectionModel().selectNext();
            }
        });
        next2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                tabpane.getSelectionModel().selectNext();
            }
        });
        next3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                tabpane.getSelectionModel().selectNext();
            }
        });
        next4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                tabpane.getSelectionModel().selectNext();
            }
        });
        form_submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                if(submit()){
                    try {
                        DButils.submitted();
                        DButils.FinalRemark();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    DButils.changeScene(event, "Login", "login.fxml", null, null, 1);

                }

                // RadioButton selectedRadioButton = (RadioButton)q11.getSelectedToggle();
                // System.out.println(q11.getSelectedToggle());
            }
        });
    }

}