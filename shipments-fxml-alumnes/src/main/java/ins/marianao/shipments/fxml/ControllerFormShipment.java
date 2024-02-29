package ins.marianao.shipments.fxml;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.exception.ExceptionUtils;

import cat.institutmarianao.shipmentsws.model.Address;
import cat.institutmarianao.shipmentsws.model.Shipment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;

public class ControllerFormShipment implements Initializable {
	@FXML
	private BorderPane viewProfileForm;

	@FXML
	private Button btnSave;

	@FXML
	private ComboBox<Pair<String, String>> cmbCategory;

	@FXML
	private TextField tfSender;
	@FXML
	private TextField tfRecipient;
	@FXML
	private TextField tfWeight;
	@FXML
	private TextField tfHeight;
	@FXML
	private TextField tfWidth;
	@FXML
	private TextField tfLength;

	@FXML
	private CheckBox cbExpress;
	@FXML
	private CheckBox cbFragile;

	@FXML
	private TextArea taComment;

	/**
	 * Initializes the controller class.
	 */

	@Override
	public void initialize(URL location, ResourceBundle resource) {

//		List<Pair<String, String>> categories = Stream.of(Shipment.Category.values())
//				.map(new Function<Category, Pair<String, String>>() {
//					@Override
//					public Pair<String, String> apply(Category t) {
//						String key = t.name();
//						return new Pair<String, String>(key, resource.getString("text.Shipment." + key));
//					}
//
//				}).collect(Collectors.toList());

		List<Pair<String, String>> categories = Stream.of(Shipment.Category.values())
				.map(category -> new Pair<>(category.name(), resource.getString("text.Category." + category.name())))
				.collect(Collectors.toList());

		// Configurar el ComboBox con las categorías de envíos
		cmbCategory.getItems().addAll(categories);

		setCmFormat(tfHeight);
		setCmFormat(tfLength);
		setCmFormat(tfWidth);

		setGFormat(tfWeight);

		tfSender.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override

			public void handle(MouseEvent event) {

				editAddress();

			}

		});

	}

	public void editAddress() {

		Dialog<Address> dialog = new AddressDialog(new Address());

		Optional<Address> result = dialog.showAndWait();
	}

	@FXML
	public void saveProfileClick(ActionEvent event) {
		try {

			String stringSender = this.tfSender.getText();
			String stringrecipient = this.tfRecipient.getText();

			float floatHeight = (float) this.tfHeight.getTextFormatter().getValue();
			float floatLength = (float) this.tfLength.getTextFormatter().getValue();
			float floatWidth = (float) this.tfWidth.getTextFormatter().getValue();
			float floatWeigth = (float) this.tfWeight.getTextFormatter().getValue();

			Pair<String, String> category = this.cmbCategory.getValue();

			boolean booleanExpress = this.cbExpress.isSelected();
			boolean booleanFragile = this.cbFragile.isSelected();

			String stringComment = this.taComment.getText();

		} catch (Exception e) {
			ControllerMenu.showError(e.getMessage(), e.getMessage(), ExceptionUtils.getStackTrace(e));
		}

	}

//	private void saveUserProfile(User user, boolean insert) throws Exception {
//		final ServiceSaveUser saveUser = new ServiceSaveUser(user, insert);
//
//		saveUser.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//
//			@Override
//			public void handle(WorkerStateEvent t) {
//
//				User user = saveUser.getValue();
//
//				if (user instanceof Courier) {
//
//				} else {
//					Receptionist receptionist = (Receptionist) user;
//					Office office = receptionist.getOffice();
//
//					if (office != null && !cmbOffice.getItems().contains(office)) { // New Office
//						cmbOffice.getItems().add(office);
//						// cmbOffice.setConverter(Formatters.getRoomConverter(cmbOffice.getItems()));
//					}
//				}
//
//				if (insert)
//					ResourceManager.getInstance().getMenuController().usersDirectoryMenuClick(null);
//				else {
//					// Update current user
//					ResourceManager.getInstance().setCurrentUser(user);
//
//					txtPassword.clear();
//					txtConfirm.clear();
//
//					ControllerMenu.showInfo(ResourceManager.getInstance().getText("fxml.text.formUser.update.title"),
//							ResourceManager.getInstance().getText("fxml.text.formUser.update.text"));
//				}
//			}
//		});
//
//		saveUser.setOnFailed(new EventHandler<WorkerStateEvent>() {
//
//			@Override
//			public void handle(WorkerStateEvent t) {
//				Throwable e = t.getSource().getException();
//
//				ControllerMenu.showError(ResourceManager.getInstance().getText("error.formUser.save.web.service"),
//						e.getMessage(), ExceptionUtils.getStackTrace(e));
//			}
//
//		});
//
//		saveUser.start();
//	}

	private void setCmFormat(TextField textField) {

		Pattern pattern = Pattern.compile("\\d*");
		// Operador que filtra la entrada del usuario para que solo permita números
		UnaryOperator<TextFormatter.Change> filter = change -> {
			if (pattern.matcher(change.getControlNewText()).matches()) {
				return change;
			} else {
				return null;
			}
		};

		// Formateador que une "cm" al final del texto
		TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0);
		textFormatter.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !textField.getText().endsWith("cm")) {
				textField.setText(newValue + "cm");
			}
		});

		// Aplicar el filtro y el formateador al TextField
		textField.setTextFormatter(new TextFormatter<>(filter));
		textField.setTextFormatter(textFormatter);
	}

	private void setGFormat(TextField textField) {

		Pattern pattern = Pattern.compile("\\d*");
		// Operador que filtra la entrada del usuario para que solo permita números
		UnaryOperator<TextFormatter.Change> filter = change -> {
			if (pattern.matcher(change.getControlNewText()).matches()) {
				return change;
			} else {
				return null;
			}
		};

		// Formateador que une "cm" al final del texto
		TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0);
		textFormatter.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !textField.getText().endsWith("g")) {
				textField.setText(newValue + "g");
			}
		});

		// Aplicar el filtro y el formateador al TextField
		textField.setTextFormatter(new TextFormatter<>(filter));
		textField.setTextFormatter(textFormatter);
	}
}
