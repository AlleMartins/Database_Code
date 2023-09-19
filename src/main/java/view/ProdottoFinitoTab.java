package view;

import db.ConnectionProvider;
import db.tables.*;
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
import model.Prodotto_Finito;

import java.io.IOException;
import java.util.Objects;

public class ProdottoFinitoTab extends TabController{
    private ProdottoFinitoTable prodottoFinitoTable;
    private ProcessoProduttivoTable processoProduttivoTable;
    private ConsegnaClienteTable consegnaClienteTable;
    private ObservableList<Prodotto_Finito> prodottoFinitoList;

    @FXML
    private ListView<Prodotto_Finito> prodfinitiListView;
    @FXML
    private TextField idProdotto;
    @FXML
    private TextField quantitaDisp;
    @FXML
    private TextField descrizione;
    @FXML
    private TextField documentazione;
    @FXML
    private TextField idProcesso;
    @FXML
    private TextField idConsegna;
    @FXML
    private TextField idProdottoCancella;
    @FXML
    private TextField idProdottoCerca;
    @FXML
    private Button modificaProdottoFinito;
    @FXML
    private TextField idProdottoModificare;
    @FXML
    private TextField idProdottoNew;
    @FXML
    private TextField quantitaDisponibileNew;
    @FXML
    private TextField descrizioneNew;
    @FXML
    private TextField documentazioneNew;
    @FXML
    private TextField idProcessoNew;
    @FXML
    private TextField idConsegnaNew;
    @FXML
    private Button back;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        prodottoFinitoTable = new ProdottoFinitoTable(connectionProvider.getMySQLConnection());
        consegnaClienteTable = new ConsegnaClienteTable(connectionProvider.getMySQLConnection());
        processoProduttivoTable = new ProcessoProduttivoTable(connectionProvider.getMySQLConnection());
        this.prodottoFinitoList = FXCollections.observableArrayList(this.prodottoFinitoTable.findAll());
        this.prodfinitiListView.setItems(this.prodottoFinitoList);
    }

    @FXML
    public void saveProdottoFinito(final ActionEvent event) {
        String id = this.idProdotto.getText();
        String quantitaDispString = this.quantitaDisp.getText();
        String description = this.descrizione.getText();
        String documentation = this.documentazione.getText();
        String processId = this.idProcesso.getText();
        String deliveryId = this.idConsegna.getText();

        if (id.isEmpty() || quantitaDispString.isEmpty() || description.isEmpty()
        || documentation.isEmpty() || processId.isEmpty() || deliveryId.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            int quantity = Integer.parseInt(quantitaDispString);
            if (this.processoProduttivoTable.findByPrimaryKey(processId).isPresent()) {
                if (this.consegnaClienteTable.findByPrimaryKey(deliveryId).isPresent()) {
                    if (this.prodottoFinitoTable.findByPrimaryKey(id).isPresent()) {
                        showPopUp("Prodotto con questo id già presente nel database!!", null, Alert.AlertType.WARNING);
                    } else {
                        Prodotto_Finito prodFin = new Prodotto_Finito(id, quantity, description, documentation, processId, deliveryId);
                        this.prodottoFinitoTable.save(prodFin);
                        this.prodottoFinitoList = FXCollections.observableArrayList(this.prodottoFinitoTable.findAll());
                        this.prodfinitiListView.setItems(this.prodottoFinitoList);
                    }
                } else {
                    showPopUp("ID consegna errata!!", null, Alert.AlertType.WARNING);
                }
            } else {
                showPopUp("ID per processo errato!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void deleteProdottoFinito(final ActionEvent event) {
        String id = this.idProdottoCancella.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.prodottoFinitoTable.delete(id);
            this.prodottoFinitoList = FXCollections.observableArrayList(this.prodottoFinitoTable.findAll());
            this.prodfinitiListView.setItems(this.prodottoFinitoList);
        }
    }

    @FXML
    public void searchProdottoFinito(final ActionEvent event) {
        String id = this.idProdottoCerca.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.prodottoFinitoTable.findByPrimaryKey(id).isPresent()) {
                this.prodottoFinitoList = FXCollections.observableArrayList(this.prodottoFinitoTable.findByPrimaryKey(id).get());
                this.prodfinitiListView.setItems(this.prodottoFinitoList);
            } else {
                showPopUp("L'azienda che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void seeProdottoFinito(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        prodottoFinitoTable = new ProdottoFinitoTable(connectionProvider.getMySQLConnection());
        this.prodottoFinitoList = FXCollections.observableArrayList(this.prodottoFinitoTable.findAll());
        this.prodfinitiListView.setItems(this.prodottoFinitoList);
    }

    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaProdottoFinito) {
            stage = (Stage) this.modificaProdottoFinito.getScene().getWindow();
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
    public void updateProdottoFinito(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        prodottoFinitoTable = new ProdottoFinitoTable(connectionProvider.getMySQLConnection());
        consegnaClienteTable = new ConsegnaClienteTable(connectionProvider.getMySQLConnection());
        processoProduttivoTable = new ProcessoProduttivoTable(connectionProvider.getMySQLConnection());

        String id = this.idProdottoNew.getText();
        String quantitaDispString = this.quantitaDisponibileNew.getText();
        String description = this.descrizioneNew.getText();
        String documentation = this.documentazioneNew.getText();
        String processId = this.idProcessoNew.getText();
        String deliveryId = this.idConsegnaNew.getText();
        String idControllo = this.idProdottoModificare.getText();

        if (idControllo.isEmpty()) {
            showPopUp("Inserirsci tutti i campi per selezionare il prod finito da modificare!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.prodottoFinitoTable.findByPrimaryKey(idControllo).isPresent()) {
                if (id.isEmpty() || quantitaDispString.isEmpty() || description.isEmpty()
                        || documentation.isEmpty() || processId.isEmpty() || deliveryId.isEmpty()) {
                    showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
                } else {
                    int quantity = Integer.parseInt(quantitaDispString);
                    if (this.processoProduttivoTable.findByPrimaryKey(processId).isPresent()) {
                        if (this.consegnaClienteTable.findByPrimaryKey(deliveryId).isPresent()) {
                            Prodotto_Finito prodFin = new Prodotto_Finito(id, quantity, description, documentation, processId, deliveryId);
                            this.prodottoFinitoTable.save(prodFin);
                            this.prodottoFinitoList = FXCollections.observableArrayList(this.prodottoFinitoTable.findAll());
                            this.prodfinitiListView.setItems(this.prodottoFinitoList);
                        } else {
                            showPopUp("ID consegna errata!!", null, Alert.AlertType.WARNING);
                        }
                    } else {
                        showPopUp("ID per processo errato!!", null, Alert.AlertType.WARNING);
                    }
                }
            } else {
                showPopUp("Il prod finito che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }
}
