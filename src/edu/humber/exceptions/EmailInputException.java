package edu.humber.exceptions; /*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 2
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Mon Feb 14 2022
 **************************************************************************************************/

/**
 * Email Input Exception
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class EmailInputException extends Throwable {
    public EmailInputException(String message) {
        super(message);
    }
}