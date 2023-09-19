package db.tables;

import db.Table;
import model.Uso_Macchinario;
import utils.FourKeys;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UsoMacchinarioTable implements Table<Uso_Macchinario, FourKeys<String, String, Date, String>> {

    public static final String TABLE_NAME = "USO_MACCHINARIO";
    private final Connection connection;

    public UsoMacchinarioTable(final Connection connection) {
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
    public Optional<Uso_Macchinario> findByPrimaryKey(final FourKeys<String, String, Date, String> primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Macchina = ? AND ID_Dipendente = ? AND Data = ? AND Ora = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(3, Utils.dateToSqlDate(primaryKey.getZ()));
            statement.setString(1, primaryKey.getX());
            statement.setString(2, primaryKey.getY());
            statement.setString(4, primaryKey.getM());
            final ResultSet resultSet = statement.executeQuery();
            return readUsoMacchinarioFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Uso_Macchinario> readUsoMacchinarioFromResultSet(final ResultSet resultSet) {
        final List<Uso_Macchinario> machines = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final Date data = Utils.sqlDateToDate(resultSet.getDate("Data"));
                final String idMachine = resultSet.getString("ID_Macchina");
                final String idEmplyee = resultSet.getString("ID_Dipendente");
                final String time = resultSet.getString("Ora");
                final Uso_Macchinario machine = new Uso_Macchinario(idMachine, idEmplyee, data, time);
                machines.add(machine);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return machines;
    }

    @Override
    public List<Uso_Macchinario> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readUsoMacchinarioFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Uso_Macchinario value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Macchina, ID_Dipendente, Data, Ora) VALUES (?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(3, Utils.dateToSqlDate(value.getData()));
            statement.setString(1, value.getIdMachine());
            statement.setString(2, value.getIdEmployee());
            statement.setString(4, value.getTime());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean usoMachineExists(final FourKeys<String, String, Date, String> id) {
        final UsoMacchinarioTable usomachine = new UsoMacchinarioTable(connection);
        return usomachine.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Uso_Macchinario value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Macchina = ?," +
                "ID_Dipendente = ?," +
                "Data = ?," +
                "Ora = ?" +
                "WHERE ID_Macchina = ? AND ID_Dipendente = ? AND Data = ? AND Ora = ?";
        final FourKeys<String, String, Date, String> control = new FourKeys<>(value.getIdMachine(), value.getIdEmployee(), value.getData(), value.getTime());
        if (!this.usoMachineExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(3, Utils.dateToSqlDate(value.getData()));
            statement.setString(1, value.getIdMachine());
            statement.setString(2, value.getIdEmployee());
            statement.setString(4, value.getTime());
            statement.setDate(7, Utils.dateToSqlDate(value.getData()));
            statement.setString(5, value.getIdMachine());
            statement.setString(6, value.getIdEmployee());
            statement.setString(8, value.getTime());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final FourKeys<String, String, Date, String> primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Macchina = ? AND ID_Dipendente = ? AND Data = ? AND Ora = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(3, Utils.dateToSqlDate(primaryKey.getZ()));
            statement.setString(1, primaryKey.getX());
            statement.setString(2, primaryKey.getY());
            statement.setString(4, primaryKey.getM());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
