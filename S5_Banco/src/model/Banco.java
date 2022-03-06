package model;

import java.time.LocalDate;

public class Banco {
	double amount;
	String description;
	//Date now;
	double amountExpense;
	double balance;
	LocalDate dateNow;
	

	

	public double getAmount() {
		return amount;
	}




	public void setAmount(double amount) {
		this.amount = amount;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public double getAmountExpense() {
		return amountExpense;
	}




	public void setAmountExpense(double amountExpense) {
		this.amountExpense = amountExpense;
	}




	public double getBalance() {
		return balance;
	}




	public void setBalance(double balance) {
		this.balance = balance;
	}




	public Banco(double amount, String description, double amountExpense,LocalDate dateNow ,double balance) {
		super();
		this.amount = amount;
		this.description = description;
		this.amountExpense = amountExpense;
		this.balance = balance;
		this.dateNow = dateNow;
	}




	public LocalDate getDateNow() {
		return dateNow;
	}




	public void setDateNow(LocalDate dateNow) {
		this.dateNow = dateNow;
	}

	

}
