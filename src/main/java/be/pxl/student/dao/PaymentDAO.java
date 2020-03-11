package be.pxl.student.dao;

import be.pxl.student.entity.Payment;

import java.sql.*;

public class PaymentDAO {

    private static final String SELECT_BY_ID = "SELECT * FROM Payment WHERE id = ?";
    private static final String UPDATE = "UPDATE Payment SET date=?, amount=?, currency=?, detail=?, accountId=?, counterAccountId=? WHERE id = ?";
    private static final String INSERT = "INSERT INTO Payment (date, amount, currency, detail, accountId, counterAccountId) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Payment WHERE id = ?";
    private String url;
    private String user;
    private String password;

    public PaymentDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    public Payment createPayment(Payment payment) {

        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, java.sql.Date.valueOf(payment.getDate().toLocalDate()));
            stmt.setFloat(2, payment.getAmount());
            stmt.setString(3, payment.getCurrency());
            stmt.setString(4, payment.getDetail());
            stmt.setInt(5, payment.getId());
            stmt.setInt(6, payment.getCounterAccountId());
            //stmt.setInt(7, payment.getLabelId());
            if (stmt.executeUpdate() == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        payment.setId(rs.getInt(1));
                        return payment;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updatePayment(Payment payment) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
            stmt.setDate(1, java.sql.Date.valueOf(payment.getDate().toLocalDate()));
            stmt.setFloat(2, payment.getAmount());
            stmt.setString(3, payment.getCurrency());
            stmt.setString(4, payment.getDetail());
            stmt.setInt(5, payment.getId());
            stmt.setInt(6, payment.getCounterAccountId());
            stmt.setInt(7, payment.getId());
            return stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deletePayment(int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(DELETE)) {
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Payment readPayment(int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapPayment(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Payment mapPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setDetail(rs.getString("detail"));
        payment.setDate(rs.getDate("date").toLocalDate().atTime(rs.getTime("date").toLocalTime()));
        payment.setCurrency(rs.getString("currency"));
        payment.setAmount(rs.getFloat("amount"));
        payment.setId(rs.getInt("accountId"));
        payment.setCounterAccountId(rs.getInt("counterAccountId"));
        payment.setLabelId(rs.getInt("labelId"));
        return payment;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);

    }
}
