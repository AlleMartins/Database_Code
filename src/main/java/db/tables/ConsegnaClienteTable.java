package db.tables;

import db.Table;
import model.Consegna_Cliente;
import model.Prodotto_Finito;
import model.Revisione;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ConsegnaClienteTable implements Table<Consegna_Cliente, String> {
    public static final String TABLE_NAME = "CONSEGNA_CLIENTE";
    private final Connection connection;

    public ConsegnaClienteTable(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public boolean dropTable() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.executeUpdate("DROP TABLE " + TABLE_NAME);
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    @Override
    public Optional<Consegna_Cliente> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Consegna = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readConsegneFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Consegna_Cliente> readConsegneFromResultSet(final ResultSet resultSet) {
        final List<Consegna_Cliente> consegne = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idConsegna = resultSet.getString("ID_Consegna");
                final Date shipmentDate = Utils.sqlDateToDate(resultSet.getDate("Data_Spedizione"));
                final Date arrivalDate = Utils.sqlDateToDate(resultSet.getDate("Data_Arrivo"));
                final String address = resultSet.getString("Indirizzo_Spedizione");
                final String city = resultSet.getString("Citta_spedizione");
                final int cap = resultSet.getInt("CAP_spedizione");
                final String employee = resultSet.getString("Dipendente_Responsabile");
                final int weight = resultSet.getInt("Peso");
                final String documents = resultSet.getString("Documenti_Associati");
                final String transport = resultSet.getString("Mezzo_Trasporto");
                final Consegna_Cliente consegna = new Consegna_Cliente(idConsegna, shipmentDate, arrivalDate, address, city, cap, employee, weight, documents, transport);
                consegne.add(consegna);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return consegne;
    }

    @Override
    public List<Consegna_Cliente> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readConsegneFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Consegna_Cliente value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setDate(2, Utils.dateToSqlDate(value.getShipmentDate()));
            statement.setDate(3, Utils.dateToSqlDate(value.getArrivalDate()));
            statement.setString(4, value.getAddress());
            statement.setString(5, value.getCity());
            statement.setInt(6, value.getCap());
            statement.setString(7, value.getEmployee());
            statement.setInt(8, value.getWeight());
            statement.setString(9, value.getDocuments());
            statement.setString(10, value.getTransport());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean consegnaExists(final String id) {
        final ConsegnaClienteTable consegna = new ConsegnaClienteTable(connection);
        return consegna.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Consegna_Cliente value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Consegna = ?," +
                "Data_Spedizione = ?," +
                "Data_Arrivo = ?," +
                "Indirizzo_spedizione = ?," +
                "Citta_spedizione = ?," +
                "CAP_spedizione = ?," +
                "Dipendente_Responsabile = ?," +
                "Peso = ?," +
                "Documenti_Associati = ?," +
                "Mezzo_Trasporto = ?" +
                "WHERE ID_Consegna = ?";
        if (!this.consegnaExists(value.getId())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setDate(2, Utils.dateToSqlDate(value.getShipmentDate()));
            statement.setDate(3, Utils.dateToSqlDate(value.getArrivalDate()));
            statement.setString(4, value.getAddress());
            statement.setString(5, value.getCity());
            statement.setInt(6, value.getCap());
            statement.setString(7, value.getEmployee());
            statement.setInt(8, value.getWeight());
            statement.setString(9, value.getDocuments());
            statement.setString(10, value.getTransport());
            statement.setString(11, value.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Consegna = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Prodotto_Finito> prodottiTrasportati (final String idConsegna) {
        final String query = "SELECT PF.*\n" +
                "FROM CONSEGNA_CLIENTE CC\n" +
                "JOIN PRODOTTO_FINITO PF ON CC.ID_Consegna = PF.ID_Consegna\n" +
                "WHERE CC.ID_Consegna = ?";

        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, idConsegna);
            ResultSet resultSet = statement.executeQuery();
            return readProdottiFinitiFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Prodotto_Finito> readProdottiFinitiFromResultSet(final ResultSet resultSet) {
        final List<Prodotto_Finito> finishProd = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idProduct = resultSet.getString("ID_Prodotto");
                final int disponibility = resultSet.getInt("Quantita_Disponibile");
                final String description = resultSet.getString("Descrizione");
                final String documents = resultSet.getString("Documentazione");
                final String idProcess = resultSet.getString("ID_Processo");
                final String idConsegna = resultSet.getString("ID_Consegna");
                final Prodotto_Finito prod = new Prodotto_Finito(idProduct, disponibility, description, documents, idProcess, idConsegna);
                finishProd.add(prod);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return finishProd;
    }
}
