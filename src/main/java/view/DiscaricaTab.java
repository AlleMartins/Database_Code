package view;

import db.ConnectionProvider;
import db.tables.DiscaricaTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Discarica;
import utils.Utils;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class DiscaricaTab extends TabController{
    private DiscaricaTable discaricaTable;
    private ObservableList<Discarica> discaricaList;

    @FXML
    private ListView<Discarica> discaricaListView = new ListView<>();
    @FXML
    private TextField idDiscarica;
    @FXML
    private TextField nomeDiscarica;
    @FXML
    private TextField indirizzo;
    @FXML
    private TextField citta;
    @FXML
    private TextField provincia;
    @FXML
    private TextField cap;
    @FXML
    private TextField paese;
    @FXML
    private TextField tipoRifiuti;
    @FXML
    private DatePicker dataApertura;
    @FXML
    private DatePicker dataChiusura;
    @FXML
    private TextField gestore;
    @FXML
    private TextField idDiscaricaCancella;
    @FXML
    private TextField idDiscaricaCerca;
    @FXML
    private Button modificaDiscarica;
    @FXML
    private TextField idDiscaricaModificare;
    @FXML
    private TextField idDiscaricaNew;
    @FXML
    private TextField nomeDiscaricaNew;
    @FXML
    private TextField indirizzoNew;
    @FXML
    private TextField cittaNew;
    @FXML
    private TextField provinciaNew;
    @FXML
    private TextField capNew;
    @FXML
    private TextField paeseNew;
    @FXML
    private TextField tipoRifiutiNew;
    @FXML
    private TextField gestoreNew;
    @FXML
    private DatePicker dataAperturaNew;
    @FXML
    private DatePicker dataChiusuraNew;
    @FXML
    private Button back;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        discaricaTable = new DiscaricaTable(connectionProvider.getMySQLConnection());
        this.discaricaList = FXCollections.observableArrayList(this.discaricaTable.findAll());
        this.discaricaListView.setItems(this.discaricaList);
    }

    @FXML
    public void saveDiscarica(final ActionEvent event) {
        String id = this.idDiscarica.getText();
        String name = this.nomeDiscarica.getText();
        String address = this.indirizzo.getText();
        String city = this.citta.getText();
        String provincie = this.provincia.getText();
        String capString = this.cap.getText();
        String nation = this.paese.getText();
        String typoWaste = this.tipoRifiuti.getText();
        String manager = this.gestore.getText();

        if (this.dataApertura.getValue() == null || this.dataChiusura.getValue() == null || id.isEmpty() || name.isEmpty() || address.isEmpty() || city.isEmpty() ||
                provincie.isEmpty() || capString.isEmpty() || nation.isEmpty() || typoWaste.isEmpty() || manager.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.discaricaTable.findByPrimaryKey(id).isPresent()) {
                showPopUp("Id già presente nel DB!!", null, Alert.AlertType.WARNING);
            } else {
                int cap = Integer.parseInt(capString);
                Date open = Utils.buildDate(this.dataApertura.getValue().getDayOfMonth(), this.dataApertura.getValue().getMonthValue(), this.dataApertura.getValue().getYear()).get();
                Date close = Utils.buildDate(this.dataChiusura.getValue().getDayOfMonth(), this.dataChiusura.getValue().getMonthValue(), this.dataChiusura.getValue().getYear()).get();
                Discarica discarica = new Discarica(id, name, address, city, provincie, cap, nation, typoWaste, open, close, manager);
                this.discaricaTable.save(discarica);
                this.discaricaList = FXCollections.observableArrayList(this.discaricaTable.findAll());
                this.discaricaListView.setItems(this.discaricaList);
            }
        }
    }

    @FXML
    public void deleteDiscarica(final ActionEvent event) {
        String id = this.idDiscaricaCancella.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.discaricaTable.delete(id);
            this.discaricaList = FXCollections.observableArrayList(discaricaTable.findAll());
            this.discaricaListView.setItems(this.discaricaList);
        }
    }

    @FXML
    public void searchDiscarica(final ActionEvent event) {
        String id = this.idDiscaricaCerca.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.discaricaTable.findByPrimaryKey(id).isPresent()) {
                this.discaricaList = FXCollections.observableArrayList(this.discaricaTable.findByPrimaryKey(id).get());
                this.discaricaListView.setItems(this.discaricaList);
            } else {
                showPopUp("La discarica che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void seeDiscarica(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        discaricaTable = new DiscaricaTable(connectionProvider.getMySQLConnection());
        this.discaricaList = FXCollections.observableArrayList(this.discaricaTable.findAll());
        this.discaricaListView.setItems(this.discaricaList);
    }

    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaDiscarica) {
            stage = (Stage) this.modificaDiscarica.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaDiscarica.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateDiscarica(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        discaricaTable = new DiscaricaTable(connectionProvider.getMySQLConnection());

        String id = this.idDiscaricaNew.getText();
        String name = this.nomeDiscaricaNew.getText();
        String address = this.indirizzoNew.getText();
        String city = this.cittaNew.getText();
        String provincie = this.provinciaNew.getText();
        String capString = this.capNew.getText();
        String nation = this.paeseNew.getText();
        String wasteTyoe = this.tipoRifiutiNew.getText();
        String manager = this.gestoreNew.getText();

        String idDiscaricaControllo = this.idDiscaricaModificare.getText();
        if (idDiscaricaControllo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare la discarica da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.discaricaTable.findByPrimaryKey(idDiscaricaControllo).isPresent()) {
                if (this.dataAperturaNew.getValue() == null || this.dataChiusuraNew.getValue() == null || id.isEmpty() || name.isEmpty() || address.isEmpty() || city.isEmpty() ||
                provincie.isEmpty() || capString.isEmpty() || nation.isEmpty() || wasteTyoe.isEmpty() || manager.isEmpty()) {
                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    if (this.discaricaTable.findByPrimaryKey(id).isPresent()) {
                        int cap = Integer.parseInt(capString);
                        Date open = Utils.buildDate(this.dataAperturaNew.getValue().getDayOfMonth(), this.dataAperturaNew.getValue().getMonthValue(), this.dataAperturaNew.getValue().getYear()).get();
                        Date close = Utils.buildDate(this.dataChiusuraNew.getValue().getDayOfMonth(), this.dataChiusuraNew.getValue().getMonthValue(), this.dataChiusuraNew.getValue().getYear()).get();
                        Discarica discarica = new Discarica(id, name, address, city, provincie, cap, nation, wasteTyoe, open, close, manager);
                        this.discaricaTable.update(discarica);
                        this.discaricaList = FXCollections.observableArrayList(this.discaricaTable.findAll());
                        this.discaricaListView.setItems(this.discaricaList);
                    } else {
                        showPopUp("ID deve essere presente nel database!!", null, Alert.AlertType.WARNING);
                    }
                }
            } else {
                showPopUp("La discarica che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}
