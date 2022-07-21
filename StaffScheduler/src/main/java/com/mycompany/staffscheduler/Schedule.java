/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staffscheduler;

import static com.mycompany.staffscheduler.Main.staffList;
import static com.mycompany.staffscheduler.Main.updateDirectorList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Abe
 */
public class Schedule 
{
    static String[][] mySchedule;
    static String[] myFreePlaySchedule = null;
    
    static int[][] electivePeriodsG = 
    {{2, 1},
     {3, 1},
     {4, 1}, 
     {5, 1}, 
     {6, 1}, 
     {7, 1}, 
     {8, 1}, 
     {9, 1}, 
     {10, 1}, 
     {11, 4}, 
     {12, 4}, 
     {13, 4}, 
     {14, 24}, 
     {15, 24}, 
     {16, 24}, 
     {17, 24}, 
     {18, 25}, 
     {19, 25}, 
     {20, 25}, 
     {21, 35}, 
     {22, 35}, 
     {23, 35}, 
     {24, 35},
     {25, 35},
     {26, 35},
     {27, 3}};
    
    static int[][] electivePeriodsB = 
    {{2, 1},
     {3, 1},
     {4, 1}, 
     {5, 1}, 
     {6, 1}, 
     {7, 1}, 
     {8, 1}, 
     {9, 1}, 
     {10, 1}, 
     {11, 4}, 
     {12, 4}, 
     {13, 4}, 
     {14, 24}, 
     {15, 24}, 
     {16, 24}, 
     {17, 25}, 
     {18, 25}, 
     {19, 35}, 
     {20, 35}, 
     {21, 35}, 
     {22, 35}, 
     {23, 35}, 
     {24, 35},
     {25, 3}};
                                           //       //      //          //              //              //              //                  //           //       //        //          //              //              //              //          //              //                  //            //                //                  //                  //              //              //              //         //       //         //       //              //      //          //              //              //       //             //          //        //        //          //          //          //                 //               //               //                 //      //      //       //     //      //              //              //           //                 //            //            //              //              //           //             //                  //                          //                              //          //              //                  //              //                  //          //      //          //              //             //           //                  //            //          //                //                  //              //      //       //         //              //          //                  //                  //          //              //           //          //             //              //                  //              //              //              //              //       //       //            //              //              //          //             //               //              //          //         //       //                  //              //              //              //                          //                  //                      //                   //                     //                  //                  //              //      //          //          //                  //          //              //                  //                  //          //                  //              //              //              //                  //              //              //          //          //              //              //            //            //              //              //              //                  //
    public static String[] activities = {"OFF", "Sailing", "Lake", "Bananna Boat", "Ice Mountain", "Lake Boats", "Lake Intruction", "Lake Recreation", "Swim", "Pool", "Blue Pool", "Pool Party", "Waterslides", "Swim Instruction", "Swim Rec", "Swim Team", "Video Editing", "Video Production", "Ropes", "Ropes & Climbing Wall", "Climbing Wall", "Outdoor Cooking", "Mountain Bikes", "MTB Road Riders", "Hondas", "Quads", "Fishing", "STEM", "Board Games", "Coding", "Lego", "Model Making", "Perler Beads", "Robotics", "Rocketry", "Rubik's Cube", "Weights", "Zumba", "Aerobics", "Bootcamp", "Yoga", "Fitness Center", "Basketball", "Basketball Clinic", "Basketball League", "Varsity", "WNBA", "WNCAA", "NCAA", "NBA", "Hockey", "Floor Hockey", "Roller Hockey", "Hockey Clinic", "Hockey League", "Soccer", "Soccer Clinic", "Soccer League", "Lacrosse", "Ninja Warrior", "Gymnastics", "Gymnastics -Foam Pit", "Gymnastics - Bar, Beam, Floor & Vault", "Softball", "Baseball", "Softball Clinic", "Softball League", "Baseball Clinic", "Baseball League", "MLB", "CBL", "Batting Cage", "Cheer Leading", "Archery", "Flag Football", "Beach Volleyball", "Newcomb", "Volleyball", "Volleyball Leagues", "Volleyball Clinic", "Golf", "SNAG", "Cooking", "Painting", "Glass Fusion", "Art Shack", "Beading & Bracelets", "Calligraphy", "Zendoodle", "T-Shirt Studio", "Dance", "Woodworking", "Dramatic Arts", "Ukulele", "Digital Art & Design", "Photo - Digital", "Ceramics", "Ceramics Wheel", "Crochet", "Gaga", "Kiting", "Magic Tricks", "Mah Jongg", "Old Alma Maters", "Ping Pong", "Tetherball", "Ultimate Frisbee", "Book Club", "Running", "Bunko", "Card Games", "CIT Activity", "Jump Rope Skills", "Kickboxing", "Entrepreneurship ", "Guided Meditation with ", "Track & Field", "Gymnastics - Bar, Beam, ", "Dance - Hip Hop", "Gymnastics - Foam Pit", "Photography", "Stationery Making", "Tik Tok", "Zen Art", "Jewelry", "Sticker Design", "Meditation", "Spike Ball", "Computer Art", "Dance - Musical Theater", "Slime", "STEM - Experiments ", "Improv", "Graphic Design", "Dance - Tik Tok", "Floor & Vault", "STEM - Challenge", "Photoshop", "Pickle Ball", "SL Pool", "SL Gaga", "SL VolleyBall", "Outdoor Pursuits", "Horses", "Lacrosse Boys", "Lacrosse Girls", "Media", "Evening Activities", "Schedule Later"};
    public static double[] activityRatios = {0.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 10.0, 10.0, 10.0, 10.0, 10.0, 8.0, 10.0, 8.0, 6.0, 6.0, -1.0, -1.0, -1.0, 8.0, 5.0, 8.0, -1.0, -1.0, 8.0, 6.0, 12.0, 6.0, 10.0, 10.0, 10.0, 6.0, 6.0, 6.0, 8.0, 12.0, 12.0, 12.0, 10.0, 8.0, 15.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 8.0, 8.0, 8.0, 8.0, 8.0, 12.0, 12.0, 12.0, 5.0, 8.0, 8.0, 8.0, 8.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 14.0, 5.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 5.0, 8.0, 5.0, 8.0, 12.0, 8.0, 8.0, 8.0, 10.0, 10.0, 10.0, 8.0, 6.0, 6.0, 8.0, 6.0, 10.0, 12.0, 10.0, 6.0, 8.0, 8.0, 8.0, 8.0, 8.0, 15.0, 5.0, 0.0, 12.0, 16.0, 10.0, 8.0, 7.0, 10.0, 12.0, 8.0, 12.0, 8.0, 8.0, 12.0, 8.0, 10.0, 10.0, 5.0, 12.0, 8.0, 5.0, 12.0, 10.0, 10.0, 6.0, 6.0, 12.0, 8.0, 10.0, 5.0, 8.0, 30.0, 40.0, 40.0, 5.0, -1.0, 15.0, 15.0, -1.0, -1.0, -1.0};
    //-1=All staff,                          //    //     //    //    //    //    //    //   //     //   //     //    //  //    //    //  //    //   //     //    //  //    //   //    //   //    //   //   //    //    //    //    //   //   //   //   //   //    //    //     //   //   //    //     //   //     //   //     //   //    //   //   //  //    //    //   //     //   //   //  //    //   //   //    //     //   //     //   //    //    //     //   //     //   //   //     //    //    //   //     //    //   //   //   //   //    //   //   //   //   //    //    //    //  //    //  //    //    //   //     //   //   //  //    //   //   //    //   //   //    //   //     //   //   //    //   //    //   //    //   //   //    //    //   //    //   //    //   //   //     //   //    //   //   //   //     //   //   //    //   //     //   //    //   //    //     //    //    //
    public static LinkedList<Activity> myActivities;
    
    public Schedule(LinkedList<Employee> myEmployees)
    {
        //myActivities  = new LinkedList();
        /*
        for(int i = 0; i < activities.length; i++)
        {
            myActivities.add(new Activity(activities[i], activityRatios[i]));
        }
        */
        
        //loadActivities("ActivityParameters.txt");
        
        mySchedule =  new String[myEmployees.size()][8];
        for(int i = 0; i < myEmployees.size(); i++)
        {
            mySchedule[i][0] = myEmployees.get(i).bunk;
            mySchedule[i][1] = myEmployees.get(i).name;
            
            if(!mySchedule[i][0].equalsIgnoreCase("OOB"))
            {
                int mybunk = Integer.parseInt(mySchedule[i][0].substring(1)); 
                //this is where you need to separate boys from girls bunks
                
                if(mySchedule[i][0].substring(0, 1).equals("b"))
                {
                    for(int j = 0; j < electivePeriodsB.length; j++)
                    {
                        if(electivePeriodsB[j][0] == mybunk)
                        {
                            if(electivePeriodsB[j][1] < 10)
                            {
                                mySchedule[i][electivePeriodsB[j][1] + 1] = "OFF";
                            }
                            else
                            {
                                mySchedule[i][(electivePeriodsB[j][1] % 10) + 1] = "OFF";
                                mySchedule[i][((electivePeriodsB[j][1] - (electivePeriodsB[j][1] % 10))/10) + 1] = "OFF";
                            }
                        }
                    }
                }
                if(mySchedule[i][0].substring(0, 1).equals("g"))
                {
                    for(int j = 0; j < electivePeriodsG.length; j++)
                    {
                        if(electivePeriodsG[j][0] == mybunk)
                        {
                            if(electivePeriodsG[j][1] < 10)
                            {
                                mySchedule[i][electivePeriodsG[j][1] + 1] = "OFF";
                            }
                            else
                            {
                                mySchedule[i][(electivePeriodsG[j][1] % 10) + 1] = "OFF";
                                mySchedule[i][((electivePeriodsG[j][1] - (electivePeriodsG[j][1] % 10))/10) + 1] = "OFF";
                            }
                        }
                    }
                }
                
            }
            else
            {
                //mySchedule[i][2] = "OFF";
            }            
        }
    }
    
    static Activity getActivity(String a)
    {
        for(int i = 0; i < myActivities.size(); i++)
        {
            if(myActivities.get(i).name.equals(a))
            {
                return myActivities.get(i);
            } 
        }
        return null;
    }
    
    public static void assignAll(String activity, int numCampers, int period, LinkedList<Employee> myEmployees, double elective)
    {
        
        
        if(getActivity(activity).numScheduled[period - 1] == -1 && numCampers != 0)
        {
            getActivity(activity).numScheduled[period - 1] = 0;
        }
        
        for(int i = 0; i < myActivities.size(); i++)
        {
            System.out.println(myActivities.get(i).name);
        }
        
        if(myEmployees == Main.directorList)
        {
            System.out.println(activity);
            getActivity(activity).numCampers[period - 1] += numCampers;
        }        
        
        
        
        int numAssigned = 0;
        for(int i = 0; i < mySchedule.length; i++)
        {
            if(mySchedule[i][period+1] != null && mySchedule[i][period+1].equals(activity))
            {
                numAssigned += 1;
            }
        }
        
        LinkedList<Employee> skilledEmployees = new LinkedList<>();
        for(int i = 0; i < myEmployees.size(); i++)
        {
            if((myEmployees != Main.directorList || Main.contains(Main.getPositions(activity), myEmployees.get(i).position)) && myEmployees.get(i).skills.contains(activity) && (getAssignment(myEmployees.get(i).name, period) == null || ((!Main.contains(Main.getActivities(myEmployees.get(i).position), getAssignment(myEmployees.get(i).name, period)) && Main.contains(Main.getActivities(myEmployees.get(i).position), activity)) || (getAssignment(myEmployees.get(i).name, period) == null || getAssignment(myEmployees.get(i).name, period).equals("OFF"))) && (coverage(myEmployees.get(i).bunk, period) > 0 || (Main.contains(Main.getActivities(myEmployees.get(i).position), activity) && coverage(myEmployees.get(i).bunk, period) < 0  && !Main.contains(Main.getActivities(myEmployees.get(i).position), getAssignment(myEmployees.get(i).name, period)) && !getAssignment(myEmployees.get(i).name, period).equals(activity)))))
            {
                skilledEmployees.add(myEmployees.get(i));
                if(activity.equals("Soccer"))
                    {
                        //System.out.println("skilled emp: " + skilledEmployees.size());
                    }
            }
        }
        
        
        boolean titledEmployeesExhausted = true;
        for(int i = 0; i < skilledEmployees.size(); i++)
        {
            if(Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position))
            {
                titledEmployeesExhausted = false;
                if(activity.equals("Outdoor Cooking"))
                {
                    System.out.println(skilledEmployees.get(i).name + " " + titledEmployeesExhausted);
                }
            }
        }
        
        int[] skilledCoverages = new int[skilledEmployees.size()];
        
        int bestCoverage = -1;
        
        for(int i = 0; i  < skilledEmployees.size(); i++)
        {
            skilledCoverages[i] = coverage(skilledEmployees.get(i).bunk, period);
            if(coverage(skilledEmployees.get(i).bunk, period) == -1 && Main.contains(Main.getActivities(myEmployees.get(i).position), activity) && !Main.contains(Main.getActivities(myEmployees.get(i).position), getAssignment(myEmployees.get(i).name, period)) && !getAssignment(myEmployees.get(i).name, period).equals(activity))
            {
                skilledCoverages[i] = 10;
            }
            if((titledEmployeesExhausted || (!titledEmployeesExhausted && Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position))) && skilledCoverages[i] > bestCoverage)
            {
                bestCoverage = skilledCoverages[i];
                if(activity.equals("Outdoor Cooking"))
                {
                    System.out.println(skilledEmployees.get(i).name + " best coverage " + bestCoverage + " " + titledEmployeesExhausted);
                }
            }
        } 
        
        while(bestCoverage > -1)
        {
            LinkedList<Employee> assignmentPool = new LinkedList();
            int highestPriority = 0;
            
            for(int i = 0; i < skilledEmployees.size(); i++)
            {
                if(skilledEmployees.get(i).getPriority(activity) > highestPriority && skilledCoverages[i] != -1)
                {
                    highestPriority = skilledEmployees.get(i).getPriority(activity);
                }
            }
            
            for(int i = 0; i < skilledEmployees.size(); i++)
            {
                if(activity.equals("Soccer"))
                {
                    System.out.println("skilled employees size " + skilledEmployees.size());
                }
                if((titledEmployeesExhausted || (!titledEmployeesExhausted && Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position))) && skilledCoverages[i] == bestCoverage  && skilledCoverages[i] != -1)
                {
                    assignmentPool.add(skilledEmployees.get(i));
                    
                    skilledCoverages[i] = -1;
                }
            }
            
            boolean isElective = false;
            if(elective == 0.0)
            {
                isElective = true;
            }
            // + ((double)(getActivity(activity).countCounselorsBunk * elective) * ((double)getActivity(activity).numCampers[period - 1]/10.0)) left side
            if((assignmentPool.size() + numAssigned) >= Main.getReqCounselors(getActivity(activity).numCampers[period - 1], activity, isElective) || (numAssigned == 0 && assignmentPool.size() > 0 && getActivity(activity).numCampers[period - 1] > 0))
            {
                boolean enoughInPool = true;
                //double requiredCounselors = ((double)getActivity(activity).numCampers[period - 1] / idealRatio) - (double)numAssigned - (elective * ((double)getActivity(activity).numCampers[period - 1]/10.0));
                
                int requiredCounselors = Main.getReqCounselors(getActivity(activity).numCampers[period - 1], activity, isElective) - numAssigned;
                if(activity.equals("Soccer"))
                    {
                        System.out.println("assignment attempted. Assignment pool: " + assignmentPool.size() + " num assigned: " + numAssigned + " required counselors: " + requiredCounselors + " " + elective + " " + Main.getReqCounselors(getActivity(activity).numCampers[period - 1], activity, isElective) + " " + getActivity(activity).numCampers[period - 1]);
                    }
                while(enoughInPool && (requiredCounselors > 0 || (numAssigned == 0 && getActivity(activity).numCampers[period - 1] > 0)) && assignmentPool.size() > 0) //possible issue here with assignment pool's size
                {
                    int toAssign = (int)(Math.random() * Double.valueOf(assignmentPool.size()));
                    //System.out.println((assignmentPool.get(toAssign)).name);
                    boolean considerOffs = !(Schedule.hasDoubleOffs(assignmentPool.get(toAssign).name));
                    
                    boolean success = assignActivity(activity, period, assignmentPool.get(toAssign), considerOffs);
                    if(success)
                    {
                    numAssigned += 1;
                    requiredCounselors -= 1.0;                    
                    }      
                    /*else
                    {
                        enoughInPool = ((double)assignmentPool.size() + (double)numAssigned) >= ((double)getActivity(activity).numCampers[period - 1] / idealRatio) - (elective * (getActivity(activity).numCampers[period - 1]/10.0));
                    }*/
                    //need to go back in and factor in skilled employees who have their off period
                    assignmentPool.remove(toAssign); 
                    enoughInPool = (assignmentPool.size() + numAssigned) >= Main.getReqCounselors(getActivity(activity).numCampers[period - 1], activity, isElective);
                    if(activity.equals("Woodworking"))
                    {
                        //System.out.println("assignment attempted. Assignment pool: " + assignmentPool.size() + " num assigned: " + numAssigned + " required counselors: " + requiredCounselors);
                    }
                }
                if(requiredCounselors <= 0)
                {
                    return;
                }                
            }
            else
            {
                for(int i = assignmentPool.size() - 1; i >= 0; i--)
                {
                    boolean considerOffs = !(Schedule.hasDoubleOffs(assignmentPool.get(i).name));
                    //System.out.println(assignmentPool.get(i).name);
                    boolean success = assignActivity(activity, period, assignmentPool.get(i), considerOffs);
                    if(success)
                    {
                        numAssigned += 1;
                    }                    
                    assignmentPool.remove(i);
                    if(activity.equals("Soccer"))
                    {
                        System.out.println("assignment attempted. Assignment pool: " + assignmentPool.size());
                    }
                }
            }
            bestCoverage = -1;

            titledEmployeesExhausted = true;
            for(int i = 0; i < skilledEmployees.size(); i++)
            {
                if(Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position) && skilledCoverages[i] != -1)
                {
                    titledEmployeesExhausted = false;
                }
            }
            
            
            for(int i = 0; i  < skilledEmployees.size(); i++)
            {
                if((titledEmployeesExhausted || (!titledEmployeesExhausted && Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position))) && skilledCoverages[i] > bestCoverage)
                {
                    bestCoverage = skilledCoverages[i];
                    //System.out.println(bestCoverage);
                }
            }
            
            
        }
        //if this point is reached, there were not enough skilled employees to satisfy the demand
        
        boolean isElective = false;
        if(elective == 0.0)
        {
            isElective = true;
        }
        
        if(myEmployees != Main.directorList)
        {
            Main.warnings.add("not enough skilled employees for " + activity + " period " + period + ". assigned: " + numAssigned + " needed: " + Main.getReqCounselors(getActivity(activity).numCampers[period - 1], activity, isElective));
        }
        
        //need to make a string function to see if a period is occupied/by what based on emoployee name
    }
    
    String getAssignment(int period, String name)
    {
        for(int i = 0; i < mySchedule.length; i++)
        {
            if(mySchedule[i][1].equals(name))
            {
                return mySchedule[i][period + 1];
            }
        }
        return "error: name not found";
    }
    
    public static boolean assignActivity(String activity, int period, Employee toAssign, boolean considerOffs)
    {
        if(getActivity(activity).numScheduled[period - 1] == -1)
        {
            getActivity(activity).numScheduled[period - 1] += 1;
        }
        
        for(int i = 0; i < mySchedule.length; i++)
        {
            if(mySchedule[i][1].equals(toAssign.name))
            {
                if(mySchedule[i][period + 1] != null && !mySchedule[i][period + 1].equals("OFF"))
                {
                    
                    if(mySchedule[i][period + 1].equals(activity))
                    {
                        return false;
                    }
                    
                    String prevActivity = mySchedule[i][period + 1];
                    mySchedule[i][period + 1] = activity;
                    getActivity(activity).numScheduled[period - 1] += 1;
                    
                    getActivity(prevActivity).numScheduled[period - 1] -= 1;
                    
                    double elective = 0.0;
                    if(!getActivity(prevActivity).elective[period - 1])
                    {
                        elective = 1.0;
                    }
                    System.out.println("trying to schedule " + activity + " over " + prevActivity + " period " + period);
                    assignAll(prevActivity, 0, period, Main.staffList, elective);
                    
                    return true;
                }
                
                if(considerOffs)
                {
                    if(mySchedule[i][period + 1] != null && mySchedule[i][period + 1].equals("OFF"))
                    {
                        int bestCoverage = 0;
                        int bestPeriod = -1;
                        for(int j = 1; j < 7; j++)
                        {
                            if(j != period)
                            {
                                if(coverage((toAssign.bunk), j) > bestCoverage && mySchedule[i][j + 1] == null)
                                {
                                    bestCoverage = coverage((toAssign.bunk), j);
                                    bestPeriod = j;
                                }
                            }
                        }
                        if(bestCoverage > 1)
                        {
                            assignActivity("OFF", bestPeriod, toAssign, false);
                            mySchedule[i][period + 1] = activity;
                            getActivity(activity).numScheduled[period - 1] += 1;
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
                else
                {
                    if(mySchedule[i][period + 1] ==  null || mySchedule[i][period + 1].equals("OFF"))
                    {
                        mySchedule[i][period + 1] = activity;
                        getActivity(activity).numScheduled[period - 1] += 1;
                        return true;
                    }
                }
                
                if(mySchedule[i][period + 1] ==  null)
                {
                    mySchedule[i][period + 1] = activity;
                    getActivity(activity).numScheduled[period - 1] += 1;
                    return true;
                }                                                
            }
        }
        return false;
    }
    
    public static void assignActivityStrong(String activity, Employee toAssign, int p1, int p2, int p3, int p4, int p5, int p6)
    {
        for(int i = 0; i < mySchedule.length; i++)
        {
            if(mySchedule[i][1].equals(toAssign.name))
            {
                if(p1 != -1)
                {
                    mySchedule[i][p1 + 1] = activity;
                    /*if(getActivity(activity).numScheduled[p1 - 1] == -1)
                    {
                    getActivity(activity).numScheduled[p1 - 1] += 1;
                    }
                    getActivity(activity).numScheduled[p1 - 1] += 1;*/
                }
                if(p2 != -1)
                {
                    mySchedule[i][p2 + 1] = activity;
                    /*if(getActivity(activity).numScheduled[p2 - 1] == -1)
                    {
                    getActivity(activity).numScheduled[p2 - 1] += 1;
                    }
                    getActivity(activity).numScheduled[p2 - 1] += 1;*/
                }
                if(p3 != -1)
                {
                    mySchedule[i][p3 + 1] = activity;
                    /*if(getActivity(activity).numScheduled[p3 - 1] == -1)
                    {
                    getActivity(activity).numScheduled[p3 - 1] += 1;
                    }
                    getActivity(activity).numScheduled[p3 - 1] += 1;*/
                }
                if(p4 != -1)
                {
                    mySchedule[i][p4 + 1] = activity;
                    /*if(getActivity(activity).numScheduled[p4 - 1] == -1)
                    {
                    getActivity(activity).numScheduled[p4 - 1] += 1;
                    }
                    getActivity(activity).numScheduled[p4 - 1] += 1;*/
                }
                if(p5 != -1)
                {
                    mySchedule[i][p5 + 1] = activity;
                    /*if(getActivity(activity).numScheduled[p5 - 1] == -1)
                    {
                    getActivity(activity).numScheduled[p5 - 1] += 1;
                    }
                    getActivity(activity).numScheduled[p5 - 1] += 1;*/
                }
                if(p6 != -1)
                {
                    mySchedule[i][p6 + 1] = activity;
                    /*if(getActivity(activity).numScheduled[p6 - 1] == -1)
                    {
                    getActivity(activity).numScheduled[p6 - 1] += 1;
                    }
                    getActivity(activity).numScheduled[p6 - 1] += 1;*/
                }
            }
        }
    }
    
    public static void assignAllStrong(String activity, LinkedList<Employee> myStaff)
    {
        for(int i = 0; i < myStaff.size(); i++)
        {
            assignActivityStrong(activity, myStaff.get(i), 1, 2, 3, 4, 5, 6);
        }
    }
    
    public static int coverage(String bunk, int period)
    {
        if(bunk.equalsIgnoreCase("OOB"))
        {
            return 10;
        }
        
        if(bunk.substring(0, 1).equals("b"))
        {
            for(int j = 0; j < electivePeriodsB.length; j++)
            {
                if(electivePeriodsB[j][0] == Integer.parseInt(bunk.substring(1)))
                {
                    if(electivePeriodsB[j][1] < 10)
                    {
                        if(electivePeriodsB[j][1] == period)
                        {
                            return 10;
                        }
                    }
                    else
                    {
                        if(electivePeriodsB[j][1] % 10 == period)
                        {
                            return 10;
                        }
                        if((electivePeriodsB[j][1] - (electivePeriodsB[j][1] % 10))/10 == period)
                        {
                            return 10;
                        }
                    }
                }
            }
        }
        if(bunk.substring(0, 1).equals("g"))
        {
            for(int j = 0; j < electivePeriodsG.length; j++)
            {
                if(electivePeriodsG[j][0] == Integer.parseInt(bunk.substring(1)))
                {
                    if(electivePeriodsG[j][1] < 10)
                    {
                        if(electivePeriodsG[j][1] == period)
                        {
                            return 10;
                        }
                    }
                    else
                    {
                        if(electivePeriodsG[j][1] % 10 == period)
                        {
                            return 10;
                        }
                        if((electivePeriodsG[j][1] - (electivePeriodsG[j][1] % 10))/10 == period)
                        {
                            return 10;
                        }
                    }
                }
            }
        }
                
        int toReturn = 0;
        for(int i = 0; i < mySchedule.length; i++)
        {
            if(mySchedule[i][0].equals(bunk) && mySchedule[i][period + 1] == null)
            {
                toReturn += 1;
            }
        }
        return toReturn;
    }
    
    public void printSchedule()
    {
        
        for(int i = 0; i < mySchedule.length; i++)
        {
            for(int j = 0; j < mySchedule[i].length; j++)
            {
                if(j == 0)
                {
                    System.out.print(mySchedule[i][j].toUpperCase());
                }
                else
                {
                    System.out.print(mySchedule[i][j]);
                }                
            }
            System.out.println();
        }
    }
    
    public static void scheduleToExcel(String myPath) throws FileNotFoundException, IOException, InvalidFormatException
    {
        XSSFWorkbook workbook = null;
        File file = new File(myPath);
        FileOutputStream fileOut = new FileOutputStream(file);
        
        workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Staff Schedule");
        
        for(int i = 0; i < mySchedule.length; i++)
        {
            workbook.getSheetAt(0).createRow(i);
            for(int j = 0; j < mySchedule[0].length; j++)
            {
                if(mySchedule[i][j] != null)
                {
                    workbook.getSheetAt(0).getRow(i).createCell(j);
                }                
            }
        }
        
        for(int i = 0; i < mySchedule.length; i++)
        {
            for(int j = 0; j < mySchedule[0].length; j++)
            {
                if(mySchedule[i][j] != null)
                {
                    if(j != 1)
                    {
                        workbook.getSheetAt(0).getRow(i).getCell(j).setCellValue(mySchedule[i][j].toUpperCase());
                    }
                    else
                    {
                        workbook.getSheetAt(0).getRow(i).getCell(j).setCellValue(mySchedule[i][j]);
                    }
                }                
            }
        }
        
        XSSFSheet sheet2 = workbook.createSheet("Staff Per Activity");
        XSSFSheet sheet3 = workbook.createSheet("Campers Per Activity");
        
        printActivities(true, workbook);
        
        XSSFSheet sheet4 = workbook.createSheet("Warnings");
        //Main.correctWarnings();
        
        if(myFreePlaySchedule != null && Main.doFreePlay)
        {
            for(int i = 0; i < myFreePlaySchedule.length; i++)
            {
                if(myFreePlaySchedule[i] != null)
                {
                    workbook.getSheetAt(0).getRow(i).createCell(mySchedule[0].length);
                    workbook.getSheetAt(0).getRow(i).getCell(mySchedule[0].length).setCellValue(myFreePlaySchedule[i].toUpperCase());                
                }
            }
        }
        
        for(int i = 0; i < Main.warnings.size(); i++)
        {
            workbook.getSheetAt(3).createRow(i);
            workbook.getSheetAt(3).getRow(i).createCell(0);
            workbook.getSheetAt(3).getRow(i).getCell(0).setCellValue(Main.warnings.get(i));
        }                
        
        workbook.write(fileOut);
        fileOut.close();
    }
    
    public static boolean hasDoubleOffs(String name)
    {
        int myEmployeeIndex = -1;
        for(int i = 0; i < mySchedule.length; i++)
        {
            if(mySchedule[i][1].equals(name))
            {
                myEmployeeIndex = i;
                i = mySchedule.length;
            }
        }
        boolean firstOff = false;
        boolean secondOff = false;
        if(myEmployeeIndex != -1)
        {
            for(int i = 0; i < 7; i++)
            {
                if(mySchedule[myEmployeeIndex][i+1] != null && mySchedule[myEmployeeIndex][i+1].equals("OFF"))
                {
                    if(firstOff)
                    {
                        secondOff = true;
                        return secondOff;
                    }
                    if(!firstOff)
                    {
                        firstOff = true;
                    }
                }
            }
        }
        return false;
    }
    
    public static void correctDoubleOffs()
    {
        for(int i = 0; i < mySchedule.length; i++)
        {
            boolean doubleOff = false;
            int firstOff = -1;
            int secondOff = -1;
            for(int j = 0; j < 7; j++)
            {
                if(mySchedule[i][j+1] != null && mySchedule[i][j+1].equals("OFF"))
                {
                    if(firstOff == -1)
                    {
                        firstOff = j;
                    }
                    else
                    {
                        secondOff = j;
                        doubleOff = true;
                    }
                }
            }
            if(doubleOff)
            {
                /*
                if(coverage(mySchedule[i][0], firstOff) != 10)
                {
                    System.out.println("mark1");
                    
                    mySchedule[i][firstOff + 1] = null;
                    return;
                }
                if(coverage(mySchedule[i][0], secondOff) != 10)
                {
                    System.out.println("mark2");
                    
                    mySchedule[i][secondOff + 1] = null;
                    return;
                }
                */
                
                Employee myEmployee = Main.getEmployeeByName(mySchedule[i][1]);
                String neededActivity = "OFF";
                int neededPeriod = -1;
                double mostReqCounselors = -100.0;
                for(int k = 0; k < myEmployee.skills.size(); k++)
                {
                    Activity a = getActivity(myEmployee.skills.get(k));
                    double elective;
                    elective = 1.0;
                    //System.out.println(a.elective[firstOff-1]);
                    if(a != null && !a.name.equals("OFF") && a.elective[firstOff-1])
                    {
                        elective = 0.0;
                    }
                    // && Main.getReqCounselors(a.numCampers[firstOff - 1], a.name, a.elective[firstOff - 1])
                    if(a != null && !a.name.equals("OFF") && !a.name.equals("Swim Team") && !a.name.equals("Pool") && !a.name.equals("Cooking"))
                    {
                        if(a.numScheduled[firstOff - 1] != -1  && elective == 0.0 && Double.valueOf(a.numScheduled[firstOff - 1] + 1) < 1.5 * Double.valueOf(Main.getReqCounselors(a.numCampers[firstOff - 1], a.name, a.elective[firstOff - 1])) && a.numScheduled[firstOff - 1] + 1 < a.maxElective && a.numCampers[firstOff -1] > 7)
                        {
                            //double reqCounselors = (double)a.numCampers[firstOff - 1] / ((double)a.idealRatio) - ((double)a.numScheduled[firstOff - 1] - (elective * ((double)a.numCampers[firstOff - 1] / (10.0))));
                            int reqCounselors = Main.getReqCounselors(getActivity(a.name).numCampers[firstOff - 1], a.name, false) - (a.numScheduled[firstOff - 1]);
                            //double requiredCounselors = ((double)getActivity(activity).numCampers[period - 1] / idealRatio) - (double)numAssigned - (elective * (getActivity(activity).numCampers[period - 1]/10.0));
                            //double reqCounselors = Double.valueOf(getActivity(a.name).numCampers[firstOff - 1]) / Double.valueOf(a.numScheduled[firstOff - 1]);
                            if(reqCounselors > mostReqCounselors)
                            {
                                neededActivity = a.name;
                                neededPeriod = firstOff;
                                mostReqCounselors = reqCounselors;
                            }
                        }
                        elective = 1.0;
                        if(a.elective[secondOff-1])
                        {
                            elective = 0.0;
                        }
                        if(a.numScheduled[secondOff - 1] != -1 && elective == 0.0 && Double.valueOf(a.numScheduled[secondOff - 1] + 1) < 1.5 * Double.valueOf(Main.getReqCounselors(a.numCampers[secondOff - 1], a.name, a.elective[secondOff - 1])) && a.numScheduled[secondOff - 1] + 1 < a.maxElective && a.numCampers[secondOff -1] > 7)
                        {
                            int reqCounselors = Main.getReqCounselors(getActivity(a.name).numCampers[secondOff - 1], a.name, false) - (a.numScheduled[secondOff - 1]);
                            //double reqCounselors = Double.valueOf(getActivity(a.name).numCampers[secondOff - 1]) / Double.valueOf(a.numScheduled[secondOff - 1]);
                            if(reqCounselors > mostReqCounselors)
                            {
                                neededActivity = a.name;
                                neededPeriod = secondOff;
                                mostReqCounselors = reqCounselors;
                            }
                        }
                    }                                       
                }
                // && !neededActivity.equals("Swim") && !neededActivity.equals("Pool") && !neededActivity.equals("Blue Pool") && !neededActivity.equals("Pool Party") && !neededActivity.equals("Waterslides") && !neededActivity.equals("Swim Instruction") && !neededActivity.equals("Swim Rec") && !neededActivity.equals("Swim Team")
                //this line assigns the counselor to an activity based upon their skills without looking at the need in other areas
                if(mostReqCounselors != -100 && !neededActivity.equals("OFF") && !neededActivity.equals("Tennis") && !neededActivity.equals("Pickle Ball") && !neededActivity.equals("Sailing") && !neededActivity.equals("Lake") && !neededActivity.equals("Banana Boat") && !neededActivity.equals("Ice Mountain") && !neededActivity.equals("Lake Boats") && !neededActivity.equals("Lake Instruction") && !neededActivity.equals("Lake Recreation") && !neededActivity.equals("Ropes") && !neededActivity.equals("Climbing Wall") && !neededActivity.equals("Ropes & Climbing Wall") && !neededActivity.equals("Hondas") && !neededActivity.equals("Quads") && neededPeriod != -1)
                {
                    assignActivity(neededActivity, neededPeriod, myEmployee, false);
                }
                else
                {
                    mostReqCounselors = -100;
                    for(int j = 0; j < myActivities.size(); j++)
                    {
                        Activity a = myActivities.get(j);
                        // && !a.name.equals("Pool") && !a.name.equals("Blue Pool") && !a.name.equals("Pool Party") && !a.name.equals("Waterslides") && !a.name.equals("Swim Instruction") && !a.name.equals("Swim Rec") && !a.name.equals("Swim Team")
                        if(a != null && !a.name.equals("OFF") && !a.name.equals("Tennis") && !a.name.equals("Pickle Ball") && !a.name.equals("Sailing") && !a.name.equals("Lake") && !a.name.equals("Banana Boat") && !a.name.equals("Ice Mountain") && !a.name.equals("Lake Boats") && !a.name.equals("Lake Instruction") && !a.name.equals("Lake Recreation") && !a.name.equals("Swim") && !a.name.equals("Ropes") && !a.name.equals("Climbing Wall") && !a.name.equals("Ropes & Climbing Wall") && !a.name.equals("Hondas") && !a.name.equals("Quads")&& !a.name.equals("Pool") && !a.name.equals("Blue Pool") && !a.name.equals("Pool Party") && !a.name.equals("Waterslides") && !a.name.equals("Swim Instruction") && !a.name.equals("Swim Rec") && !a.name.equals("Swim Team") && !a.name.equals("Cooking") && myEmployee.getPriority(a.name) >= 0)
                        {
                        double elective = 1.0;
                        if(a.elective[firstOff-1])
                        {
                            elective = 0.0;
                        }
                            
                            if(a.numScheduled[firstOff - 1] != -1 && elective == 0.0 && Main.getReqCounselors(a.numCampers[firstOff - 1], a.name, a.elective[firstOff - 1]) > 0 && a.numCampers[firstOff -1] > 7)
                            {
                                
                                
                                
                                int reqCounselors = Main.getReqCounselors(getActivity(a.name).numCampers[firstOff - 1], a.name, false) - (a.numScheduled[firstOff - 1]);
                                //double reqCounselors = Double.valueOf(getActivity(a.name).numCampers[firstOff - 1]) / Double.valueOf(a.numScheduled[firstOff - 1]);
                                if(reqCounselors > mostReqCounselors)
                                {
                                    neededActivity = a.name;
                                    neededPeriod = firstOff;
                                    mostReqCounselors = reqCounselors;
                                }
                            }
                            elective = 1.0;
                            if(a.elective[secondOff-1])
                            {
                                elective = 0.0;
                            }
                            
                            if(a.numScheduled[secondOff - 1] != -1  && elective == 0.0 && Main.getReqCounselors(a.numCampers[secondOff - 1], a.name, a.elective[secondOff - 1]) > 0 && a.numCampers[secondOff -1] > 7)
                            {
                                int reqCounselors = Main.getReqCounselors(getActivity(a.name).numCampers[secondOff - 1], a.name, false) - (a.numScheduled[secondOff - 1]);
                                //double reqCounselors = Double.valueOf(getActivity(a.name).numCampers[secondOff - 1]) / Double.valueOf(a.numScheduled[secondOff - 1]);
                                if(reqCounselors > mostReqCounselors)
                                {
                                    neededActivity = a.name;
                                    neededPeriod = secondOff;
                                    mostReqCounselors = reqCounselors;
                                }
                            }
                        }                        
                    }
                    if(mostReqCounselors != -100 && !neededActivity.equals("OFF")  && neededPeriod != -1)
                    {
                        assignActivity(neededActivity, neededPeriod, myEmployee, false);
                    }
                }
            }
        }
    }
    
    public static void correctDoubleOffsNew()
    {
        LinkedList myDoubleOffs = new LinkedList();
        for(int i = 2; i < mySchedule[0].length; i++)
        {
            myDoubleOffs.add(new int[2]); //first array entry is num available, second is num already scheduled
        }
        LinkedList myAvailableStaff = new LinkedList();
        for(int i = 0; i < myDoubleOffs.size(); i++)
        {
            myAvailableStaff.add(new LinkedList());
        }
        for(int i = 0; i < mySchedule.length; i++)
        {
            int[] offPeriods = new int[mySchedule.length - 2];
            for(int j = 0; j < offPeriods.length; j++)
            {
                offPeriods[j] = -1;
            }
            for(int j = 2; j < mySchedule[i].length; j++)
            {
                if(mySchedule[i][j] != null && mySchedule[i][j].equals("OFF"))
                {
                    for(int k = 0; k < offPeriods.length; k++)
                    {
                        if(offPeriods[k] == -1)
                        {
                            offPeriods[k] = j - 1;
                            k = offPeriods.length;
                        }
                    }
                }
            }
            if(offPeriods[1] != -1)
            {
                for(int j = 0; j < offPeriods.length; j++)
                {
                    if(offPeriods[j] != -1)
                    {
                        ((int[])myDoubleOffs.get(offPeriods[j]))[0] += 1;
                        ((LinkedList)myAvailableStaff.get(offPeriods[j])).add(Main.getEmployeeByName(mySchedule[i][1]));
                    }
                }
            }
        }
        
        LinkedList myElectives = new LinkedList();
        
        for(int i = 0; i < myDoubleOffs.size(); i++)
        {
            myElectives.add(new LinkedList());
        }
        
        for(int i = 0; i < myElectives.size(); i++)
        {
            ((LinkedList)myElectives.get(i)).add(myActivities.get(myActivities.size() - 1));
            ((LinkedList)myElectives.get(i)).add(myActivities.get(myActivities.size() - 3));
            ((LinkedList)myElectives.get(i)).add(myActivities.get(myActivities.size() - 5));
            ((LinkedList)myElectives.get(i)).add(myActivities.get(myActivities.size() - 7));
            ((LinkedList)myElectives.get(i)).add(myActivities.get(myActivities.size() - 8));
            ((LinkedList)myElectives.get(i)).add(myActivities.get(myActivities.size() - 6));
            ((LinkedList)myElectives.get(i)).add(myActivities.get(myActivities.size() - 4));
            ((LinkedList)myElectives.get(i)).add(myActivities.get(myActivities.size() - 2));
            for(int j = 0; j < myActivities.size(); j++)
            {
                if(myActivities.get(j).numCampers[i] > 5 && myActivities.get(j).elective[i])
                {
                    ((LinkedList)myElectives.get(i)).add(myActivities.get(j));
                }
            }
            System.out.println("myElectives size index: " + i + " size: " + ((LinkedList)myElectives.get(i)).size());
        }
        
        int[] myIterators = new int[myElectives.size()];
        
        for(int i = 0; i < myIterators.length; i++)
        {
            myIterators[i] = 0;
        }
        
        boolean allAssigned = true;
        for(int i = 0; i < myDoubleOffs.size(); i++)
        {
            if(((int[])myDoubleOffs.get(i))[0] > 0)
            {
                allAssigned = false;
            }
        }
        
        while(!allAssigned)
        {
            int leastAvailable = 1000;
            int leastAvailablePeriod = -1;
            for(int i = 0; i < myDoubleOffs.size(); i++)
            {
                if(((int[])myDoubleOffs.get(i))[0] + ((int[])myDoubleOffs.get(i))[1] < leastAvailable && ((int[])myDoubleOffs.get(i))[0] > 0)
                {
                    leastAvailable = ((int[])myDoubleOffs.get(i))[0] + ((int[])myDoubleOffs.get(i))[1];
                    leastAvailablePeriod = i;
                }
            }
            
            Employee toAssign = null;
            int toAssignIndex = -1;
            boolean FoundSkilledEmp = false;
            for(int i = 0; i < ((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).size(); i++)
            {
                if(((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i)).getPriority(((Activity)((LinkedList)myElectives.get(leastAvailablePeriod - 1)).get(myIterators[leastAvailablePeriod])).name) >= 0 && FoundSkilledEmp && toAssign.skills.size() > ((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i)).skills.size() && ((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i)).skills.contains(((Activity)((LinkedList)myElectives.get(leastAvailablePeriod - 1)).get(myIterators[leastAvailablePeriod])).name))
                {
                    toAssign = ((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i));
                    toAssignIndex = i;
                }
                if(((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i)).getPriority(((Activity)((LinkedList)myElectives.get(leastAvailablePeriod - 1)).get(myIterators[leastAvailablePeriod])).name) >= 0 && !FoundSkilledEmp && ((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i)).skills.contains(((Activity)((LinkedList)myElectives.get(leastAvailablePeriod - 1)).get(myIterators[leastAvailablePeriod])).name))
                {
                    FoundSkilledEmp = true;
                    toAssign = ((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i));
                    toAssignIndex = i;
                }
                if(((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i)).getPriority(((Activity)((LinkedList)myElectives.get(leastAvailablePeriod - 1)).get(myIterators[leastAvailablePeriod])).name) >= 0 && !FoundSkilledEmp && toAssign != null && toAssign.skills.size() > ((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i)).skills.size())
                {
                    toAssign = ((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i));
                    toAssignIndex = i;
                }
                if(((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i)).getPriority(((Activity)((LinkedList)myElectives.get(leastAvailablePeriod - 1)).get(myIterators[leastAvailablePeriod])).name) >= 0 && !FoundSkilledEmp && toAssign == null)
                {
                    toAssign = ((Employee)((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).get(i));
                    toAssignIndex = i;
                }
            }
            
            //DO ASSIGNMENT HERE    
            assignActivity(((Activity)((LinkedList)myElectives.get(leastAvailablePeriod - 1)).get(myIterators[leastAvailablePeriod])).name, leastAvailablePeriod, toAssign, false);
            Main.warnings.add(((Activity)((LinkedList)myElectives.get(leastAvailablePeriod - 1)).get(myIterators[leastAvailablePeriod])).name + " Period: " + (leastAvailablePeriod) + " Assigned: " + toAssign.name);
            
            ((LinkedList)myAvailableStaff.get(leastAvailablePeriod)).remove(toAssignIndex);
            
            myIterators[leastAvailablePeriod] += 1;
            if(myIterators[leastAvailablePeriod] >= ((LinkedList)myElectives.get(leastAvailablePeriod - 1)).size())
            {
                myIterators[leastAvailablePeriod] = 0;
            }
            ((int[])myDoubleOffs.get(leastAvailablePeriod))[0] -= 1;
            ((int[])myDoubleOffs.get(leastAvailablePeriod))[1] += 1;
            
            int firstOff = -1;
            int firstOffIndex = -1;
            int secondOff = -1;
            
            for(int i = 0; i < myAvailableStaff.size(); i++)
            {
                for(int j = 0; j < ((LinkedList)myAvailableStaff.get(i)).size(); j++)
                {
                    if(((Employee)((LinkedList)myAvailableStaff.get(i)).get(j)).name.equals(toAssign.name))
                    {
                        if(firstOff == -1)
                        {
                            firstOff = i;
                            firstOffIndex = j;
                        }
                        else
                        {
                            secondOff = j;
                        }
                    }
                }
            }
            
            if(secondOff == -1)
            {                
                ((LinkedList)myAvailableStaff.get(firstOff)).remove(firstOffIndex);
                ((int[])myDoubleOffs.get(firstOff))[0] -= 1;
            }
            
            allAssigned = true;
            for(int i = 0; i < myDoubleOffs.size(); i++)
            {
                if(((int[])myDoubleOffs.get(i))[0] > 0)
                {
                    allAssigned = false;
                }
            }
        }
    }
    
    public static void printActivities(boolean toExcel, XSSFWorkbook workbook)
    {
        Activity off = getActivity("OFF");
        off.numScheduled[0] = 0; off.numScheduled[1] = 0; off.numScheduled[2] = 0; off.numScheduled[3] = 0; off.numScheduled[4] = 0; off.numScheduled[5] = 0;
        for(int i = 0; i < mySchedule.length; i++)
        {
            for(int j = 1; j < 7; j++)
            {
                if(mySchedule[i][j+1] != null && mySchedule[i][j+1].equals("OFF"))
                {
                    off.numScheduled[j-1] += 1;
                }
            }
        }
        
        if(toExcel)
        {
            for(int i = 0; i < myActivities.size(); i++)
            {
                workbook.getSheetAt(1).createRow(i);
                for(int j = 0; j < 7; j++)
                {                    
                        workbook.getSheetAt(1).getRow(i).createCell(j);                                   
                }
            }
            for(int i = 0; i < myActivities.size(); i++)
            {
                workbook.getSheetAt(2).createRow(i);
                for(int j = 0; j < 7; j++)
                {                    
                        workbook.getSheetAt(2).getRow(i).createCell(j);                                   
                }
            }
        }
        
        for(int i = 0; i < myActivities.size(); i++)
        {
            if(!toExcel)
            {
                System.out.println();            
                System.out.print(myActivities.get(i).name + " ");
            }
            else
            {
                workbook.getSheetAt(1).getRow(i).getCell(0).setCellValue(myActivities.get(i).name);
            }
            
            for(int j = 0; j < 6; j++)
            {
                if(myActivities.get(i).numScheduled[j] == -1)
                {
                    if(!toExcel)
                    {
                        System.out.print("X" + " ");
                    }
                    else
                    {
                        workbook.getSheetAt(1).getRow(i).getCell(j + 1).setCellValue("X");
                    }
                }
                else
                {
                    if(!toExcel)
                    {
                        System.out.print(myActivities.get(i).numScheduled[j] + " ");
                    }
                    else
                    {
                        workbook.getSheetAt(1).getRow(i).getCell(j + 1).setCellValue(myActivities.get(i).numScheduled[j]);
                    }
                }                            
            }
        }
        
        if(!toExcel)
        {
            System.out.println();
        }
                
        for(int i = 0; i < myActivities.size(); i++)
        {
            if(!toExcel)
            {
                System.out.println();
                System.out.print(myActivities.get(i).name + " " + myActivities.get(i).numCampers[0] + " " + myActivities.get(i).numCampers[1] + " " + myActivities.get(i).numCampers[2] + " " + myActivities.get(i).numCampers[3] + " " + myActivities.get(i).numCampers[4] + " " + myActivities.get(i).numCampers[5]);
            }
            else
            {
                workbook.getSheetAt(2).getRow(i).getCell(0).setCellValue(myActivities.get(i).name);
                workbook.getSheetAt(2).getRow(i).getCell(1).setCellValue(myActivities.get(i).numCampers[0]);
                workbook.getSheetAt(2).getRow(i).getCell(2).setCellValue(myActivities.get(i).numCampers[1]);
                workbook.getSheetAt(2).getRow(i).getCell(3).setCellValue(myActivities.get(i).numCampers[2]);
                workbook.getSheetAt(2).getRow(i).getCell(4).setCellValue(myActivities.get(i).numCampers[3]);
                workbook.getSheetAt(2).getRow(i).getCell(5).setCellValue(myActivities.get(i).numCampers[4]);
                workbook.getSheetAt(2).getRow(i).getCell(6).setCellValue(myActivities.get(i).numCampers[5]);
            }
        }
    }
    
    public static void removeStaff(String staffName, LinkedList<Employee> myEmployees)
    {
        myEmployees.remove(Main.getEmployeeByName(staffName));
        String[][] myNewSchedule =  new String[myEmployees.size()][8];
        int k = 0;
        if(mySchedule != null)
        {
            for(int i = 0; i < mySchedule.length; i++)
            {
                if(!mySchedule[i][1].equals(staffName))
                {
                    for(int j = 0; j < 8; j++)
                    {
                        myNewSchedule[k][j] = mySchedule[i][j];                    
                    }
                    k++;
                }
            }
            mySchedule = myNewSchedule;
        }        
    }
    
    public static boolean loadActivities(String activityFile)
    {
        try {
            LinkedList<Activity> activitiesToAdd = new LinkedList<Activity>();
            
            FileReader reader = new FileReader(activityFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String name = null;
                int ratioBunk = 0;
                int ratioElective = 0;
                int bBunk = 0;
                int bElective = 0;
                int countCounselorsBunk = 0;
                int countCounselorsElective = 0;
                int minBunk = 0;
                int minElective = 0;
                int maxBunk = 0;
                int maxElective = 0;
                
                name = line.substring(line.indexOf("Activity= ") + 10, line.indexOf("; Elective"));
                ratioBunk = Integer.parseInt(line.substring(line.indexOf("Ratio= ") + 7, line.indexOf("; b=")));
                bBunk = Integer.parseInt(line.substring(line.indexOf("b= ") + 3, line.indexOf("; subtract")));
                if(line.substring(line.indexOf("subtractBunkCounselors= ") + 24, line.indexOf("; min=")).equals("t"))
                {
                    countCounselorsBunk = 1;
                }
                minBunk = Integer.parseInt(line.substring(line.indexOf("min= ") + 5, line.indexOf("; max")));
                maxBunk = Integer.parseInt(line.substring(line.indexOf("max= ") + 5, line.indexOf(";;")));
                
                line = bufferedReader.readLine();
                System.out.println(line);
                ratioElective = Integer.parseInt(line.substring(line.indexOf("Ratio= ") + 7, line.indexOf("; b=")));
                bElective = Integer.parseInt(line.substring(line.indexOf("b= ") + 3, line.indexOf("; subtract")));
                if(line.substring(line.indexOf("subtractBunkCounselors= ") + 24, line.indexOf("; min=")).equals("t"))
                {
                    countCounselorsElective = 1;
                }
                minElective = Integer.parseInt(line.substring(line.indexOf("min= ") + 5, line.indexOf("; max")));
                maxElective = Integer.parseInt(line.substring(line.indexOf("max= ") + 5, line.indexOf(";;")));
                
                Activity toAdd = new Activity(name, ratioBunk, ratioElective, bBunk, bElective, countCounselorsBunk, countCounselorsElective, minBunk, minElective, maxBunk, maxElective);
                
                activitiesToAdd.add(toAdd);
            }
            reader.close();
            bufferedReader.close();
            //myActivities = activitiesToAdd;
            myActivities = new LinkedList();
            for(int i = 0; i < activitiesToAdd.size(); i++)
            {
                myActivities.add(activitiesToAdd.get(i));
            }
            String[] allActivities = new String[activitiesToAdd.size()];
            String[] allActivities2 = new String[activitiesToAdd.size()];
            
            for(int i = 0; i < activitiesToAdd.size(); i++)
            {
                allActivities[i] = activitiesToAdd.get(i).name;
                allActivities2[i] = activitiesToAdd.get(i).name;
            }
            
            activities = allActivities;
            Main.activities = allActivities2;
            
            return true; 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void changeBunk(Employee toChange, String newBunk, LinkedList<Employee> myEmployees)
    {
        myEmployees.remove(toChange);
        addStaff(toChange.name, newBunk, myEmployees);
        Main.getEmployeeByName(toChange.name).position = toChange.position;
        Main.getEmployeeByName(toChange.name).skills = toChange.skills;
    }
    
    public static void addStaff(String staffName, String bunk, LinkedList<Employee> myEmployees)
    {
        Employee myEmployee = new Employee(staffName, bunk.toLowerCase());
        
        boolean sameBunk = false;
        boolean added = false;
        int location = myEmployees.size() -1;
        for(int i = 0; i < myEmployees.size(); i++)
        {
            if(myEmployees.get(i).bunk.equals(bunk))
            {
                sameBunk = true;
            }
            else
            {
                if(sameBunk)
                {
                    myEmployees.add(i, myEmployee);
                    location = i;
                    i = myEmployees.size();
                    added = true;
                }            
            }
        }
        if(!added)
        {
            myEmployees.add(myEmployee);
            added = true;
        }
        
        
        String[][] myNewSchedule =  new String[myEmployees.size()][8];
        int k = 0;
        if(mySchedule != null)
        {
            for(int i = 0; i < myNewSchedule.length; i++)
            {
                if(i != location)
                {
                    for(int j = 0; j < 8; j++)
                    {
                        myNewSchedule[i][j] = mySchedule[k][j];                    
                    }
                    k++;
                }
                else
                {
                    myNewSchedule[i][0] = bunk.toLowerCase();
                    myNewSchedule[i][1] = staffName;
                    
                    if(mySchedule[i][0].substring(0, 1).equals("b"))
                    {
                        for(int j = 0; j < electivePeriodsB.length; j++)
                        {
                            if(electivePeriodsB[j][0] == Integer.parseInt(bunk.substring(1)))
                            {
                                if(electivePeriodsB[j][1] < 10)
                                {
                                    mySchedule[i][electivePeriodsB[j][1] + 1] = "OFF";
                                }
                                else
                                {
                                    mySchedule[i][(electivePeriodsB[j][1] % 10) + 1] = "OFF";
                                    mySchedule[i][((electivePeriodsB[j][1] - (electivePeriodsB[j][1] % 10))/10) + 1] = "OFF";
                                }
                            }
                        }
                    }
                    if(mySchedule[i][0].substring(0, 1).equals("g"))
                    {
                        for(int j = 0; j < electivePeriodsG.length; j++)
                        {
                            if(electivePeriodsG[j][0] == Integer.parseInt(bunk.substring(1)))
                            {
                                if(electivePeriodsG[j][1] < 10)
                                {
                                    mySchedule[i][electivePeriodsG[j][1] + 1] = "OFF";
                                }
                                else
                                {
                                    mySchedule[i][(electivePeriodsG[j][1] % 10) + 1] = "OFF";
                                    mySchedule[i][((electivePeriodsG[j][1] - (electivePeriodsG[j][1] % 10))/10) + 1] = "OFF";
                                }
                            }
                        }
                    }
                }
            }
            mySchedule = myNewSchedule;
        }        
    }
    
    public static String getAssignment(String name, int period)
    {
        for(int i = 0; i < mySchedule.length; i++)
        {
            if(mySchedule[i][1].equals(name))
            {
                return mySchedule[i][period + 1];
            }
        }
        
        return null;
    }
    
    public static void scrubOffs(String job)
    {
        for(int i = 0; i < Main.staffList.size(); i++)
        {
            if(Main.staffList.get(i).position.equals(job))
            {
                for(int j = 2; j < 8; j++)
                {
                    if(mySchedule[i][j] != null && mySchedule[i][j].equals("OFF"))
                    {
                        mySchedule[i][j] = null;
                    }
                }
            }
        }
        Main.warnings.add("Schedule off periods for " + job + " Staff");
    }
    
    public static void doFreePlayScheduling() throws Exception
    {
        String[] freePlaySchedule = new String[mySchedule.length];
        
        for(int i = 0; i < freePlaySchedule.length; i++)
        {
            freePlaySchedule[i] = null;
        }
        
        for(int i = 0; i < freePlaySchedule.length; i++)
        {
            if(Main.getEmployeeByName(mySchedule[i][1]).skills.contains("Cooking") && Main.contains(Main.getActivities(Main.getEmployeeByName(mySchedule[i][1]).position), "Cooking"))
            {
                freePlaySchedule[i] = "Cooking";
            }
            if(Main.getEmployeeByName(mySchedule[i][1]).skills.contains("Camp Photo") && Main.contains(Main.getActivities(Main.getEmployeeByName(mySchedule[i][1]).position), "Camp Photo"))
            {
                freePlaySchedule[i] = "Camp Photo";
            }
            if(Main.getEmployeeByName(mySchedule[i][1]).skills.contains("Horses") && Main.contains(Main.getActivities(Main.getEmployeeByName(mySchedule[i][1]).position), "Horses"))
            {
                freePlaySchedule[i] = "Horses";
            }
            if(Main.getEmployeeByName(mySchedule[i][1]).skills.contains("Hondas") && Main.contains(Main.getActivities(Main.getEmployeeByName(mySchedule[i][1]).position), "Hondas"))
            {
                freePlaySchedule[i] = "Hondas";
            }
        }
        
        //GO THROUGH FREE PLAY ACTIVITIES
        LinkedList<String> FreePlayActivities = Main.getFreePlayActivities();
        
        for(int i = 0; i < FreePlayActivities.size(); i++)
        {
            int numAdded = 0;
            
            for(int j = 0; j < mySchedule.length; j++)
            {
                if(Main.getEmployeeByName(mySchedule[j][1]).skills.contains(FreePlayActivities.get(i)) && Main.contains(Main.getActivities(Main.getEmployeeByName(mySchedule[j][1]).position), FreePlayActivities.get(i)) && freePlaySchedule[j] == null)
                {
                    freePlaySchedule[j] = FreePlayActivities.get(i);
                    numAdded += 1;
                }
            }
            
            if(numAdded == 0)
            {
                for(int j = 0; j < mySchedule.length && numAdded < 2; j++)
                {
                    if(Main.getEmployeeByName(mySchedule[j][1]).skills.contains(FreePlayActivities.get(i)) && freePlaySchedule[j] == null)
                    {
                        freePlaySchedule[j] = FreePlayActivities.get(i);
                        numAdded += 1;
                    }
                }
            }
            
            if(numAdded == 0)
            {
                Main.warnings.add("FREE PLAY no one assigned to " + FreePlayActivities.get(i));
            }
            
        }
        //CHECK FOR SUPER LEAGUES
        
        LinkedList<String> FreePlayLeagues = Main.getFreePlayLeagues();
        
        for(int i = 0; i < FreePlayLeagues.size(); i++)
        {
            int numAdded = 0;
            
            for(int j = 0; j < mySchedule.length; j++)
            {
                if(Main.getEmployeeByName(mySchedule[j][1]).skills.contains(FreePlayLeagues.get(i)) && Main.contains(Main.getActivities(Main.getEmployeeByName(mySchedule[j][1]).position), FreePlayLeagues.get(i)) && freePlaySchedule[j] == null)
                {
                    freePlaySchedule[j] = FreePlayLeagues.get(i);
                    numAdded += 1;
                }
            }
            
            if(numAdded == 0)
            {
                for(int j = 0; j < mySchedule.length && numAdded < 2; j++)
                {
                    if(Main.getEmployeeByName(mySchedule[j][1]).skills.contains(FreePlayLeagues.get(i)) && freePlaySchedule[j] == null)
                    {
                        freePlaySchedule[j] = FreePlayLeagues.get(i);
                        numAdded += 1;
                    }
                }
            }
            
            if(numAdded == 0)
            {
                Main.warnings.add("FREE PLAY no one assigned to " + FreePlayLeagues.get(i));
            }
            
        }
        
        //CHECK FOR PHONE CALLS
        LinkedList<String> PhoneCallBunks = Main.getPhoneCallBunks();
        
        for(int i = 0; i < PhoneCallBunks.size(); i++)
        {
            
            for(int j = 0; j < mySchedule.length; j++)
            {
                if(PhoneCallBunks.get(i).toLowerCase().equals(mySchedule[j][0]) && freePlaySchedule[j] == null)
                {
                    freePlaySchedule[j] = "PHONE CALLS";
                }
            }
            
        }
        
        //ALL JR BOYS COUNSELORS TO INTER RINK
        for(int j = 0; j < mySchedule.length; j++)
        {
            if((mySchedule[j][0].equals("b2") || mySchedule[j][0].equals("b3") || mySchedule[j][0].equals("b4") || mySchedule[j][0].equals("b5") || mySchedule[j][0].equals("b6") || mySchedule[j][0].equals("b7") || mySchedule[j][0].equals("b8") || mySchedule[j][0].equals("b9") || mySchedule[j][0].equals("b10"))  && freePlaySchedule[j] == null)
            {
                freePlaySchedule[j] = "INTER RINK";
            }
        }
        
        //ALL JR GIRLS COUNSELORS TO REC DECK
        for(int j = 0; j < mySchedule.length; j++)
        {
            if((mySchedule[j][0].equals("g2") || mySchedule[j][0].equals("g3") || mySchedule[j][0].equals("g4") || mySchedule[j][0].equals("g5") || mySchedule[j][0].equals("g6") || mySchedule[j][0].equals("g7") || mySchedule[j][0].equals("g8") || mySchedule[j][0].equals("g9") || mySchedule[j][0].equals("g10"))  && freePlaySchedule[j] == null)
            {
                freePlaySchedule[j] = "REC DECK";
            }
        }
        
        //1 COUNSELORS FROM EACH SENIOR BUNK AT BUNKS
        LinkedList temp = new LinkedList();
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("b17") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("b18") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("b19") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("b20") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("b21") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("b22") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("b23") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("b24") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("b25") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g18") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g19") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g20") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g21") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g22") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g23") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g24") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g25") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g26") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if(mySchedule[j][0].equals("g27") && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        freePlaySchedule[(int)temp.get((int)(Math.random() * Double.valueOf(temp.size())))] = "BUNK";                
        
        //SR BOYS LOCATIONS
        try
        {
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if((mySchedule[j][0].equals("b17") || mySchedule[j][0].equals("b18") || mySchedule[j][0].equals("b19") || mySchedule[j][0].equals("b20") || mySchedule[j][0].equals("b21") || mySchedule[j][0].equals("b22") || mySchedule[j][0].equals("b23") || mySchedule[j][0].equals("b24") || mySchedule[j][0].equals("b25")) && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        int rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SR CANTEEN FRONT PORCH";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SR CANTEEN BACK PORCH";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SR CANTEEN BY RESTROOMS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SR CANTEEN INSIDE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SR COURTS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SR FIELD ENTRANCE BY BOTTOM OF STEPS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SR FIELD ENTRANCE BY BOTTOM OF PATH";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "ON SR FIELD";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "LAKE TRAIL ENTRANCE, BOYS SIDE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "WATERSKI GAZEBO";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "BETWEEN SR TENNIS AND FFF";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "ARCHERY";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "CIT FIELD CLOSE TO ROAD";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SPECTRUM OUTSIDE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SPECTRUM INSIDE";
        temp.remove(rand);
        //FRESHEMEN BOYS LOCATIONS
        
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if((mySchedule[j][0].equals("b14") || mySchedule[j][0].equals("b15") || mySchedule[j][0].equals("b16")) && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }

        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "FRESHMAN HILL";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "FRESHMAN HILL CAMPFIRE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "FRESHMAN HILL TETHERBALL";
        temp.remove(rand);
        //BOYS LOCATIONS
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if((mySchedule[j][0].contains("b") && !mySchedule[j][0].toLowerCase().contains("oob")) && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "INTER COURTS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "LAKE FIELD HONDAS ENTRANCE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "GAGA BOYS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "LAKE FIELD ENTRANCE CLOSE TO LAKE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "BRIDGE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "FISHING DOCK BOYS SIDE";
        temp.remove(rand);
        //GIRLS LOCATIONS
        
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if((mySchedule[j][0].contains("g")) && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "GIRLS PLAT";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "LAKE TRAIL ENTRANCE, GIRLS SIDE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "FISHING DOCK GIRLS SIDE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "AROUND BUNKS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "INSIDE GOLD POOL AREA";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "ON LAKE FIELD";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "LOWER GIRLS TENNIS COURTS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "SR GIRLS TENNIS COURTS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "GAGA GIRLS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "TODAY SHOW DECK";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "OUTSIDE STUDIOS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "TENNIS GAZEBO";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "PAVILLION FIELD";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "INSIDE PAVILLION";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "HORSE STABLES";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "COOKING GAZEBO";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "ENTRANCE TO LOW ROPES";
        temp.remove(rand);
        
        //GENERAL LOCATIONS 
        temp = new LinkedList();
        
        for(int j = 0; j < mySchedule.length; j++)
        {
            if((!mySchedule[j][0].toLowerCase().contains("oob")) && freePlaySchedule[j] == null)
            {
                temp.add(j);
            }
        }
       
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "AMP STAGE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "LOWER CANTEEN BY RESTROOMS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "DINING HALL BBQS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "PAVER PIT";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "AMPI STEPS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "DINING HALL BACK DECK";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "IN REC HALL BY RESTROOMS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "OUTSIDE WEIGHT ROOM";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "VB HARD COURTS";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "REC HALL LAWN";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "LAKEFRONT ON THE BEACH";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "LAWN BY THE LAKE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "BOTTOM OF ART SHACK STEPS BY POOL";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "IN WEIGHT ROOM";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "AMPI BACK DECK";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "ROPES/WALL";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "THE GROVE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "MOVIE THEATER SIDE ENTRANCE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "LAWN NEXT TO BLUE POOL";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "INSIDE MOVIE THEATER";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "ART SHACK DECK GLASS FUSION";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "IN REC HALL BY STAGE";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "ART SHACK DECK WOOD/PHOTO";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "BEACH VB";
        temp.remove(rand);
        
        rand = (int)(Math.random() * Double.valueOf(temp.size()));
        freePlaySchedule[(int)temp.get(rand)] = "INSIDE DINING HALL";
        temp.remove(rand);                
        }
        catch(Exception e)
        {
            Main.warnings.add("Unable to schedule someone to each free play post. Work backwards from free play posts gender neutral assignments to resolve.");
        }
        for(int j = 0; j < freePlaySchedule.length; j++)
        {
            if(freePlaySchedule[j] == null)
            {
                freePlaySchedule[j] = "SCHEDULE LATER";
            }
        }    
        myFreePlaySchedule = freePlaySchedule;
    }
}
