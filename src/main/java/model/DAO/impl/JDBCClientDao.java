package model.DAO.impl;

import model.DAO.SqlQuarry;
import model.DAO.mapper.ClientMapper;
import model.DAO.tryService.ClientDAO;
import model.connection.ConnectionPoolHolder;
import model.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCClientDao implements ClientDAO {
    private Connection connection;

    public JDBCClientDao(Connection connection) {
        this.connection = connection;
    }
    public List<Client> getClients() {
        List<Client> list = new ArrayList<>();
//        try (Connection con = model.connection.ConnectionPoolHolder.getDataSource().getConnection();
//             Statement st = con.createStatement();
//             ResultSet rs = st.executeQuery(SqlQuarry.CLIENTS)) {
     Statement st = null;
        ResultSet rs=null;
        Connection con = null;
        try{
            con = ConnectionPoolHolder.getDataSource().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(SqlQuarry.CLIENTS);
            ClientMapper cm = new ClientMapper();
            while (rs.next()) {
                Client client = cm.mapFromResultSet(rs);
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public List<Client> getClients(int index, int offset) {
        List<Client> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs=null;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
             pst = con.prepareStatement(SqlQuarry.PAGE_CLIENTS);
             pst.setInt(1, index);
             pst.setInt(2, offset);
           rs = pst.executeQuery();

            ClientMapper cm = new ClientMapper();
            while (rs.next()) {
                Client client = cm.mapFromResultSet(rs);
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }


    public Client getClient(String login) {
        String name = SqlQuarry.CLIENT.replaceAll("login", login);
        Client client = null;
        try (Connection con = model.connection.ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(name)) {
            rs.next();
            client = new ClientMapper().mapFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public List<Client> getStaff() {
        List<Client> staff = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SqlQuarry.ADMIN_STAFF)) {
            ClientMapper cm = new ClientMapper();
            while (rs.next()) {
                Client client = cm.mapFromResultSet(rs);
                staff.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    public boolean deleteClient(String login) {
        Client client = getClient(login);
        boolean result = false;
        Connection con = null;
        try {
            con = model.connection.ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);

            PreparedStatement stt = con.prepareStatement(SqlQuarry.MOVE_CLIENT_TO_REMOVED);
            stt.setInt(1, client.getId());
            stt.setString(2, client.getLogin());
            stt.setString(3, client.getPassport());

            PreparedStatement st = con.prepareStatement(SqlQuarry.DELETE_CLIENT);
            st.setString(1, login);
            result = (stt.executeUpdate() > 0 && st.executeUpdate() > 0);

            con.commit();

        } catch (SQLException e) {
            System.out.println("FAILED to delete client");
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    public boolean makeManager(String login) {
        boolean result = false;
        Connection con = null;
        try {
            con = model.connection.ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.MAKE_MANAGER);
            st.setString(1, login);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to make manager");
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean removeManager(String login) {
        boolean result = false;
        Connection con = null;
        try {
            con = model.connection.ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.REMOVE_MANAGER);
            st.setString(1, login);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to remove manager");
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean register(Client client) {
        boolean result = false;
        Connection con = null;
        try {
            con = model.connection.ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.REGISTER);
            st.setString(1, client.getLogin());
            st.setString(2, client.getPassword());
            st.setString(3, client.getPassport());
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {

            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("Such user exists");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean ban(String login) {
        boolean result = false;
        Connection con = null;
        try {
            con = model.connection.ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.BAN);
            st.setString(1, login);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to BAN");
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean unBan(String login) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.UNBAN);
            st.setString(1, login);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to BAN");
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // =============================================
    @Override
    public boolean create(Client entity) {
        return false;
    }

    @Override
    public Client findById(int id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public boolean update(Client entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}