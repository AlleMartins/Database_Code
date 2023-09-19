package view;

import db.ConnectionProvider;
import db.tables.AziendaTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Azienda;
import utils.TwoKeys;

import java.io.IOException;
import java.util.Objects;

public class AziendaTab extends TabController{

    private AziendaTable aziendaTable;
    private ObservableList<Azienda> companyList;

    @FXML
    private ListView<Azienda> aziendaListView = new ListView<>();
    @FXML
    private TextField nomeAzienda;
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
    private TextField settoreLavorativo;
    @FXML
    private TextField numeroTelefono;
    @FXML
    private TextField email;
    @FXML
    private TextField partitaIva;
    @FXML
    private TextField contoAziendale;
    @FXML
    private TextField nomeAziendaCancella;
    @FXML
    private TextField partitaIvaCancella;
    @FXML
    private TextField nomeAziendaCerca;
    @FXML
    private TextField partitaIvaCerca;
    @FXML
    private TextField nomeAziendaModificare;
    @FXML
    private TextField nomeAziendaNew;
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
    private TextField settoreLavorativoNew;
    @FXML
    private TextField emailNew;
    @FXML
    private TextField partitaIvaNew;
    @FXML
    private TextField contoAziendaleNew;
    @FXML
    private TextField partitaIvaModificare;
    @FXML
    private TextField numeroTelefonoNew;
    @FXML
    private Button modificaAzienda;
    @FXML
    private Button back;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        aziendaTable = new AziendaTable(connectionProvider.getMySQLConnection());
        this.companyList = FXCollections.observableArrayList(this.aziendaTable.findAll());
        this.aziendaListView.setItems(this.companyList);
    }

    @FXML
    public void saveCompany(final ActionEvent actionEvent) {
        String name = this.nomeAzienda.getText();
        String address = this.indirizzo.getText();
        String city = this.citta.getText();
        String province = this.provincia.getText();
        String capString = this.cap.getText();
        String nation = this.paese.getText();
        String jobSector = this.settoreLavorativo.getText();
        String telephoneString = this.numeroTelefono.getText();
        String email = this.email.getText();
        String account = this.contoAziendale.getText();
        String partitaIva = this.partitaIva.getText();

        if (name.isEmpty() || account.isEmpty() || address.isEmpty() || city.isEmpty() || province.isEmpty() || capString.isEmpty() ||
            nation.isEmpty() || jobSector.isEmpty() || telephoneString.isEmpty() || email.isEmpty() || partitaIva.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            int cap = Integer.parseInt(capString);
            int telephone = Integer.parseInt(telephoneString);
            TwoKeys<String, String> key = new TwoKeys<>(name, partitaIva);
            if (this.aziendaTable.findByPrimaryKey(key).isPresent()) {
                showPopUp("L'azienda è già salvata!!",null ,Alert.AlertType.WARNING );
            } else {
                Azienda newCompany = new Azienda(name, address, city, province, cap, nation, jobSector, telephone, email, partitaIva, account);
                this.aziendaTable.save(newCompany);
                this.companyList = FXCollections.observableArrayList(aziendaTable.findAll());
                this.aziendaListView.setItems(this.companyList);
            }
        }
    }

    @FXML
    public void deleteCompany(final ActionEvent actionEvent) {
        String name = this.nomeAziendaCancella.getText();
        String partitaIva = this.partitaIvaCancella.getText();
        if (name.isEmpty() || partitaIva.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            TwoKeys<String, String> key = new TwoKeys<>(name, partitaIva);
            this.aziendaTable.delete(key);
            this.companyList = FXCollections.observableArrayList(this.aziendaTable.findAll());
            this.aziendaListView.setItems(this.companyList);
        }
    }

    @FXML
    public void searchCompany(final ActionEvent actionEvent) {
        String name = this.nomeAziendaCerca.getText();
        String partitaIva = this.partitaIvaCerca.getText();
        if (name.isEmpty() || partitaIva.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            TwoKeys<String, String> key = new TwoKeys<>(name, partitaIva);
            if (this.aziendaTable.findByPrimaryKey(key).isPresent()) {
                this.companyList = FXCollections.observableArrayList(this.aziendaTable.findByPrimaryKey(key).get());
                this.aziendaListView.setItems(this.companyList);
            } else {
                showPopUp("L'azienda che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void seeCompanys(final ActionEvent actionEvent) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        aziendaTable = new AziendaTable(connectionProvider.getMySQLConnection());
        this.companyList = FXCollections.observableArrayList(this.aziendaTable.findAll());
        this.aziendaListView.setItems(this.companyList);
    }

    @FXML
    public void updateCompany(final ActionEvent actionEvent) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        aziendaTable = new AziendaTable(connectionProvider.getMySQLConnection());

        String name = this.nomeAziendaNew.getText();
        String address = this.indirizzoNew.getText();
        String city = this.cittaNew.getText();
        String province = this.provinciaNew.getText();
        String capString = this.capNew.getText();
        String nation = this.paeseNew.getText();
        String jobSector = this.settoreLavorativoNew.getText();
        String telephoneString = this.numeroTelefonoNew.getText();
        String email = this.emailNew.getText();
        String account = this.contoAziendaleNew.getText();
        String partitaIva = this.partitaIvaNew.getText();

        String partitaIvaControllo = this.partitaIvaModificare.getText();
        String nomeAziendaControllo = this.nomeAziendaModificare.getText();

        if (partitaIvaControllo.isEmpty() || nomeAziendaControllo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare l'azienda da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            TwoKeys<String, String> key = new TwoKeys<>(nomeAziendaControllo, partitaIvaControllo);
            if (this.aziendaTable.findByPrimaryKey(key).isPresent()) {
                if (name.isEmpty() || account.isEmpty() || address.isEmpty() || city.isEmpty() || province.isEmpty() || capString.isEmpty() ||
                        nation.isEmpty() || jobSector.isEmpty() || telephoneString.isEmpty() || email.isEmpty() || partitaIva.isEmpty()) {

                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    int cap = Integer.parseInt(capString);
                    int telephone = Integer.parseInt(telephoneString);
                    Azienda newCompany = new Azienda(name, address, city, province, cap, nation, jobSector, telephone, email, partitaIva, account);
                    this.aziendaTable.update(newCompany);
                    this.companyList = FXCollections.observableArrayList(aziendaTable.findAll());
                    this.aziendaListView.setItems(this.companyList);
                }
            } else {
                showPopUp("L'azienda che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaAzienda) {
            stage = (Stage) this.modificaAzienda.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaAzienda.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
