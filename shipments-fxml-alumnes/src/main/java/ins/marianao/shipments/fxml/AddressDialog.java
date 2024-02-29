package ins.marianao.shipments.fxml;

import cat.institutmarianao.shipmentsws.model.Address;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class AddressDialog extends Dialog<Address>{
	
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
		// TODO Auto-generated method stub
		
	}

	private void setResultConverter() {
		// TODO Auto-generated method stub
		
	}

	private void setPropertyBindings() {
		// TODO Auto-generated method stub
		
	}
}
