package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

public class Home extends AbstractView{
    @FXML
    private Tab tabAzienda;
    @FXML
    private Tab tabClienti;
    @FXML
    private Tab tabCommessa;
    @FXML
    private Tab tabConsegna;
    @FXML
    private Tab tabDipendenti;
    @FXML
    private Tab tabFattura;
    @FXML
    private Tab tabFornitori;
    @FXML
    private Tab tabMacchinari;
    @FXML
    private Tab tabProcessoProd;
    @FXML
    private Tab tabProdotto;
    @FXML
    private Tab tabProdottoFinito;
    @FXML
    private Tab tabDiscarica;

    private String url = "jdbc:mysql://localhost:3306/Progettazione_Concettuale_nuova_prog_conc";
    private String username = "root";
    private String password = "root123!";

    @FXML
    public void init() throws Exception {
        super.getStage().sizeToScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/azienda.fxml"));
        tabAzienda.setContent(loader.load());
        AziendaTab aziendaController = loader.getController();
        aziendaController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/cliente.fxml"));
        tabClienti.setContent(loader.load());
        ClienteTab clienteController = loader.getController();
        clienteController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/commessa.fxml"));
        tabCommessa.setContent(loader.load());
        CommessaTab commessaController = loader.getController();
        commessaController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/consegnaCliente.fxml"));
        tabConsegna.setContent(loader.load());
        ConsegnaTab consegnaController = loader.getController();
        consegnaController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/dipendenti.fxml"));
        tabDipendenti.setContent(loader.load());
        DipendentiTab dipendentiController = loader.getController();
        dipendentiController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/discarica.fxml"));
        tabDiscarica.setContent(loader.load());
        DiscaricaTab discaricaController = loader.getController();
        discaricaController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/fattura.fxml"));
        tabFattura.setContent(loader.load());
        FatturaTab fatturaController = loader.getController();
        fatturaController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/fornitoriAssociati.fxml"));
        tabFornitori.setContent(loader.load());
        FornitoriTab fornitoriController = loader.getController();
        fornitoriController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/macchinari.fxml"));
        tabMacchinari.setContent(loader.load());
        MacchinariTab macchinariController = loader.getController();
        macchinariController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/processoProduttivo.fxml"));
        tabProcessoProd.setContent(loader.load());
        ProcessoTab processoController = loader.getController();
        processoController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/prodotti.fxml"));
        tabProdotto.setContent(loader.load());
        ProdottoTab prodottoController = loader.getController();
        prodottoController.init();

        loader = new FXMLLoader(getClass().getResource("/pages/prodottoFinito.fxml"));
        tabProdottoFinito.setContent(loader.load());
        ProdottoFinitoTab prodFinController = loader.getController();
        prodFinController.init();
    }
}
