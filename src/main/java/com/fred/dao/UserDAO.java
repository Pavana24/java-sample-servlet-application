package com.fred.dao;

import com.fred.model.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO {

    public ArrayList<User> findAll() throws Exception {
        ArrayList<User> list = new ArrayList<User>();
        try {
            PreparedStatement ps = getPreparedStatement("SELECT * FROM user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getModel(rs));
            }
            ps.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }

        return list;
    }

    public User findById(int id) throws Exception {
        try {
            PreparedStatement ps = getPreparedStatement("SELECT * FROM user WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getModel(rs);
            }

            ps.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return null;
    }

    public User findByEmailAndPassword(String email, String password) throws Exception {
        try {
            PreparedStatement ps = getPreparedStatement("SELECT * FROM user WHERE email = ? and password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getModel(rs);
            }

            ps.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return null;
    }

    private User getModel(ResultSet rs) throws Exception {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void insert(User user) throws Exception {
        try {
            PreparedStatement ps = getPreparedStatement("INSERT INTO user (name, email, password) VALUES (?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            int id = ps.executeUpdate();
            user.setId(id);
            ps.close();
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new Exception("E-mail já existente na base de dados");
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void update(User user) throws Exception {
        if (user.getId() == 0) {
            throw new Exception("Usuário não possui id.");
        }
        try {
            PreparedStatement ps = getPreparedStatement("UPDATE user SET name = ?, email = ?, password = ? WHERE id = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void delete(User user) throws Exception {
        if (user.getId() == 0) {
            throw new Exception("Usuário não possui id.");
        }
        try {
            PreparedStatement ps = getPreparedStatement("DELETE FROM user WHERE id = ?");
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}