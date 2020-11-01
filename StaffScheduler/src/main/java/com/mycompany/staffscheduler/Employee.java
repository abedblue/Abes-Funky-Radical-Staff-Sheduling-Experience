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
    
    public Employee(String n, String b)
    {
        this.name = n;
        this.bunk = b;
    }
}
