package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.Banco;
import model.BancoData;

public class MainWindow implements Initializable {
	
	private Banco bcClicked;
	
	private String balanceString;
	
	private double balanceT;
	@FXML
	private TableView<Banco> tableBancoTV;

	@FXML
	private TableColumn<Banco, Double> incomeTC;

	@FXML
	private TableColumn<Banco, Double> expenseTC;

	@FXML
	private TableColumn<Banco, Double> balanceTC;

	@FXML
	private TableColumn<Banco, String> dateTC;

	@FXML
	private TableColumn<Banco, String> descriptionTC;

	@FXML
	private DatePicker initDateDP;

	@FXML
	private DatePicker finalDateDP;
	
	@FXML
    private TextArea incomeTA;

    @FXML
    private TextArea expensesTA;

    @FXML
    private TextArea balanceTA;

	@FXML
	void add(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/IncomeWindow.fxml"));
		loader.setController(new IncomeWindow());

		Parent p = (Parent) loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(p);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
    void delete(ActionEvent event) {
    BancoData.data.remove(bcClicked);
    }

	@FXML
	void exitP(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		incomeTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
		expenseTC.setCellValueFactory(new PropertyValueFactory<>("amountExpense"));
		descriptionTC.setCellValueFactory(new PropertyValueFactory<>("description"));
		dateTC.setCellValueFactory(new PropertyValueFactory<>("dateNow"));
		balanceTC.setCellValueFactory(new PropertyValueFactory<>("balance"));

		tableBancoTV.setItems(BancoData.data);
		tableBancoTV.setOnMouseClicked(event -> {
			bcClicked = tableBancoTV.getSelectionModel().getSelectedItem();
			});
	

	}

	@FXML
	
	  void filterForDate(ActionEvent event) {
	 
	  }
	 
	private String incomeString;
	private double incomeT;
	
	private String expenseString;
	private double expenseT;
    @FXML
    void refreshTotals(ActionEvent event) {
    	
		for (int x = 0; x < BancoData.data.size(); x++) {
			balanceT += BancoData.data.get(x).getBalance();
			incomeT += BancoData.data.get(x).getAmount();
			expenseT += BancoData.data.get(x).getAmountExpense();
		}
		balanceString =  String.valueOf(balanceT);
		balanceTA.appendText(balanceString);
		
		incomeString =  String.valueOf(incomeT);
		incomeTA.appendText(incomeString);
		
		expenseString =  String.valueOf(expenseT);
		expensesTA.appendText(expenseString);
		
    }
}
