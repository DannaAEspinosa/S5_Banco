package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Banco;
import model.BancoData;

public class IncomeWindow implements Initializable {
	@FXML
	private TextField amountTF;

	@FXML
	private TextField descriptionTF;

	@FXML
	private DatePicker dateNowDP;

	@FXML
	private Button addBTTN;

	@FXML
	private TextField amountExpenseTF;

	@FXML
	void addNewIncome(ActionEvent event) {

		double amount = Double.parseDouble(amountTF.getText());
		String desc = descriptionTF.getText();
		double amountExpense = Double.parseDouble(amountExpenseTF.getText());
		double resBalance = balanceOperation(amount, amountExpense);
		
		LocalDate dateNow = dateNowDP.getValue();
		

		Banco bc = new Banco(amount, desc, amountExpense, dateNow,resBalance);
		BancoData.data.add(bc);

		Stage stage = (Stage) amountTF.getScene().getWindow();
		stage.close();

	}

	public double balanceOperation(double ingreso, double gasto) {
		double balance = (ingreso - gasto);
		return balance;

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
