package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.lang.String;

public class Main extends Application {

    private BorderPane displayPane;
    private Button[] empDayOff;
    private GridPane labelPane;
    private GridPane gridPane;
    private GridPane emplPane;
    private FlowPane funcPane;
    private TextField num_empl;
    private String[] employeesNames;
    private String[] employeeOffNames;
    private int emplValRef;
    

    @Override

    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("TimeTable Maker");

        createGridPane();

        Scene scene= new Scene(gridPane, 700, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        displayPlaneArea();
        gridPane.getChildren().add(displayPane);

        // Creating labelPane
        createLabelPane();
        displayPane.setTop(labelPane);

        //Creating new Pane for Routine Table Functions
        createFunctionsPane();
        //displayPane.setBottom(funcPane);

    }    
    public void createFunctionsPane(){
        funcPane = new FlowPane(Orientation.VERTICAL);
        funcPaneLabel();
    }

    public void funcPaneLabel(){
        HBox btnConfirmBox= new HBox();
        btnConfirmBox.setPadding(new Insets(5,5,5,5));
        btnConfirmBox.setSpacing(10);

        Button btnAddInfo = new Button("Add Info");
        btnConfirmBox.getChildren().addAll(btnAddInfo);

        funcPane.setAlignment(Pos.CENTER);
        funcPane.getChildren().addAll(btnConfirmBox);

        getInfoHandlerClass getInfoBtnHandler = new getInfoHandlerClass();
        btnAddInfo.setOnAction(getInfoBtnHandler);
    }
   
   

    public void emplLabel(int numOfEmployees){

        employeesNames= new String[numOfEmployees];

        empDayOff= new Button[numOfEmployees];

        Text empNameTitle = new Text("Employee Name");
        emplPane.add(empNameTitle , 2 ,0 );

        Text priorityTitle = new Text("Priority");
        emplPane.add(priorityTitle, 4 ,0);

        CheckBox[] priorityCheck= new CheckBox[numOfEmployees];
     
        class holdRefValue {
            int val;
        }

        holdRefValue refNumEmployee= new holdRefValue();
        refNumEmployee.val = -1;


        for(int i=0; i <numOfEmployees ; i++) {

            final int refFinal= i;

            Text empNum = new Text((i + 1) + ". ");
            emplPane.add(empNum, 1, i + 1);

            TextField emplName = new TextField();
            emplPane.add(emplName, 2, i + 1);
            employeesNames[i]=emplName.getText();


            empDayOff[i] = new Button("Edit Off Days");
            emplPane.add(empDayOff[i], 3, i + 1);

            empDayOff[refFinal].setOnAction(new EventHandler<ActionEvent>(){ 
                @Override

                public void handle(ActionEvent e){
                    createOffDaysPane();
                    displayPane.setBottom(offdaysPane);
                    System.out.println(refFinal);
                    refNumEmployee.val= refFinal;
                    employeeOffNames[refNumEmployee.val]=employeesNames[refNumEmployee.val];

                }
            });

            priorityCheck[i] = new CheckBox();
            emplPane.add(priorityCheck[i], 4, i + 1);
        }

     
    }

    public void offDaysInfo(){
        offDaysLabel();
        Label empNameOffDays= new Label(employeeOffNames[emplValRef] + " Off Days Schedule");
        offdaysPane.add(empNameOffDays, 0 , 1);
    }


    public void createGridPane(){
        gridPane= new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

    }

    public void createLabelPane(){
        labelPane= new GridPane();
        labelPane.setPadding(new Insets(5 , 5, 5 , 5));
        labelPane.setHgap(5);
        labelPane.setVgap(5);
        addHeaderLabels();
    }

    public void createEmplPane(){
        emplPane= new GridPane();
        emplPane.setPadding(new Insets(1 , 1 , 10 , 10));
        emplPane.setVgap(5);
        emplPane.setHgap(5);
        int num_employee = Integer.parseInt(num_empl.getText());
        emplLabel(num_employee);        
    }

    public void displayPlaneArea(){
        displayPane= new BorderPane();
    }


    public void addHeaderLabels(){

        Label num_Employees=new Label("Number Of Employees:");
        labelPane.add(num_Employees, 1 , 0);

        num_empl= new TextField();
        labelPane.add(num_empl , 2, 0);

        Button add_employees= new Button("Add Info");
        addEmployesInfoClass addEmployeesHandler= new addEmployesInfoClass();
        add_employees.setOnAction(addEmployeesHandler);
        labelPane.add(add_employees, 3,0);

        Label open_Days= new Label("Number Of Days Open:");
        labelPane.add(open_Days, 1, 1);

        TextField days_Open = new TextField();
        labelPane.add(days_Open , 2 ,1);


        Label hours_Open= new Label("Opening Time:");
        labelPane.add(hours_Open , 1 , 2);

        TextField open_Time = new TextField();
        labelPane.add(open_Time , 2, 2);


        Label hours_Close= new Label("Closing Time:");
        labelPane.add(hours_Close , 1, 3);

        TextField close_Time = new TextField();
        labelPane.add(close_Time , 2,3);

        Label employe_Info= new Label("Employees Information");
        labelPane. add(employe_Info, 3, 4);


    }

    private GridPane offdaysPane; 

    public void createOffDaysPane(){
        offdaysPane= new GridPane();
        offdaysPane.setPadding(new Insets(5 , 5, 5 , 5));
        offdaysPane.setHgap(5);
        offdaysPane.setVgap(5);
        offDaysInfo();
    }

    

    public void offDaysLabel(){
        final Label MONDAY= new Label("MONDAY");
        offdaysPane.add(MONDAY, 1, 1);
        final Label TUESDAY= new Label("TUESDAY");
        offdaysPane.add(TUESDAY, 1, 2);
        final Label WEDNESDAY= new Label("WEDNESDAY");
        offdaysPane.add(WEDNESDAY, 1, 3);
        final Label THURSDAY= new Label("THURSDAY");
        offdaysPane.add(THURSDAY, 1, 4);
        final Label FRIDAY= new Label("FRIDAY");
        offdaysPane.add(FRIDAY, 1, 5);
        final Label SATURDAY= new Label("SATURDAY");
        offdaysPane.add(SATURDAY, 1, 6);
        final Label SUNDAY= new Label("SUNDAY");
        offdaysPane.add(SUNDAY, 1, 7);
    }



    class getInfoHandlerClass implements EventHandler<ActionEvent>{
        @Override

        public void handle(ActionEvent e){
            System.out.println("Pressed Get Info Button");

        }
    }

    class addEmployesInfoClass implements EventHandler<ActionEvent>{
        @Override

        public void handle(ActionEvent e){
            // Creating Employee Pane for their Data holding
            createEmplPane();

            displayPane.setCenter(emplPane);
        } 
    }
    public static void main(String[] args) {
        launch(args);
    }
}
