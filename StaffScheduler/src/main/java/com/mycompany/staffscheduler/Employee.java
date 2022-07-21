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
public class Employee {
    LinkedList<String> skills = new LinkedList<String>();
    String name;
    String bunk;
    String position;
    LinkedList priorities = new LinkedList();
    
    public Employee(String n, String b)
    {
        this.name = n;
        this.bunk = b;
    }
    
    public void addPriority(String activity, int priority)
    {
        priorities.add(activity);
        priorities.add(priority);
    }
    
    public void removePriority(String activity)
    {
        for(int i = 0; i < priorities.size(); i++)
        {
            if(priorities.get(i).equals(activity))
            {
                priorities.remove(i + 1);
                priorities.remove(i);
            }
        }
    }
    
    public int getPriority(String activity)
    {
        for(int i = 0; i < priorities.size(); i++)
        {
            if(priorities.get(i).equals(activity))
            {
                return (int)priorities.get(i + 1);
            }
        }
        return 0;
    }
    
    public void setPriority(String activity, int priority)
    {
        for(int i = 0; i < priorities.size(); i++)
        {
            if(priorities.get(i).equals(activity))
            {
                if(priority == 0)
                {
                    removePriority(activity);
                    return;
                }
                priorities.set(i + 1, priority);
                return;
            }
        }
        addPriority(activity, priority);
    }
}
