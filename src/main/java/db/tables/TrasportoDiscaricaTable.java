package db.tables;

import db.Table;
import model.Trasporto_Discarica;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TrasportoDiscaricaTable implements Table<Trasporto_Discarica, String> {

    public static final String TABLE_NAME = "TRASPORTO_DISCARICA";
    private final Connection connection;

    public TrasportoDiscaricaTable(final Connection connection) {
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
    public Optional<Trasporto_Discarica> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Trasporto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readTrasportoFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Trasporto_Discarica> readTrasportoFromResultSet(final ResultSet resultSet) {
        final List<Trasporto_Discarica> trasports = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idTrasport = resultSet.getString("ID_Trasporto");
                final Date start = Utils.sqlDateToDate(resultSet.getDate("Data_spedizione"));
                final Date end = Utils.sqlDateToDate(resultSet.getDate("Data_Arrivo"));
                final String machine = resultSet.getString("Mezzo_di_Trasporto");
                final String employee = resultSet.getString("Dipendente_Responsabile");
                final int amount = resultSet.getInt("Quantita");
                final int weight = resultSet.getInt("Peso");
                final String documents = resultSet.getString("Documenti_Associati");
                final Trasporto_Discarica trasport = new Trasporto_Discarica(idTrasport, start, end, machine, employee, amount, weight, documents);
                trasports.add(trasport);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return trasports;
    }

    @Override
    public List<Trasporto_Discarica> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readTrasportoFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Trasporto_Discarica value) {
        final String query = "INSERT INTO " + TABLE_NAME + "() VALUES (?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setDate(2, Utils.dateToSqlDate(value.getShipmentDate()));
            statement.setDate(3, Utils.dateToSqlDate(value.getArrivalDate()));
            statement.setString(4, value.getTrasport());
            statement.setString(5, value.getEmployee());
            statement.setInt(6, value.getAmount());
            statement.setInt(7, value.getWeight());
            statement.setString(8, value.getDocuments());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean trasportExists(final String id) {
        final TrasportoDiscaricaTable review = new TrasportoDiscaricaTable(connection);
        return review.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Trasporto_Discarica value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Trasporto = ?," +
                "Data_Spedizione = ?," +
                "Data_Arrivo = ?," +
                "Mezzo_di_Trasporto = ?," +
                "Dipendente_Responsabile = ?," +
                "Quantita = ?," +
                "Peso = ?," +
                "Documenti_Associati = ?" +
                "WHERE ID_Trasporto = ?";
        if (!this.trasportExists(value.getId())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setDate(2, Utils.dateToSqlDate(value.getShipmentDate()));
            statement.setDate(3, Utils.dateToSqlDate(value.getArrivalDate()));
            statement.setString(4, value.getTrasport());
            statement.setString(5, value.getEmployee());
            statement.setInt(6, value.getAmount());
            statement.setInt(7, value.getWeight());
            statement.setString(8, value.getDocuments());
            statement.setString(9, value.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Trasporto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
