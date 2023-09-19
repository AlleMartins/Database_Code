package view;

import db.ConnectionProvider;
import db.tables.AziendaTable;
import db.tables.ClienteTable;
import db.tables.CommessaTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Commessa;
import utils.TwoKeys;
import utils.Utils;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class CommessaTab extends TabController{
    private CommessaTable commessaTable;
    private ClienteTable clienteTable;
    private AziendaTable aziendaTable;
    private ObservableList<Commessa> commessaList;

    @FXML
    private ListView<Commessa> commessaListView = new ListView<>();
    @FXML
    private TextField idCommessa;
    @FXML
    private TextArea descrizione;
    @FXML
    private DatePicker dataInizio;
    @FXML
    private DatePicker dataFine;
    @FXML
    private TextField statoCommessa;
    @FXML
    private TextField documentiAssociati;
    @FXML
    private TextField progetto;
    @FXML
    private TextField nomeAzienda;
    @FXML
    private TextField partitaIva;
    @FXML
    private TextField clientePartitaIva;
    @FXML
    private TextField idCommessaCancella;
    @FXML
    private TextField idCommessaCerca;
    @FXML
    private Button modificaCommessa;
    @FXML
    private TextField idCommessaModificare;
    @FXML
    private TextField idCommessaNew;
    @FXML
    private TextField descrizioneNew;
    @FXML
    private TextField statoCommessaNew;
    @FXML
    private TextField documentiAssociatiNew;
    @FXML
    private TextField progettoNew;
    @FXML
    private TextField nomeAziendaNew;
    @FXML
    private TextField partitaIvaNew;
    @FXML
    private TextField ricPartitaIvaNew;
    @FXML
    private DatePicker dataInizioNew;
    @FXML
    private DatePicker dataFineNew;
    @FXML
    private Button back;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        commessaTable = new CommessaTable(connectionProvider.getMySQLConnection());
        clienteTable = new ClienteTable(connectionProvider.getMySQLConnection());
        aziendaTable = new AziendaTable(connectionProvider.getMySQLConnection());
        this.commessaList = FXCollections.observableArrayList(this.commessaTable.findAll());
        this.commessaListView.setItems(this.commessaList);
    }

    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaCommessa) {
            stage = (Stage) this.modificaCommessa.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaCommessa.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void saveCommessa(final ActionEvent event) {
        String id = this.idCommessa.getText();
        String description = this.descrizione.getText();
        String stateCommessa = this.statoCommessa.getText();
        String documents = this.documentiAssociati.getText();
        String project = this.progetto.getText();
        String companyName = this.nomeAzienda.getText();
        String partitaIVA = this.partitaIva.getText();
        String ricPartitaIva = this.clientePartitaIva.getText();
        if (id.isEmpty() || description.isEmpty() || stateCommessa.isEmpty() || documents.isEmpty() ||
            project.isEmpty() || companyName.isEmpty() || partitaIVA.isEmpty() || ricPartitaIva.isEmpty() || this.dataInizio.getValue() == null || this.dataFine.getValue() == null) {
            showPopUp("Inserisci tutti i campi!!",null ,Alert.AlertType.WARNING );
        } else {
            Date start = Utils.buildDate(this.dataInizio.getValue().getDayOfMonth(), this.dataInizio.getValue().getMonthValue(), this.dataInizio.getValue().getYear()).get();
            Date end = Utils.buildDate(this.dataFine.getValue().getDayOfMonth(), this.dataFine.getValue().getMonthValue(), this.dataFine.getValue().getYear()).get();
            if (this.commessaTable.findByPrimaryKey(id).isPresent()) {
                showPopUp("La commessa è già salvata!!",null ,Alert.AlertType.WARNING );
            } else {
                TwoKeys<String, String> key = new TwoKeys<>(companyName, partitaIVA);
                if (this.aziendaTable.findByPrimaryKey(key).isPresent() && this.clienteTable.findByPrimaryKey(ricPartitaIva).isPresent()) {
                    Commessa commessa = new Commessa(id, description, start, end, stateCommessa, documents, project, companyName, partitaIVA, ricPartitaIva);
                    this.commessaTable.save(commessa);
                    this.commessaList = FXCollections.observableArrayList(commessaTable.findAll());
                    this.commessaListView.setItems(this.commessaList);
                } else {
                    showPopUp("Devi inserire dei valori corretti in nomeAzienda, partitaIVA, e la partita iva del cliente!!",null ,Alert.AlertType.WARNING );
                }
            }
        }
    }

    @FXML
    public void deleteCommessa(final ActionEvent event) {
        String id = this.idCommessaCancella.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.commessaTable.delete(id);
            this.commessaList = FXCollections.observableArrayList(this.commessaTable.findAll());
            this.commessaListView.setItems(this.commessaList);
        }
    }

    @FXML
    public void searchCommessa(final ActionEvent event) {
        String id = this.idCommessaCerca.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.commessaTable.findByPrimaryKey(id).isPresent()) {
                this.commessaList = FXCollections.observableArrayList(this.commessaTable.findByPrimaryKey(id).get());
                this.commessaListView.setItems(this.commessaList);
            } else {
                showPopUp("La commessa che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);

            }
        }
    }

    @FXML
    public void seeCommesse(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        commessaTable = new CommessaTable(connectionProvider.getMySQLConnection());
        this.commessaList = FXCollections.observableArrayList(this.commessaTable.findAll());
        this.commessaListView.setItems(this.commessaList);
    }

    @FXML
    public void updateCommessa(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        commessaTable = new CommessaTable(connectionProvider.getMySQLConnection());
        clienteTable = new ClienteTable(connectionProvider.getMySQLConnection());
        aziendaTable = new AziendaTable(connectionProvider.getMySQLConnection());

        String id = this.idCommessaNew.getText();
        String description = this.descrizioneNew.getText();
        String stateCommessa = this.statoCommessaNew.getText();
        String documents = this.documentiAssociatiNew.getText();
        String project = this.progettoNew.getText();
        String companyName = this.nomeAziendaNew.getText();
        String partitaIVA = this.partitaIvaNew.getText();
        String ricPartitaIva = this.ricPartitaIvaNew.getText();

        String idCommessaControllo = this.idCommessaModificare.getText();
        if (idCommessaControllo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare la commessa da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.commessaTable.findByPrimaryKey(idCommessaControllo).isPresent()) {
                if (id.isEmpty() || description.isEmpty() || stateCommessa.isEmpty() || documents.isEmpty() || project.isEmpty() || companyName.isEmpty() ||
                    partitaIVA.isEmpty() || ricPartitaIva.isEmpty() || this.dataInizioNew.getValue() == null || this.dataFineNew.getValue() == null) {
                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    TwoKeys<String, String> key = new TwoKeys<>(companyName, partitaIVA);
                    if (this.aziendaTable.findByPrimaryKey(key).isPresent() && this.clienteTable.findByPrimaryKey(ricPartitaIva).isPresent()) {
                        Date start = Utils.buildDate(this.dataInizioNew.getValue().getDayOfMonth(), this.dataInizioNew.getValue().getMonthValue(), this.dataInizioNew.getValue().getYear()).get();
                        Date end = Utils.buildDate(this.dataFineNew.getValue().getDayOfMonth(), this.dataFineNew.getValue().getMonthValue(), this.dataFineNew.getValue().getYear()).get();
                        Commessa commessa = new Commessa(id, description, start, end, stateCommessa, documents, project, companyName, partitaIVA, ricPartitaIva);
                        this.commessaTable.save(commessa);
                        this.commessaList = FXCollections.observableArrayList(commessaTable.findAll());
                        this.commessaListView.setItems(this.commessaList);
                    } else {
                        showPopUp("Devi inserire dei valori corretti in nomeAzienda, partitaIVA, e la partita iva del cliente!!",null ,Alert.AlertType.WARNING );
                    }
                }
            } else {
                showPopUp("La commessa che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}
