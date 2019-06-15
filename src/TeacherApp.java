
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


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


            //adds classroomList to gridpane
            Label classes = new Label("Teaches this classes: ");
            classRoomsListView = new ListView<>();
            classRoomsListView.getSelectionModel().selectedIndexProperty().addListener(
                    new ListSelectChangeListener());
            dataClass = getDbData2();
            classRoomsListView.setItems(dataClass);
            //puts heading and list in a vbox
            VBox clvbox = new VBox();
            clvbox.getChildren().addAll(classes,classRoomsListView);
            //puts clvbox in another VBox
            VBox teacherclasses = new VBox();
            teacherclasses.setSpacing(10);
            teacherclasses.getChildren().addAll(clvbox);
            grid.add(teacherclasses,3,1);

            // teacher name label and text fld - in a vbox

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

            //VBox for center with all the labels
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

            //String  id = Integer.toString(teacher.getTeacherId());//converts id to a string to be able to display it

            idtxt.setText(Integer.toString(teacher.getTeacherId()));//other possibility to convert to integer
            nametxt.setText(teacher.getTeacherName());
            surnametxt.setText(teacher.getTeacherSurname());
            email.setText(teacher.getTeacherEmail() + " - selected");

            //dataClass = getDbData(Integer.valueOf(teacher.getTeacherId())); working on it
            classRoomsListView.setItems(dataClass);
        }
    }

        //adds second listener to classroom - not necessary at the moment still needs work
        /*private class ClassSelectChangeListener implements ChangeListener<Number> {

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
        }*/

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


