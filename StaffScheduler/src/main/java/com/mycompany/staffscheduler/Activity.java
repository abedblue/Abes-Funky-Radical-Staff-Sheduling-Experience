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
    
    public Activity(String name, double r)
    {
        this.name = name;
        this.idealRatio = r;    
        numScheduled = new int[6];
        numCampers = new int[6];
        for(int i = 0; i < 6; i++)
        {
            numScheduled[i] = -1;
            numCampers[i] = 0;
        }
    }
}
