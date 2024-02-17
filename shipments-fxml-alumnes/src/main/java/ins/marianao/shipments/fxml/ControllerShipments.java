package ins.marianao.shipments.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.lang3.exception.ExceptionUtils;

import cat.institutmarianao.shipmentsws.model.Shipment;
import ins.marianao.shipments.fxml.manager.ResourceManager;
import ins.marianao.shipments.fxml.services.ServiceQueryShipments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ControllerShipments extends AbstractControllerPDF {

	@FXML
	private BorderPane viewUsersDirectory;
	@FXML
	private ComboBox<String> cmbCategory;
	@FXML
	private ComboBox<String> cmbStatus;

	@FXML
	private ComboBox<String> cmbUserCourier;
	@FXML
	private ComboBox<String> cmbUserReceived;
	@FXML
	private DatePicker dpFrom;
	@FXML
	private DatePicker dpTo;
	@FXML
	private TableView<Shipment> shipmentsTable;
	@FXML
	private TableColumn<Shipment, Number> colIndex;
	@FXML
	private TableColumn<Shipment, String> colStatus;
	@FXML
	private TableColumn<Shipment, String> colCategory;
	@FXML
	private TableColumn<Shipment, String> colReceivedBy;
	@FXML
	private TableColumn<Shipment, Integer> colCourierAssigned;
	@FXML
	private TableColumn<Shipment, String> colDate;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		reloadShipments();
	};

	private void reloadShipments() {

		this.shipmentsTable.setEditable(false);
		final ServiceQueryShipments queryShipments = new ServiceQueryShipments(null, null, null, null, null, null);

		queryShipments.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent t) {
				shipmentsTable.setEditable(true);

				shipmentsTable.getItems().clear();

				ObservableList<Shipment> shipments = FXCollections.observableArrayList(queryShipments.getValue());

				shipmentsTable.setItems(shipments);
			}
		});

		queryShipments.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				shipmentsTable.setEditable(true);

				Throwable e = t.getSource().getException();

				ControllerMenu.showError(ResourceManager.getInstance().getText("error.viewUsers.web.service"),
						e.getMessage(), ExceptionUtils.getStackTrace(e));
			}

		});

		queryShipments.start();
	}

	@FXML
	private void generarPDFClick() {
		// Método que maneja el evento de clic en el botón para generar un PDF
	}

	@Override
	protected String htmlContentToPDF() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String documentTitle() {
		return ResourceManager.getInstance().getText("fxml.text.viewShipments.report.title");
	}

	@Override
	protected String documentFileName() {
		return ResourceManager.FILE_REPORT_SHIPMENTS;
	}
}