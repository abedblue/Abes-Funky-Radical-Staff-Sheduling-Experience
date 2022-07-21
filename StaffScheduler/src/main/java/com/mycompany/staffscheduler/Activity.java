/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staffscheduler;

/**
 *
 * @author Abe
 */
public class Activity {
    String name;
    double idealRatio;
    int[] numScheduled;
    int[] numCampers;
    boolean[] elective;
    
    int ratioBunk;
    int ratioElective;
    int bBunk;
    int bElective;
    int countCounselorsBunk;
    int countCounselorsElective;
    int minBunk;
    int minElective;
    int maxBunk;
    int maxElective;
    
    public Activity(String name, double r)
    {
        this.name = name;
        this.idealRatio = r;    
        numScheduled = new int[6];
        numCampers = new int[6];
        elective = new boolean[6];
        for(int i = 0; i < 6; i++)
        {
            numScheduled[i] = -1;
            numCampers[i] = 0;
            elective[i] = false;
        }
    }
    
    public Activity(String name, int ratioBunk, int ratioElective, int bBunk, int bElective, int countCounselorsBunk, int countCounselorsElective, int minBunk, int minElective, int maxBunk, int maxElective)
    {
        this.name = name;
        this.ratioBunk = ratioBunk;
        this.ratioElective = ratioElective;
        this.bBunk = bBunk;
        this.bElective = bElective;
        this.countCounselorsBunk = countCounselorsBunk;
        this.countCounselorsElective = countCounselorsElective;
        this.minBunk = minBunk;
        this.minElective = minElective;
        this.maxBunk = maxBunk;
        this.maxElective = maxElective;
        
        numScheduled = new int[6];
        numCampers = new int[6];
        elective = new boolean[6];
        for(int i = 0; i < 6; i++)
        {
            numScheduled[i] = -1;
            numCampers[i] = 0;
            elective[i] = false;
        }
    }
}
