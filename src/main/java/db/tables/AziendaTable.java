package db.tables;

import db.Table;
import model.Azienda;
import utils.TwoKeys;
import java.util.ArrayList;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AziendaTable implements Table<Azienda, TwoKeys<String, String>> {
    public static final String TABLE_NAME = "AZIENDA";
    private final Connection connection;

    public AziendaTable(final Connection connection) {
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
    public Optional<Azienda> findByPrimaryKey(final TwoKeys<String, String> id) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE Nome_Azienda = ? AND Partita_IVA = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, id.getX());
            statement.setString(2, id.getY());
            final ResultSet resultSet = statement.executeQuery();
            return readAziendeFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Azienda> readAziendeFromResultSet(ResultSet resultSet) {
        final List<Azienda> companys = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String nameCompany = resultSet.getString("Nome_Azienda");
                final String partitaIva =  resultSet.getString("Partita_IVA");
                final String address = resultSet.getString("Indirizzo");
                final String city = resultSet.getString("Citta");
                final String province = resultSet.getString("Provincia");
                final int cap = resultSet.getInt("CAP");
                final String nation = resultSet.getString("Paese");
                final String jobSector = resultSet.getString("Settore");
                final int telephone = resultSet.getInt("Numero_di_Telefono");
                final String email = resultSet.getString("Email");
                final String account = resultSet.getString("Conto_Azienda");
                final Azienda company = new Azienda(nameCompany, address, city, province, cap, nation, jobSector, telephone, email, partitaIva, account);
                companys.add(company);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return companys;
    }

    @Override
    public List<Azienda> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readAziendeFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Azienda value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Nome_Azienda, Indirizzo, Citta, Provincia, CAP, Paese, Settore, Numero_di_Telefono, Email, Partita_IVA, Conto_Azienda) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getNameCompany());
            statement.setString(2, value.getAddress());
            statement.setString(3, value.getCity());
            statement.setString(4, value.getProvince());
            statement.setInt(5, value.getCap());
            statement.setString(6, value.getNation());
            statement.setString(7, value.getJobSector());
            statement.setInt(8, value.getTelephone());
            statement.setString(9, value.getEmail());
            statement.setString(10, value.getPartitaIva());
            statement.setString(11, value.getAccount());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean companyExists(final TwoKeys<String, String> company) {
        final AziendaTable azienda = new AziendaTable(connection);
        return azienda.findByPrimaryKey(company).isPresent();
    }

    @Override
    public boolean update(final Azienda updatedValue) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "Nome_Azienda = ?," +
                "Indirizzo = ?," +
                "Citta = ?," +
                "Provincia = ?," +
                "CAP = ?," +
                "Paese = ?," +
                "Settore = ?," +
                "Numero_di_Telefono = ?," +
                "Email = ?," +
                "Partita_IVA = ?," +
                "Conto_Azienda = ? " +
                "WHERE Nome_Azienda = ? AND Partita_IVA = ?";

        final TwoKeys<String, String> control = new TwoKeys<>(updatedValue.getNameCompany(), updatedValue.getPartitaIva());
        if (!this.companyExists(control)) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, updatedValue.getNameCompany());
            statement.setString(2, updatedValue.getAddress());
            statement.setString(3, updatedValue.getCity());
            statement.setString(4, updatedValue.getProvince());
            statement.setInt(5, updatedValue.getCap());
            statement.setString(6, updatedValue.getNation());
            statement.setString(7, updatedValue.getJobSector());
            statement.setInt(8, updatedValue.getTelephone());
            statement.setString(9, updatedValue.getEmail());
            statement.setString(10, updatedValue.getPartitaIva());
            statement.setString(11, updatedValue.getAccount());
            statement.setString(12, updatedValue.getNameCompany());
            statement.setString(13, updatedValue.getPartitaIva());


            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final TwoKeys<String, String> keys) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE Nome_Azienda = ? AND Partita_IVA = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, keys.getX());
            statement.setString(2, keys.getY());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
