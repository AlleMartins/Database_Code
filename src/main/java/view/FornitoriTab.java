package view;

import db.ConnectionProvider;
import db.tables.FornitoriAssociatiTable;
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
import model.Fornitori_Associati;
import model.Prodotti;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FornitoriTab extends TabController{
    private FornitoriAssociatiTable fornitoriTable;
    private ObservableList<Prodotti> prodottiList;
    private ObservableList<Fornitori_Associati> fornitoriList;

    @FXML
    private TextField idFornitoreProdotti;
    //private ListView<Fornitori_Associati> fornitoriListView;
    @FXML
    private ListView fornitoriListView;
    @FXML
    private TextField idFornitori;
    @FXML
    private TextField nomeFornitori;
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
    private TextField tipoFornitura;
    @FXML
    private TextField numeroTelefono;
    @FXML
    private TextField email;
    @FXML
    private TextField tempoFornitura;
    @FXML
    private TextField idFornitoriCancella;
    @FXML
    private TextField idFornitoriCerca;
    @FXML
    private Button modificaFornitori;
    @FXML
    private TextField idFornitoreModificare;
    @FXML
    private TextField idFornitoreNew;
    @FXML
    private TextField nomeFornitoreNew;
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
    private TextField tipoFornituraNew;
    @FXML
    private TextField numeroTelefonoNew;
    @FXML
    private TextField emailNew;
    @FXML
    private TextField tempoFornituraNew;
    @FXML
    private Button back;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        fornitoriTable = new FornitoriAssociatiTable(connectionProvider.getMySQLConnection());
        this.fornitoriList = FXCollections.observableArrayList(this.fornitoriTable.findAll());
        this.fornitoriListView.setItems(this.fornitoriList);
    }

    @FXML
    public void saveFornitori(final ActionEvent event) {
        String id = this.idFornitori.getText();
        String name = this.nomeFornitori.getText();
        String address = this.indirizzo.getText();
        String city = this.citta.getText();
        String provincie = this.provincia.getText();
        String capString = this.cap.getText();
        String nation = this.nazione.getText();
        String typo = this.tipoFornitura.getText();
        String telephoneString = this.numeroTelefono.getText();
        String email = this.email.getText();
        String timeFornitureString = this.tempoFornitura.getText();

        if (id.isEmpty() || name.isEmpty() || address.isEmpty() || city.isEmpty() || provincie.isEmpty() || capString.isEmpty() ||
        nation.isEmpty() || typo.isEmpty() || telephoneString.isEmpty() || email.isEmpty() || timeFornitureString.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            int cap = Integer.parseInt(capString);
            int telephone = Integer.parseInt(telephoneString);
            int time = Integer.parseInt(timeFornitureString);
            if (this.fornitoriTable.findByPrimaryKey(id).isPresent()) {
                showPopUp("Il fornitore è già salvato!!",null ,Alert.AlertType.WARNING );
            } else {
                Fornitori_Associati fornitori = new Fornitori_Associati(id, name, address, city, provincie, cap, nation, typo, telephone, email, time);
                this.fornitoriTable.save(fornitori);
                this.fornitoriList = FXCollections.observableArrayList(fornitoriTable.findAll());
                this.fornitoriListView.setItems(this.fornitoriList);
            }
        }
    }

    @FXML
    public void deleteFornitori(final ActionEvent event) {
        String id = this.idFornitoriCancella.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.fornitoriTable.delete(id);
            this.fornitoriList = FXCollections.observableArrayList(this.fornitoriTable.findAll());
            this.fornitoriListView.setItems(this.fornitoriList);
        }
    }

    @FXML
    public void searchFornitori(final ActionEvent event) {
        String id = this.idFornitoriCerca.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.fornitoriTable.findByPrimaryKey(id).isPresent()) {
                this.fornitoriList = FXCollections.observableArrayList(this.fornitoriTable.findByPrimaryKey(id).get());
                this.fornitoriListView.setItems(this.fornitoriList);
            } else {
                showPopUp("Il fornitore che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void seeFornitori(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        fornitoriTable = new FornitoriAssociatiTable(connectionProvider.getMySQLConnection());
        this.fornitoriList = FXCollections.observableArrayList(this.fornitoriTable.findAll());
        this.fornitoriListView.setItems(this.fornitoriList);
    }

    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaFornitori) {
            stage = (Stage) this.modificaFornitori.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaFornitori.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateFornitori(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        fornitoriTable = new FornitoriAssociatiTable(connectionProvider.getMySQLConnection());

        String id = this.idFornitoreNew.getText();
        String name = this.nomeFornitoreNew.getText();
        String address = this.indirizzoNew.getText();
        String city = this.cittaNew.getText();
        String provincie = this.provinciaNew.getText();
        String capString = this.capNew.getText();
        String nation = this.paeseNew.getText();
        String typo = this.tipoFornituraNew.getText();
        String telephoneString = this.numeroTelefonoNew.getText();
        String email = this.emailNew.getText();
        String timeFornitureString = this.tempoFornituraNew.getText();

        String idControllo = this.idFornitoreModificare.getText();
        if (idControllo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare il fornitore da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.fornitoriTable.findByPrimaryKey(idControllo).isPresent()) {
                if (id.isEmpty() || name.isEmpty() || address.isEmpty() || city.isEmpty() || provincie.isEmpty() || capString.isEmpty() ||
                        nation.isEmpty() || typo.isEmpty() || telephoneString.isEmpty() || email.isEmpty() || timeFornitureString.isEmpty()) {
                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    if (this.fornitoriTable.findByPrimaryKey(id).isPresent()) {
                        int cap = Integer.parseInt(capString);
                        int telephone = Integer.parseInt(telephoneString);
                        int time = Integer.parseInt(timeFornitureString);
                        Fornitori_Associati fornitori = new Fornitori_Associati(id, name, address, city, provincie, cap, nation, typo, telephone, email, time);
                        this.fornitoriTable.update(fornitori);
                        this.fornitoriList = FXCollections.observableArrayList(fornitoriTable.findAll());
                        this.fornitoriListView.setItems(this.fornitoriList);
                    } else {
                        showPopUp("Id deve essere presente nel database!!", null, Alert.AlertType.WARNING);
                    }
                }
            } else {
                showPopUp("Il fornitore che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    public void prodottiPosseduti(final ActionEvent event) {
        String id = this.idFornitoreProdotti.getText();
        if (id.isEmpty()) {
            showPopUp("Inserire campo!!", null, Alert.AlertType.WARNING);
        } else {
            List<Prodotti> products = this.fornitoriTable.fornitureProducts(id);
            if (!products.isEmpty()) {
                this.prodottiList = FXCollections.observableArrayList(products);
                this.fornitoriListView.setItems(this.prodottiList);
            } else {
                showPopUp("Il fornitore che cerchi non possiede prodotti nel database!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}
