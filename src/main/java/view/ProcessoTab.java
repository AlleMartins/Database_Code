package view;

import db.ConnectionProvider;
import db.tables.AziendaTable;
import db.tables.ProcessoProduttivoTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Processo_Produttivo;
import utils.TwoKeys;
import utils.Utils;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class ProcessoTab extends TabController{
    private ProcessoProduttivoTable processoTable;
    private AziendaTable aziendaTable;
    private ObservableList<Processo_Produttivo> processoList;

    @FXML
    private ListView<Processo_Produttivo> proceProdListView;
    @FXML
    private TextField idProcesso;
    @FXML
    private TextField nomeProcesso;
    @FXML
    private DatePicker dataInizio;
    @FXML
    private DatePicker dataFine;
    @FXML
    private TextField nomeAzienda;
    @FXML
    private TextField partitaIva;
    @FXML
    private TextField idProcessoCancella;
    @FXML
    private TextField idProcessoCerca;
    @FXML
    private Button modificaProcessoProd;
    @FXML
    private TextField idProcessoModificare;
    @FXML
    private TextField idProcessoNew;
    @FXML
    private TextField nomeProcessoNew;
    @FXML
    private TextField nomeAziendaNew;
    @FXML
    private TextField partitaIvaNew;
    @FXML
    private DatePicker dataInizioNew;
    @FXML
    private DatePicker dataFineNew;
    @FXML
    private Button back;

    public void init() {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        processoTable = new ProcessoProduttivoTable(connectionProvider.getMySQLConnection());
        aziendaTable = new AziendaTable(connectionProvider.getMySQLConnection());
        this.processoList = FXCollections.observableArrayList(this.processoTable.findAll());
        this.proceProdListView.setItems(this.processoList);
    }

    @FXML
    public void saveProcessoProd(final ActionEvent event) {
        String id = this.idProcesso.getText();
        String name = this.nomeProcesso.getText();
        String companyName = this.nomeAzienda.getText();
        String partitaIva= this.partitaIva.getText();

        if (id.isEmpty() || name.isEmpty() || this.dataInizio.getValue() == null ||
        this.dataFine.getValue() == null || companyName.isEmpty() || partitaIva.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            TwoKeys<String, String> key = new TwoKeys<>(companyName, partitaIva);
            if (this.aziendaTable.findByPrimaryKey(key).isPresent()) {
                Date start = Utils.buildDate(this.dataInizio.getValue().getDayOfMonth(), this.dataInizio.getValue().getMonthValue(), this.dataInizio.getValue().getYear()).get();
                Date end = Utils.buildDate(this.dataFine.getValue().getDayOfMonth(), this.dataFine.getValue().getMonthValue(), this.dataFine.getValue().getYear()).get();
                Processo_Produttivo processo = new Processo_Produttivo(id, name, start, end, companyName, partitaIva);
                this.processoTable.save(processo);
                this.processoList = FXCollections.observableArrayList(this.processoTable.findAll());
                this.proceProdListView.setItems(this.processoList);
            } else {
                showPopUp("L'azienda a cui ti riferirsci non è presente nel database, inserici un'azienda valida'!!",null ,Alert.AlertType.WARNING );
            }
        }
    }

    @FXML
    public void deleteProcessoProd(final ActionEvent event) {
        String id = this.idProcessoCancella.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            this.processoTable.delete(id);
            this.processoList = FXCollections.observableArrayList(this.processoTable.findAll());
            this.proceProdListView.setItems(this.processoList);
        }
    }

    @FXML
    public void searchProcessoProd(final ActionEvent event) {
        String id = this.idProcessoCerca.getText();
        if (id.isEmpty()) {
            showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
        } else {
            if (this.processoTable.findByPrimaryKey(id).isPresent()) {
                this.processoList = FXCollections.observableArrayList(this.processoTable.findByPrimaryKey(id).get());
                this.proceProdListView.setItems(this.processoList);
            } else {
                showPopUp("Il processo che cerchi non è presente in questo DataBase!!", null, Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void seeProcessoProd(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        processoTable = new ProcessoProduttivoTable(connectionProvider.getMySQLConnection());
        this.processoList = FXCollections.observableArrayList(this.processoTable.findAll());
        this.proceProdListView.setItems(this.processoList);
    }

    @FXML
    public void handleButtonAction(final ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == this.modificaProcessoProd) {
            stage = (Stage) this.modificaProcessoProd.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/modificaProcesso.fxml")));
        } else {
            stage = (Stage) this.back.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateProcesso(final ActionEvent event) {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        processoTable = new ProcessoProduttivoTable(connectionProvider.getMySQLConnection());
        aziendaTable = new AziendaTable(connectionProvider.getMySQLConnection());

        String id = this.idProcessoNew.getText();
        String name = this.nomeProcessoNew.getText();
        String companyName = this.nomeAziendaNew.getText();
        String partitaIva= this.partitaIvaNew.getText();

        String idControllo = this.idProcessoModificare.getText();
        if (idControllo.isEmpty() || this.processoTable.findByPrimaryKey(idControllo).isEmpty()) {
            showPopUp("id di controllo assente, oppure non presente all'interno del DB'!!", null, Alert.AlertType.WARNING);
        } else {
            if (id.isEmpty() || name.isEmpty() || this.dataInizioNew.getValue() == null ||
                    this.dataFineNew.getValue() == null || companyName.isEmpty() || partitaIva.isEmpty()) {
                showPopUp("Inserirsci tutti i campi!!", null, Alert.AlertType.WARNING);
            } else {
                if (this.processoTable.findByPrimaryKey(id).isPresent()) {
                    TwoKeys<String, String> key = new TwoKeys<>(companyName, partitaIva);
                    if (this.aziendaTable.findByPrimaryKey(key).isPresent()) {
                        Date start = Utils.buildDate(this.dataInizioNew.getValue().getDayOfMonth(), this.dataInizioNew.getValue().getMonthValue(), this.dataInizioNew.getValue().getYear()).get();
                        Date end = Utils.buildDate(this.dataFineNew.getValue().getDayOfMonth(), this.dataFineNew.getValue().getMonthValue(), this.dataFineNew.getValue().getYear()).get();
                        Processo_Produttivo processo = new Processo_Produttivo(id, name, start, end, companyName, partitaIva);
                        this.processoTable.save(processo);
                        this.processoList = FXCollections.observableArrayList(this.processoTable.findAll());
                        this.proceProdListView.setItems(this.processoList);
                    } else {
                        showPopUp("L'azienda a cui ti riferirsci non è presente nel database, inserici un'azienda valida'!!", null, Alert.AlertType.WARNING);
                    }
                } else {
                    showPopUp("Il processo a cui ti riferirsci non è presente nel database, inserici un'azienda valida'!!", null, Alert.AlertType.WARNING);
                }
            }
        }
    }
}
