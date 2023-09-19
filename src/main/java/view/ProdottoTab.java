package view;

import db.ConnectionProvider;
import db.tables.FornitoriAssociatiTable;
import db.tables.ProdottiTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Prodotti;

import java.io.IOException;
import java.util.Objects;

public class ProdottoTab extends TabController{
    private ProdottiTable prodottiTable;
    private FornitoriAssociatiTable fornitoriTable;
    private ObservableList<Prodotti> prodottiList;

    @FXML
    private ListView<Prodotti> prodottiListView;
    @FXML
    private TextField idProdotto;
    @FXML
    private TextField nomeProdotto;
    @FXML
    private TextArea descrizione;
    @FXML
    private TextField categoriaProdotto;
    @FXML
    private TextField prezzo;
    @FXML
    private TextField disponibilita;
    @FXML
    private TextField documentazione;
    @FXML
    private TextField licenzeProdotto;
    @FXML
    private TextField idFornitore;
    @FXML
    private TextField idProdottoCancella;
    @FXML
    private TextField idProdottoCerca;
    @FXML
    private Button modificaProdotto;
    @FXML
    private TextField idProdottoModificare;
    @FXML
    private TextField idProdottoNew;
    @FXML
    private TextField nomeProdottoNew;
    @FXML
    private TextField categoriaProdottoNew;
    @FXML
    private TextField prezzoNew;
    @FXML
    private TextField disponibilitaNew;
    @FXML
    private TextField documentazioneNew;
    @FXML
    private TextField licenzeProdottoNew;
    @FXML
    private TextField idFornitoreNew;
    @FXML
    private Button back;
    @FXML
    private TextArea descrizioneNew;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        prodottiTable = new ProdottiTable(connectionProvider.getMySQLConnection());
        fornitoriTable = new FornitoriAssociatiTable(connectionProvider.getMySQLConnection());
        this.prodottiList = FXCollections.observableArrayList(this.prodottiTable.findAll());
        this.prodottiListView.setItems(this.prodottiList);
    }

    @FXML
    public void saveProdotto(final ActionEvent event) {
        String id = this.idProdotto.getText();
        String name = this.nomeProdotto.getText();
        String description = this.descrizione.getText();
        String category = this.categoriaProdotto.getText();
        String prezzoString = this.prezzo.getText();
        String disponibilitaString = this.disponibilita.getText();
        String documentation = this.documentazione.getText();
        String license = this.licenzeProdotto.getText();
        String fornitureId = this.idFornitore.getText();

        if (id.isEmpty() || name.isEmpty() || description.isEmpty() || category.isEmpty() || prezzoString.isEmpty() ||
        disponibilitaString.isEmpty() || documentation.isEmpty() || license.isEmpty() || fornitureId.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.fornitoriTable.findByPrimaryKey(fornitureId).isPresent()) {
                if (this.prodottiTable.findByPrimaryKey(id).isPresent()) {
                    showPopUp("Il prodotto è già salvato!!",null ,Alert.AlertType.WARNING );
                } else {
                    int price = Integer.parseInt(prezzoString);
                    int disponibility = Integer.parseInt(disponibilitaString);
                    Prodotti prodotto = new Prodotti(id, name, description, category, price, disponibility, documentation, license, fornitureId);
                    this.prodottiTable.save(prodotto);
                    this.prodottiList = FXCollections.observableArrayList(this.prodottiTable.findAll());
                    this.prodottiListView.setItems(this.prodottiList);
                }
            } else {
                showPopUp("L'ID del fornitore deve essere un id valido, presente nella tabella fornitori!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void deleteProdotto(final ActionEvent event) {
        String id = this.idProdottoCancella.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.prodottiTable.delete(id);
            this.prodottiList = FXCollections.observableArrayList(this.prodottiTable.findAll());
            this.prodottiListView.setItems(this.prodottiList);
        }
    }

    @FXML
    public void searchProdotto(final ActionEvent event) {
        String id = this.idProdottoCerca.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.prodottiTable.findByPrimaryKey(id).isPresent()) {
                this.prodottiList = FXCollections.observableArrayList(this.prodottiTable.findByPrimaryKey(id).get());
                this.prodottiListView.setItems(this.prodottiList);
            } else {
                showPopUp("Il prodotto che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void seeProdotto(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        prodottiTable = new ProdottiTable(connectionProvider.getMySQLConnection());
        this.prodottiList = FXCollections.observableArrayList(this.prodottiTable.findAll());
        this.prodottiListView.setItems(this.prodottiList);
    }

    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaProdotto) {
            stage = (Stage) this.modificaProdotto.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaAzienda.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateProdotti(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        prodottiTable = new ProdottiTable(connectionProvider.getMySQLConnection());
        fornitoriTable = new FornitoriAssociatiTable(connectionProvider.getMySQLConnection());

        String id = this.idProdottoNew.getText();
        String name = this.nomeProdottoNew.getText();
        String description = this.descrizioneNew.getText();
        String category = this.categoriaProdottoNew.getText();
        String prezzoString = this.prezzoNew.getText();
        String disponibilitaString = this.disponibilitaNew.getText();
        String documentation = this.documentazioneNew.getText();
        String license = this.licenzeProdottoNew.getText();
        String fornitureId = this.idFornitoreNew.getText();
        String idControllo = this.idProdottoModificare.getText();

        if (idControllo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare l'azienda da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.prodottiTable.findByPrimaryKey(idControllo).isPresent()) {
                if (id.isEmpty() || name.isEmpty() || description.isEmpty() || category.isEmpty() || prezzoString.isEmpty() ||
                        disponibilitaString.isEmpty() || documentation.isEmpty() || license.isEmpty() || fornitureId.isEmpty()) {
                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    if (this.fornitoriTable.findByPrimaryKey(fornitureId).isPresent()) {
                        int price = Integer.parseInt(prezzoString);
                        int disponibility = Integer.parseInt(disponibilitaString);
                        Prodotti prodotto = new Prodotti(id, name, description, category, price, disponibility, documentation, license, fornitureId);
                        this.prodottiTable.save(prodotto);
                        this.prodottiList = FXCollections.observableArrayList(this.prodottiTable.findAll());
                        this.prodottiListView.setItems(this.prodottiList);
                    } else {
                        showPopUp("L'ID del fornitore deve essere un id valido, presente nella tabella fornitori!!", null, Alert.AlertType.WARNING);
                    }
                }
            } else {
                showPopUp("Il prodotto che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}
