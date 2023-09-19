package view;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import db.ConnectionProvider;
import db.tables.AziendaTable;
import db.tables.FatturaTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Fattura;
import utils.TwoKeys;
import utils.Utils;

public class FatturaTab extends TabController{
    private FatturaTable fatturaTable;
    private AziendaTable aziendaTable;
    private ObservableList<Fattura> fatturaList;

    @FXML
    private ListView<Fattura> fatturaListView = new ListView<>();
    @FXML
    private TextField numeroFattura;
    @FXML
    private DatePicker dataEmissione;
    @FXML
    private TextField totale;
    @FXML
    private TextArea descrizione;
    @FXML
    private TextField metodoPagamento;
    @FXML
    private TextField statoPagamento;
    @FXML
    private DatePicker dataScadenza;
    @FXML
    private TextField indirizzoFatturazione;
    @FXML
    private TextField nomeAzienda;
    @FXML
    private TextField partitaIva;
    @FXML
    private TextField numeroFatturaCancella;
    @FXML
    private TextField numeroFatturaCerca;
    @FXML
    private Button modificaFattura;
    @FXML
    private TextField numeroFatturaModificare;
    @FXML
    private TextField numeroFatturaNew;
    @FXML
    private DatePicker dataEmissioneNew;
    @FXML
    private TextField descrizioneNew;
    @FXML
    private TextField totaleNew;
    @FXML
    private TextField metodoPagamentoNew;
    @FXML
    private TextField statoPagamentoNew;
    @FXML
    private DatePicker dataScadenzaNew;
    @FXML
    private TextField indirizzoFatturazioneNew;
    @FXML
    private TextField nomeAziendaNew;
    @FXML
    private TextField partitaIvaNew;
    @FXML
    private Button back;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        this.fatturaTable = new FatturaTable(connectionProvider.getMySQLConnection());
        this.fatturaList = FXCollections.observableArrayList(fatturaTable.findAll());
        this.fatturaListView.setItems(this.fatturaList);
        this.aziendaTable = new AziendaTable(connectionProvider.getMySQLConnection());
    }

    @FXML
    public void saveFattura(final ActionEvent event) {
        String numeroFatturaString = this.numeroFattura.getText();
        String description = this.descrizione.getText();
        String totaleString = this.totale.getText();
        String paymentMethod = this.metodoPagamento.getText();
        String paymentState = this.statoPagamento.getText();
        String addressFattura = this.indirizzoFatturazione.getText();
        String companyName = this.nomeAzienda.getText();
        String partitaIVA = this.partitaIva.getText();

        if (this.dataEmissione.getValue() == null || this.dataScadenza.getValue() == null || numeroFatturaString.isEmpty() || description.isEmpty() || totaleString.isEmpty() ||
                paymentMethod.isEmpty() || paymentState.isEmpty() || addressFattura.isEmpty() || companyName.isEmpty() || partitaIVA.isEmpty()) {
                showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            numeroFatturaString = numeroFatturaString.trim();
            int number = Integer.parseInt(numeroFatturaString);
            if (this.fatturaTable.findByPrimaryKey(number).isPresent()) {
                showPopUp("La fattura è già salvata!!",null ,Alert.AlertType.WARNING );
            } else {
                TwoKeys<String, String> key = new TwoKeys<>(companyName, partitaIVA);
                if (this.aziendaTable.findByPrimaryKey(key).isPresent()) {
                    totaleString = totaleString.trim();
                    int total = Integer.parseInt(totaleString);
                    Date emissione = Utils.buildDate(this.dataEmissione.getValue().getDayOfMonth(), this.dataEmissione.getValue().getMonthValue(), this.dataEmissione.getValue().getYear()).get();
                    Date scadenza = Utils.buildDate(this.dataScadenza.getValue().getDayOfMonth(), this.dataScadenza.getValue().getMonthValue(), this.dataScadenza.getValue().getYear()).get();
                    Fattura fattura = new Fattura(number, emissione, description, total, paymentMethod, paymentState, scadenza, addressFattura, companyName, partitaIVA);
                    this.fatturaTable.save(fattura);
                    this.fatturaList = FXCollections.observableArrayList(fatturaTable.findAll());
                    this.fatturaListView.setItems(this.fatturaList);
                } else {
                    showPopUp("L'azienda a cui ti riferirsci non è presente nel database, inserici un'azienda valida'!!",null ,Alert.AlertType.WARNING );
                }
            }
        }
    }

    @FXML
    public void deleteFattura(final ActionEvent event) {
        String number = this.numeroFatturaCancella.getText();
        if (number.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            int numfattura = Integer.parseInt(number);
            this.fatturaTable.delete(numfattura);
            this.fatturaList = FXCollections.observableArrayList(fatturaTable.findAll());
            this.fatturaListView.setItems(this.fatturaList);
        }
    }

    @FXML
    public void searchFattura(final ActionEvent event) {
        String numero = this.numeroFatturaCerca.getText();
        if (numero.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            int numFattura = Integer.parseInt(numero);
            if (this.fatturaTable.findByPrimaryKey(numFattura).isPresent()) {
                this.fatturaList = FXCollections.observableArrayList(this.fatturaTable.findByPrimaryKey(numFattura).get());
                this.fatturaListView.setItems(this.fatturaList);
            } else {
                showPopUp("La fattura che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void seeFattura(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        fatturaTable = new FatturaTable(connectionProvider.getMySQLConnection());
        this.fatturaList = FXCollections.observableArrayList(this.fatturaTable.findAll());
        this.fatturaListView.setItems(this.fatturaList);
    }

    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaFattura) {
            stage = (Stage) this.modificaFattura.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaFattura.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateFattura(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        fatturaTable = new FatturaTable(connectionProvider.getMySQLConnection());
        aziendaTable = new AziendaTable(connectionProvider.getMySQLConnection());

        String numeroFatturaString = this.numeroFatturaNew.getText();
        String description = this.descrizioneNew.getText();
        String totaleString = this.totaleNew.getText();
        String paymentMethod = this.metodoPagamentoNew.getText();
        String paymentState = this.statoPagamentoNew.getText();
        String addressFattura = this.indirizzoFatturazioneNew.getText();
        String companyName = this.nomeAziendaNew.getText();
        String partitaIVA = this.partitaIvaNew.getText();

        String numeroFatturaControlloString = this.numeroFatturaModificare.getText();
        if (numeroFatturaControlloString.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare la fattura da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            int numeroFatturaControllo = Integer.parseInt(numeroFatturaControlloString);
            if (this.fatturaTable.findByPrimaryKey(numeroFatturaControllo).isPresent()) {
                if (this.dataEmissioneNew.getValue() == null || this.dataScadenzaNew.getValue() == null || numeroFatturaString.isEmpty() || description.isEmpty() || totaleString.isEmpty() ||
                paymentMethod.isEmpty() || paymentState.isEmpty() || addressFattura.isEmpty() || companyName.isEmpty() || partitaIVA.isEmpty()) {
                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    TwoKeys<String, String> key = new TwoKeys<>(companyName, partitaIVA);
                    if (this.aziendaTable.findByPrimaryKey(key).isPresent()) {
                        int fatturaNumber = Integer.parseInt(numeroFatturaString);
                        int totale = Integer.parseInt(totaleString);
                        Date dataEmissione = Utils.buildDate(this.dataEmissioneNew.getValue().getDayOfMonth(), this.dataEmissioneNew.getValue().getMonthValue(), this.dataEmissioneNew.getValue().getYear()).get();
                        Date dataScadenza = Utils.buildDate(this.dataScadenzaNew.getValue().getDayOfMonth(), this.dataScadenzaNew.getValue().getMonthValue(), this.dataScadenzaNew.getValue().getYear()).get();
                        Fattura fattura = new Fattura(fatturaNumber, dataEmissione, description, totale, paymentMethod, paymentState, dataScadenza, addressFattura, companyName, partitaIVA);
                        this.fatturaTable.update(fattura);
                        this.fatturaList = FXCollections.observableArrayList(this.fatturaTable.findAll());
                        this.fatturaListView.setItems(this.fatturaList);
                    } else {
                        showPopUp("L'azienda a cui ti riferirsci non è presente nel database, inserici un'azienda valida'!!",null ,Alert.AlertType.WARNING );
                    }
                }
            } else {
                showPopUp("La Fattura che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}
