package db.tables;

import db.Table;
import model.Contatta;
import utils.ThreeKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContattaTable implements Table<Contatta, ThreeKeys <String, String, String>> {
    public static final String TABLE_NAME = "contatta";
    private final Connection connection;

    public ContattaTable(final Connection connection) {
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
    public Optional<Contatta> findByPrimaryKey(final ThreeKeys<String, String, String> id) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE Nome_Azienda = ? AND Partita_IVA = ? AND ID_Fornitore = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, id.getX());
            statement.setString(2, id.getY());
            statement.setString(3, id.getZ());
            final ResultSet resultSet = statement.executeQuery();
            return readContattaFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Contatta> readContattaFromResultSet(final ResultSet resultSet) {
        final List<Contatta> contatti = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String nameCompany = resultSet.getString("Nome_Azienda");
                final String partitaIva =  resultSet.getString("Partita_IVA");
                final String ID_Fornitore = resultSet.getString("ID_Fornitore");
                final Contatta contatto = new Contatta(nameCompany, partitaIva, ID_Fornitore);
                contatti.add(contatto);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return contatti;
    }

    @Override
    public List<Contatta> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readContattaFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Contatta value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Nome_Azienda, Partita_IVA, ID_Fornitore) VALUES (?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getCompanyName());
            statement.setString(2, value.getPartitaIva());
            statement.setString(3, value.getId());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean contactExists(final ThreeKeys<String, String, String> contatto) {
        final ContattaTable contattaTable = new ContattaTable(connection);
        return contattaTable.findByPrimaryKey(contatto).isPresent();
    }

    @Override
    public boolean update(final Contatta updatedValue) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "Nome_Azienda = ?," +
                "Partita_IVA = ?," +
                "ID_Fornitore = ?" +
                "WHERE Nome_Azienda = ? AND Partita_IVA = ? AND ID_Fornitore = ?";

        final ThreeKeys<String, String, String> control = new ThreeKeys<>(updatedValue.getCompanyName(), updatedValue.getPartitaIva(), updatedValue.getId());
        if (!this.contactExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, updatedValue.getCompanyName());
            statement.setString(2, updatedValue.getPartitaIva());
            statement.setString(3, updatedValue.getId());
            statement.setString(4, updatedValue.getCompanyName());
            statement.setString(5, updatedValue.getPartitaIva());
            statement.setString(6, updatedValue.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final ThreeKeys<String, String, String> primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE Nome_Azienda = ? AND Partita_IVA = ? AND ID_Fornitore = ? ";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey.getX());
            statement.setString(2, primaryKey.getY());
            statement.setString(3, primaryKey.getZ());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
