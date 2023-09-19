package db.tables;

import db.Table;
import model.Necessita;
import utils.TwoKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NecessitaTable implements Table<Necessita, TwoKeys<String, String>> {
    public static final String TABLE_NAME = "necessita";
    private final Connection connection;

    public NecessitaTable(final Connection connection) {
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
    public Optional<Necessita> findByPrimaryKey(final TwoKeys<String, String> id) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Macchina = ? AND ID_Processo = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, id.getX());
            statement.setString(2, id.getY());
            final ResultSet resultSet = statement.executeQuery();
            return readNecessitaFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Necessita> readNecessitaFromResultSet(ResultSet resultSet) {
        final List<Necessita> necs = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idMachine = resultSet.getString("ID_Macchina");
                final String idProcess =  resultSet.getString("ID_Processo");
                final Necessita nec = new Necessita(idMachine, idProcess);
                necs.add(nec);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return necs;
    }

    @Override
    public List<Necessita> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readNecessitaFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Necessita value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Macchina, ID_Processo) VALUES (?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdMachine());
            statement.setString(2, value.getIdProcess());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean necExists(final TwoKeys<String, String> necessity) {
        final NecessitaTable nec = new NecessitaTable(connection);
        return nec.findByPrimaryKey(necessity).isPresent();
    }

    @Override
    public boolean update(final Necessita value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Macchina = ?," +
                "ID_Processo = ?" +
                "WHERE ID_Macchina = ? AND ID_Processo = ?";

        final TwoKeys<String, String> control = new TwoKeys<>(value.getIdMachine(), value.getIdProcess());
        if (!this.necExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdMachine());
            statement.setString(2, value.getIdProcess());
            statement.setString(3, value.getIdMachine());
            statement.setString(4, value.getIdProcess());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final TwoKeys<String, String> keys) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Macchina = ? AND ID_Processo = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, keys.getX());
            statement.setString(2, keys.getY());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
