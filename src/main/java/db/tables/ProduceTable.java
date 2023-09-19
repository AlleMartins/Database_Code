package db.tables;

import db.Table;
import model.Produce;
import utils.FiveKeys;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProduceTable implements Table<Produce, FiveKeys<Date, String, String, String, String>> {

    public static final String TABLE_NAME = "produce";
    private final Connection connection;

    public ProduceTable(final Connection connection) {
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
    public Optional<Produce> findByPrimaryKey(final FiveKeys<Date, String, String, String, String> primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE Data = ? AND ID_Macchina = ? AND ID_Dipendente = ? AND Ora = ? AND ID_Scarto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(primaryKey.getX()));
            statement.setString(2, primaryKey.getY());
            statement.setString(3, primaryKey.getZ());
            statement.setString(4, primaryKey.getM());
            statement.setString(5, primaryKey.getN());
            final ResultSet resultSet = statement.executeQuery();
            return readProduceFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Produce> readProduceFromResultSet(final ResultSet resultSet) {
        final List<Produce> produces = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final Date data = Utils.sqlDateToDate(resultSet.getDate("Data"));
                final String idMachine = resultSet.getString("ID_Macchina");
                final String idEmplyee = resultSet.getString("ID_Dipendente");
                final String time = resultSet.getString("Ora");
                final String idWaste = resultSet.getString("ID_Scarto");
                final Produce produce = new Produce(data, idMachine, idEmplyee, time, idWaste);
                produces.add(produce);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return produces;
    }

    @Override
    public List<Produce> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readProduceFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Produce value) {
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

    private boolean produceExists(final FiveKeys<Date, String, String, String, String> id) {
        final ProduceTable produce = new ProduceTable(connection);
        return produce.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Produce value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "Data = ?," +
                "ID_Macchina = ?," +
                "ID_Dipendente = ?," +
                "Ora = ?," +
                "ID_Scarto = ?" +
                "WHERE Data = ? AND ID_Macchina = ? AND ID_Dipendente = ? AND Ora = ? AND ID_Scarto = ?";
        final FiveKeys<Date, String, String, String, String> control = new FiveKeys<>(value.getData(), value.getIdMachine(), value.getIdEmployee(), value.getTime(), value.getIdWaste());
        if (!this.produceExists(control)) {
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
