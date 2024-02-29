package ins.marianao.shipments.fxml;

import cat.institutmarianao.shipmentsws.model.Address;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AddressDialog extends Dialog<Address> {

	private Address address;

	private TextField name;

	private TextField street;

	private TextField number;

	private TextField floor;

	private TextField door;

	private TextField city;

	private TextField province;

	private TextField postalCode;

	private TextField country;

	public AddressDialog(Address address) {
		super();
		this.setTitle("Inserta la teva adreça");
		this.address = address;
		buildUI();
		setPropertyBindings();
		setResultConverter();
	}

	private void buildUI() {

		Pane pane = createGridPane();
		getDialogPane().setContent(pane);

	}

	private void setResultConverter() {
		// TODO Auto-generated method stub

	}

	private void setPropertyBindings() {
		// TODO Auto-generated method stub

	}

	private Pane createGridPane() {
		VBox content = new VBox(10);

		GridPane grid = new GridPane();

		grid.setVgap(10);

		grid.setHgap(10);

		// Labels

		Label nameLabel = new Label("Name:");

		Label streetLabel = new Label("Street:");

		Label numberLabel = new Label("Number:");

		Label floorLabel = new Label("Floor:");

		Label doorLabel = new Label("Door:");

		Label cityLabel = new Label("City:");

		Label provinceLabel = new Label("Province:");

		Label postalCodeLabel = new Label("Postal Code:");

		Label countryLabel = new Label("Country:");

		// Add nodes to the grid

		grid.add(nameLabel, 0, 0);

		grid.add(name, 1, 0);

		GridPane.setHgrow(this.name, Priority.ALWAYS);

		grid.add(streetLabel, 0, 1);

		grid.add(street, 1, 1);

		GridPane.setHgrow(this.street, Priority.ALWAYS);

		grid.add(numberLabel, 0, 2);

		grid.add(number, 1, 2);

		GridPane.setHgrow(this.number, Priority.ALWAYS);

		grid.add(floorLabel, 0, 3);

		grid.add(floor, 1, 3);

		GridPane.setHgrow(this.floor, Priority.ALWAYS);

		grid.add(doorLabel, 0, 4);

		grid.add(door, 1, 4);

		GridPane.setHgrow(this.door, Priority.ALWAYS);

		grid.add(cityLabel, 0, 5);

		grid.add(city, 1, 5);

		GridPane.setHgrow(this.city, Priority.ALWAYS);

		grid.add(provinceLabel, 0, 6);

		grid.add(province, 1, 6);

		GridPane.setHgrow(this.province, Priority.ALWAYS);

		grid.add(postalCodeLabel, 0, 7);

		grid.add(postalCode, 1, 7);

		GridPane.setHgrow(this.postalCode, Priority.ALWAYS);

		grid.add(countryLabel, 0, 8);

		grid.add(country, 1, 8);

		GridPane.setHgrow(this.country, Priority.ALWAYS);

		content.getChildren().add(grid);

		return content;

	}
}
