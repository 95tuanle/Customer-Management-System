package edu.humber.models; /*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 2
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Mon Feb 14 2022
 **************************************************************************************************/

/**
 * Customer Model
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class Customer {
    private final int id;
    private String name;
    private String phone;
    private String email;
    private String postalCode;

    public Customer(int id, String name, String phone, String email, String postalCode) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String toString() {
        return this.id + "|" + this.name + "|" + this.phone + "|" + this.email + "|" + this.postalCode;
    }
}