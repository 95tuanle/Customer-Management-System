package edu.humber; /*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 2
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Mon Feb 14 2022
 **************************************************************************************************/

import edu.humber.exceptions.EmailInputException;
import edu.humber.exceptions.NameInputException;
import edu.humber.exceptions.PhoneInputException;
import edu.humber.exceptions.PostalCodeInputException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Validator
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class Validator {
    private final Border defaultJTextFieldBorder;

    public Validator(Border defaultJTextFieldBorder) {
        this.defaultJTextFieldBorder = defaultJTextFieldBorder;
    }

    //    Validate user input base on conditions, this will trigger UI elements to let user know which fields that they entered invalid inputs
    public boolean validate(JTextField nameJTextField, JLabel nameResultJLabel, JTextField phoneJTextField, JLabel phoneResultJLabel, JTextField emailJTextField, JLabel emailResultJLabel, JTextField postalCodeJTextField, JLabel postalCodeResultJLabel) {
        boolean isValid = true;
        if (nameJTextField.getText().isBlank() || nameJTextField.getText().isEmpty()) {
            isValid = false;
            try {
                throw new NameInputException("Please enter the customer's name.");
            } catch (NameInputException ex) {
                triggerInvalidJTextField(nameJTextField, nameResultJLabel, ex.getMessage());
            }
        }

        if (phoneJTextField.getText().isBlank() || phoneJTextField.getText().isEmpty()
                || !phoneJTextField.getText().matches("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")) {
            isValid = false;
            try {
                throw new PhoneInputException("Please enter a valid phone number.");
            } catch (PhoneInputException ex) {
                triggerInvalidJTextField(phoneJTextField, phoneResultJLabel, ex.getMessage());
            }
        }

        if (emailJTextField.getText().isBlank() || emailJTextField.getText().isEmpty()
                || !emailJTextField.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            isValid = false;
            try {
                throw new EmailInputException("Please enter a valid email.");
            } catch (EmailInputException ex) {
                triggerInvalidJTextField(emailJTextField, emailResultJLabel, ex.getMessage());
            }
        }

//        Current postal code regex is for Canadian postal codes only
        if (postalCodeJTextField.getText().isBlank() || postalCodeJTextField.getText().isEmpty()
                || !postalCodeJTextField.getText().matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$")) {
            isValid = false;
            try {
                throw new PostalCodeInputException("Please enter a valid postal code.");
            } catch (PostalCodeInputException ex) {
                triggerInvalidJTextField(postalCodeJTextField, postalCodeResultJLabel, ex.getMessage());
            }
        }
        return isValid;
    }

    //    This will set up the UI elements to let the user know about the input errors
    private void triggerInvalidJTextField(JTextField jTextField, JLabel jLabel, String message) {
        jLabel.setText(message);
        jLabel.setForeground(Color.RED);
        jTextField.setBorder(new LineBorder(Color.RED, 1));
//        The UI elements will be reverted whenever the user make changes to them
        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jTextField.setBorder(defaultJTextFieldBorder);
                jLabel.setText("");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jTextField.setBorder(defaultJTextFieldBorder);
                jLabel.setText("");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                jTextField.setBorder(defaultJTextFieldBorder);
                jLabel.setText("");
            }
        });
    }
}