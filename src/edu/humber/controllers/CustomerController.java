package edu.humber.controllers; /*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 2
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Mon Feb 14 2022
 **************************************************************************************************/

import edu.humber.models.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer Controller
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class CustomerController {
	private static final String PATH_NAME = "src/customers.dat";

	private final List<Customer> customers = new ArrayList<>();
	private DataOutputStream appendDataOutputStream;
	private DataOutputStream replaceDataOutputStream;

//	Load data from the binary file to Customer list
	public CustomerController() {
		try {
			appendDataOutputStream = new DataOutputStream(new FileOutputStream(PATH_NAME, true));
			DataInputStream dataInputStream = new DataInputStream(new FileInputStream(PATH_NAME));
			while (dataInputStream.available() != 0) {
				Customer customer = new Customer(dataInputStream.readInt(), dataInputStream.readUTF(),
						dataInputStream.readUTF(), dataInputStream.readUTF(), dataInputStream.readUTF());
				customers.add(customer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Customer> getCustomers() {
		return customers;
	}

//	Add customer to the list
	public void createCustomer(String name, String phone, String email, String postalCode) {
		Customer customer = new Customer(this.generateID(), name, phone, email, postalCode);
		customers.add(customer);
		commitIntoFile(appendDataOutputStream, customer.getId(), name, phone, email, postalCode);
	}

//	Update customer information to the list
	public void updateCustomer(int id, String name, String phone, String email, String postalCode) {
		try {
			replaceDataOutputStream = new DataOutputStream(new FileOutputStream(PATH_NAME));
			customers.forEach((customer) -> {
				if (customer.getId() == id) {
					customer.setName(name);
					customer.setEmail(email);
					customer.setPhone(phone);
					customer.setPostalCode(postalCode);
				}
				commitIntoFile(replaceDataOutputStream, customer.getId(), customer.getName(), customer.getPhone(), customer.getEmail(),
						customer.getPostalCode());
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

//	Handle ID generation whenever a new customer is created
	private int generateID() {
		if(customers.isEmpty()) {
			return 1000;
		}
		return customers.get(customers.size() - 1).getId() + 1;
	}

//	Append or replace data of the file
	private void commitIntoFile(DataOutputStream dataOutputStream, int id, String name, String phone, String email, String postalCode) {
		try {
			dataOutputStream.writeInt(id);
			dataOutputStream.writeUTF(name);
			dataOutputStream.writeUTF(phone);
			dataOutputStream.writeUTF(email);
			dataOutputStream.writeUTF(postalCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}