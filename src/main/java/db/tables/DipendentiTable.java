package db.tables;

import db.Table;
import model.Dipendenti;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DipendentiTable implements Table<Dipendenti, String> {
    public static final String TABLE_NAME = "DIPENDENTI";
    private final Connection connection;

    public DipendentiTable(final Connection connection) {
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
    public Optional<Dipendenti> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Dipendente = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readDipendentiFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Dipendenti> readDipendentiFromResultSet(final ResultSet resultSet) {
        final List<Dipendenti> employees = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idEmployee = resultSet.getString("ID_Dipendente");
                final String name = resultSet.getString("Nome");
                final Date birthday = Utils.sqlDateToDate(resultSet.getDate("Data_Nascita"));
                final String address = resultSet.getString("Indirizzo");
                final String city = resultSet.getString("Citta");
                final String province = resultSet.getString("Provincia");
                final int cap = resultSet.getInt("CAP");
                final String nation = resultSet.getString("Paese");
                final int telephone = resultSet.getInt("Numero_di_Telefono");
                final String email = resultSet.getString("Email");
                final String role = resultSet.getString("Ruolo");
                final String department = resultSet.getString("Dipartimento");
                final Date assumptionDate = Utils.sqlDateToDate(resultSet.getDate("Data_Assunzione"));
                final int salary = resultSet.getInt("Stipendio");
                final String certifications = resultSet.getString("Certificazioni");
                final Dipendenti employee = new Dipendenti(idEmployee, name, birthday, address, city, province, cap, nation, telephone, email, role, department, assumptionDate, salary, certifications);
                employees.add(employee);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return employees;
    }

    @Override
    public List<Dipendenti> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readDipendentiFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Dipendenti value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getName());
            statement.setDate(3, Utils.dateToSqlDate(value.getBirthday()));
            statement.setString(4, value.getAddress());
            statement.setString(5, value.getCity());
            statement.setString(6, value.getProvince());
            statement.setInt(7, value.getCap());
            statement.setString(8, value.getNation());
            statement.setInt(9, value.getTelephone());
            statement.setString(10, value.getEmail());
            statement.setString(11, value.getRole());
            statement.setString(12, value.getDepartment());
            statement.setDate(13, Utils.dateToSqlDate(value.getAssumptionDate()));
            statement.setInt(14, value.getSalary());
            statement.setString(15, value.getCertification());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean dipendenteExists(final String id) {
        final DipendentiTable employee = new DipendentiTable(connection);
        return employee.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Dipendenti value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Dipendente = ?," +
                "Nome = ?," +
                "Data_Nascita = ?," +
                "Indirizzo = ?," +
                "Citta = ?," +
                "Provincia = ?," +
                "CAP = ?," +
                "Paese = ?," +
                "Numero_di_Telefono = ?," +
                "Email = ?," +
                "Ruolo = ?," +
                "Dipartimento = ?," +
                "Data_Assunzione = ?," +
                "Stipendio = ?," +
                "Certificazioni = ?" +
                "WHERE ID_Dipendente = ?";
        if (!this.dipendenteExists(value.getId())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getName());
            statement.setDate(3, Utils.dateToSqlDate(value.getBirthday()));
            statement.setString(4, value.getAddress());
            statement.setString(5, value.getCity());
            statement.setString(6, value.getProvince());
            statement.setInt(7, value.getCap());
            statement.setString(8, value.getNation());
            statement.setInt(9, value.getTelephone());
            statement.setString(10, value.getEmail());
            statement.setString(11, value.getRole());
            statement.setString(12, value.getDepartment());
            statement.setDate(13, Utils.dateToSqlDate(value.getAssumptionDate()));
            statement.setInt(14, value.getSalary());
            statement.setString(15, value.getCertification());
            statement.setString(16, value.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Dipendente = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
