package db.tables;

import db.Table;
import model.Processo_Produttivo;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProcessoProduttivoTable implements Table<Processo_Produttivo, String> {

    public static final String TABLE_NAME = "PROCESSO_PRODUTTIVO";
    private final Connection connection;

    public ProcessoProduttivoTable(final Connection connection) {
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
    public Optional<Processo_Produttivo> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Processo = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readProcessoProdFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Processo_Produttivo> readProcessoProdFromResultSet(final ResultSet resultSet) {
        final List<Processo_Produttivo> processProds = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idProcess = resultSet.getString("ID_Processo");
                final String name = resultSet.getString("Nome_Processo");
                final Date start = Utils.sqlDateToDate(resultSet.getDate("Data_inizio"));
                final Date end = Utils.sqlDateToDate(resultSet.getDate("Data_fine"));
                final String companyName = resultSet.getString("Nome_Azienda");
                final String partitaIva = resultSet.getString("Partita_IVA");
                final Processo_Produttivo process = new Processo_Produttivo(idProcess, name, start, end, companyName, partitaIva);
                processProds.add(process);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return processProds;
    }

    @Override
    public List<Processo_Produttivo> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readProcessoProdFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Processo_Produttivo value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdProcess());
            statement.setString(2, value.getNameProcess());
            statement.setDate(3, Utils.dateToSqlDate(value.getStart()));
            statement.setDate(4, Utils.dateToSqlDate(value.getEnd()));
            statement.setString(5, value.getCompanyName());
            statement.setString(6, value.getPartitaIva());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean processoExists(final String id) {
        final ProcessoProduttivoTable process = new ProcessoProduttivoTable(connection);
        return process.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Processo_Produttivo value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Processo = ?," +
                "Nome_Processo = ?," +
                "Data_inizio = ?," +
                "Data_fine = ?," +
                "Nome_Azienda = ?," +
                "Partita_IVA = ?" +
                "WHERE ID_Processo = ?";
        if (!this.processoExists(value.getIdProcess())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdProcess());
            statement.setString(2, value.getNameProcess());
            statement.setDate(3, Utils.dateToSqlDate(value.getStart()));
            statement.setDate(4, Utils.dateToSqlDate(value.getEnd()));
            statement.setString(5, value.getCompanyName());
            statement.setString(6, value.getPartitaIva());
            statement.setString(7, value.getIdProcess());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Processo = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
