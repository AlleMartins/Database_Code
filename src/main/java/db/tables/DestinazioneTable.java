package db.tables;

import db.Table;
import model.Destinazione;
import utils.TwoKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DestinazioneTable implements Table<Destinazione, TwoKeys<String, String>> {
    public static final String TABLE_NAME = "destinazione";
    private final Connection connection;

    public DestinazioneTable(Connection connection) {
        this.connection = connection;
    }
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public boolean dropTable() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.executeQuery("DROP TABLE " + TABLE_NAME);
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    @Override
    public Optional<Destinazione> findByPrimaryKey(final TwoKeys<String, String> id) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Discarica = ? AND ID_Trasporto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, id.getX());
            statement.setString(2, id.getY());
            final ResultSet resultSet = statement.executeQuery();
            return readDestinazioneFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Destinazione> readDestinazioneFromResultSet(final ResultSet resultSet) {
        final List<Destinazione> destinations = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idDiscarica = resultSet.getString("ID_Discarica");
                final String idTrasport =  resultSet.getString("ID_Trasporto");
                final Destinazione destinantion = new Destinazione(idDiscarica, idTrasport);
                destinations.add(destinantion);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return destinations;
    }

    @Override
    public List<Destinazione> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readDestinazioneFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Destinazione value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Discarica, ID_Trasporto) VALUES (?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdDump());
            statement.setString(2, value.getIdTransport());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean destinationExists(final TwoKeys<String, String> dest) {
        final DestinazioneTable destination = new DestinazioneTable(connection);
        return destination.findByPrimaryKey(dest).isPresent();
    }

    @Override
    public boolean update(final Destinazione updatedValue) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Discarica = ?," +
                "ID_Trasporto = ?" +
                "WHERE ID_Discarica = ? AND ID_Trasporto = ?";

        final TwoKeys<String, String> control = new TwoKeys<>(updatedValue.getIdDump(), updatedValue.getIdTransport());
        if (!this.destinationExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, updatedValue.getIdDump());
            statement.setString(2, updatedValue.getIdTransport());
            statement.setString(3, updatedValue.getIdDump());
            statement.setString(4, updatedValue.getIdTransport());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final TwoKeys<String, String> primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Discarica = ? AND ID_Trasporto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey.getX());
            statement.setString(2, primaryKey.getY());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
