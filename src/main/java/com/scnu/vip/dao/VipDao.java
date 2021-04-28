package com.scnu.vip.dao;

import com.scnu.utils.JdbcUtil;
import com.scnu.vip.entity.Vip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VipDao {
    public VipDao(){
    }
    public int addVip(Vip vip){
        Connection conn = null;
        PreparedStatement ps = null;
        String username = vip.getUsername();
        String password = vip.getPassword();
        String email = vip.getEmail();
        String sql = "insert into t_vip(username,password,email) values(?,?,?)";
        int result = 0;
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,email);
            result = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if(conn != null)
                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
            return result;
        }
    }

    public boolean findUserName(String username){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) as cnt from t_vip where username = ?";
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            if(rs.next()){
                flag = (1 == rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            return flag;
        }
    }

    public int loginVerify(String username,String password){
        int id = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id from t_vip where username = ? and password = ?";
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            return id;
        }
    }

    public Vip QueryVipInfo(int id){
        Vip vip = new Vip();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_vip where id = ?";
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                vip.setId(rs.getInt("id"));
                vip.setUsername(rs.getString("username"));
                vip.setPassword(rs.getString("password"));
                vip.setEmail(rs.getString("email"));
                vip.setAddress(rs.getString("address"));
                vip.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return vip;
    }

    public boolean isOtherRegisterEmail(int vipid,String email){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(id) as cnt from t_vip where email = ? and id != ?";
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,email);
            ps.setInt(2,vipid);
            rs = ps.executeQuery();
            if(rs.next()){
                flag = (1 == rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
            return flag;
        }
    }

    public boolean updateVipInfo(Vip vip){
        if(isOtherRegisterEmail(vip.getId(),vip.getEmail())) return false;
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        int id = vip.getId();
        String sql = "update t_vip set username = ?,password=?,email=?,address=?,phone=? where id = ?";
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,vip.getUsername());
            ps.setString(2,vip.getPassword());
            ps.setString(3,vip.getEmail());
            ps.setString(4,vip.getAddress());
            ps.setString(5,vip.getPhone());
            ps.setInt(6,vip.getId());
            flag = (1 == ps.executeUpdate());
            conn.commit();
        } catch (SQLException e) {
            if(conn != null)
                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
            return flag;
        }
    }
}
