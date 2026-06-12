package com.bank;

import java.sql.*;
import java.util.*;

public class BankService {

	String url = "jdbc:mysql://localhost:3306/bank";
	String userName = "root";
	
	// Replace with your local MySQL password before running
	String password = "YOUR_DATABASE_PASSWORD";

	public void crateAccount(BankAccount account) {
		try {
			Connection con = DriverManager.getConnection(url, userName, password);
			PreparedStatement pst = con.prepareStatement(
					"insert into bank_account(account_number, account_holder, balance, pin) values(?, ?, ?, ?)");

			pst.setString(1, account.getAccountNumber());
			pst.setString(2, account.getAccountHolder());
			pst.setDouble(3, account.getBalance());
			pst.setInt(4, account.getPin());

			int rowsAffectected = pst.executeUpdate();

			if (rowsAffectected > 0) {
				System.out.println("Account Created Successfully!");
			} else {
				System.out.println("Failed to create account");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void depositMoney(String accNum, int pin, double amount) {
		String checkQuery = "select balance from bank_account where account_number = ? and pin = ?";
		String updateQuery = "update bank_account set balance = ? where account_number = ?";

		try {
			Connection con = DriverManager.getConnection(url, userName, password);
			PreparedStatement checkPst = con.prepareStatement(checkQuery);

			checkPst.setString(1, accNum);
			checkPst.setInt(2, pin);

			ResultSet rs = checkPst.executeQuery();

			if (rs.next()) {
				double currentBalance = rs.getDouble("balance");
				double newBalance = currentBalance + amount;

				PreparedStatement updatePst = con.prepareStatement(updateQuery);
				updatePst.setDouble(1, newBalance);
				updatePst.setString(2, accNum);

				int rowsAffected = updatePst.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Deposit Successful!");
					System.out.println("Now current balance in " + accNum + " is: $" + newBalance);
				} else {
					System.out.println("Transaction failed. Please try again.");
				}
				updatePst.close();
			} else {
				System.out.println("Transaction failed. Please try again.");
			}

			checkPst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void withdrawMoney(String accNum, int pin, double amount) {
		String checkQuery = "select balance from bank_account where account_number = ? and pin = ?";
		String updateQuery = "update bank_account set balance = ? where account_number = ?";

		try {
			Connection con = DriverManager.getConnection(url, userName, password);
			PreparedStatement checkPst = con.prepareStatement(checkQuery);

			checkPst.setString(1, accNum);
			checkPst.setInt(2, pin);

			ResultSet rs = checkPst.executeQuery();

			if (rs.next()) {
				double currentBalance = rs.getDouble("balance");

				if (currentBalance >= amount) {
					double newBalance = currentBalance - amount;

					PreparedStatement updatePst = con.prepareStatement(updateQuery);
					updatePst.setDouble(1, newBalance);
					updatePst.setString(2, accNum);

					int rowsAffected = updatePst.executeUpdate();

					if (rowsAffected > 0) {
						System.out.println("Withdrawal Successful!");
						System.out.println("Please collect your cash: $ " + amount);
						System.out.println("Remaining Balance: $" + newBalance);
					}
					updatePst.close();
				} else {
					System.out.println(
							"[Transaction Failed] Insufficient Balance! Your current balance is: $" + currentBalance);
				}
			} else {
				System.out.println("Invalid Account Number and PIN!");
			}
			checkPst.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Something went wrong during withdrawal.");
		}
	}
	
	public void checkBalance(String accNum, int pin) {
		try {
			Connection con = DriverManager.getConnection(url,userName, password);
			PreparedStatement pst = con.prepareStatement("select account_holder, balance from bank_account where account_number = ? and pin = ?");
			
			pst.setString(1, accNum);
			pst.setInt(2, pin);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("account_holder");
				double currentBalance = rs.getInt("balance");
				System.out.println("========== Balance Enquiry ==========");
				System.out.println("Account Number: " + accNum);
				System.out.println("Account Holder: " + name);
				System.out.println("Available Balance: $" + currentBalance);
			} else {
				System.out.println("Invalid Account Number or PIN!");
			}
			pst.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Something went wrong while fetching balance.");
		}
	}
}
