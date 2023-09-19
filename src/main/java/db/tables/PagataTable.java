package db.tables;

import db.Table;
import model.Pagata;
import utils.TwoKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PagataTable implements Table<Pagata, TwoKeys<String, Integer>> {

    public static final String TABLE_NAME = "pagata";
    private final Connection connection;

    public PagataTable(final Connection connection) {
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
    public Optional<Pagata> findByPrimaryKey(final TwoKeys<String, Integer> id) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE Partita_IVA = ? AND Numero_Fattura = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, id.getX());
            statement.setInt(2, id.getY());
            final ResultSet resultSet = statement.executeQuery();
            return readPagamentiFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Pagata> readPagamentiFromResultSet(ResultSet resultSet) {
        final List<Pagata> payments = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String partitaIVA = resultSet.getString("Partita_IVA");
                final int number =  resultSet.getInt("Numero_Fattura");
                final Pagata pay = new Pagata(partitaIVA, number);
                payments.add(pay);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return payments;
    }

    @Override
    public List<Pagata> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readPagamentiFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Pagata value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Partita_IVA, Numero_Fattura) VALUES (?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getPartitaIva());
            statement.setInt(2, value.getInvoiceNumber());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean paymentExists(final TwoKeys<String, Integer> necessity) {
        final PagataTable payment = new PagataTable(connection);
        return payment.findByPrimaryKey(necessity).isPresent();
    }

    @Override
    public boolean update(final Pagata value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "Partita_IVA = ?," +
                "Numero_Fattura = ?" +
                "WHERE Partita_IVA = ? AND Numero_Fattura = ?";

        final TwoKeys<String, Integer> control = new TwoKeys<>(value.getPartitaIva(), value.getInvoiceNumber());
        if (!this.paymentExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getPartitaIva());
            statement.setInt(2, value.getInvoiceNumber());
            statement.setString(3, value.getPartitaIva());
            statement.setInt(4, value.getInvoiceNumber());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final TwoKeys<String, Integer> keys) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE Partita_IVA = ? AND Numero_Fattura = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, keys.getX());
            statement.setInt(2, keys.getY());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
