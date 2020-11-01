/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staffscheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Abe
 */
public class UserInterface extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Staff Scheduler");
        
        Label assignmentReview = new Label(Main.assignmentReview);
        Label divBreakDown = new Label(Main.divBreakDown);
        Label bunkSchedule = new Label(Main.bunkSchedule);
        Label saveErr = new Label(Main.bunkSchedule);
        
        Button constructSchedule = new Button("Construct Schedule");
        Button displayAll = new Button("Display All");
        Button CreateExcelSheet = new Button("Create Excel Sheet");
        Button saveFilePaths = new Button("Save File Paths");
        Button loadFilePaths = new Button("Load File Paths");
        Button loadStaff = new Button("Load Staff From Save");
        Button saveStaff = new Button("Save Staff");
        Button setAssignmentReview = new Button("Set Assignment Review");
        Button setDivBreakdown = new Button("Set Divisional Breakdown");
        Button setBunkSchedule = new Button("Set Bunk Schedule");
        Button changeJob = new Button("Set Staff Job");
        Button changeJobConfirm = new Button("Confirm New Staff Job");
        Button addSkill = new Button("Add Staff Skill");
        Button addSkillConfirm = new Button("Confirm New Staff Skill");
        Button removeSkill = new Button("Remove Staff Skill");
        Button removeSkillConfirm = new Button("Confirm Remove Staff Skill");
        Button removeStaff = new Button("Remove Staff Member");
        Button addStaff = new Button("Add Staff Member");
        Button confirmStaff = new Button("Add Staff Member");
        Button populateSkills = new Button("Assign Skills Based On Jobs");
        Button populateStaff = new Button("Import Staff Members From Excel");
        Button setBunk = new Button("Change Employee Bunk");
        Button confirmBunk = new Button("Change Staff Member Bunk");
        
        Label label1 = new Label("Name:");
        TextField staffName = new TextField ();
        Label label2 = new Label("Bunk:");
        TextField staffBunk = new TextField ();
        
        
        displayAll.setDisable(true);
        //constructSchedule.setDisable(true);
        
        ListView staffView = new ListView();        
        ListView optionView = new ListView();
        
        VBox listViews = new VBox(staffView, optionView);
        
        constructSchedule.setOnAction(value ->  {
           try
           {
           if(Main.staffList != null)
           {
                Main.constructSchedule();
                displayAll.setDisable(false);
           }
           else
           {
               saveErr.setText("failed to construct: no staff");
           }
           }
           catch(FileNotFoundException e)
           {
               saveErr.setText("failed to construct: file not found. input file paths");
               //e.printStackTrace();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
                                
        displayAll.setOnAction(value ->  {
           try
           {
           Main.displayAll();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        CreateExcelSheet.setOnAction(value ->  {
           try
           {
           Schedule.scheduleToExcel();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        saveFilePaths.setOnAction(value ->  {
           try
           {
           Main.saveFilePaths();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        loadFilePaths.setOnAction(value ->  {
           try
           {
           Main.loadFilePaths();
           assignmentReview.setText(Main.assignmentReview);
           bunkSchedule.setText(Main.bunkSchedule);
           divBreakDown.setText(Main.divBreakDown);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        setAssignmentReview.setOnAction(value ->  {
           try
           {
           FileChooser myFileChooser = new FileChooser();
           File file = myFileChooser.showOpenDialog(primaryStage);              
           Main.assignmentReview = file.getPath();
           assignmentReview.setText(Main.assignmentReview);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        setDivBreakdown.setOnAction(value ->  {
           try
           {
           FileChooser myFileChooser = new FileChooser();
           File file = myFileChooser.showOpenDialog(primaryStage);              
           Main.divBreakDown = file.getPath();
           assignmentReview.setText(Main.divBreakDown);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        setBunkSchedule.setOnAction(value ->  {
           try
           {
           FileChooser myFileChooser = new FileChooser();
           File file = myFileChooser.showOpenDialog(primaryStage);              
           Main.bunkSchedule = file.getPath();
           assignmentReview.setText(Main.bunkSchedule);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        loadStaff.setOnAction(value ->  {
           try
           {
           Main.loadStaff();
           constructSchedule.setDisable(false);
           update(staffView);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        saveStaff.setOnAction(value ->  {
           try
           {
           if(!Main.staffList.isEmpty())
           {
               Main.saveStaff();
           }               
           else
           {
               saveErr.setText("failed to save: blank");
           }
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        changeJob.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1)
           {
                optionView.getItems().clear();
        
                for(int i = 0; i < Main.positions.length; i++)
                {
                    String toAdd = "";
                    toAdd += (Main.positions[i] + " ");                    
                    optionView.getItems().add(toAdd);
                }
           }               
           else
           {
               saveErr.setText("failed to display positions: select index");
           }
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        changeJobConfirm.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1 && optionView.getSelectionModel().getSelectedIndex() != -1)
           {
               Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).position = Main.positions[optionView.getSelectionModel().getSelectedIndex()];
               optionView.getItems().clear();
               update(staffView);
           }               
           else
           {
               saveErr.setText("failed to change job: select index");
           }
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        addSkill.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1)
           {
                optionView.getItems().clear();
        
                for(int i = 0; i < Main.activities.length; i++)
                {
                    String toAdd = "";
                    toAdd += (Main.activities[i] + " ");                    
                    optionView.getItems().add(toAdd);
                }
           }               
           else
           {
               saveErr.setText("failed to display activities: select index");
           }
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        addSkillConfirm.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1 && optionView.getSelectionModel().getSelectedIndex() != -1)
           {
               Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).skills.add(Main.activities[optionView.getSelectionModel().getSelectedIndex()]);
               optionView.getItems().clear();
               update(staffView);
           }               
           else
           {
               saveErr.setText("failed to add skill: select index");
           }
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        removeSkill.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1)
           {
                optionView.getItems().clear();
        
                for(int i = 0; i < Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).skills.size(); i++)
                {
                    String toAdd = "";
                    toAdd += (Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).skills.get(i) + " ");                    
                    optionView.getItems().add(toAdd);
                }
           }               
           else
           {
               saveErr.setText("failed to display staff skills: select index");
           }
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        removeSkillConfirm.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1 && optionView.getSelectionModel().getSelectedIndex() != -1)
           {
               Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).skills.remove(optionView.getSelectionModel().getSelectedIndex());               
               optionView.getItems().clear();
               update(staffView);
           }               
           else
           {
               saveErr.setText("failed to remove  skill: select index");
           }
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        removeStaff.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1)
           {
                optionView.getItems().clear();
                Schedule.removeStaff(Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).name, Main.staffList);
                update(staffView);
           }               
           else
           {
               saveErr.setText("failed to display staff skills: select index");
           }
           }
           catch(Exception e)
           {
            e.printStackTrace();
           }                        
        });
        
        setBunk.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1)
           {
                VBox vb = new VBox();
                vb.getChildren().addAll(label2, staffBunk, confirmBunk);
                vb.setSpacing(10);

                Scene scene = new Scene(vb, 800, 600);
                primaryStage.setScene(scene);

                primaryStage.show();
           }               
           else
           {
               saveErr.setText("failed to display staff skills: select index");
           }
           }
           catch(Exception e)
           {
            e.printStackTrace();
           }                        
        });
        
        addStaff.setOnAction(value ->  {
           try
           {            
            VBox vb = new VBox();
            vb.getChildren().addAll(label1, staffName, label2, staffBunk, confirmStaff);
            vb.setSpacing(10);
            
            Scene scene = new Scene(vb, 800, 600);
            primaryStage.setScene(scene);

            primaryStage.show();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        confirmStaff.setOnAction(value ->  {
           try
           {            
            optionView.getItems().clear();
            Schedule.addStaff(staffName.getText(), staffBunk.getText(), Main.staffList);
            update(staffView);
            primaryStage.hide();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        confirmBunk.setOnAction(value ->  {
           try
           {                                       
                optionView.getItems().clear();
                Schedule.changeBunk(Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()), staffBunk.getText(), Main.staffList);
                update(staffView);               
                primaryStage.hide();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        populateSkills.setOnAction(value ->  {
           try
           {
           Main.populateSkills();
           update(staffView);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        populateStaff.setOnAction(value ->  {
           try
           {
           Main.populateStaff("C:\\Users\\Abe\\Downloads\\DAY 2_7.7.19.xls");
           update(staffView);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        HBox Hbox1 = new HBox(saveFilePaths, loadFilePaths, setAssignmentReview, setDivBreakdown, setBunkSchedule);
        HBox Hbox2 = new HBox(loadStaff, saveStaff);
        HBox Hbox3 = new HBox(changeJob, changeJobConfirm);
        HBox Hbox4 = new HBox(addSkill, addSkillConfirm);
        HBox Hbox5 = new HBox(removeSkill, removeSkillConfirm);
        VBox vbox = new VBox(constructSchedule, displayAll, CreateExcelSheet, Hbox1, Hbox2, Hbox3, Hbox4, Hbox5, removeStaff, addStaff, setBunk, populateSkills, populateStaff, assignmentReview, divBreakDown, bunkSchedule, saveErr, listViews);
        
        Scene scene = new Scene(vbox, 800, 600);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Abe's Funky Radical Staff Scheduling Experience");
        
        secondaryStage.setScene(scene);

        secondaryStage.show();
    }
    
    public static void update(ListView myLV)
    {
        myLV.getItems().clear();
        
        for(int i = 0; i < Main.staffList.size(); i++)
        {
            String toAdd = "";
            toAdd += (Main.staffList.get(i).name + " " + Main.staffList.get(i).bunk + " ");
            if(Main.staffList.get(i).position != null)
            {
            toAdd += (Main.staffList.get(i).position + " ");
            }
            for(int j = 0; j < Main.staffList.get(i).skills.size(); j++)
            {
                toAdd += (Main.staffList.get(i).skills.get(j) + " ");
            }
            myLV.getItems().add(toAdd);
        }
    }
    
    public static void launchUI(String[] args) {
        Application.launch(args);
    }
}
