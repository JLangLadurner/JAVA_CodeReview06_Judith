
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TeacherApp extends Application {
    private ListView<Teacher> listView;
    private ObservableList<Teacher> data;
    private TextField idtxt;
    private TextField nametxt;
    private TextField surnametxt;
    private TextField email;
    private ListView<ClassRooms>classRoomsListView;
    private ObservableList<ClassRooms>dataClass;
    private TextField classRoom;


    private TeacherDataAccess dbaccess;
    private ClassesDataAccess dbaccess2;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        try {
            dbaccess = new TeacherDataAccess();
            dbaccess2 = new ClassesDataAccess();
        } catch (Exception e) {
            displayException(e);
        }
    }

    @Override
    public void stop() {

        try {
            dbaccess.closeDb();
            dbaccess2.closeDb();
        } catch (Exception e) {

            displayException(e);
        }
    }

        @Override
        public void start (Stage primaryStage) {

            primaryStage.setTitle("School Statistics of Teachers (1)");

            // gridPane layout

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(15);
            grid.setVgap(20);
            grid.setPadding(new Insets(25, 25, 25, 25));

            // list view, listener and list data

            listView = new ListView<>();
            listView.getSelectionModel().selectedIndexProperty().addListener(
                    new ListSelectChangeListener());
            data = getDbData();
            listView.setItems(data);
            grid.add(listView, 1, 1); // col = 1, row = 1

            Label classes = new Label("Teaches this classes");

            classRoomsListView = new ListView<>();


            classRoomsListView.getSelectionModel().selectedIndexProperty().addListener(
                    new ListSelectChangeListener());
            classRoomsListView.setMaxSize(200, 400);

            //classRoomsListView.setMouseTransparent(true);
            //classRoomsListView.setFocusTraversable(false);
            dataClass = getDbData2();
            classRoomsListView.setItems(dataClass);
            VBox teacherclasses = new VBox();
            teacherclasses.setSpacing(10);
            teacherclasses.getChildren().addAll(classes,classRoomsListView);
            grid.add(classRoomsListView,1,4);





            // todo name label and text fld - in a hbox

            Label headlbl = new Label("This Teacher:");
            Label idlbl = new Label("ID");
            idtxt = new TextField();
            idtxt.setMinHeight(30.0);
            idtxt.setPrefColumnCount(20);
            Label namelbl = new Label("Name");
            nametxt = new TextField();
            nametxt.setMinHeight(30.0);
            nametxt.setPrefColumnCount(20);
            Label surnamelbl = new Label("Surname");
            surnametxt = new TextField();
            surnametxt.setMinHeight(30.0);
            surnametxt.setPrefColumnCount(20);
            Label emaillbl = new Label("Email");
            email = new TextField();
            email.setMinHeight(30.0);
            email.setPrefColumnCount(20);
            VBox vlable = new VBox();
            vlable.setSpacing(10);
            vlable.getChildren().addAll(headlbl, idlbl,idtxt,namelbl,nametxt,surnamelbl,surnametxt, emaillbl,email);

            // todo desc text area in a scrollpane


            /*ScrollPane sp = new ScrollPane();
            sp.setFitToWidth(true);
            sp.setFitToHeight(true);
            sp.setPrefHeight(300);
            sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);*/

            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.getChildren().addAll(vlable);

            grid.add(vbox, 2, 1); // col = 2, row = 1*/

            Scene scene = new Scene(grid, 750, 400); // width = 750, height = 400
            primaryStage.setScene(scene);
            primaryStage.show();

            // initial selection
            listView.getSelectionModel().selectFirst();
            classRoomsListView.getSelectionModel().selectFirst();

        }
    private class ListSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= data.size())) {

                return; // invalid data
            }

            // set name and desc fields for the selected teacher
            Teacher teacher = data.get(new_val.intValue());
            String  id = Integer.toString(teacher.getTeacherId());//converts id to a string to be able to display it
            idtxt.setText(id);
            nametxt.setText(teacher.getTeacherName());
            surnametxt.setText(teacher.getTeacherSurname());
            email.setText(teacher.getTeacherEmail() + " - selected");
        }
    }

    //adds second listener to classroom
    private class ClassSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= data.size())) {

                return; // invalid data
            }

            // set ClassName for the selected teacher
            ClassRooms clssR= dataClass.get(new_val.intValue());
            classRoom.setText(clssR.getClassName()+ " - selected");

        }
    }

    private ObservableList<Teacher> getDbData() {

        List<Teacher> list = null;

        try {
            list = dbaccess.getAllRows();
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Teacher> dbData = FXCollections.observableList(list);
        return dbData;
    }

    private ObservableList<ClassRooms> getDbData2() {

        List<ClassRooms> listClass = null;

        try {
            listClass = dbaccess2.getAllClass();
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<ClassRooms> dbData2 = FXCollections.observableList(listClass);
        return dbData2;
    }


    //exeption e definition:
            private void displayException (Exception e){

                System.out.println("###### Exception ######");
                e.printStackTrace();
                System.exit(0);
            }

        }


