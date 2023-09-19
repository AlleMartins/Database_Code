package db.tables;

import db.Table;
import model.Fattura;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class FatturaTable implements Table<Fattura, Integer> {
    public static final String TABLE_NAME = "FATTURA";
    private final Connection connection;

    public FatturaTable(final Connection connection) {
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
    public Optional<Fattura> findByPrimaryKey(Integer primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE Numero_Fattura = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readFattureFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Fattura> readFattureFromResultSet(final ResultSet resultSet) {
        final List<Fattura> fatture = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idFattura = resultSet.getInt("Numero_Fattura");
                final Date emission = Utils.sqlDateToDate(resultSet.getDate("Data_Emissione"));
                final String description = resultSet.getString("Descrizione");
                final int total = resultSet.getInt("Totale");
                final String paymentMethod = resultSet.getString("Metodo_Pagamento");
                final String statePayment = resultSet.getString("Stato_Pagamento");
                final Date endCommission = Utils.sqlDateToDate(resultSet.getDate("Data_Scadenza"));
                final String addressPayment = resultSet.getString("Indirizzo_Fatturazione");
                final String companyName = resultSet.getString("Nome_Azienda");
                final String partitaIVA = resultSet.getString("Partita_IVA");
                final Fattura fattura = new Fattura(idFattura, emission, description, total, paymentMethod, statePayment, endCommission, addressPayment, companyName, partitaIVA);
                fatture.add(fattura);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return fatture;
    }

    @Override
    public List<Fattura> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readFattureFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Fattura value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Numero_Fattura, Data_Emissione, Descrizione, Totale, Metodo_Pagamento, Stato_Pagamento, Data_Scadenza, Indirizzo_Fatturazione, Nome_Azienda, Partita_IVA) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getInvoiceNumber());
            statement.setDate(2, Utils.dateToSqlDate(value.getEmission()));
            statement.setString(3, value.getDescription());
            statement.setInt(4, value.getTotal());
            statement.setString(5, value.getPaymentMethod());
            statement.setString(6, value.getStatePayment());
            statement.setDate(7, Utils.dateToSqlDate(value.getExpirationDate()));
            statement.setString(8, value.getAddressInvoice());
            statement.setString(9, value.getCompanyName());
            statement.setString(10, value.getPartitaIva());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
            //return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean fatturaExists(final int id) {
        final FatturaTable fattura = new FatturaTable(connection);
        return fattura.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(Fattura value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "Numero_Fattura = ?," +
                "Data_Emissione = ?," +
                "Descrizione = ?," +
                "Totale = ?," +
                "Metodo_Pagamento = ?," +
                "Stato_Pagamento = ?," +
                "Data_Scadenza = ?," +
                "Indirizzo_Fatturazione = ?," +
                "Nome_Azienda = ?," +
                "Partita_IVA = ?" +
                "WHERE Numero_Fattura = ?";
        if (!this.fatturaExists(value.getInvoiceNumber())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getInvoiceNumber());
            statement.setDate(2, Utils.dateToSqlDate(value.getEmission()));
            statement.setString(3, value.getDescription());
            statement.setInt(4, value.getTotal());
            statement.setString(5, value.getPaymentMethod());
            statement.setString(6, value.getStatePayment());
            statement.setDate(7, Utils.dateToSqlDate(value.getExpirationDate()));
            statement.setString(8, value.getAddressInvoice());
            statement.setString(9, value.getCompanyName());
            statement.setString(10, value.getPartitaIva());
            statement.setInt(11, value.getInvoiceNumber());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(Integer primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE Numero_Fattura = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
