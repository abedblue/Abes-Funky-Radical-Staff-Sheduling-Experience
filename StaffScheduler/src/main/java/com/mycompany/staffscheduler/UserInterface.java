/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staffscheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Abe
 */
public class UserInterface extends Application {
    
    
    ListView staffView;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Staff Scheduler");
        
        Label assignmentReview = new Label(Main.assignmentReview);
        Label divBreakDown = new Label(Main.divBreakDown);
        Label bunkSchedule = new Label(Main.bunkSchedule);
        Label saveErr = new Label(Main.bunkSchedule);
        Label myDay = new Label(Main.day);
        
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
        Button removeStaffConfirm = new Button("Confirm Remove Staff Member");
        Button addStaff = new Button("Add Staff Member");
        Button confirmStaff = new Button("Add Staff Member");
        Button populateSkills = new Button("Assign Skills Based On Jobs");
        Button populateStaff = new Button("Import Staff Members From Excel");
        Button setBunk = new Button("Change Employee Bunk");
        Button confirmBunk = new Button("Change Staff Member Bunk");
        Button changeName = new Button("Change Employee Name");
        Button changeNameConfirm = new Button("Confirm New Name");
        Button setPriority = new Button("Set Employee Activity Priority");
        Button setPriorityConfirm = new Button("Confirm Activity Priority");
        Button day14 = new Button("Day 1/4");
        Button day25 = new Button("Day 2/5");
        Button day36 = new Button("Day 3/6");
        Button searchStaffList = new Button("Go");
        Button hardScheduleIndividual = new Button("Add Strong Assignment for Individual");
        Button hardScheduleIndividualConfirm = new Button("Confirm");
        Button hardScheduleJob = new Button("Add Strong Assignment for Job");
        Button hardScheduleJobConfirm = new Button("Confirm");
        Button hardScheduleActivity = new Button("Add Strong Assignment for Activity");
        Button hardScheduleActivityConfirm = new Button("Confirm");
        Button cancel = new Button("Cancel");
        Button saveHardScheduled = new Button("Save Strong Assignments");
        Button removeHardScheduled = new Button ("Remove Strong Assignment");
        Button removeHardScheduledConfirm = new Button("Confirm Remove Strong Assignment");
        CheckBox correctDoubleOffs = new CheckBox("Automagically Correct Double Off Periods");
        CheckBox scheduleFreePlay = new CheckBox("Automagically Schedule Free Play");
        correctDoubleOffs.setSelected(true);
        scheduleFreePlay.setSelected(true);
        
        Label label1 = new Label("Name:");
        TextField staffName = new TextField ();
        Label label2 = new Label("Bunk:");
        TextField staffBunk = new TextField ();
        Label label3 = new Label("Activity:");
        TextField activityName = new TextField ();
        Label label4 = new Label("Priority:");
        TextField priority = new TextField ();    
        TextField searchBox = new TextField ();    
        TextField tb1 = new TextField();
        TextField tb2 = new TextField();
        
        displayAll.setDisable(true);
        day14.setDisable(true);
        CreateExcelSheet.setDisable(true);
        changeJobConfirm.setDisable(true);
        addSkillConfirm.setDisable(true);
        removeSkillConfirm.setDisable(true);
        removeStaffConfirm.setDisable(true);
        //constructSchedule.setDisable(true);
        
        staffView = new ListView();        
        ListView optionView = new ListView();
        
        HBox listViews = new HBox(staffView, optionView);
        staffView.setPrefHeight(300);
        optionView.setPrefHeight(300);
        HBox.setHgrow(staffView, Priority.ALWAYS);
        HBox.setHgrow(optionView, Priority.ALWAYS);
        staffView.setPrefWidth(400);
        optionView.setPrefWidth(400);
        
        constructSchedule.setOnAction(value ->  {
           try
           {
           if(Main.staffList != null)
           {
                Main.constructSchedule();
                CreateExcelSheet.setDisable(false);
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
                FileChooser myFileChooser = new FileChooser();
                File file = myFileChooser.showSaveDialog(primaryStage);   
                String fileName = file.getPath();
                if (!fileName.endsWith(".xlsx"))
                fileName += ".xlsx";
               
                Schedule.scheduleToExcel(fileName);
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
           FileChooser myFileChooser = new FileChooser();
           File file = myFileChooser.showOpenDialog(primaryStage);     
           Main.loadStaff(file.getPath());
           constructSchedule.setDisable(false);
           update(staffView);
           
           updateAutoSave();
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
               FileChooser myFileChooser = new FileChooser();
               File file = myFileChooser.showSaveDialog(primaryStage);     
               Main.saveStaff(file.getPath());
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
                    toAdd += (Main.positions[i]);                    
                    optionView.getItems().add(toAdd);
                }
                changeJobConfirm.setDisable(false);
                alphabetize(optionView);
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
               Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).position = (String)optionView.getSelectionModel().getSelectedItem();
               optionView.getItems().clear();
               update(staffView);  
               
               updateAutoSave();
           }               
           else
           {
               saveErr.setText("failed to change job: select index");
           }
           changeJobConfirm.setDisable(true);
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
                    toAdd += (Main.activities[i]);                    
                    optionView.getItems().add(toAdd);
                }
                addSkillConfirm.setDisable(false);
                alphabetize(optionView);
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
               Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).skills.add((String)optionView.getSelectionModel().getSelectedItem());
               optionView.getItems().clear();
               update(staffView);
               
               updateAutoSave();
           }               
           else
           {
               saveErr.setText("failed to add skill: select index");
           }
           addSkillConfirm.setDisable(true);
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
                    toAdd += (Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).skills.get(i));                    
                    optionView.getItems().add(toAdd);
                }
                
                alphabetize(optionView);
           }               
           else
           {
               saveErr.setText("failed to display staff skills: select index");
           }
           removeSkillConfirm.setDisable(false);
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
               //Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).skills.remove(optionView.getSelectionModel().getSelectedIndex()); 
               Main.removeSkill(Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).name, (String)optionView.getSelectionModel().getSelectedItem());
               optionView.getItems().clear();
               update(staffView);
               
               updateAutoSave();
           }               
           else
           {
               saveErr.setText("failed to remove  skill: select index");
           }
           removeSkillConfirm.setDisable(true);
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
                removeStaffConfirm.setDisable(false);
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
        
        removeStaffConfirm.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1)
           {                
                Schedule.removeStaff(Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).name, Main.staffList);
                update(staffView);
                removeStaffConfirm.setDisable(true);
                
                updateAutoSave();
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
            
            updateAutoSave();
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
                
                updateAutoSave();
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
           
           updateAutoSave();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        populateStaff.setOnAction(value ->  {
           try
           {
           FileChooser myFileChooser = new FileChooser();
           File file = myFileChooser.showOpenDialog(primaryStage);      
               
           Main.populateStaff(file.getPath());
           update(staffView);
           
           updateAutoSave();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        day14.setOnAction(value ->  {
           try
           {
               Main.day = "Day 1/4";
               myDay.setText(Main.day);
               day14.setDisable(true);
               day25.setDisable(false);
               day36.setDisable(false);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        day25.setOnAction(value ->  {
           try
           {
               Main.day = "Day 2/5";
               myDay.setText(Main.day);
               day14.setDisable(false);
               day25.setDisable(true);
               day36.setDisable(false);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        day36.setOnAction(value ->  {
           try
           {
               Main.day = "Day 3/6";
               myDay.setText(Main.day);
               day14.setDisable(false);
               day25.setDisable(false);
               day36.setDisable(true);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        changeName.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1)
           {
                VBox vb = new VBox();
                vb.getChildren().addAll(label1, staffName, changeNameConfirm);
                vb.setSpacing(10);
                
                staffName.clear();
                staffName.setText(Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).name);

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
        
        changeNameConfirm.setOnAction(value ->  {
           try
           {                                       
                optionView.getItems().clear();
                Main.changeName(Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()), staffName.getText());
                staffName.clear();
                update(staffView);               
                primaryStage.hide();
                
                updateAutoSave();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        setPriority.setOnAction(value ->  {
           try
           {
           if(staffView.getSelectionModel().getSelectedIndex() != -1)
           {
                VBox vb = new VBox();
                vb.getChildren().addAll(label3, activityName, label4, priority, setPriorityConfirm);
                vb.setSpacing(10);
                
                activityName.clear();
                priority.clear();

                Scene scene = new Scene(vb, 800, 600);
                primaryStage.setScene(scene);

                primaryStage.show();
           }               
           else
           {
               saveErr.setText("failed to set priority");
           }
           }
           catch(Exception e)
           {
            e.printStackTrace();
           }                        
        });
        
        setPriorityConfirm.setOnAction(value ->  {
           try
           {                                       
                optionView.getItems().clear();
                //Main.changeName(Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()), staffName.getText());
                Main.staffList.get(staffView.getSelectionModel().getSelectedIndex()).setPriority(activityName.getText(), Integer.parseInt(priority.getText()));
                activityName.clear();
                priority.clear();
                update(staffView);               
                primaryStage.hide();
                
                updateAutoSave();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        searchStaffList.setOnAction(value ->  {
           try
           {                                       
                if(searchStaff(searchBox.getText()) != -1)
                {
                    staffView.getSelectionModel().select(searchStaff(searchBox.getText()));
                    staffView.getFocusModel().focus(searchStaff(searchBox.getText()));
                    staffView.scrollTo(searchStaff(searchBox.getText()));
                    //staffView.scrollTo(searchStaff(searchBox.getText()));
                }
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        hardScheduleIndividual.setOnAction(value ->  {
           try
           {
           
                VBox vb = new VBox();
                Label name = new Label("Name: ");
                Label activity = new Label("Activity: ");
                tb1.clear();
                tb2.clear();
                vb.getChildren().addAll(name, tb1, activity, tb2, hardScheduleIndividualConfirm, cancel);
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
        
        hardScheduleIndividualConfirm.setOnAction(value ->  {
           try
           {                                       
                optionView.getItems().clear();
                Main.hardScheduleIndividual(tb1.getText(), tb2.getText());              
                primaryStage.hide();                
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        cancel.setOnAction(value ->  {
           try
           {                                       
                optionView.getItems().clear();             
                primaryStage.hide();                
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        hardScheduleJob.setOnAction(value ->  {
           try
           {
           
                VBox vb = new VBox();
                Label name = new Label("Job: ");
                Label activity = new Label("Activity: ");
                tb1.clear();
                tb2.clear();
                vb.getChildren().addAll(name, tb1, activity, tb2, hardScheduleIndividualConfirm, cancel);
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
        
        hardScheduleJobConfirm.setOnAction(value ->  {
           try
           {                                       
                optionView.getItems().clear();
                Main.hardScheduleJob(tb1.getText(), tb2.getText());              
                primaryStage.hide();                
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        hardScheduleActivity.setOnAction(value ->  {
           try
           {
           
                VBox vb = new VBox();
                Label activity = new Label("Activity: ");
                tb2.clear();
                vb.getChildren().addAll(activity, tb2, hardScheduleIndividualConfirm, cancel);
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
        
        hardScheduleActivityConfirm.setOnAction(value ->  {
           try
           {                                       
                optionView.getItems().clear();
                Main.hardScheduleActivity(tb2.getText());              
                primaryStage.hide();                
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        saveHardScheduled.setOnAction(value ->  {
           try
           {                                       
                Main.saveHardScheduled("HardScheduled.txt");
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        removeHardScheduled.setOnAction(value ->  {
           try
           {           
                optionView.getItems().clear();                              
                //optionView.getItems().add(toAdd);
                
                for(int i = 0; i < Main.hardScheduledIndividuals.size(); i += 2)
                {
                    String toAdd = "Name: " + Main.hardScheduledIndividuals.get(i) + " Activity: " + Main.hardScheduledIndividuals.get(i + 1);
                    optionView.getItems().add(toAdd);
                }
                
                for(int i = 0; i < Main.hardScheduledJobs.size(); i += 2)
                {
                    String toAdd = "Job: " + Main.hardScheduledJobs.get(i) + " Activity: " + Main.hardScheduledJobs.get(i + 1);
                    optionView.getItems().add(toAdd);
                }
                
                for(int i = 0; i < Main.hardScheduledActivities.size(); i ++)
                {
                    String toAdd = "Activity: " + Main.hardScheduledActivities.get(i);
                    optionView.getItems().add(toAdd);
                }
                
                removeHardScheduledConfirm.setDisable(false);
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        removeHardScheduledConfirm.setOnAction(value ->  {
           try
           {      
                if(optionView.getSelectionModel().getSelectedIndex() != -1)
                {
                    if(((String)optionView.getItems().get(optionView.getSelectionModel().getSelectedIndex())).substring(0,1).equals("N"))
                    {
                        Main.unhardScheduleIndividual(((String)optionView.getItems().get(optionView.getSelectionModel().getSelectedIndex())).substring(6,((String)optionView.getItems().get(optionView.getSelectionModel().getSelectedIndex())).indexOf(" Activity:")));
                    }
                    if(((String)optionView.getItems().get(optionView.getSelectionModel().getSelectedIndex())).substring(0,1).equals("J"))
                    {
                        Main.unhardScheduleJob(((String)optionView.getItems().get(optionView.getSelectionModel().getSelectedIndex())).substring(5,((String)optionView.getItems().get(optionView.getSelectionModel().getSelectedIndex())).indexOf(" Activity:")));
                    }
                    if(((String)optionView.getItems().get(optionView.getSelectionModel().getSelectedIndex())).substring(0,1).equals("A"))
                    {
                        Main.unhardScheduleActivity(((String)optionView.getItems().get(optionView.getSelectionModel().getSelectedIndex())).substring(10,((String)optionView.getItems().get(optionView.getSelectionModel().getSelectedIndex())).length()));
                    }
                }
                
                removeHardScheduledConfirm.setDisable(true);
                
                optionView.getItems().clear(); 
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        correctDoubleOffs.setOnAction(value ->  {
           try
           {                                       
                Main.doDoubleOffs = correctDoubleOffs.isSelected();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        scheduleFreePlay.setOnAction(value ->  {
           try
           {                                       
                Main.doFreePlay = scheduleFreePlay.isSelected();
           }
           catch(Exception e)
           {
           e.printStackTrace();
           }                        
        });
        
        HBox Hbox0 = new HBox(constructSchedule, correctDoubleOffs, scheduleFreePlay);
        HBox Hbox1 = new HBox(saveFilePaths, loadFilePaths, setAssignmentReview, setDivBreakdown, setBunkSchedule);
        HBox Hbox2 = new HBox(loadStaff, saveStaff);
        HBox Hbox3 = new HBox(changeJob, changeJobConfirm);
        HBox Hbox4 = new HBox(addSkill, addSkillConfirm);
        HBox Hbox5 = new HBox(removeSkill, removeSkillConfirm);
        HBox Hbox6 = new HBox(day14, day25, day36);
        HBox Hbox7 = new HBox(changeName);
        HBox Hbox8 = new HBox(searchBox, searchStaffList);
        HBox Hbox9 = new HBox(removeStaff, removeStaffConfirm);
        HBox Hbox10 = new HBox(hardScheduleIndividual, hardScheduleJob, hardScheduleActivity, saveHardScheduled);
        HBox Hbox11 = new HBox(removeHardScheduled, removeHardScheduledConfirm);
        
        InputStream stream = new FileInputStream("Resources//cropped-CampCanadensisLogo_sq.png");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(650);
        imageView.setY(0);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        
        VBox vbox = new VBox(Hbox0, CreateExcelSheet, Hbox1, Hbox2, Hbox3, Hbox4, Hbox5, Hbox6, Hbox7, Hbox8, Hbox9, Hbox10, Hbox11, addStaff, setBunk, setPriority, populateSkills, populateStaff, assignmentReview, divBreakDown, bunkSchedule, myDay, saveErr, listViews);

        vbox.setPrefHeight(835.0);
        vbox.setPrefWidth(1000.0);
       
        Scene scene = new Scene(new Group(), 1000, 835);  
        
        
        imageView.setX(scene.getWidth() - 150);
        
        Group root = (Group) scene.getRoot();
        root.getChildren().add(vbox);
        root.getChildren().add(imageView);
        //Scene scene = new Scene(vbox);              
         
        scene.setFill(new LinearGradient(
        0, 0, 1, 1, true,                      //sizing
        CycleMethod.NO_CYCLE,                  //cycling
        new Stop(0, (Color.GOLD)),     //colors Color.web
        new Stop(1, (Color.MEDIUMBLUE)))
        );
        
        //scene.setFill(Color.rgb(208,69,28));
        //root.getChildren().add(r);
        
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Abe's Funky Radical Staff Scheduling Experience");
        
        secondaryStage.setScene(scene);

        
        
        secondaryStage.show();
    }
    
    public static void alphabetize(ListView LV)
    {
        LinkedList<String> sorted = new LinkedList();
        sorted.add((String)LV.getItems().get(0));
        for(int i = 1; i < LV.getItems().size(); i++)
        {
            boolean isSorted = false;
            for(int j = 0; j < sorted.size(); j++)
            {
                if(j == 0 && sorted.get(j).compareToIgnoreCase((String)LV.getItems().get(i)) > 0)
                {
                    sorted.add(j, (String)LV.getItems().get(i));
                    j = sorted.size();
                    isSorted = true;
                }
                if(j != sorted.size() && j != 0 && sorted.get(j-1).compareToIgnoreCase((String)LV.getItems().get(i)) < 0 && sorted.get(j).compareToIgnoreCase((String)LV.getItems().get(i)) > 0)
                {
                    sorted.add(j, (String)LV.getItems().get(i));
                    j = sorted.size();
                    isSorted = true;
                }
                if(j != sorted.size() && j == sorted.size() - 1 && sorted.get(j).compareToIgnoreCase((String)LV.getItems().get(i)) < 0)
                {
                    sorted.add((String)LV.getItems().get(i));
                    j = sorted.size();
                    isSorted = true;
                }
            }
            if(!isSorted)
            {
                System.out.println("activity not sorted: " + (String)LV.getItems().get(i));
            }
        }
        
        LV.getItems().clear();
        for(int i = 0; i < sorted.size(); i++)
        {
            LV.getItems().add(sorted.get(i));
        }
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
            for(int j = 0; j < Main.staffList.get(i).priorities.size(); j++)
            {
                toAdd += (Main.staffList.get(i).priorities.get(j) + " ");
            }
            myLV.getItems().add(toAdd);
        }
    }
    
    public int searchStaff(String toSearch)
    {
        for(int i = 0; i < staffView.getItems().size(); i++)
        {
            if(((String)staffView.getItems().get(i)).toLowerCase().contains(toSearch.toLowerCase()))
            {
                return i;
            }
        }        
        return -1;
    }
    
    public static void updateAutoSave()
    {
        Main.saveStaff("Autosave");
    }
    
    public static void launchUI(String[] args) {
        Application.launch(args);
    }
}
