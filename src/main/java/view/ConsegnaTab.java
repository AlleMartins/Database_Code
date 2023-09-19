package view;

import db.ConnectionProvider;
import db.tables.ConsegnaClienteTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Consegna_Cliente;
import model.Prodotto_Finito;
import utils.Utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ConsegnaTab extends TabController{
    private ConsegnaClienteTable consegnaTable;
    private ObservableList<Prodotto_Finito> prodottoFinitoList;
    private ObservableList<Consegna_Cliente> consegnaList;

    @FXML
    private TextField idConsegnaProdotto;
    @FXML
    private Button modificaConsegna;
    @FXML
    private Button back;
    @FXML
    private ListView consegnaListView = new ListView<>();
    @FXML
    private TextField idConsegna;
    @FXML
    private DatePicker dataSpedizione;
    @FXML
    private DatePicker dataArrivo;
    @FXML
    private TextField indirizzoSpedizione;
    @FXML
    private TextField cittaSpedizione;
    @FXML
    private TextField capSpedizione;
    @FXML
    private TextField dipendenteResponsabile;
    @FXML
    private TextField peso;
    @FXML
    private TextField documentiAssociati;
    @FXML
    private TextField mezzoTrasporto;
    @FXML
    private TextField idConsegnaCancella;
    @FXML
    private TextField idConsegnaCerca;
    @FXML
    private TextField idConsegnaModifica;
    @FXML
    private TextField idConsegnaNew;
    @FXML
    private TextField indirizzoSpedizioneNew;
    @FXML
    private DatePicker dataSpedizioneNew;
    @FXML
    private DatePicker dataArrivoNew;
    @FXML
    private TextField cittaSpedizioneNew;
    @FXML
    private TextField capSpedizioneNew;
    @FXML
    private TextField dipendenteResponsabileNew;
    @FXML
    private TextField pesoPaccoNew;
    @FXML
    private TextField documentiAssociatiNew;
    @FXML
    private TextField mezzoTrasportoNew;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        consegnaTable = new ConsegnaClienteTable(connectionProvider.getMySQLConnection());
        this.consegnaList = FXCollections.observableArrayList(this.consegnaTable.findAll());
        this.consegnaListView.setItems(this.consegnaList);
    }

    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaConsegna) {
            stage = (Stage) this.modificaConsegna.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaConsegna.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateConsegna(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        consegnaTable = new ConsegnaClienteTable(connectionProvider.getMySQLConnection());

        String id = this.idConsegnaNew.getText();
        String address = this.indirizzoSpedizioneNew.getText();
        String city = this.cittaSpedizioneNew.getText();
        String capString = this.capSpedizioneNew.getText();
        String employee = this.dipendenteResponsabileNew.getText();
        String pesoString = this.pesoPaccoNew.getText();
        String documents = this.documentiAssociatiNew.getText();
        String mezzo = this.mezzoTrasportoNew.getText();

        String idConsegnaControllo = this.idConsegnaModifica.getText();
        if (idConsegnaControllo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare la consegna da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.consegnaTable.findByPrimaryKey(idConsegnaControllo).isPresent()) {
                if (id.isEmpty() || this.dataSpedizioneNew.getValue() == null || this.dataArrivoNew.getValue() == null || address.isEmpty() ||
                city.isEmpty() || capString.isEmpty() || employee.isEmpty() || pesoString.isEmpty() || documents.isEmpty() || mezzo.isEmpty()) {
                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    int cap = Integer.parseInt(capString);
                    int weight = Integer.parseInt(pesoString);
                    Date spedito = Utils.buildDate(this.dataSpedizioneNew.getValue().getDayOfMonth(), this.dataSpedizioneNew.getValue().getMonthValue(), this.dataSpedizioneNew.getValue().getYear()).get();
                    Date arrivo = Utils.buildDate(this.dataArrivoNew.getValue().getDayOfMonth(), this.dataArrivoNew.getValue().getMonthValue(), this.dataArrivoNew.getValue().getYear()).get();
                    Consegna_Cliente consegnaCliente = new Consegna_Cliente(id, spedito, arrivo, address, city, cap, employee, weight, documents, mezzo);
                    this.consegnaTable.update(consegnaCliente);
                    this.consegnaList = FXCollections.observableArrayList(this.consegnaTable.findAll());
                    this.consegnaListView.setItems(this.consegnaList);
                }
            } else {
                showPopUp("La consegna per il cliente che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void saveConsegna(final ActionEvent event) {
        String id = this.idConsegna.getText();
        String address = this.indirizzoSpedizione.getText();
        String city = this.cittaSpedizione.getText();
        String capString = this.capSpedizione.getText();
        String employee = this.dipendenteResponsabile.getText();
        String pesoString = this.peso.getText();
        String documents = this.documentiAssociati.getText();
        String mezzo = this.mezzoTrasporto.getText();

        if (id.isEmpty() || this.dataSpedizione.getValue() == null || this.dataArrivo.getValue() == null || address.isEmpty() ||
                city.isEmpty() || capString.isEmpty() || employee.isEmpty() || pesoString.isEmpty() || documents.isEmpty() || mezzo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.consegnaTable.findByPrimaryKey(id).isPresent()) {
                showPopUp("Id già presente nel DB!!", null, Alert.AlertType.WARNING);
            } else {
                int cap = Integer.parseInt(capString);
                int weight = Integer.parseInt(pesoString);
                Date spedizione = Utils.buildDate(this.dataSpedizione.getValue().getDayOfMonth(), this.dataSpedizione.getValue().getMonthValue(), this.dataSpedizione.getValue().getYear()).get();
                Date arrivo = Utils.buildDate(this.dataArrivo.getValue().getDayOfMonth(), this.dataArrivo.getValue().getMonthValue(), this.dataArrivo.getValue().getYear()).get();
                Consegna_Cliente consegnaCliente = new Consegna_Cliente(id, spedizione, arrivo, address, city, cap, employee, weight, documents, mezzo);
                this.consegnaTable.save(consegnaCliente);
                this.consegnaList = FXCollections.observableArrayList(this.consegnaTable.findAll());
                this.consegnaListView.setItems(this.consegnaList);
            }
        }

    }

    @FXML
    public void seeConsegna(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        consegnaTable = new ConsegnaClienteTable(connectionProvider.getMySQLConnection());
        this.consegnaList = FXCollections.observableArrayList(this.consegnaTable.findAll());
        this.consegnaListView.setItems(this.consegnaList);
    }

    @FXML
    public void searchConsegna(final ActionEvent event) {
        String id = this.idConsegnaCerca.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.consegnaTable.findByPrimaryKey(id).isPresent()) {
                this.consegnaList = FXCollections.observableArrayList(this.consegnaTable.findByPrimaryKey(id).get());
                this.consegnaListView.setItems(this.consegnaList);
            } else {
                showPopUp("La consegna che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void deleteConsegna(final ActionEvent event){
        String id = this.idConsegnaCancella.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.consegnaTable.delete(id);
            this.consegnaList = FXCollections.observableArrayList(consegnaTable.findAll());
            this.consegnaListView.setItems(this.consegnaList);
        }
    }

    @FXML
    public void visualizzaProdotti(final ActionEvent event) {
        String id = this.idConsegnaProdotto.getText();
        if (id.isEmpty()) {
            showPopUp("Inserire campo!!", null, Alert.AlertType.WARNING);
        } else {
            List<Prodotto_Finito> prodotti = this.consegnaTable.prodottiTrasportati(id);
            if (!prodotti.isEmpty()) {
                this.prodottoFinitoList = FXCollections.observableArrayList(prodotti);
                this.consegnaListView.setItems(this.prodottoFinitoList);
            } else {
                showPopUp("La seguente consegna non sta portando nessun Prodotto!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}
