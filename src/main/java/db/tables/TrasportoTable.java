package db.tables;

import db.Table;
import model.Trasporto;
import utils.TwoKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrasportoTable implements Table<Trasporto, TwoKeys<String, String>> {

    public static final String TABLE_NAME = "trasporto";
    private final Connection connection;

    public TrasportoTable(final Connection connection) {
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
    public Optional<Trasporto> findByPrimaryKey(final TwoKeys<String, String> id) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Scarto = ? AND ID_Trasporto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, id.getX());
            statement.setString(2, id.getY());
            final ResultSet resultSet = statement.executeQuery();
            return readTrasportoFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Trasporto> readTrasportoFromResultSet(ResultSet resultSet) {
        final List<Trasporto> trasports = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idWaste = resultSet.getString("ID_Scarto");
                final String idTrasport =  resultSet.getString("ID_Trasporto");
                final Trasporto trasport = new Trasporto(idWaste, idTrasport);
                trasports.add(trasport);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return trasports;
    }

    @Override
    public List<Trasporto> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readTrasportoFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Trasporto value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Scarto, ID_Trasporto) VALUES (?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdWaste());
            statement.setString(2, value.getIdTrasport());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean trasportExists(final TwoKeys<String, String> control) {
        final TrasportoTable trasport = new TrasportoTable(connection);
        return trasport.findByPrimaryKey(control).isPresent();
    }

    @Override
    public boolean update(final Trasporto value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Scarto = ?," +
                "ID_Trasporto = ?" +
                "WHERE ID_Scarto = ? AND ID_Trasporto = ?";

        final TwoKeys<String, String> control = new TwoKeys<>(value.getIdWaste(), value.getIdTrasport());
        if (!this.trasportExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdWaste());
            statement.setString(2, value.getIdTrasport());
            statement.setString(3, value.getIdWaste());
            statement.setString(4, value.getIdTrasport());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final TwoKeys<String, String> keys) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Scarto = ? AND ID_Trasporto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, keys.getX());
            statement.setString(2, keys.getY());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
