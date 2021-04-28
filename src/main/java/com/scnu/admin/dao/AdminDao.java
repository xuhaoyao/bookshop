package com.scnu.admin.dao;

import com.scnu.admin.entity.Admin;
import com.scnu.utils.JdbcUtil;
import com.scnu.vip.entity.Vip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    public AdminDao() {
    }

    public boolean loginVerify(Admin admin){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id from t_admin where username = ? and password = ?";
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,admin.getUsername());
            ps.setString(2, admin.getPassword());
            rs = ps.executeQuery();
            flag = rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return flag;
    }

    public List queryVips(){
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_vip";
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Integer id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                list.add(new Vip(id,username,password,email,address,phone));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            return list;
        }
    }

    public Vip queryVip(String vipId){
        Vip vip = new Vip();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_vip where id = ?";
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(vipId));
            rs = ps.executeQuery();
            while(rs.next()){
                vip.setId(rs.getInt("id"));
                vip.setUsername(rs.getString("username"));
                vip.setPassword(rs.getString("password"));
                vip.setEmail(rs.getString("email"));
                vip.setAddress(rs.getString("address"));
                vip.setPhone(rs.getString("phone"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return vip;
    }

    public boolean updateVip(Vip vip){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update t_vip set username = ?,password = ?,email = ?,address = ?,phone = ? where id = ?";
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
            JdbcUtil.close(conn,ps,null);
        }
        return flag;
    }

    public boolean delVip(Integer id){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from t_vip where id = ?";
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
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
            JdbcUtil.close(conn,ps,null);
        }
        return flag;
    }
}
