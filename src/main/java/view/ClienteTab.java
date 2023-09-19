package view;

import db.ConnectionProvider;
import db.tables.ClienteTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Cliente;
import model.Commessa;
import utils.Utils;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ClienteTab extends TabController{
    private ClienteTable clientiTable;
    private ObservableList<Commessa> commessaList;
    private ObservableList<Cliente> clientList;

    @FXML
    private TextField partitaIvaCommessa;
    //private ListView<Cliente> clienteListView;
    @FXML
    private ListView clienteListView;
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private DatePicker dataNascita;
    @FXML
    private TextField citta;
    @FXML
    private TextField indirizzoCivico;
    @FXML
    private TextField cap;
    @FXML
    private TextField provincia;
    @FXML
    private TextField nazione;
    @FXML
    private TextField numeroTelefono;
    @FXML
    private TextField email;
    @FXML
    private TextField professione;
    @FXML
    private TextField partitaIva;
    @FXML
    private TextField partitaIvaCancella;
    @FXML
    private TextField partitaIvaCerca;
    @FXML
    private Button modificaCliente;
    @FXML
    private TextField partitaIvaNew;
    @FXML
    private TextField partitaIvaModificare;
    @FXML
    private TextField nomeNew;
    @FXML
    private TextField cognomeNew;
    @FXML
    private TextField indirizzoCivicoNew;
    @FXML
    private DatePicker dataNascitaNew;
    @FXML
    private TextField cittaNew;
    @FXML
    private TextField provinciaNew;
    @FXML
    private TextField capNew;
    @FXML
    private TextField paeseNew;
    @FXML
    private TextField emailNew;
    @FXML
    private TextField numeroTelefonoNew;
    @FXML
    private TextField professioneNew;
    @FXML
    private Button back;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        clientiTable = new ClienteTable(connectionProvider.getMySQLConnection());
        this.clientList = FXCollections.observableArrayList(this.clientiTable.findAll());
        this.clienteListView.setItems(this.clientList);
    }

    @FXML
    public void saveClient(final ActionEvent event) {
        String name = this.nome.getText();
        String surname = this.cognome.getText();
        String address = this.indirizzoCivico.getText();
        String city = this.citta.getText();
        String province = this.provincia.getText();
        String capString = this.cap.getText();
        String nation = this.nazione.getText();
        String email = this.email.getText();
        String telephoneString = this.numeroTelefono.getText();
        String job = this.professione.getText();
        String partitaIva = this.partitaIva.getText();

        if (name.isEmpty() || surname.isEmpty() || this.dataNascita.getValue() == null || address.isEmpty() || city.isEmpty() || province.isEmpty() ||
            capString.isEmpty() || nation.isEmpty() || email.isEmpty() || telephoneString.isEmpty() || job.isEmpty() || partitaIva.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            int cap = Integer.parseInt(capString);
            int telephone = Integer.parseInt(telephoneString);
            Date bornDate = Utils.buildDate(this.dataNascita.getValue().getDayOfMonth(), this.dataNascita.getValue().getMonthValue(), this.dataNascita.getValue().getYear()).get();
            if (this.clientiTable.findByPrimaryKey(partitaIva).isPresent()) {
                showPopUp("Il cliente è già salvato!!",null ,Alert.AlertType.WARNING );
            } else {
                Cliente cliente = new Cliente(name, surname, bornDate,address,city, province, cap, nation, email, telephone, job, partitaIva);
                this.clientiTable.save(cliente);
                this.clientList = FXCollections.observableArrayList(clientiTable.findAll());
                this.clienteListView.setItems(this.clientList);
            }
        }
    }

    @FXML
    public void deleteClient(final ActionEvent event) {
        String partitaIva = this.partitaIvaCancella.getText();
        if (partitaIva.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.clientiTable.delete(partitaIva);
            this.clientList = FXCollections.observableArrayList(clientiTable.findAll());
            this.clienteListView.setItems(this.clientList);
        }
    }

    @FXML
    public void searchClient(final ActionEvent event) {
        String partitaIva = this.partitaIvaCerca.getText();
        if (partitaIva.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.clientiTable.findByPrimaryKey(partitaIva).isPresent()) {
                this.clientList = FXCollections.observableArrayList(this.clientiTable.findByPrimaryKey(partitaIva).get());
                this.clienteListView.setItems(this.clientList);
            } else {
                showPopUp("Il Cliente che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }
    @FXML
    public void seeClient(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        clientiTable = new ClienteTable(connectionProvider.getMySQLConnection());
        this.clientList = FXCollections.observableArrayList(this.clientiTable.findAll());
        this.clienteListView.setItems(this.clientList);
    }
    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaCliente) {
            stage = (Stage) this.modificaCliente.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaCliente.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateClient(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        clientiTable = new ClienteTable(connectionProvider.getMySQLConnection());

        String name = this.nomeNew.getText();
        String surname = this.cognomeNew.getText();
        String address = this.indirizzoCivicoNew.getText();
        String city = this.cittaNew.getText();
        String province = this.provinciaNew.getText();
        String capString = this.capNew.getText();
        String nation = this.paeseNew.getText();
        String email = this.emailNew.getText();
        String telephoneString = this.numeroTelefonoNew.getText();
        String job = this.professioneNew.getText();
        String partitaIva = this.partitaIvaNew.getText();

        String partitaIvaControllo = this.partitaIvaModificare.getText();
        if (partitaIvaControllo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare il cliente da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.clientiTable.findByPrimaryKey(partitaIvaControllo).isPresent()) {
                if (name.isEmpty() || surname.isEmpty() || this.dataNascitaNew.getValue() == null || address.isEmpty() || city.isEmpty() || province.isEmpty() ||
                        capString.isEmpty() || nation.isEmpty() || email.isEmpty() || telephoneString.isEmpty() || job.isEmpty() || partitaIva.isEmpty()) {
                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    int cap = Integer.parseInt(capString);
                    int telephone = Integer.parseInt(telephoneString);
                    Date bornDate = Utils.buildDate(this.dataNascitaNew.getValue().getDayOfMonth(), this.dataNascitaNew.getValue().getMonthValue(), this.dataNascitaNew.getValue().getYear()).get();
                    Cliente cliente = new Cliente(name, surname, bornDate,address,city, province, cap, nation, email, telephone, job, partitaIva);
                    this.clientiTable.update(cliente);
                    this.clientList = FXCollections.observableArrayList(this.clientiTable.findAll());
                    this.clienteListView.setItems(this.clientList);
                }
            } else {
                showPopUp("Il Cliente che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void visualizzaCommesse(final ActionEvent event) {
        String partita = this.partitaIvaCommessa.getText();
        if (partita.isEmpty()) {
            showPopUp("Inserire campo!!", null, Alert.AlertType.WARNING);
        } else {
            List<Commessa> commesse = this.clientiTable.commesseClients(partita);
            if (!commesse.isEmpty()) {
                this.commessaList = FXCollections.observableArrayList(commesse);
                this.clienteListView.setItems(this.commessaList);
            } else {
                showPopUp("Il cliente non ha commesse nel database!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}