package com.scnu.order.dao;

import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.scnu.book.entity.Book;
import com.scnu.book.entity.PageBean;
import com.scnu.order.entity.Orders;
import com.scnu.utils.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao {
    public OrderDao() {
    }

    public boolean addNewOrder_fromCart(Book book,Integer vipid,Long date){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql =
                "insert into t_orders" +
                        "(vipid,bookid,bookName,price,ischeckout,startDate)" +
                        "values(?,?,?,?,?,?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Date = sdf.format(new Date(date));
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,vipid);
            ps.setInt(2,book.getBookid());
            ps.setString(3,book.getBookName());
            ps.setBigDecimal(4,book.getPrice());
            ps.setString(5,"未结账");
            ps.setString(6,Date);
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
            return flag;
        }
    }

    public boolean addNewOrder_fromShop(Book book,Integer vipid,Long date){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql =
                "insert into t_orders" +
                        "(vipid,bookid,bookName,price,ischeckout,startDate,endDate)" +
                        "values(?,?,?,?,?,?,?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Date = sdf.format(new Date(date));
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,vipid);
            ps.setInt(2,book.getBookid());
            ps.setString(3,book.getBookName());
            ps.setBigDecimal(4,book.getPrice());
            ps.setString(5,"已结账");
            ps.setString(6,Date);
            ps.setString(7,Date);
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
            return flag;
        }
    }

    public boolean updateOrder_fromCart(Integer vipid,Integer bookid,Long date){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update t_orders set ischeckout = ?,endDate = ? where vipid = ? and bookid = ? and endDate is null";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Date = sdf.format(new Date(date));
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,"已结账");
            ps.setString(2,Date);
            ps.setInt(3,vipid);
            ps.setInt(4,bookid);
            flag = (1 == ps.executeUpdate());
            if(flag){
                sql = "update t_cart set lastDate = ? where vipid = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,Date);
                ps.setInt(2,vipid);
                flag = (1 == ps.executeUpdate());
                if(flag){
                    sql = "delete from t_cartInfo where id = ? and bookid = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1,vipid);
                    ps.setInt(2,bookid);
                    flag = (1 == ps.executeUpdate());
                    if(flag) conn.commit();
                    else conn.rollback();
                }
            }
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
            return flag;
        }
    }

    public PageBean<Orders> findByPage(int pc){
        PageBean<Orders> pageBean = new PageBean<Orders>();
        int _ps = 10;
        int all = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(ordersid) as cnt from t_orders";
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
                all = rs.getInt("cnt");
            pageBean.setPc(pc);
            pageBean.setPs(_ps);
            pageBean.setAll(all);
            sql = "select * from t_orders limit " + ((pc - 1) * _ps) + "," + _ps;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Orders> list = new ArrayList<Orders>();
            while(rs.next()){
                Orders o = new Orders();
                o.setOrdersid(rs.getInt("ordersid"));
                o.setVipid(rs.getInt("vipid"));
                o.setBookid(rs.getInt("bookid"));
                o.setBookName(rs.getString("bookName"));
                o.setPrice(rs.getBigDecimal("price"));
                o.setIscheckout(rs.getString("ischeckout"));
                o.setStartDate(rs.getString("startDate"));
                o.setEndDate(rs.getString("endDate"));
                list.add(o);
            }
            pageBean.setBeanList(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            return pageBean;
        }
    }

    public PageBean<Book> findByPage(int pc,int vipid){
        int _ps = 5;
        int all = 0;
        String sql = "select count(bookid) from t_orders where vipid = ? and endDate is not null and vipOper is null";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,vipid);
            rs = ps.executeQuery();
            if(rs.next())
                all = rs.getInt("count(bookid)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
        }
        PageBean<Book> pageBean = new PageBean<Book>();
        List<Book> list = new ArrayList<Book>();
        String sql1 = "select b.*,o.endDate from t_book as b,t_orders as o where" +
                " o.vipid = ? and o.bookid = b.bookid and o.endDate is not null " +
                "and o.vipOper is null limit ?,?";
        pageBean.setAll(all);
        pageBean.setPc(pc);
        pageBean.setPs(_ps);
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql1);
            ps.setInt(1,vipid);
            ps.setInt(2,(pc - 1) * _ps);
            ps.setInt(3,_ps);
            rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookid(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookName"));
                book.setAuthor(rs.getString("author"));
                book.setPress(rs.getString("press"));
                book.setPublishDate(rs.getString("publishDate"));
                book.setIsbn(rs.getString("isbn"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setBuyDate(rs.getString("endDate"));
                list.add(book);
            }
            pageBean.setBeanList(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            return pageBean;
        }
    }

    public boolean vipDelBookRecord(int vipid,int bookid,String buyDate){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update t_orders set vipOper = 'del' where vipid = ? and bookid = ? and endDate = ?";
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,vipid);
            ps.setInt(2,bookid);
            ps.setString(3,buyDate);
            flag = (1 == ps.executeUpdate());
            conn.commit();
        } catch (SQLException e) {
            if(conn != null)
                conn.rollback();
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,null);
            return flag;
        }
    }
}
