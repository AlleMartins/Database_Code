package view;

import db.ConnectionProvider;
import db.tables.MacchinariTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Macchinari;
import model.Revisione;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MacchinariTab extends TabController{
    private MacchinariTable macchinariTable;
    private ObservableList<Revisione> revisioniList;
    private ObservableList<Macchinari> macchinariList;

    @FXML
    private TextField idMacchinaRevisione;
    @FXML
    private ListView macchinariListView = new ListView<>();
    @FXML
    private TextField idMacchina;
    @FXML
    private TextField nomeMacchina;
    @FXML
    private TextField tipoMacchina;
    @FXML
    private TextField marca;
    @FXML
    private TextArea descrizione;
    @FXML
    private TextField modello;
    @FXML
    private TextField capacitaLavorativa;
    @FXML
    private TextField idMacchinaCancella;
    @FXML
    private TextField idMacchinaCerca;
    @FXML
    private Button modificaMacchinari;
    @FXML
    private TextField idMacchinaModificare;
    @FXML
    private TextField idMacchinaNew;
    @FXML
    private TextField nomeMacchinaNew;
    @FXML
    private TextField tipoMacchinaNew;
    @FXML
    private TextField marcaNew;
    @FXML
    private TextField modelloNew;
    @FXML
    private TextField capacitaLavorativaNew;
    @FXML
    private Button back;
    @FXML
    private TextArea descrizioneNew;


    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        macchinariTable = new MacchinariTable(connectionProvider.getMySQLConnection());
        this.macchinariList = FXCollections.observableArrayList(this.macchinariTable.findAll());
        this.macchinariListView.setItems(this.macchinariList);
    }

    @FXML
    public void saveMacchinario(final ActionEvent event) {
        String id = this.idMacchina.getText();
        String name = this.nomeMacchina.getText();
        String typo = this.tipoMacchina.getText();
        String description = this.descrizione.getText();
        String marcaM = this.marca.getText();
        String model = this.modello.getText();
        String capacitaString = this.capacitaLavorativa.getText();

        if (id.isEmpty() || name.isEmpty() || typo.isEmpty() || description.isEmpty() ||marcaM.isEmpty() ||
        model.isEmpty() || capacitaString.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.macchinariTable.findByPrimaryKey(id).isPresent()) {
                showPopUp("Id già presente nel DB!!", null, Alert.AlertType.WARNING);
            } else {
                int capacity = Integer.parseInt(capacitaString);
                Macchinari macchinario = new Macchinari(id, name, typo, description, marcaM, model, capacity);
                this.macchinariTable.save(macchinario);
                this.macchinariList = FXCollections.observableArrayList(this.macchinariTable.findAll());
                this.macchinariListView.setItems(this.macchinariList);
            }
        }
    }

    @FXML
    public void deleteMacchinario(final ActionEvent event) {
        String id = this.idMacchinaCancella.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.macchinariTable.delete(id);
            this.macchinariList = FXCollections.observableArrayList(this.macchinariTable.findAll());
            this.macchinariListView.setItems(this.macchinariList);
        }
    }

    @FXML
    public void searchMacchinario(final ActionEvent event) {
        String id = this.idMacchinaCerca.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.macchinariTable.findByPrimaryKey(id).isPresent()) {
                this.macchinariList = FXCollections.observableArrayList(this.macchinariTable.findByPrimaryKey(id).get());
                this.macchinariListView.setItems(this.macchinariList);
            } else {
                showPopUp("Il macchinario che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void seeMacchinario(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        macchinariTable = new MacchinariTable(connectionProvider.getMySQLConnection());
        this.macchinariList = FXCollections.observableArrayList(this.macchinariTable.findAll());
        this.macchinariListView.setItems(this.macchinariList);
    }

    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaMacchinari) {
            stage = (Stage) this.modificaMacchinari.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaMacchinari.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateMacchinari(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        macchinariTable = new MacchinariTable(connectionProvider.getMySQLConnection());

        String id = this.idMacchinaNew.getText();
        String name = this.nomeMacchinaNew.getText();
        String typo = this.tipoMacchinaNew.getText();
        String description = this.descrizioneNew.getText();
        String marcaM = this.marcaNew.getText();
        String model = this.modelloNew.getText();
        String capacitaString = this.capacitaLavorativaNew.getText();

        String idControllo = this.idMacchinaModificare.getText();
        if (idControllo.isEmpty() || this.macchinariTable.findByPrimaryKey(idControllo).isEmpty()) {
            showPopUp("Id di controllo assente oppure non presente nel Database!!", null, Alert.AlertType.WARNING);
        } else {
            if (id.isEmpty() || name.isEmpty() || typo.isEmpty() || description.isEmpty() || marcaM.isEmpty() ||
                    model.isEmpty() || capacitaString.isEmpty()) {
                showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
            } else {
                if (this.macchinariTable.findByPrimaryKey(id).isPresent()) {
                    int capacity = Integer.parseInt(capacitaString);
                    Macchinari macchinario = new Macchinari(id, name, typo, description, marcaM, model, capacity);
                    this.macchinariTable.save(macchinario);
                    this.macchinariList = FXCollections.observableArrayList(this.macchinariTable.findAll());
                    this.macchinariListView.setItems(this.macchinariList);
                } else {
                    showPopUp("L'id nuovo deve essere uguale al vecchio!!", null, Alert.AlertType.WARNING);
                }
            }
        }
    }

    @FXML
    public void findRevisioni(final ActionEvent event) {
        String id = this.idMacchinaRevisione.getText();
        if (id.isEmpty()) {
            showPopUp("Inserire campo!!", null, Alert.AlertType.WARNING);
        } else {
            List<Revisione> revisioni = this.macchinariTable.machineRevisione(id);
            if (!revisioni.isEmpty()) {
                this.revisioniList = FXCollections.observableArrayList(revisioni);
                this.macchinariListView.setItems(this.revisioniList);
            } else {
                showPopUp("Il macchinario non ha svolto nessuna revisione!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}
