package db.tables;

import db.Table;
import model.Ricicla;
import utils.FiveKeys;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RiciclaTable implements Table<Ricicla, FiveKeys<Date, String, String, String, String>> {

    public static final String TABLE_NAME = "ricicla";
    private final Connection connection;

    public RiciclaTable(final Connection connection) {
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
    public Optional<Ricicla> findByPrimaryKey(final FiveKeys<Date, String, String, String, String> primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE Data = ? AND ID_Macchina = ? AND ID_Dipendente = ? AND Ora = ? AND ID_Scarto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(primaryKey.getX()));
            statement.setString(2, primaryKey.getY());
            statement.setString(3, primaryKey.getZ());
            statement.setString(4, primaryKey.getM());
            statement.setString(5, primaryKey.getN());
            final ResultSet resultSet = statement.executeQuery();
            return readRiciclaFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Ricicla> readRiciclaFromResultSet(final ResultSet resultSet) {
        final List<Ricicla> ricicli = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final Date data = Utils.sqlDateToDate(resultSet.getDate("Data"));
                final String idMachine = resultSet.getString("ID_Macchina");
                final String idEmplyee = resultSet.getString("ID_Dipendente");
                final String time = resultSet.getString("Ora");
                final String idWaste = resultSet.getString("ID_Scarto");
                final Ricicla riciclo = new Ricicla(data, idMachine, idEmplyee, time, idWaste);
                ricicli.add(riciclo);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return ricicli;
    }

    @Override
    public List<Ricicla> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readRiciclaFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Ricicla value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Data, ID_Macchina, ID_Dipendente, Ora, ID_Scarto) VALUES (?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(value.getData()));
            statement.setString(2, value.getIdMachine());
            statement.setString(3, value.getIdEmployee());
            statement.setString(4, value.getTime());
            statement.setString(5, value.getIdWaste());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean riciclaExists(final FiveKeys<Date, String, String, String, String> id) {
        final RiciclaTable produce = new RiciclaTable(connection);
        return produce.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Ricicla value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "Data = ?," +
                "ID_Macchina = ?," +
                "ID_Dipendente = ?," +
                "Ora = ?," +
                "ID_Scarto = ?" +
                "WHERE Data = ? AND ID_Macchina = ? AND ID_Dipendente = ? AND Ora = ? AND ID_Scarto = ?";
        final FiveKeys<Date, String, String, String, String> control = new FiveKeys<>(value.getData(), value.getIdMachine(), value.getIdEmployee(), value.getTime(), value.getIdWaste());
        if (!this.riciclaExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(value.getData()));
            statement.setString(2, value.getIdMachine());
            statement.setString(3, value.getIdEmployee());
            statement.setString(4, value.getTime());
            statement.setString(5, value.getIdWaste());
            statement.setDate(6, Utils.dateToSqlDate(value.getData()));
            statement.setString(7, value.getIdMachine());
            statement.setString(8, value.getIdEmployee());
            statement.setString(9, value.getTime());
            statement.setString(10, value.getIdWaste());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final FiveKeys<Date, String, String, String, String> primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE Data = ? AND ID_Macchina = ? AND ID_Dipendente = ? AND Ora = ? AND ID_Scarto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(primaryKey.getX()));
            statement.setString(2, primaryKey.getY());
            statement.setString(3, primaryKey.getZ());
            statement.setString(4, primaryKey.getM());
            statement.setString(5, primaryKey.getN());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
