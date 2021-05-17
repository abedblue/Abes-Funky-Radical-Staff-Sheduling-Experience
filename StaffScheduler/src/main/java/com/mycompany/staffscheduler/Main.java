/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staffscheduler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
/**
 *
 * @author Abe
 */
public class Main {
    
    //create add/remove staff member options in ui CHECK
    //bunk changes CHECK
    //give easy user output
    //input file paths
    
    //public static final String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\Abe\\Downloads\\Staff Board 2019.xlsx";
    public static LinkedList<Employee> staffList = new LinkedList<Employee>();
    public static LinkedList<Employee> directorList = new LinkedList<Employee>();
    public static String[] positions = {"Theatre", "Theatre(tech)", "Theatre(dir)", "Theatre(rock band)", "Photography", "Photography(dir)", "Ceramics", "Ceramics(dir)", "Woodworking", "Woodworking(dir)", "Dance", "T-Shirt", "T-shirt(dir)", "Art", "Art(dir)", "Glass", "Glass(dir)", "Painting", "Cooking", "Cooking(dir)", "Minor Sports(golf)", "Minor Sports(VB)", "Minor Sports(FB)", "Minor Sports(Archery)", "Minor Sports(Cheer)", "Baseball/Softball", "Baseball/Softball(dir)", "Gymnastics", "Gymnastics(dir)", "Gymnastics(parkour)", "Lacrosse", "Soccer", "Soccer(dir)", "Hockey", "Basketball", "Basketball(dir)", "Fitness", "Yoga", "Aerobics", "Zumba", "Weights", "STEM", "Fishing", "Hondas", "Hondas(dir)", "Mountain Bikes", "Mountain Bikes(dir)", "Horseback Riding", "Horseback Riding(dir)", "COALS", "COALS(dir)", "Ropes/Wall", "Ropes/Wall(dir)", "Evening Activities", "Canadensis Today/Media", "Canadensis Today/Media(dir)", "Camp Photographer", "Swimming", "Swimming(dir)", "Lake", "Lake(dir)", "Lake(driver)", "Lake(kayak/canoe)", "Lake(sailing)", "Office", "Nurse", "Programming", "Programming(AD)", "Programming(Asst AD)", "Programming(Camp Mom)", "Programming(Parent Relations)", "Programming(Tours)", "General", "General(Group Leader)", "General(JC)", "Ready Staff", "Head Counselor", "Other"};
    public static String[] activities = {"Sailing", "Lake", "Bananna Boat", "Ice Mountain", "Lake Boats", "Lake Intruction", "Lake Recreation", "Swim", "Pool", "Blue Pool", "Pool Party", "Waterslides", "Swim Instruction", "Swim Rec", "Swim Team", "Video Editing", "Video Production", "Ropes", "Ropes & Climbing Wall", "Climbing Wall", "Outdoor Cooking", "Mountain Bikes", "MTB Road Riders", "Hondas", "Quads", "Fishing", "STEM", "Board Games", "Coding", "Lego", "Model Making", "Perler Beads", "Robotics", "Rocketry", "Rubik's Cube", "Weights", "Zumba", "Aerobics", "Bootcamp", "Yoga", "Fitness Center", "Basketball", "Basketball Clinic", "Basketball League", "Varsity", "WNBA", "WNCAA", "NCAA", "NBA", "Hockey", "Floor Hockey", "Roller Hockey", "Hockey Clinic", "Hockey League", "Soccer", "Soccer Clinic", "Soccer League", "Lacrosse", "Ninja Warrior", "Gymnastics", "Gymnastics -Foam Pit", "Gymnastics - Bar, Beam, Floor & Vault", "Softball", "Baseball", "Softball Clinic", "Softball League", "Baseball Clinic", "Baseball League", "MLB", "CBL", "Batting Cage", "Cheer Leading", "Archery", "Flag Football", "Beach Volleyball", "Newcomb", "Volleyball", "Volleyball Leagues", "Volleyball Clinic", "Golf", "SNAG", "Cooking", "Painting", "Glass Fusion", "Art Shack", "Beading & Bracelets", "Calligraphy", "Zendoodle", "T-Shirt Studio", "Dance", "Woodworking", "Dramatic Arts", "Ukulele", "Digital Art & Design", "Photo - Digital", "Ceramics", "Ceramics Wheel", "Crochet", "Gaga", "Kiting", "Magic Tricks", "Mah Jongg", "Old Alma Maters", "Ping Pong", "Tetherball", "Ultimate Frisbee", "Book Club", "Running", "Bunko", "Card Games", "CIT Activity", "Jump Rope Skills", "Kickboxing"};
    public static String divBreakDown = "";//"C:\\Users\\Abe\\Downloads\\2019 Divisional Breakdown as of 6.23.19.xlsx";
    public static String assignmentReview = "";//"C:\\Users\\Abe\\Downloads\\AssignmentReview.xls";
    public static String bunkSchedule = "";//"C:\\Users\\Abe\\Downloads\\Day 3 7.27.19.xlsx";
    public static Schedule mySchedule;
    public static LinkedList<String> warnings = new LinkedList<String>();
    
    public static boolean isInteger(String strNum) 
    {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static void assignPositions(int e)
    {
           Scanner in = new Scanner(System.in);
           System.out.println(staffList.get(e).name + " " + staffList.get(e).position);
           
           while(true)
           {
               String s = in.nextLine();
                if(s.equals("x"))
                {
                    return;
                }
                if(contains(positions, s))
                {
                    staffList.get(e).position = s;
                    return;
                }
                System.out.println(staffList.get(e).name + staffList.get(e).position);
           }
    }
    
    public static boolean contains(String[] arr, String c)
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i].equals(c))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean saveStaff()
    {
        try {
            FileWriter writer = new FileWriter("StaffList.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            for(int i = 0; i < staffList.size(); i++)
            {
                bufferedWriter.write("([" + staffList.get(i).name + "] ");
                bufferedWriter.write("[" + staffList.get(i).bunk + "] ");
                bufferedWriter.write("[" + staffList.get(i).position + "] ");
                
                for(int j = 0; j < staffList.get(i).skills.size(); j++)
                {
                    bufferedWriter.write("[" + staffList.get(i).skills.get(j) + "] ");
                }
                bufferedWriter.write(")");
                bufferedWriter.newLine();
            }
 
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            //System.out.println("failure");
            e.printStackTrace();
            return false;
        }
    }
    
    public static void updateDirectorList()
    {
        directorList.clear();
        for(int i = 0; i < staffList.size(); i++)
        {
            if(staffList.get(i).position.contains("dir"))
            {
                directorList.add(staffList.get(i));
            }
        }
    }
    
    public static boolean loadStaff()
    {
        try {
            staffList = new LinkedList<Employee>();
            
            FileReader reader = new FileReader("StaffList.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String name = null;
                String bunk = null;
                String position = null;
                LinkedList<String> s = new LinkedList<String>();
                
                for(int i = 0; i < line.length(); i++)
                {
                    if(line.charAt(i) == '[')
                    {
                        if(name == null)
                        {
                            name = line.substring(i + 1, line.indexOf("]", i + 1));
                            i = line.indexOf("]", i + 1);
                        }
                        else
                        {
                            if(bunk == null)
                            {
                                bunk = line.substring(i + 1, line.indexOf("]", i + 1));
                                i = line.indexOf("]", i + 1);
                            }
                            else
                            {
                                if(position == null)
                                {
                                    position = line.substring(i + 1, line.indexOf("]", i + 1));
                                    i = line.indexOf("]", i + 1);
                                }
                                else
                                {
                                    s.add(line.substring(i + 1, line.indexOf("]", i + 1)));
                                    i = line.indexOf("]", i + 1);
                                }
                            }
                        }                                                
                    }
                }
                Employee toAdd = new Employee(name, bunk);
                toAdd.position = position;
                toAdd.skills = s;
                staffList.add(toAdd);
            }
            reader.close();
            bufferedReader.close();
            updateDirectorList();
            return true; 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void populateStaff(String filename) throws IOException, InvalidFormatException
    {
        Workbook workbook = WorkbookFactory.create(new File(filename));
        DataFormatter dataFormatter = new DataFormatter();
        
        String bunkPrefix = null;
        
        for(int i = 0; i < workbook.getSheetAt(0).getLastRowNum(); i++)
        {
            if(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(0)).equals("LJR"))
            {
                if(bunkPrefix == null)
                {
                    bunkPrefix = "b";
                }
                else
                {
                    if(bunkPrefix.equals("b"))
                    {
                        bunkPrefix = "g";
                    }
                }                
            }
            
            if(isInteger(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(0))))
            {
                System.out.println(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(1)) + " " + bunkPrefix + dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(0)));
                staffList.add(new Employee(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(1)), bunkPrefix + dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(0))));
            }
            if(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(0)).equals("OOB"))
            {
                staffList.add(new Employee(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(1)), dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(0))));
            }
        }    
        workbook.close();
    }
    
    public static void assignPosition(Employee e, String job)
    {
        e.position = job;
        for(int i = 0; i < getActivities(job).length; i++)
        {
            e.skills.add(getActivities(job)[i]);
        }
        updateDirectorList();
    }
    
    public static void addSkill(Employee e, String skill)
    {
        if(!e.skills.contains(skill))
        {
            e.skills.add(skill);
        }
    }
    
    public static String[] getActivities(String job)
    {
        if(job != null)
        {
            if(job.equals("Theatre"))
            {
                String[] toReturn = {"Dramatic Arts"}; 
                return toReturn;
            }        
            if(job.equals("Theatre(tech)"))
            {
                String[] toReturn = {"Dramatic Arts"}; 
                return toReturn;
            }        
            if(job.equals("Theatre(dir)"))
            {
                String[] toReturn = {"Dramatic Arts"}; 
                return toReturn;
            }        
            if(job.equals("Theatre(rock band)"))
            {
                String[] toReturn = {"Ukulele"}; 
                return toReturn;
            }        
            if(job.equals("Photography"))
            {
                String[] toReturn = {"Digital Art & Design", "Photo - Digital"}; 
                return toReturn;
            }
            if(job.equals("Photography(dir)"))
            {
                String[] toReturn = {"Digital Art & Design", "Photo - Digital"}; 
                return toReturn;
            }        
            if(job.equals("Ceramics"))
            {
                String[] toReturn = {"Ceramics", "Ceramics Wheel"}; 
                return toReturn;
            }        
            if(job.equals("Ceramics(dir)"))
            {
                String[] toReturn = {"Ceramics", "Ceramics Wheel"}; 
                return toReturn;
            }        
            if(job.equals("Woodworking"))
            {
                String[] toReturn = {"Woodworking"}; 
                return toReturn;
            }        
            if(job.equals("Woodworking(dir)"))
            {
                String[] toReturn = {"Woodworking"}; 
                return toReturn;
            }
            if(job.equals("Dance"))
            {
                String[] toReturn = {"Dance"}; 
                return toReturn;
            }        
            if(job.equals("T-Shirt"))
            {
                String[] toReturn = {"T-Shirt Studio"}; 
                return toReturn;
            }        
            if(job.equals("T-shirt(dir)"))
            {
                String[] toReturn = {"T-Shirt Studio"}; 
                return toReturn;
            }        
            if(job.equals("Art"))
            {
                String[] toReturn = {"Art Shack", "Beading & Bracelets", "Calligraphy", "Zendoodle"}; 
                return toReturn;
            }        
            if(job.equals("Art(dir)"))
            {
                String[] toReturn = {"Art Shack", "Beading & Bracelets", "Calligraphy", "Zendoodle"}; 
                return toReturn;
            }
            if(job.equals("Glass"))
            {
                String[] toReturn = {"Glass Fusion"}; 
                return toReturn;
            }        
            if(job.equals("Glass(dir)"))
            {
                String[] toReturn = {"Glass Fusion"}; 
                return toReturn;
            }        
            if(job.equals("Painting"))
            {
                String[] toReturn = {"Painting"}; 
                return toReturn;
            }        
            if(job.equals("Cooking"))
            {
                String[] toReturn = {"Cooking"}; 
                return toReturn;
            }        
            if(job.equals("Cooking(dir)"))
            {
                String[] toReturn = {"Cooking"}; 
                return toReturn;
            }
            if(job.equals("Minor Sports(golf)"))
            {
                String[] toReturn = {"Golf", "SNAG"}; 
                return toReturn;
            }        
            if(job.equals("Minor Sports(VB)"))
            {
                String[] toReturn = {"Beach Volleyball", "Newcomb", "Volleyball", "Volleyball Leagues", "Volleyball Clinic"}; 
                return toReturn;
            }
            if(job.equals("Minor Sports(FB)"))
            {
                String[] toReturn = {"Flag Football"}; 
                return toReturn;
            }        
            if(job.equals("Minor Sports(Archery)"))
            {
                String[] toReturn = {"Archery"}; 
                return toReturn;
            }
            if(job.equals("Minor Sports(Cheer)"))
            {
                String[] toReturn = {"Cheer Leading"}; 
                return toReturn;
            }        
            if(job.equals("Baseball/Softball"))
            {
                String[] toReturn = {"Softball", "Baseball", "Softball Clinic", "Softball League", "Baseball Clinic", "Baseball League", "MLB", "CBL", "Batting Cage"}; 
                return toReturn;
            }
            if(job.equals("Baseball/Softball(dir)"))
            {
                String[] toReturn = {"Softball", "Baseball", "Softball Clinic", "Softball League", "Baseball Clinic", "Baseball League", "MLB", "CBL", "Batting Cage"}; 
                return toReturn;
            }        
            if(job.equals("Gymnastics"))
            {
                String[] toReturn = {"Gymnastics", "Gymnastics -Foam Pit", "Gymnastics - Bar, Beam, Floor & Vault"}; 
                return toReturn;
            }
            if(job.equals("Gymnastics(dir)"))
            {
                String[] toReturn = {"Gymnastics", "Gymnastics -Foam Pit", "Gymnastics - Bar, Beam, Floor & Vault"}; 
                return toReturn;
            }        
            if(job.equals("Gymnastics(parkour)"))
            {
                String[] toReturn = {"Ninja Warrior"}; 
                return toReturn;
            }
            if(job.equals("Lacrosse"))
            {
                String[] toReturn = {"Lacrosse"}; 
                return toReturn;
            }        
            if(job.equals("Soccer"))
            {
                String[] toReturn = {"Soccer", "Soccer Clinic", "Soccer League"}; 
                return toReturn;
            }
            if(job.equals("Soccer(dir)"))
            {
                String[] toReturn = {"Soccer", "Soccer Clinic", "Soccer League"}; 
                return toReturn;
            }        
            if(job.equals("Hockey"))
            {
                String[] toReturn = {"Hockey", "Floor Hockey", "Roller Hockey", "Hockey Clinic", "Hockey League"}; 
                return toReturn;
            }
            if(job.equals("Basketball"))
            {
                String[] toReturn = {"Basketball", "Basketball Clinic", "Basketball League", "Varsity", "WNBA", "WNCAA", "NCAA", "NBA"}; 
                return toReturn;
            }        
            if(job.equals("Basketball(dir)"))
            {
                String[] toReturn = {"Basketball", "Basketball Clinic", "Basketball League", "Varsity", "WNBA", "WNCAA", "NCAA", "NBA"}; 
                return toReturn;
            }
            if(job.equals("Fitness"))
            {
                String[] toReturn = {"Fitness Center"}; 
                return toReturn;
            }        
            if(job.equals("Yoga"))
            {
                String[] toReturn = {"Yoga"}; 
                return toReturn;
            }
            if(job.equals("Aerobics"))
            {
                String[] toReturn = {"Aerobics", "Bootcamp"}; 
                return toReturn;
            }        
            if(job.equals("Zumba"))
            {
                String[] toReturn = {"Zumba"}; 
                return toReturn;
            }
            if(job.equals("Weights"))
            {
                String[] toReturn = {"Weights"}; 
                return toReturn;
            }        
            if(job.equals("STEM"))
            {
                String[] toReturn = {"STEM", "Board Games", "Coding", "Lego", "Model Making", "Perler Beads", "Robotics", "Rocketry", "Rubik's Cube"}; 
                return toReturn;
            }
            if(job.equals("Fishing"))
            {
                String[] toReturn = {"Fishing"}; 
                return toReturn;
            }        
            if(job.equals("Hondas"))
            {
                String[] toReturn = {"Hondas", "Quads"}; 
                return toReturn;
            }
            if(job.equals("Hondas(dir)"))
            {
                String[] toReturn = {"Hondas", "Quads"}; 
                return toReturn;
            }
            if(job.equals("Mountain Bikes"))
            {
                String[] toReturn = {"Mountain Bikes", "MTB Road Riders"}; 
                return toReturn;
            }
            if(job.equals("Mountain Bikes(dir)"))
            {
                String[] toReturn = {"Mountain Bikes", "MTB Road Riders"}; 
                return toReturn;
            }
            if(job.equals("Horseback Riding"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Horseback Riding(dir)"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("COALS"))
            {
                String[] toReturn = {"Outdoor Cooking"}; 
                return toReturn;
            }
            if(job.equals("COALS(dir)"))
            {
                String[] toReturn = {"Outdoor Cooking"}; 
                return toReturn;
            }
            if(job.equals("Ropes/Wall"))
            {
                String[] toReturn = {"Ropes", "Ropes & Climbing Wall", "Climbing Wall"}; 
                return toReturn;
            }
            if(job.equals("Ropes/Wall(dir)"))
            {
                String[] toReturn = {"Ropes", "Ropes & Climbing Wall", "Climbing Wall"}; 
                return toReturn;
            }
            if(job.equals("Evening Activities"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Canadensis Today/Media"))
            {
                String[] toReturn = {"Video Editing", "Video Production"}; 
                return toReturn;
            }
            if(job.equals("Canadensis Today/Media(dir)"))
            {
                String[] toReturn = {"Video Editing", "Video Production"}; 
                return toReturn;
            }
            if(job.equals("Camp Photographer"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Swimming"))
            {
                String[] toReturn = {"Swim", "Pool", "Blue Pool", "Pool Party", "Waterslides", "Swim Instruction", "Swim Rec", "Swim Team"}; 
                return toReturn;
            }
            if(job.equals("Swimming(dir)"))
            {
                String[] toReturn = {"Swim", "Pool", "Blue Pool", "Pool Party", "Waterslides", "Swim Instruction", "Swim Rec", "Swim Team"}; 
                return toReturn;
            }
            if(job.equals("Lake"))
            {
                String[] toReturn = {"Lake", "Bananna Boat", "Ice Mountain", "Lake Boats", "Lake Intruction", "Lake Recreation"}; 
                return toReturn;
            }
            if(job.equals("Lake(dir)"))
            {
                String[] toReturn = {"Lake", "Bananna Boat", "Ice Mountain", "Lake Boats", "Lake Intruction", "Lake Recreation"}; 
                return toReturn;
            }
            if(job.equals("Lake(driver)"))
            {
                String[] toReturn = {"Lake", "Bananna Boat", "Ice Mountain", "Lake Boats", "Lake Intruction", "Lake Recreation"}; 
                return toReturn;
            }
            if(job.equals("Lake(kayak/canoe)"))
            {
                String[] toReturn = {"Lake", "Bananna Boat", "Ice Mountain", "Lake Boats", "Lake Intruction", "Lake Recreation"}; 
                return toReturn;
            }
            if(job.equals("Lake(sailing)"))
            {
                String[] toReturn = {"Lake", "Bananna Boat", "Ice Mountain", "Lake Boats", "Lake Intruction", "Lake Recreation", "Sailing"}; 
                return toReturn;
            }
            if(job.equals("Office"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Nurse"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Programming"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Programming(AD)"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Programming(Asst AD)"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Programming(Camp Mom)"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Programming(Parent Relations)"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Programming(Tours)"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("General"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("General(Group Leader)"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("General(JC)"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Ready Staff"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Head"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Counselor"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            if(job.equals("Other"))
            {
                String[] toReturn = {}; 
                return toReturn;
            }
            String[] toReturn = {}; 
            return toReturn;
        }
        else
        {
            String[] toReturn = {}; 
            return toReturn;
        }
    }
    
    static String[] getPositions(String activity)
    {
        String[] toReturn = new String[0];
        
        for(int i = 0; i < positions.length; i++)
        {
            if(contains(getActivities(positions[i]), activity))
            {
                String[] temp = new String[toReturn.length + 1];
                for(int j = 0; j < toReturn.length; j++)
                {
                    temp[j] = toReturn[j];
                }
                temp[toReturn.length] = positions[i];
                toReturn = temp;
            }
        }
        
        return toReturn;
    }
    
    public static LinkedList<Employee> employeesWithTitles(String[] positions)
    {
        LinkedList<Employee> toReturn = new LinkedList();
        
        for(int i = 0; i < staffList.size(); i++)
        {
            if(contains(positions, (staffList.get(i).position)))
            {
                toReturn.add(staffList.get(i));
            }
        }
        
        return toReturn;
    }
    
    public static void populateSkills()
    {
        for(int i = 0; i < staffList.size(); i++)
        {
            for(int j = 0; j < getActivities(staffList.get(i).position).length; j++)
            {
                addSkill(staffList.get(i), getActivities(staffList.get(i).position)[j]);
            }
        }
    }
    
    public static void renameJob(String jobFormer, String jobNow)
    {
        for(int i = 0; i < staffList.size(); i++)
        {
            if(staffList.get(i).position.equals(jobFormer))
            {
                staffList.get(i).position = jobNow;
            }
        }
        updateDirectorList();
    }
    
    public static void setJobByName(String name, String job)
    {
        for(int i = 0; i < staffList.size(); i++)
        {
            if(staffList.get(i).name.equals(name))
            {
                staffList.get(i).position = job;
                return;
            }
        }
        updateDirectorList();
    }
    
    public static Employee getEmployeeByName(String n)
    {
        for(int i = 0; i < staffList.size(); i++)
        {
            if(staffList.get(i).name.equals(n))
            {
                return staffList.get(i);
            }
        }
        return null;
    }
    
    public static void scheduleFromAssignmentReview(String filepath) throws IOException, InvalidFormatException
    {
        Workbook workbook = WorkbookFactory.create(new File(filepath));
        DataFormatter dataFormatter = new DataFormatter();
        
        for(int i = 1; i < workbook.getSheetAt(0).getLastRowNum(); i++)
        {
            if(!dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Tennis") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Pickle Ball") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Sailing") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Lake") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Banana Boat") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Ice Mountain") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Lake Boats") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Lake Instruction") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Lake Recreation") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Swim") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Pool") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Blue Pool") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Pool Party") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Waterslides") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Swim Instruction") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Swim Rec") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Swim Team") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Ropes") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Climbing Wall") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Ropes & Climbing Wall") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Hondas") && !dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)).equals("Quads"))
            {
                //System.out.println(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)) + " " + Integer.parseInt(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(11))) + " " + Integer.parseInt(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(2))));
                Schedule.assignAll(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)), Schedule.getActivity(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4))).idealRatio, Integer.parseInt(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(11))), Integer.parseInt(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(2))), directorList, 0.0);
                Schedule.assignAll(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4)), Schedule.getActivity(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4))).idealRatio, Integer.parseInt(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(11))), Integer.parseInt(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(2))), staffList, 0.0);
                Schedule.getActivity(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(4))).elective[Integer.parseInt(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(2)))] = true;
            }            
        }
        workbook.close();
//C:\\Users\\Abe\\Downloads\\AssignmentReview        
    }
    
    public static void initializeBunkPops(String filePath) throws IOException, InvalidFormatException
    {
        BunkPopulation myPops = new BunkPopulation();
        
        
        
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        DataFormatter dataFormatter = new DataFormatter();
        
        for(int i = 3; i < workbook.getSheetAt(0).getLastRowNum(); i++)
        {
            String bunkNum = dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(2));
            
            if(bunkNum != null && !bunkNum.equals("") && !bunkNum.contains("TOTAL"))
            {
                if(bunkNum.contains("(L"))
                {
                    bunkNum = bunkNum.substring(0,1);
                }
                if(bunkNum.contains("(S") || bunkNum.contains("(C"))
                {
                    bunkNum = bunkNum.substring(0,2);
                }
                myPops.addBunk('g', Integer.parseInt(bunkNum), Integer.parseInt(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(3))));
            }            
        }
        
        for(int i = 3; i < workbook.getSheetAt(0).getLastRowNum(); i++)
        {
            String bunkNum = dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(5));
            if(bunkNum != null && !bunkNum.equals("") && !bunkNum.contains("TOTAL"))
            {
                if(bunkNum.contains("(L"))
                {
                    bunkNum = bunkNum.substring(0,1);
                }
                if(bunkNum.contains("(S") || bunkNum.contains("(C"))
                {
                    bunkNum = bunkNum.substring(0,2);
                }
                myPops.addBunk('b', Integer.parseInt(bunkNum), Integer.parseInt(dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(6))));
            }            
        }
        workbook.close();        
    }
    
    public static String extractActivity(String toExtract)
    {
        if(toExtract.toLowerCase().contains("bunko") || "bunko".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("lice check") || "lice check".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("elective") || "elective".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("lake") || "lake".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("ice mountain") || "ice mountain".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("boat") || "boat".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("sail") || "sail".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("hondas") || "hondas".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("quads") || "quads".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("ropes") || "ropes".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("wall") || "wall".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("pool") || "pool".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("swim") || "swim".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("bunks") || "bunks".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("clean up") || "clean up".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("mtv") || "mtv".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("chill") || "chill".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("bbk") || "bbk".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("tennis") || "tennis".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("outdoor pursuits") || "outdoor pursuits".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("off") || "off".contains(toExtract.toLowerCase()))
        {
            return "ignore";
        }
        if(toExtract.toLowerCase().contains("lax"))
        {
            return "Lacrosse";
        }
        if(toExtract.toLowerCase().contains("bk league") || "bk league".contains(toExtract.toLowerCase()))
        {
            return "Basketball League";
        }
        if(toExtract.toLowerCase().contains("soc league") || "soc league".contains(toExtract.toLowerCase()))
        {
            return "Soccer League";
        }
        if(toExtract.toLowerCase().contains("soc clinic") || "soc clinic".contains(toExtract.toLowerCase()))
        {
            return "Soccer Clinic";
        }
        if(toExtract.toLowerCase().contains("beads") || "beads".contains(toExtract.toLowerCase()))
        {
            return "Beading & Bracelets";
        }
        if(toExtract.toLowerCase().contains("bead & bracelets") || "bead & bracelets".contains(toExtract.toLowerCase()))
        {
            return "Beading & Bracelets";
        }
        if(toExtract.toLowerCase().contains("softball clinic") || "softball clinic".contains(toExtract.toLowerCase()))
        {
            return "Softball Clinic";
        }
        if(toExtract.toLowerCase().contains("softball league") || "softball league".contains(toExtract.toLowerCase()))
        {
            return "Softball League";
        }
        if(toExtract.toLowerCase().contains("baseball clinic") || "baseball clinic".contains(toExtract.toLowerCase()))
        {
            return "Baseball Clinic";
        }
        if(toExtract.toLowerCase().contains("baseball league") || "baseball league".contains(toExtract.toLowerCase()))
        {
            return "Baseball League";
        }
        for(int i = 0; i < activities.length; i++)
        {
            if(toExtract.contains(activities[i].toLowerCase()) || activities[i].toLowerCase().contains(toExtract))
            {
                return activities[i];
            }
        }        
        return null;
    }
    
    public static void scheduleFromBunkSchedule(String filePath) throws IOException, InvalidFormatException
    {
        
        
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        DataFormatter dataFormatter = new DataFormatter();
        
        for(int i = 6; i < workbook.getSheetAt(0).getLastRowNum(); i++)
        {
            if(workbook.getSheetAt(0).getRow(i) != null)
            {
                String bunkNum = dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(0));
                if(bunkNum != null && !bunkNum.equals("") && (bunkNum.substring(0, 2).equals("G-") || bunkNum.substring(0, 2).equals("B-")))
                {
                    int myPop = BunkPopulation.getPop(bunkNum.substring(0, 1), Integer.parseInt(bunkNum.substring(2)));
                    for(int j = 1; j < 7; j++)
                    {
                        String toExtract = dataFormatter.formatCellValue(workbook.getSheetAt(0).getRow(i).getCell(j)).toLowerCase();
                        String extraction = extractActivity(toExtract);
                        if(extraction != null)
                        {                        
                            if(extraction.equals("ignore"))
                            {
                            
                            }
                            else
                            {
                                Schedule.assignAll(extraction, Schedule.getActivity(extraction).idealRatio, myPop, j, directorList, 1.0);
                                Schedule.assignAll(extraction, Schedule.getActivity(extraction).idealRatio, myPop, j, staffList, 1.0); 
                            }                                               
                        }
                        else
                        {                            
                            warnings.add(toExtract + " not schedled: activity not recognized");
                        }
                    }           
                } 
            }
                       
        }
        
        for(int i = 6; i < workbook.getSheetAt(1).getLastRowNum(); i++)
        {
            if(workbook.getSheetAt(1).getRow(i) != null)
            {
                String bunkNum = dataFormatter.formatCellValue(workbook.getSheetAt(1).getRow(i).getCell(0));
                if(bunkNum != null && !bunkNum.equals("") && (bunkNum.substring(0, 2).equals("G-") || bunkNum.substring(0, 2).equals("B-")))
                {
                    int myPop = BunkPopulation.getPop(bunkNum.substring(0, 1), Integer.parseInt(bunkNum.substring(2)));
                    for(int j = 1; j < 7; j++)
                    {
                        String toExtract = dataFormatter.formatCellValue(workbook.getSheetAt(1).getRow(i).getCell(j)).toLowerCase();
                        String extraction = extractActivity(toExtract);
                        if(extraction != null)
                        {                        
                            if(extraction.equals("ignore"))
                            {
                            
                            }
                            else
                            {
                                Schedule.assignAll(extraction, Schedule.getActivity(extraction).idealRatio, myPop, j, directorList, 1.0);
                                Schedule.assignAll(extraction, Schedule.getActivity(extraction).idealRatio, myPop, j, staffList, 1.0); 
                            }                     
                        }
                        else
                        {
                            warnings.add(toExtract + " not schedled: activity not recognized");
                        }
                    }           
                }
            }                        
        }
    }
    
    public static boolean saveFilePaths()
    {
        try {
            FileWriter writer = new FileWriter("filePaths.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.write("[" + divBreakDown + "] ");
            bufferedWriter.write("(" + assignmentReview + ") ");
            bufferedWriter.write("{" + bunkSchedule + "}");            
 
            bufferedWriter.close();
            writer.close();
            return true;
        } catch (IOException e) {
            //System.out.println("failure");
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean loadFilePaths()
    {
        try {            
            FileReader reader = new FileReader("filePaths.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);            
            String line = bufferedReader.readLine();            
            if(line == null)
            {
                return false;
            }                         
            //System.out.println(line);
            divBreakDown = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
            assignmentReview = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
            bunkSchedule = line.substring(line.indexOf("{") + 1, line.indexOf("}"));                       
            
            reader.close();
            return true; 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }    
    
    public static void constructSchedule() throws IOException, InvalidFormatException
    {
        mySchedule = new Schedule(staffList);
        initializeBunkPops(divBreakDown);                        
        
        Schedule.assignAllStrong("Lake", employeesWithTitles(getPositions("Lake")));
        Schedule.assignAllStrong("Ropes/Wall", employeesWithTitles(getPositions("Ropes & Climbing Wall")));
        Schedule.assignAllStrong("Pool", employeesWithTitles(getPositions("Pool")));
        Schedule.assignAllStrong("Hondas", employeesWithTitles(getPositions("Hondas")));
        Schedule.assignAllStrong("Camp Photo", employeesWithTitles(new String[] {"Camp Photographer"}));
        
        warnings = new LinkedList<String>();
        
        scheduleFromBunkSchedule(bunkSchedule);                

        scheduleFromAssignmentReview(assignmentReview);
        
        Schedule.correctDoubleOffs();
    }
    
    public static void displayAll() throws IOException, InvalidFormatException
    {
        mySchedule.printSchedule();
        
        Schedule.printActivities(false, null);
        
        BunkPopulation.displayPops();
    }
    
    public static void main(String[] args) throws IOException, InvalidFormatException 
    {
        
        //populateStaff("C:\\Users\\Abe\\Downloads\\DAY 2_7.7.19.xls");
        //saveStaff();
        //loadStaff();
        
        //setJobByName("James LeBrun", "Woodworking");
        //setJobByName("Ellie Jindra", "Minor Sports(FB)");
        //setJobByName("Lozza Pendall", "Fishing");
        //populateSkills();
        //for(int i = 0; i < staffList.size(); i++)
        //{
        //    assignPositions(i);
        //}
        
        /*
        initializeBunkPops(divBreakDown);
                
        mySchedule = new Schedule(staffList);
        
        Schedule.assignAllStrong("Lake", employeesWithTitles(getPositions("Lake")));
        Schedule.assignAllStrong("Ropes/Wall", employeesWithTitles(getPositions("Ropes & Climbing Wall")));
        Schedule.assignAllStrong("Pool", employeesWithTitles(getPositions("Pool")));
        Schedule.assignAllStrong("Hondas", employeesWithTitles(getPositions("Hondas")));
        Schedule.assignAllStrong("Camp Photo", employeesWithTitles(new String[] {"Camp Photographer"}));
        
        scheduleFromBunkSchedule(bunkSchedule);                

        scheduleFromAssignmentReview(assignmentReview);
        
        Schedule.correctDoubleOffs();
        
        mySchedule.printSchedule();
        
        Schedule.printActivities();
                        
        BunkPopulation.displayPops();
        
        Schedule.scheduleToExcel();
        */
        
        
        
        UserInterface.launchUI(args);
               
        //populateSkills();
        //saveStaff();
    }
}
