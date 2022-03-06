package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
	private TableColumn<Banco, LocalDate> dateTC;

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
	private MenuBar mBar;

	@FXML
	private MenuItem aIncomeMI;

	@FXML
	private MenuItem dIncomeMI;

	
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
	private static ObservableList<Banco> filter = FXCollections.observableArrayList();

	@FXML

	void filterForDate(ActionEvent event) {
		
		LocalDate initDate = initDateDP.getValue();
		LocalDate finalDate = finalDateDP.getValue();

		for (int x = 0; x < BancoData.data.size(); x++) {
			LocalDate dateOne = BancoData.data.get(x).getDateNow();
			if (initDate.isBefore(dateOne) && finalDate.isAfter(dateOne)) {
				filter.add(BancoData.data.get(x));
					
				}
				

			}
			tableBancoTV.setItems(filter);
		}

	

	private String incomeString;
	private double incomeT;

	private String expenseString;
	private double expenseT;

	@FXML
	void refreshTotals(ActionEvent event) {
		double sumaB = 0;
		double sumaI = 0;
		double sumaE = 0;
		for (int x = 0; x < BancoData.data.size(); x++) {
			try {
				balanceT = BancoData.data.get(x).getBalance();
				incomeT = BancoData.data.get(x).getAmount();
				expenseT = BancoData.data.get(x).getAmountExpense();
			} catch (NumberFormatException e) {
				balanceT = 0;
				incomeT = 0;
				expenseT = 0;
			}
			sumaB += balanceT;
			sumaI += incomeT;
			sumaE += expenseT;
		}

		balanceString = String.valueOf(sumaB);
		balanceTA.setText(balanceString);

		incomeString = String.valueOf(sumaI);
		incomeTA.setText(incomeString);

		expenseString = String.valueOf(sumaE);
		expensesTA.setText(expenseString);
		
		if(tableBancoTV.getItems().equals(filter)) {
			
			for (int x = 0; x < filter.size(); x++) {
				try {
					balanceT = filter.get(x).getBalance();
					incomeT = filter.get(x).getAmount();
					expenseT = filter.get(x).getAmountExpense();
				} catch (NumberFormatException e) {
					balanceT = 0;
					incomeT = 0;
					expenseT = 0;
				}
				sumaB += balanceT;
				sumaI += incomeT;
				sumaE += expenseT;
			}
		}

	}

	@FXML
	void noFilter(ActionEvent event) {
		tableBancoTV.setItems(BancoData.data);
	}


}
