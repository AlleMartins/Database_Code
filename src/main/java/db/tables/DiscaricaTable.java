package db.tables;

import db.Table;
import model.Discarica;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DiscaricaTable implements Table<Discarica, String> {

    public static final String TABLE_NAME = "DISCARICA";
    private final Connection connection;

    public DiscaricaTable(final Connection connection) {
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
    public Optional<Discarica> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Discarica = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readDiscaricaFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Discarica> readDiscaricaFromResultSet(final ResultSet resultSet) {
        final List<Discarica> discariche = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idWaste = resultSet.getString("ID_Discarica");
                final String name = resultSet.getString("Nome_Discarica");
                final String address = resultSet.getString("Indirizzo");
                final String city = resultSet.getString("Citta");
                final String province = resultSet.getString("Provincia");
                final int cap = resultSet.getInt("CAP");
                final String nation = resultSet.getString("Paese");
                final String wasteTyper = resultSet.getString("Tipo_Rifiuti");
                final Date open = Utils.sqlDateToDate(resultSet.getDate("Data_Apertura"));
                final Date close = Utils.sqlDateToDate(resultSet.getDate("Data_Chiusura"));
                final String manager = resultSet.getString("Gestore");
                final Discarica discarica = new Discarica(idWaste, name, address, city, province, cap, nation, wasteTyper, open, close, manager);
                discariche.add(discarica);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return discariche;
    }

    @Override
    public List<Discarica> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readDiscaricaFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Discarica value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Discarica, Nome_Discarica, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Rifiuti, Data_Apertura, Data_Chiusura, Gestore) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getName());
            statement.setString(3, value.getAddress());
            statement.setString(4, value.getCity());
            statement.setString(5, value.getProvince());
            statement.setInt(6, value.getCap());
            statement.setString(7, value.getNation());
            statement.setString(8, value.getWaste());
            statement.setDate(9, Utils.dateToSqlDate(value.getOpen()));
            statement.setDate(10, Utils.dateToSqlDate(value.getClose()));
            statement.setString(11, value.getManager());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean discaricaExists(final String id) {
        final DiscaricaTable control = new DiscaricaTable(connection);
        return control.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Discarica value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Discarica = ?," +
                "Nome_Discarica = ?," +
                "Indirizzo = ?," +
                "Citta = ?," +
                "Provincia = ?," +
                "CAP = ?," +
                "Paese = ?," +
                "Tipo_Rifiuti = ?," +
                "Data_Apertura = ?," +
                "Data_Chiusura = ?, " +
                "Gestore = ?" +
                "WHERE ID_Discarica = ?";
        if (!this.discaricaExists(value.getId())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getName());
            statement.setString(3, value.getAddress());
            statement.setString(4, value.getCity());
            statement.setString(5, value.getProvince());
            statement.setInt(6, value.getCap());
            statement.setString(7, value.getNation());
            statement.setString(8, value.getWaste());
            statement.setDate(9, Utils.dateToSqlDate(value.getOpen()));
            statement.setDate(10, Utils.dateToSqlDate(value.getClose()));
            statement.setString(11, value.getManager());
            statement.setString(12, value.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Discarica = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
