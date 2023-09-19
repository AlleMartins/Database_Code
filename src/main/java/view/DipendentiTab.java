package view;

import db.ConnectionProvider;
import db.tables.DipendentiTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Dipendenti;
import utils.Utils;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class DipendentiTab extends TabController{
    private DipendentiTable dipendentiTable;
    private ObservableList<Dipendenti> dipendentiList;

    @FXML
    private ListView<Dipendenti> dipendentiListView = new ListView<>();
    @FXML
    private TextField idDipendente;
    @FXML
    private TextField nome;
    @FXML
    private DatePicker dataNascita;
    @FXML
    private TextField indirizzo;
    @FXML
    private TextField citta;
    @FXML
    private TextField provincia;
    @FXML
    private TextField cap;
    @FXML
    private TextField nazione;
    @FXML
    private TextField numeroTelefono;
    @FXML
    private TextField email;
    @FXML
    private TextField ruolo;
    @FXML
    private TextField dipartimento;
    @FXML
    private DatePicker dataAssunzione;
    @FXML
    private TextField stipendio;
    @FXML
    private TextField certificazioni;
    @FXML
    private TextField idDipendenteCancella;
    @FXML
    private TextField idDipendenteCerca;
    @FXML
    private Button modificaDipendente;
    @FXML
    private TextField idDipendenteModificare;
    @FXML
    private TextField idDipendenteNew;
    @FXML
    private TextField nomeNew;
    @FXML
    private TextField cittaNew;
    @FXML
    private TextField indirizzoNew;
    @FXML
    private TextField provinciaNew;
    @FXML
    private TextField capNew;
    @FXML
    private TextField nazioneNew;
    @FXML
    private TextField numeroTelefonoNew;
    @FXML
    private TextField emailNew;
    @FXML
    private TextField ruoloNew;
    @FXML
    private TextField dipartimentoNew;
    @FXML
    private TextField stipendioNew;
    @FXML
    private TextField certificazioniNew;
    @FXML
    private DatePicker dataNascitaNew;
    @FXML
    private Button back;
    @FXML
    private DatePicker dataAssunzioneNew;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        dipendentiTable = new DipendentiTable(connectionProvider.getMySQLConnection());
        this.dipendentiList = FXCollections.observableArrayList(this.dipendentiTable.findAll());
        this.dipendentiListView.setItems(this.dipendentiList);
    }

    public void saveCliente(final ActionEvent event) {
        String id = this.idDipendente.getText();
        String name = this.nome.getText();
        String address = this.indirizzo.getText();
        String city= this.citta.getText();
        String provincie = this.provincia.getText();
        String capString = this.cap.getText();
        String nation = this.nazione.getText();
        String numeroTelefonoString = this.numeroTelefono.getText();
        String emailClient = this.email.getText();
        String role = this.ruolo.getText();
        String department = this.dipartimento.getText();
        String stipendioString = this.stipendio.getText();
        String certifications = this.certificazioni.getText();

        if (this.dataNascita.getValue() == null || this.dataAssunzione == null || id.isEmpty() || name.isEmpty() || address.isEmpty() || city.isEmpty() ||
                provincie.isEmpty() || capString.isEmpty() || nation.isEmpty() || numeroTelefonoString.isEmpty() || emailClient.isEmpty() || role.isEmpty() || department.isEmpty() ||
                stipendioString.isEmpty() || certifications.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            int cap = Integer.parseInt(capString);
            int telephone = Integer.parseInt(numeroTelefonoString);
            int salary = Integer.parseInt(stipendioString);
            Date bornDate = Utils.buildDate(this.dataNascita.getValue().getDayOfMonth(), this.dataNascita.getValue().getMonthValue(), this.dataNascita.getValue().getYear()).get();
            Date assunzione = Utils.buildDate(this.dataAssunzione.getValue().getDayOfMonth(), this.dataAssunzione.getValue().getMonthValue(), this.dataAssunzione.getValue().getYear()).get();
            if (this.dipendentiTable.findByPrimaryKey(id).isPresent()) {
                showPopUp("Il dipendente è già salvato!!",null ,Alert.AlertType.WARNING );
            } else {
                Dipendenti dipendente = new Dipendenti(id, name, bornDate, address, city, provincie, cap, nation, telephone, emailClient, role, department, assunzione, salary, certifications);
                this.dipendentiTable.save(dipendente);
                this.dipendentiList = FXCollections.observableArrayList(this.dipendentiTable.findAll());
                this.dipendentiListView.setItems(this.dipendentiList);
            }
        }
    }

    public void deleteDipendente(final ActionEvent event) {
        String id = this.idDipendenteCancella.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.dipendentiTable.delete(id);
            this.dipendentiList = FXCollections.observableArrayList(dipendentiTable.findAll());
            this.dipendentiListView.setItems(this.dipendentiList);
        }
    }

    public void searchDipendente(final ActionEvent event) {
        String id = this.idDipendenteCerca.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.dipendentiTable.findByPrimaryKey(id).isPresent()) {
                this.dipendentiList = FXCollections.observableArrayList(this.dipendentiTable.findByPrimaryKey(id).get());
                this.dipendentiListView.setItems(this.dipendentiList);
            } else {
                showPopUp("Il dipendente che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    public void seeDipendenti(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        dipendentiTable = new DipendentiTable(connectionProvider.getMySQLConnection());
        this.dipendentiList = FXCollections.observableArrayList(this.dipendentiTable.findAll());
        this.dipendentiListView.setItems(this.dipendentiList);
    }

    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaDipendente) {
            stage = (Stage) this.modificaDipendente.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaDipendenti.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateDipendente(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        dipendentiTable = new DipendentiTable(connectionProvider.getMySQLConnection());

        String id = this.idDipendenteNew.getText();
        String name = this.nomeNew.getText();
        String address = this.indirizzoNew.getText();
        String city= this.cittaNew.getText();
        String provincie = this.provinciaNew.getText();
        String capString = this.capNew.getText();
        String nation = this.nazioneNew.getText();
        String numeroTelefonoString = this.numeroTelefonoNew.getText();
        String emailEmployee = this.emailNew.getText();
        String role = this.ruoloNew.getText();
        String departments = this.dipartimentoNew.getText();
        String stipendioString = this.stipendioNew.getText();
        String certifications = this.certificazioniNew.getText();

        String idDipendenteControllo = this.idDipendenteModificare.getText();
        if (idDipendenteControllo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare il dipendente da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.dipendentiTable.findByPrimaryKey(idDipendenteControllo).isPresent()) {
                if (this.dataNascitaNew.getValue() == null || this.dataAssunzioneNew == null || id.isEmpty() || name.isEmpty() || address.isEmpty() || city.isEmpty() ||
                provincie.isEmpty() || capString.isEmpty() || nation.isEmpty() || numeroTelefonoString.isEmpty() || emailEmployee.isEmpty() || role.isEmpty() || departments.isEmpty() ||
                stipendioString.isEmpty() || certifications.isEmpty()) {
                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    int cap = Integer.parseInt(capString);
                    int telephone = Integer.parseInt(numeroTelefonoString);
                    int salary = Integer.parseInt(stipendioString);
                    Date bornDate = Utils.buildDate(this.dataNascitaNew.getValue().getDayOfMonth(), this.dataNascitaNew.getValue().getMonthValue(), this.dataNascitaNew.getValue().getYear()).get();
                    Date assunzione = Utils.buildDate(this.dataAssunzioneNew.getValue().getDayOfMonth(), this.dataAssunzioneNew.getValue().getMonthValue(), this.dataAssunzioneNew.getValue().getYear()).get();
                    Dipendenti dipendente = new Dipendenti(id, name, bornDate, address, city, provincie, cap, nation, telephone, emailEmployee, role, departments, assunzione , salary, certifications);
                    this.dipendentiTable.update(dipendente);
                    this.dipendentiList = FXCollections.observableArrayList(this.dipendentiTable.findAll());
                    this.dipendentiListView.setItems(this.dipendentiList);
                }
            } else {
                showPopUp("Il Dipendente che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}
