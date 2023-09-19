package db.tables;

import db.Table;
import model.Usato;
import utils.TwoKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsatoTable implements Table<Usato, TwoKeys<String, String>> {

    public static final String TABLE_NAME = "usato";
    private final Connection connection;

    public UsatoTable(final Connection connection) {
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
    public Optional<Usato> findByPrimaryKey(final TwoKeys<String, String> id) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Processo = ? AND ID_Prodotto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, id.getX());
            statement.setString(2, id.getY());
            final ResultSet resultSet = statement.executeQuery();
            return readUsatoFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Usato> readUsatoFromResultSet(ResultSet resultSet) {
        final List<Usato> uses = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idProcess = resultSet.getString("ID_Processo");
                final String idProduct =  resultSet.getString("ID_Prodotto");
                final Usato use = new Usato(idProcess, idProduct);
                uses.add(use);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return uses;
    }

    @Override
    public List<Usato> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readUsatoFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Usato value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Processo, ID_Prodotto) VALUES (?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdProcess());
            statement.setString(2, value.getIdProduct());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean usatoExists(final TwoKeys<String, String> control) {
        final UsatoTable use = new UsatoTable(connection);
        return use.findByPrimaryKey(control).isPresent();
    }

    @Override
    public boolean update(final Usato value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Processo = ?," +
                "ID_Prodotto = ?" +
                "WHERE ID_Processo = ? AND ID_Prodotto = ?";

        final TwoKeys<String, String> control = new TwoKeys<>(value.getIdProcess(), value.getIdProduct());
        if (!this.usatoExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdProcess());
            statement.setString(2, value.getIdProduct());
            statement.setString(3, value.getIdProcess());
            statement.setString(4, value.getIdProduct());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final TwoKeys<String, String> keys) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Processo = ? AND ID_Prodotto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, keys.getX());
            statement.setString(2, keys.getY());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
