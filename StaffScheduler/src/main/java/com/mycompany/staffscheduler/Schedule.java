/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staffscheduler;

import static com.mycompany.staffscheduler.Main.staffList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    
    static int[][] electivePeriodsG = 
    {{2, 1},
     {3, 1},
     {4, 1}, 
     {5, 1}, 
     {6, 1}, 
     {7, 1}, 
     {8, 1}, 
     {9, 1}, 
     {10, 4}, 
     {11, 4}, 
     {12, 4}, 
     {13, 4}, 
     {14, 24}, 
     {15, 24}, 
     {16, 24}, 
     {17, 25}, 
     {18, 25}, 
     {19, 25}, 
     {20, 35}, 
     {21, 35}, 
     {22, 35}, 
     {23, 35}, 
     {24, 35},
     {25, 35},
     {26, 3}};
    
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
     {19, 25}, 
     {20, 35}, 
     {21, 35}, 
     {22, 35}, 
     {23, 35}, 
     {24, 3}};
    
    public static String[] activities = {"OFF", "Sailing", "Lake", "Bananna Boat", "Ice Mountain", "Lake Boats", "Lake Intruction", "Lake Recreation", "Swim", "Pool", "Blue Pool", "Pool Party", "Waterslides", "Swim Instruction", "Swim Rec", "Swim Team", "Video Editing", "Video Production", "Ropes", "Ropes & Climbing Wall", "Climbing Wall", "Outdoor Cooking", "Mountain Bikes", "MTB Road Riders", "Hondas", "Quads", "Fishing", "STEM", "Board Games", "Coding", "Lego", "Model Making", "Perler Beads", "Robotics", "Rocketry", "Rubik's Cube", "Weights", "Zumba", "Aerobics", "Bootcamp", "Yoga", "Fitness Center", "Basketball", "Basketball Clinic", "Basketball League", "Varsity", "WNBA", "WNCAA", "NCAA", "NBA", "Hockey", "Floor Hockey", "Roller Hockey", "Hockey Clinic", "Hockey League", "Soccer", "Soccer Clinic", "Soccer League", "Lacrosse", "Ninja Warrior", "Gymnastics", "Gymnastics -Foam Pit", "Gymnastics - Bar, Beam, Floor & Vault", "Softball", "Baseball", "Softball Clinic", "Softball League", "Baseball Clinic", "Baseball League", "MLB", "CBL", "Batting Cage", "Cheer Leading", "Archery", "Flag Football", "Beach Volleyball", "Newcomb", "Volleyball", "Volleyball Leagues", "Volleyball Clinic", "Golf", "SNAG", "Cooking", "Painting", "Glass Fusion", "Art Shack", "Beading & Bracelets", "Calligraphy", "Zendoodle", "T-Shirt Studio", "Dance", "Woodworking", "Dramatic Arts", "Ukulele", "Digital Art & Design", "Photo - Digital", "Ceramics", "Ceramics Wheel", "Crochet", "Gaga", "Kiting", "Magic Tricks", "Mah Jongg", "Old Alma Maters", "Ping Pong", "Tetherball", "Ultimate Frisbee", "Book Club", "Running", "Bunko", "Card Games", "CIT Activity", "Jump Rope Skills", "Kickboxing"};
    public static double[] activityRatios = {0.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 6.0, 6.0, -1.0, -1.0, -1.0, 8.0, 8.0, 8.0, -1.0, -1.0, 8.0, 6.0, 12.0, 6.0, 10.0, 10.0, 10.0, 6.0, 6.0, 6.0, 8.0, 12.0, 12.0, 12.0, 10.0, 8.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 8.0, 8.0, 8.0, 8.0, 8.0, 12.0, 12.0, 12.0, 5.0, 8.0, 8.0, 8.0, 8.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 5.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 5.0, 8.0, 5.0, 8.0, 12.0, 8.0, 8.0, 8.0, 10.0, 10.0, 10.0, 8.0, 6.0, 6.0, 8.0, 6.0, 10.0, 12.0, 10.0, 6.0, 8.0, 8.0, 8.0, 8.0, 8.0, 15.0, 5.0, 0.0, 12.0, 16.0, 10.0, 8.0};
    //-1=All staff, 
    public static LinkedList<Activity> myActivities;
    
    Schedule(LinkedList<Employee> myEmployees)
    {
        myActivities  = new LinkedList();
        for(int i = 0; i < activities.length; i++)
        {
            myActivities.add(new Activity(activities[i], activityRatios[i]));
        }
        
        mySchedule =  new String[myEmployees.size()][8];
        for(int i = 0; i < myEmployees.size(); i++)
        {
            mySchedule[i][0] = myEmployees.get(i).bunk;
            mySchedule[i][1] = myEmployees.get(i).name;
            
            if(!mySchedule[i][0].equals("OOB"))
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
    
    public static void assignAll(String activity, double idealRatio, int numCampers, int period, LinkedList<Employee> myEmployees, double elective)
    {
        if(myEmployees == Main.directorList)
        {
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
            if(myEmployees.get(i).skills.contains(activity))
            {
                skilledEmployees.add(myEmployees.get(i));
                
            }
        }
        
        
        boolean titledEmployeesExhausted = true;
        for(int i = 0; i < skilledEmployees.size(); i++)
        {
            if(Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position))
            {
                titledEmployeesExhausted = false;
            }
        }
        
        int[] skilledCoverages = new int[skilledEmployees.size()];
        
        int bestCoverage = -1;
        
        for(int i = 0; i  < skilledEmployees.size(); i++)
        {
            skilledCoverages[i] = coverage(skilledEmployees.get(i).bunk, period);
            if((titledEmployeesExhausted || (!titledEmployeesExhausted && Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position))) && skilledCoverages[i] > bestCoverage)
            {
                bestCoverage = skilledCoverages[i];
            }
        } 
        
        while(bestCoverage > -1)
        {
            LinkedList<Employee> assignmentPool = new LinkedList();
            for(int i = 0; i < skilledEmployees.size(); i++)
            {
                if((titledEmployeesExhausted || (!titledEmployeesExhausted && Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position))) && skilledCoverages[i] == bestCoverage)
                {
                    assignmentPool.add(skilledEmployees.get(i));
                    skilledCoverages[i] = -1;
                }
            }

            if(((double)assignmentPool.size() + (double)numAssigned + (elective * (numCampers/10.0))) >= ((double)numCampers / idealRatio))
            {
                boolean enoughInPool = true;
                double requiredCounselors = ((double)numCampers / idealRatio) - (double)numAssigned - (elective * (numCampers/10.0));
                while(enoughInPool && requiredCounselors > 0.0 && assignmentPool.size() > 0) //possible issue here with assignment pool's size
                {
                    int toAssign = (int)(Math.random() * assignmentPool.size());
                    //System.out.println((assignmentPool.get(toAssign)).name);
                    boolean success = assignActivity(activity, period, assignmentPool.get(toAssign), true);
                    if(success)
                    {
                    numAssigned += 1;
                    requiredCounselors -= 1.0;                    
                    }      
                    else
                    {
                        enoughInPool = ((double)assignmentPool.size() + (double)numAssigned) >= ((double)numCampers / idealRatio) - (elective * (numCampers/10.0));
                    }
                    //need to go back in and factor in skilled employees who have their off period
                    assignmentPool.remove(toAssign);                    
                }
                if(enoughInPool)
                {
                    return;
                }                
            }
            else
            {
                for(int i = assignmentPool.size() - 1; i >= 0; i--)
                {
                    //System.out.println(assignmentPool.get(i).name);
                    boolean success = assignActivity(activity, period, assignmentPool.get(i), true);
                    if(success)
                    {
                        numAssigned += 1;
                    }                    
                    assignmentPool.remove(i);
                }
            }
            bestCoverage = -1;

            for(int i = 0; i  < skilledEmployees.size(); i++)
            {
                if((titledEmployeesExhausted || (!titledEmployeesExhausted && Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position))) && skilledCoverages[i] > bestCoverage)
                {
                    bestCoverage = skilledCoverages[i];
                    //System.out.println(bestCoverage);
                }
            }
            
            titledEmployeesExhausted = true;
            for(int i = 0; i < skilledEmployees.size(); i++)
            {
                if(Main.contains(Main.getPositions(activity), skilledEmployees.get(i).position) && skilledCoverages[i] != -1)
                {
                    titledEmployeesExhausted = false;
                }
            }
        }
        //if this point is reached, there were not enough skilled employees to satisfy the demand
        
        if(myEmployees != Main.directorList)
        {
            Main.warnings.add("not enough skilled employees for " + activity + " period " + period + ". assigned: " + numAssigned + " needed: " + ((double)numCampers / idealRatio));
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
        if(bunk.equals("OOB"))
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
                    if(j == 0)
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
        for(int i = 0; i < Main.warnings.size(); i++)
        {
            workbook.getSheetAt(3).createRow(i);
            workbook.getSheetAt(3).getRow(i).createCell(0);
            workbook.getSheetAt(3).getRow(i).getCell(0).setCellValue(Main.warnings.get(i));
        }
        
        workbook.write(fileOut);
        fileOut.close();
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
                    if(a != null && !a.name.equals("OFF"))
                    {
                        if(a.numScheduled[firstOff - 1] != -1)
                        {
                            double reqCounselors = (double)a.numCampers[firstOff - 1] / ((double)a.idealRatio) - ((double)a.numScheduled[firstOff - 1] + (elective * ((double)a.numCampers[firstOff - 1] / (10.0))));
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
                        if(a.numScheduled[secondOff - 1] != -1)
                        {
                            double reqCounselors = (double)a.numCampers[secondOff - 1] / ((double)a.idealRatio) - ((double)a.numScheduled[secondOff - 1] + (elective * ((double)a.numCampers[secondOff - 1] / (10.0))));
                            if(reqCounselors > mostReqCounselors)
                            {
                                neededActivity = a.name;
                                neededPeriod = secondOff;
                                mostReqCounselors = reqCounselors;
                            }
                        }
                    }                                       
                }
                
                if(mostReqCounselors != -100 && !neededActivity.equals("OFF") && !neededActivity.equals("Tennis") && !neededActivity.equals("Pickle Ball") && !neededActivity.equals("Sailing") && !neededActivity.equals("Lake") && !neededActivity.equals("Banana Boat") && !neededActivity.equals("Ice Mountain") && !neededActivity.equals("Lake Boats") && !neededActivity.equals("Lake Instruction") && !neededActivity.equals("Lake Recreation") && !neededActivity.equals("Swim") && !neededActivity.equals("Pool") && !neededActivity.equals("Blue Pool") && !neededActivity.equals("Pool Party") && !neededActivity.equals("Waterslides") && !neededActivity.equals("Swim Instruction") && !neededActivity.equals("Swim Rec") && !neededActivity.equals("Swim Team") && !neededActivity.equals("Ropes") && !neededActivity.equals("Climbing Wall") && !neededActivity.equals("Ropes & Climbing Wall") && !neededActivity.equals("Hondas") && !neededActivity.equals("Quads") && neededPeriod != -1)
                {
                    assignActivity(neededActivity, neededPeriod, myEmployee, false);
                }
                else
                {
                    mostReqCounselors = -100;
                    for(int j = 0; j < myActivities.size(); j++)
                    {
                        Activity a = myActivities.get(j);
                        if(a != null && !a.name.equals("OFF") && !a.name.equals("Tennis") && !a.name.equals("Pickle Ball") && !a.name.equals("Sailing") && !a.name.equals("Lake") && !a.name.equals("Banana Boat") && !a.name.equals("Ice Mountain") && !a.name.equals("Lake Boats") && !a.name.equals("Lake Instruction") && !a.name.equals("Lake Recreation") && !a.name.equals("Swim") && !a.name.equals("Pool") && !a.name.equals("Blue Pool") && !a.name.equals("Pool Party") && !a.name.equals("Waterslides") && !a.name.equals("Swim Instruction") && !a.name.equals("Swim Rec") && !a.name.equals("Swim Team") && !a.name.equals("Ropes") && !a.name.equals("Climbing Wall") && !a.name.equals("Ropes & Climbing Wall") && !a.name.equals("Hondas") && !a.name.equals("Quads"))
                        {
                        double elective = 1.0;
                        if(a.elective[firstOff-1])
                        {
                            elective = 0.0;
                        }
                            
                            if(a.numScheduled[firstOff - 1] != -1)
                            {
                                
                                
                                
                                double reqCounselors = (double)a.numCampers[firstOff - 1] / ((double)a.idealRatio) - ((double)a.numScheduled[firstOff - 1] + (elective * ((double)a.numCampers[firstOff - 1] / (10.0))));
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
                            
                            if(a.numScheduled[secondOff - 1] != -1)
                            {
                                double reqCounselors = (double)a.numCampers[secondOff - 1] / ((double)a.idealRatio) - ((double)a.numScheduled[secondOff - 1] + (elective * ((double)a.numCampers[secondOff - 1] / (10.0))));
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
}
