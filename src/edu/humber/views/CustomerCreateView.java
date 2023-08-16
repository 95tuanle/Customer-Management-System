package edu.humber.views; /*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 2
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Mon Feb 14 2022
 **************************************************************************************************/

import edu.humber.Validator;
import edu.humber.controllers.CustomerController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Customer Create View
 * This class forms the GUI for Create Customer pane. It also takes necessary actions to meet
 * the boundary conditions And finally call the method that pushes the data into customers.txt.
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class CustomerCreateView {
    //    All panels are grouped into a single panel with Border layout.
    private final JPanel addCustomerJPanel = new JPanel(new BorderLayout());
    private final Border defaultJTextFieldBorder = new JTextField().getBorder();

    public CustomerCreateView(CustomerController customerController) {

//        All fields and respective labels are grouped in a panel with grid layout
        JPanel gridJPanel = new JPanel(new GridLayout(9, 2));
//        All button are grouped into a panel with flow layout
        JPanel flowJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel titleJLabel = new JLabel("Customer Details");
        titleJLabel.setFont(new Font("", Font.BOLD, 13));
        JLabel nameJLabel = new JLabel("Name");
        JLabel phoneJLabel = new JLabel("Phone");
        JLabel emailJLabel = new JLabel("Email");
        JLabel postalCodeJLabel = new JLabel("Postal Code");

        JLabel titleLineBreakJLabel = new JLabel();
        JLabel nameLineBreak = new JLabel();
        JLabel phoneLineBreak = new JLabel();
        JLabel emailLineBreak = new JLabel();
        JLabel postalCodeLineBreak = new JLabel();

        JLabel nameResultJLabel = new JLabel();
        JLabel phoneResultJLabel = new JLabel();
        JLabel emailResultJLabel = new JLabel();
        JLabel postalCodeResultJLabel = new JLabel();

        JTextField nameJTextField = new JTextField();
        JTextField phoneJTextField = new JTextField();
        JTextField emailJTextField = new JTextField();
        JTextField postalCodeJTextField = new JTextField();

        JButton clearJButton = new JButton("CLEAR");
        JButton addJButton = new JButton("ADD");

        gridJPanel.add(titleJLabel);
        gridJPanel.add(titleLineBreakJLabel);

        gridJPanel.add(nameLineBreak);
        gridJPanel.add(nameResultJLabel);

        gridJPanel.add(nameJLabel);
        gridJPanel.add(nameJTextField);

        gridJPanel.add(phoneLineBreak);
        gridJPanel.add(phoneResultJLabel);

        gridJPanel.add(phoneJLabel);
        gridJPanel.add(phoneJTextField);

        gridJPanel.add(emailLineBreak);
        gridJPanel.add(emailResultJLabel);

        gridJPanel.add(emailJLabel);
        gridJPanel.add(emailJTextField);

        gridJPanel.add(postalCodeLineBreak);
        gridJPanel.add(postalCodeResultJLabel);

        gridJPanel.add(postalCodeJLabel);
        gridJPanel.add(postalCodeJTextField);

        flowJPanel.add(clearJButton);
        flowJPanel.add(addJButton);

        gridJPanel.setPreferredSize(new Dimension(700, 200));
        addCustomerJPanel.add(gridJPanel, BorderLayout.NORTH);
        addCustomerJPanel.add(flowJPanel, BorderLayout.CENTER);

//        When user clicks the clear button all field values are set to blank
        clearJButton.addActionListener(e -> {
            nameJTextField.setText("");
            phoneJTextField.setText("");
            emailJTextField.setText("");
            postalCodeJTextField.setText("");
            nameResultJLabel.setText("");
            nameJTextField.setBorder(defaultJTextFieldBorder);
            phoneResultJLabel.setText("");
            phoneJTextField.setBorder(defaultJTextFieldBorder);
            emailResultJLabel.setText("");
            emailJTextField.setBorder(defaultJTextFieldBorder);
            postalCodeResultJLabel.setText("");
            postalCodeJTextField.setBorder(defaultJTextFieldBorder);
        });

//        When the user clicks add button, the customer is added after performing a set of checks
        addJButton.addActionListener(e -> {
            if (new Validator(defaultJTextFieldBorder).validate(nameJTextField, nameResultJLabel, phoneJTextField, phoneResultJLabel, emailJTextField, emailResultJLabel, postalCodeJTextField, postalCodeResultJLabel)) {
                customerController.createCustomer(nameJTextField.getText(), phoneJTextField.getText(), emailJTextField.getText(), postalCodeJTextField.getText());

                nameJTextField.setText("");
                phoneJTextField.setText("");
                emailJTextField.setText("");
                postalCodeJTextField.setText("");

                JOptionPane.showMessageDialog(null, "Customer has been added successfully.");
            }
        });
    }

    /**
     * @return JPanel
     */
    public JPanel getAddCustomerJPanel() {
        return addCustomerJPanel;
    }
}