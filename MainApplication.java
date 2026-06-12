package com.bank;

import java.util.Scanner;

public class MainApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BankService service = new BankService();
		
		while(true) {
			System.out.println("=============== BANK MENU ===============");
			System.out.println("1. OPEN NEW ACCOUNT");
			System.out.println("2. DEPOSIT MONEY");
			System.out.println("3. WITHDRAW MONEY");
			System.out.println("4. CHECK BALANCE");
			System.out.println("5. EXIT APPLICATION");
			System.out.println("ENTER YOUR CHOICE: ");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				System.out.println("=============== Crete New Account ===============");
				
				int randomNumber = (int)(Math.random() * 90000) + 1000;
				String accountNumber = "ACC" + randomNumber;
				
				System.out.println("Enter Account Holder Name: ");
				String accountHolder = sc.nextLine();
				
				System.out.println("Enter Initial Deposit Amount: ");
				double balance = sc.nextDouble();
				
				System.out.println("Enter Your 4-Digit Pin: ");
				int pin = sc.nextInt();
				
				BankAccount account = new BankAccount(accountNumber, accountHolder, balance, pin);
				
				service.crateAccount(account);
				
//				System.out.println("Account Created Successfully!");
				System.out.println("Your Generated Account Number is: " + accountNumber);
				break;
				
			case 2:
				System.out.println("=============== Deposit System ===============");
				System.out.println("Enter Your Account Number: ");
				String depositAcc = sc.next();
				
				System.out.println("Enter Your 4-Digit PIN: ");
				int depositPin = sc.nextInt();
				
				
				System.out.println("Enter Amount to Deposit: ");
				double depositAmount = sc.nextDouble();
				
			
				//System.out.println("Processing your deposit of $" + depositAmount + " for Account: " + depositAcc);
				service.depositMoney(depositAcc, depositPin, depositAmount);
			    break;
			    
			case 3: 
				System.out.println("=============== Withdraw System ===============");
				System.out.println("Enter Your Account Number: ");
				String withdrawAcc = sc.next();
				
				System.out.println("Enter Your 4-Digit PIN: ");
				int withdrawPin = sc.nextInt();
				
	
				System.out.println("Enter Amount to Withdraw: ");
				double withdrawAmount = sc.nextDouble();
				
				//System.out.println("Processing your withdrawal of $" + withdrawAmount + " from Account: " + withdrawAcc);
				service.withdrawMoney(withdrawAcc, withdrawPin, withdrawAmount);
			    break;
			    
			case 4:
				System.out.println("=============== Balance Enquiry ===============");
				System.out.println("Enter Your Account Number: ");
				String balanceAcc = sc.next();
				
				System.out.println("Enter Your 4-Digit PIN: ");
				int balancePin = sc.nextInt();
				
				//System.out.println("Fetching balance for Account: " + balanceAcc);
				service.checkBalance(balanceAcc, balancePin);
			    break;
			case 5:
				System.out.println("=============================================");
				System.out.println("    Thakyou for banking with us. Goodbye!");
				System.out.println("=============================================");
				sc.close();
				System.exit(0);
				break;
			    
			    default: 
			    	System.out.println("Envalid Choice! Please enter a valid choice between 1 to 5.");
				
			}
		}
		

	}

}
