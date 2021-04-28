package com.scnu.cart.dao;

import com.scnu.book.dao.BookDao;
import com.scnu.book.entity.Book;
import com.scnu.cart.entity.Cart;
import com.scnu.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDao {
    private BookDao bookDao = new BookDao();

    public Book findBook(int bookid){
        return bookDao.findBook(bookid);
    }

    public boolean isHavingCart(int vipid) throws SQLException{
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) from t_cart where vipid = ?";
        conn = JdbcUtil.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1,vipid);
        rs = ps.executeQuery();
        if(rs.next())
            flag = (1 == rs.getInt("count(*)"));
        JdbcUtil.close(conn,ps,null);
        return flag;
    }

    public boolean isHavingBookInCart(int bookid, int vipid) throws SQLException{
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) from t_cartInfo where id = ? and bookid = ?";
        conn = JdbcUtil.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1,vipid);
        ps.setInt(2,bookid);
        rs = ps.executeQuery();
        if(rs.next())
            flag = (1 == rs.getInt("count(*)"));
        JdbcUtil.close(conn,ps,rs);
        return flag;
    }

    public int addToCart(Book book,int vipid, long date){
        int flag = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Date = sdf.format(new Date(date));
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            if(!isHavingCart(vipid)){
                //该用户还没有购物车 新建购物车
                sql = "insert into t_cart values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,vipid);
                ps.setString(2,Date);
                ps.setString(3,Date);
                ps.executeUpdate();
                conn.commit();
            }
            //若该本书还没有被用户选进购物车,则更新数据
            if(!isHavingBookInCart(book.getBookid(),vipid)) {
                sql = "update t_cart set lastDate = ? where vipid = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, Date);
                ps.setInt(2, vipid);
                ps.executeUpdate();
                conn.commit();
                sql = "insert into t_cartInfo(id,bookid,bookName,price) values(?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,vipid);
                ps.setInt(2,book.getBookid());
                ps.setString(3,book.getBookName());
                ps.setBigDecimal(4,book.getPrice());
                flag = ps.executeUpdate();
                conn.commit();
            }
        } catch (SQLException e) {
            flag = -1;
            if(conn != null)
                conn.rollback();
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,null);
            return flag;
        }
    }

    public Cart findCartInfo(int vipid){
        Cart cart = new Cart();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_cart where vipid = ?";
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,vipid);
            rs = ps.executeQuery();
            while(rs.next()){
                cart.setVipid(rs.getInt("vipid"));
                cart.setBuildDate(rs.getString("buildDate"));
                cart.setLastDate(rs.getString("lastDate"));
            }
            List<Book> bookList = new ArrayList<Book>();
            sql = "select * from t_cartInfo where id = ? order by bookid";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,vipid);
            rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookid(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getBigDecimal("price"));
                bookList.add(book);
            }
            cart.setBookList(bookList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            return cart;
        }
    }

    public boolean deleteCartInfo(int vipid,int bookid){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update t_cart set lastDate = ? where vipid = ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Date = sdf.format(new Date());
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
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
        } catch (SQLException e) {
            if(conn != null)
                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            return flag;
        }
    }
}
