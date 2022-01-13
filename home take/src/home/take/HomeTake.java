/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package home.take;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import javafx.geometry.Insets;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class HomeTake extends Application {

    Connection con = null;

    @Override
    public void start(Stage stage) throws IOException {
        String s = null;

        //Label for name
        Label sidLabel = new Label("SID");
        //Text Field for Name
        TextField sidText = new TextField();
        //Label for studid
        Label stdLabel = new Label("StudId");
        TextField stdText = new TextField();
        Label fnameLabel = new Label("Firstname");
        TextField fnameText = new TextField();
        Label lnameLabel = new Label("Lastname");
        TextField lnameText = new TextField();
        Label secLabel = new Label("Section");
        TextField secText = new TextField();
        Label deptLabel = new Label("Department");
        TextField deptText = new TextField();
        //Label for insert 
        Button Insert = new Button("Insert");
        TableView table = new TableView();

        Insert.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBConnectionC db = new DBConnectionC();
                Connection con = null;
                try {
                    con = db.connMethod();
                    if (con != null) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("you are connected sucssfuly");
                        a.showAndWait();
                    } else {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("you are not connected sucssfuly");
                        a.showAndWait();
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(HomeTake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

                try {
                    con = db.connMethod();
                    String id = sidText.getText();
                    String sid = stdText.getText();
                    String fn = fnameText.getText();
                    String ln = lnameText.getText();
                    String sec = secText.getText();
                    String dep = deptText.getText();

                    //Connection conn= DriverManager.getConnection(db.con);
                    String query = "insert into dept_tb1 values('" + id + "','" + sid + "','" + fn + "','" + ln + "','" + sec + "','" + dep + "')";
                    if (id.equals("") || sid.equals("") || fn.equals("") || ln.equals("") || sec.equals("") || dep.equals("")) {
                        JOptionPane.showMessageDialog(null, "every field is required");

                    } else {
                        PreparedStatement ps = con.prepareStatement(query);

                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "inserted successfuly");
                    }

//                    ps.setString(1, sidText.getText());
//                    ps.setString(2, stdText.getText());
//                    ps.setString(3, fnameText.getText());
//                    ps.setString(4, lnameText.getText());
//                    ps.setString(5, secText.getText());
//                    ps.setString(6, deptText.getText());
                    //System.out.println("inserted successfuly");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "insertion failed");
                    //System.out.println(ex.getMessage());
                }

            }
        });

        Button buttonDisplay = new Button("Display");
        Button buttonDelete = new Button("Delete");
        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert A = new Alert(Alert.AlertType.INFORMATION);
                DBConnectionC db = new DBConnectionC();
               
                try {
                    con = db.connMethod();
                    String val = fnameText.getText();
                    String sql2 = "Delete * FROM dept_tb1 WHERE fristname='eyerusalem'";
                    String val1 = "eyerusalem";
                    String sql = "Delete *FROM dept_tb1 SET FIRSTNAME='eyerusaalem" + val + "' WHERE FIRSTNAME='eyerusalem" + val1 + "'";

                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.executeUpdate();

                    A.setContentText("Deleted successfuly");
                    A.showAndWait();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        Button buttonUpdate = new Button("Update");
        Button buttonSpecificDisplay = new Button("SpecificDisplay");
        
        buttonUpdate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert A = new Alert(Alert.AlertType.INFORMATION);
                DBConnectionC db = new DBConnectionC();
                Connection con = null;
                try {

                    Scanner input = new Scanner(System.in);
                    System.out.print("Enter an updated name:fom db ");
                    String vall = input.nextLine();
                    System.out.print("Enter an new  name: ");
                    String vall1 = input.nextLine();
                    // closing the scanner object
                    input.close();
                    String sql = "UPDATE dept_tb1 SET fristname='" + vall1 + "' WHERE fristname='" + vall + "'";

                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.executeUpdate();
                    A.setContentText("Updated successfuly");
                    A.showAndWait();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        buttonDisplay.setOnAction(new EventHandler<ActionEvent>() {
            private ObservableList<ObservableList> data;
            //private TableView tbl;

            @Override
            public void handle(ActionEvent event) {

                DBConnectionC obj1;
                Connection c;
                ResultSet rs;
                PreparedStatement prs;
                data = FXCollections.observableArrayList();
                try {

                    table.setStyle("-fx-background-color:red; -fx-font-color:yellow ");
                    obj1 = new DBConnectionC();
                    c = obj1.connMethod();
                    String SQL = "SELECT * from dept_tb1";
                    // prs = c.prepareStatement(SQL);
                    rs = (ResultSet) c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                        table.getColumns().addAll(col);
                        System.out.println("Column [" + i + "] ");

                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        System.out.println("Row[1]added " + row);
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }
            }
        });

        //Creating a Grid Pane
        GridPane gridpane = new GridPane();
        //setting size for Pane 
        gridpane.setMinSize(500, 300);
        //setting the padding 
        gridpane.setPadding(new Insets(10, 10, 10, 10));
        //setting the vertica and horizontal gaps between the columns
        gridpane.setVgap(5);
        gridpane.setHgap(5);
        //setting the grid alignment
        gridpane.setAlignment(Pos.CENTER);
        //Arraning  all the nodes in the grid
        gridpane.add(sidLabel, 0, 0);
        gridpane.add(sidText, 1, 0);

        gridpane.add(stdLabel, 0, 1);
        gridpane.add(stdText, 1, 1);

        gridpane.add(fnameLabel, 0, 2);
        gridpane.add(fnameText, 1, 2);

        gridpane.add(lnameLabel, 0, 3);
        gridpane.add(lnameText, 1, 3);

        gridpane.add(secLabel, 0, 4);
        gridpane.add(secText, 1, 4);

        gridpane.add(deptLabel, 0, 5);
        gridpane.add(deptText, 1, 5);

        gridpane.add(Insert,0,6);
        gridpane.add(buttonDisplay,1,6);
        gridpane.add(buttonDelete,2,6);
        gridpane.add(buttonUpdate,3,6);
        gridpane.add(buttonSpecificDisplay,4,6);
        gridpane.addColumn(2,table);
        ;

        //setting the backgroun color
        gridpane.setStyle("-fx-background-color:BEIGE;");
        sidLabel.setStyle("-fx-font:normal bold 20px 'serif'");
        stdLabel.setStyle("-fx-font:normal bold 20px 'serif'");
        fnameLabel.setStyle("-fx-font:normal bold 20px 'serif'");
        lnameLabel.setStyle("-fx-font:normal bold 20px 'serif'");
        secLabel.setStyle("-fx-font:normal bold 20px 'serif'");
        deptLabel.setStyle("-fx-font:normal bold 20px 'serif'");
        Insert.setStyle("-fx-font:normal bold 10px 'serif'");
        buttonDisplay.setStyle("-fx-font:normal bold 15px 'serif'");
        buttonDelete.setStyle("-fx-font:normal bold 15px 'serif'");
        buttonUpdate.setStyle("-fx-font:normal bold 15px 'serif'");
        buttonSpecificDisplay.setStyle("-fx-font:normal bold 15px 'serif'");
          //styling nodes 
        Insert.setStyle("-fx-background-color:green; -fx-textfill:white;");
        
        buttonDisplay.setStyle("-fx-background-color:yellow; -fx-textfill:white;");
        buttonDelete.setStyle("-fx-background-color:red; -fx-textfill:white;");
        buttonUpdate.setStyle("-fx-background-color:orange; -fx-textfill:white;");
        //creating a scene object
        Scene scene = new Scene(gridpane);

        //setting the title of the stage
        stage.setTitle("HOME TAKE");

        //adding scene to the stage
        stage.setScene(scene);

        //displying the contents of the stage
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
