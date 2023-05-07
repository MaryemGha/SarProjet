package com.example.RestProject.Models;

import javax.annotation.processing.Generated;
import jakarta.persistence.* ;


@Entity
public class Employer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id ;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String address ;

    @Column
    private int accountNum ;

    @Column
    private int grade ;

    @Column
    private int sup ;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSup() {
        return sup;
    }

    public void setSup(int sup) {
        this.sup = sup;
    }
}
