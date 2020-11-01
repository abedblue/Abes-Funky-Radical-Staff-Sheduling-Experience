/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staffscheduler;

import java.util.LinkedList;

/**
 *
 * @author Abe
 */
public class BunkPopulation {
    public static LinkedList<int[]> myBunks;
    
    public BunkPopulation()
    {
        myBunks = new LinkedList();
    }
    
    public void addBunk(char bg, int bunk, int numCampers)
    {
        int bgBin = -1;
        if(bg == 'b')
        {
            bgBin = 0;
        }
        if(bg == 'g')
        {
            bgBin = 1;
        }
        if(bgBin != -1)
        {
            int[] toAdd = new int[3];
            toAdd[0] = bgBin;
            toAdd[1] = bunk;
            toAdd[2] = numCampers;
            
            myBunks.add(toAdd);
        }
        else
        {
            System.out.println("b/g unspecified");
        }
    }
    
    public static void displayPops()
    {
        System.out.println();
        for(int i = 0; i < myBunks.size(); i++)
        {
            if(myBunks.get(i)[0] == 0)
            {
                System.out.print("B");
            }
            if(myBunks.get(i)[0] == 1)
            {
                System.out.print("G");
            }
            System.out.print(" " + myBunks.get(i)[1]);
            System.out.print(" " + myBunks.get(i)[2]);
            System.out.println();
        }
        System.out.println();
    }
    
    public static int getPop(String bg, int bunk)
    {
        int BG = -1;        
        if(bg.equals("b") || bg.equals("B"))
        {
            BG = 0;
        }
        if(bg.equals("g") || bg.equals("G"))
        {
            BG = 1;
        }
        for(int i = 0; i < myBunks.size(); i++)
        {
            if(myBunks.get(i)[0] == BG && myBunks.get(i)[1] == bunk)
            {
                return myBunks.get(i)[2];
            }
        }
        return -1;
    }
}
