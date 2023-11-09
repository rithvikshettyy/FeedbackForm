
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * loginInfo
 */
class loginInfo {
    public static String PRN;
    public static String Password;
    public static int row;
    
}
public class DButils extends loginInfo{
    public static ArrayList<List<String>>  Data() throws Exception{
        // File csvFile = new File("AIML.csv");
        String FilePath = System.getProperty("user.dir");
        
        ArrayList<List<String>> data = new ArrayList<>();
        // String Header[] = {"PRN","Name","Password"};
        // data.add(Arrays.asList(Header));
        
        
        BufferedReader br = new BufferedReader(new FileReader(FilePath+"\\AIML.csv"));  //FileNotFound
        String line="";
        while((line = br.readLine())!=null){  //IOException
            data.add(Arrays.asList(line.split(",")));
        }
        br.close();

        //Reading Style
        // for(int i=0;i<data.size();i++){
        //     for(int j=0;j<data.get(0).size();j++){
        //         System.out.print(data.get(i).get(j)+" ");
        //     }
        //     System.out.println();
        // }

        return data;
    }
    public static ArrayList<List<String>>  RemarkData() throws Exception{
        // File csvFile = new File("AIML.csv");
        String FilePath = System.getProperty("user.dir");
        
        ArrayList<List<String>> remark = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(FilePath+"\\Remark.csv"));  //FileNotFound
        String line="";
        while((line = br.readLine())!=null){  //IOException
            remark.add(Arrays.asList(line.split(",")));
        }
        br.close();
        return remark;
    }

    // public static int rowit(ArrayList<List<String>> data,String PRN,String Password){
    //     int row=-1;
    //     for(int i=1;i<data.size();i++){
    //         if(PRN.compareTo((data.get(i).get(0)))==0 && (data.get(i).get(2)).compareTo(Password)==0){
    //             row=i;
    //         }
    //     }
    //     return row;
    // }


    public static void changeScene(ActionEvent event,String title,String fxmlFile, String PRN, String Password,int check){
        Parent root = null;
        if(PRN != null && Password!= null && check==0){  //if the fields are not empty
            try{
                FXMLLoader loader = new FXMLLoader(DButils.class.getResource(fxmlFile));
                root = loader.load();
                // LoginController loginController= loader.getController();
            }catch(Exception e){
                System.out.println(e);
            }
        }
        else if(check==1){
            try{
                FXMLLoader loader = new FXMLLoader(DButils.class.getResource(fxmlFile));
                root = loader.load();
                if(fxmlFile == "start.fxml"){
                    StartController start = loader.getController();
                    start.setUserInformation(PRN,Password);
                }
                else if(fxmlFile == "form.fxml"){
                    FormController form = loader.getController();
                    form.setUserInformation(PRN, Password);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
        else{
            System.out.println("Enter correct credentials");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provided Credentials are not correct!");
            alert.show();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,400));
        if (fxmlFile == "form.fxml") {
            stage.setFullScreen(true);
        }
        stage.show();
    }



    public static void login(ActionEvent event, String PRN, String Password,ArrayList<List<String>>  data){
        for(int i=1;i<data.size();i++){
            if(PRN.compareTo((data.get(i).get(0)))==0 && (data.get(i).get(2)).compareTo(Password)==0){
                loginInfo.PRN=PRN;
                loginInfo.Password=Password;
                loginInfo.row=i;
                if(data.get(row).get(5).compareTo("0")==0){  //checks whether already submitted or not
                    changeScene(event,"Start Page", "start.fxml", PRN, Password,1);
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Already Submitted!");
                    alert.show();
                }
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Provided Credentials are not correct!");
        alert.show();
        
    }

    // Changing the Status of submission
    public static void submitted() throws Exception{
        ArrayList<List<String>> data = DButils.Data();
        String FilePath = System.getProperty("user.dir");
        BufferedWriter bw=new BufferedWriter(new FileWriter(FilePath+"\\AIML.csv"));
        int row= DButils.row;
        for(int i=0;i<data.size();i++){
            for(int j=0;j<data.get(0).size();j++){
                if(i==row && j==5){
                    try {
                        bw.write("1");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }else{
                    try {
                        if(j==data.get(0).size()-1){
                            bw.write(data.get(i).get(j));
                        }else{

                            bw.write(data.get(i).get(j)+",");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            bw.write("\n");
        }
        bw.close();

    }
    public static void AddRemark(int rating[],String remark[]) throws Exception{
        String FilePath = System.getProperty("user.dir");
        BufferedWriter bw=new BufferedWriter(new FileWriter(FilePath+"\\Remark.csv",true));
        for(int i=0;i<5;i++){
            if(i==4) bw.write(Integer.toString(rating[i])+"/"+remark[i]);
            else bw.write(Integer.toString(rating[i])+"/"+remark[i]+",");
        }
        bw.write("\n");
        bw.close();
    }
    public static void FinalRemark() throws Exception{
        int Trating[] = new int[5];
        int remark[][] = new int[5][4];
        for(int i=0;i<5;i++){
            for(int j=0;j<4;j++){
                remark[i][j]=0;
            }
        }
        String Tstyle[]={"Lecture-style teaching","Interactive discussions","Group projects and collaboration","Visual aids and multimedia"};
        ArrayList<List<String>> rd = RemarkData();
        int n=rd.size()-1;
        for(int i=1;i<rd.size();i++){
            for(int j=0;j<rd.get(0).size();j++){
                String temp[] = rd.get(i).get(j).split("/");
                Trating[j]+=Integer.parseInt(temp[0]);
                //remark!!!
                if(temp[1].compareTo(Tstyle[0])==0) remark[j][0]++;
                else if(temp[1].compareTo(Tstyle[1])==0) remark[j][1]++;
                else if(temp[1].compareTo(Tstyle[2])==0) remark[j][2]++;
                else if(temp[1].compareTo(Tstyle[3])==0) remark[j][3]++;
            }
        }
        int output[]=new int[5];
        for(int i=0;i<5;i++){
            int max=-1;
            for(int j=0;j<4;j++){
                if(Math.max(remark[i][j],max )!= max){

                    max=Math.max(remark[i][j],max );
                    output[i]=j;
                }
            }
        }
        System.out.println("HEllow");
        String FilePath = System.getProperty("user.dir");
        BufferedWriter bw=new BufferedWriter(new FileWriter(FilePath+"..\\Remarks.txt"));
        bw.write("Mathematics: \n");
        bw.write("\t"+"Rating: "+Integer.toString(Trating[0]/n)+"\n");
        bw.write("\t"+"Teaching Method must be preferred: "+(Tstyle[output[0]])+"\n");
        bw.write("\n");
        bw.write("DSGT: \n");
        bw.write("\t"+"Rating: "+Integer.toString(Trating[1]/n)+"\n");
        bw.write("\t"+"Teaching Method must be preferred: "+(Tstyle[output[1]])+"\n");
        bw.write("\n");
        bw.write("DS: \n");
        bw.write("\t"+"Rating: "+Integer.toString(Trating[2]/n)+"\n");
        bw.write("\t"+"Teaching Method must be preferred: "+(Tstyle[output[2]])+"\n");
        bw.write("\n");
        bw.write("DLCOA: \n");
        bw.write("\t"+"Rating: "+Integer.toString(Trating[3]/n)+"\n");
        bw.write("\t"+"Teaching Method must be preferred: "+(Tstyle[output[3]])+"\n");
        bw.write("\n");
        bw.write("CG: \n");
        bw.write("\t"+"Rating: "+Integer.toString(Trating[4]/n)+"\n");
        bw.write("\t"+"Teaching Method must be preferred: "+(Tstyle[output[4]])+"\n");
        bw.write("\n");
        
        bw.close();

        

    }
}
