package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BancoData {
	public static ObservableList<Banco> data = FXCollections.observableArrayList();
	
	public static ObservableList<Banco> getData() {
		return data;
	}

	public static void setData(ObservableList<Banco> data) {
		BancoData.data = data;
	}
}
