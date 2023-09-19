package db.tables;

import db.Table;
import model.Riceve;
import utils.TwoKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RiceveTable implements Table<Riceve, TwoKeys<String, String>> {

    public static final String TABLE_NAME = "riceve";
    private final Connection connection;

    public RiceveTable(final Connection connection) {
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
    public Optional<Riceve> findByPrimaryKey(final TwoKeys<String, String> id) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE Partita_IVA = ? AND ID_Consegna = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, id.getX());
            statement.setString(2, id.getY());
            final ResultSet resultSet = statement.executeQuery();
            return readRiceveFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Riceve> readRiceveFromResultSet(ResultSet resultSet) {
        final List<Riceve> ricevute = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idConsegna = resultSet.getString("Nome_Azienda");
                final String partitaIva =  resultSet.getString("Partita_IVA");
                final Riceve ricevuta = new Riceve(partitaIva, idConsegna);
                ricevute.add(ricevuta);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return ricevute;
    }

    @Override
    public List<Riceve> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readRiceveFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Riceve value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Partita_IVA, ID_Consegna) VALUES (?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getPartitaIva());
            statement.setString(2, value.getIdDelivery());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean deliveryExists(final TwoKeys<String, String> company) {
        final RiceveTable delivery = new RiceveTable(connection);
        return delivery.findByPrimaryKey(company).isPresent();
    }

    @Override
    public boolean update(final Riceve value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "Partita_IVA = ?," +
                "ID_Consegna = ?" +
                "WHERE Partita_IVA = ? AND ID_Consegna = ?";

        final TwoKeys<String, String> control = new TwoKeys<>(value.getPartitaIva(), value.getIdDelivery());
        if (!this.deliveryExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getPartitaIva());
            statement.setString(2, value.getIdDelivery());
            statement.setString(3, value.getPartitaIva());
            statement.setString(4, value.getIdDelivery());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final TwoKeys<String, String> keys) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE Partita_IVA = ? AND ID_Consegna = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, keys.getX());
            statement.setString(2, keys.getY());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
