package com.scnu.book.dao;

import com.scnu.book.entity.Book;
import com.scnu.book.entity.PageBean;
import com.scnu.utils.JdbcUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public BookDao() {
    }
    public boolean addBook(Book book){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql =
                "insert into t_book" +
                "(bookName,author,press,publishDate,isbn,bookInfo,bookImg ,price)" +
                "values(?,?,?,?,?,?,?,?)";
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,book.getBookName());
            ps.setString(2,book.getAuthor());
            ps.setString(3,book.getPress());
            ps.setString(4,book.getPublishDate());
            ps.setString(5,book.getIsbn());
            ps.setString(6,book.getBookInfo());
            ps.setString(7,book.getBookImg());
            ps.setBigDecimal(8,book.getPrice());
            String endSql = ps.toString();
            int index = endSql.indexOf("insert");
            System.out.println(endSql.substring(index) + ";");
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

    public PageBean<Book> findByPage(int pc){
        int _ps = 2;  //定义一个页面展示多少个数据
        int all = 0;  //页面总页数
        String sql = "select count(*) from t_book";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
                all = rs.getInt("count(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
        }
        PageBean<Book> pageBean = new PageBean<Book>();
        List<Book> list = new ArrayList<Book>();
        String sql1 = "select * from t_book limit ?,?";
        pageBean.setAll(all);
        pageBean.setPc(pc);
        pageBean.setPs(_ps);
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql1);
            ps.setInt(1,(pc - 1) * _ps);
            ps.setInt(2,_ps);
            rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookid(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookName"));
                book.setAuthor(rs.getString("author"));
                book.setPress(rs.getString("press"));
                book.setPublishDate(rs.getString("publishDate"));
                book.setIsbn(rs.getString("isbn"));
                book.setBookInfo(rs.getString("bookInfo"));
                book.setBookImg(rs.getString("bookImg"));
                book.setPrice(rs.getBigDecimal("price"));
                list.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            pageBean.setBeanList(list);
            return pageBean;
        }
    }

    public boolean updateBook(int bookid, BigDecimal price,String bookInfo){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update t_book set price = ?,bookInfo = ? where bookid = ?";
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1,price);
            ps.setString(2,bookInfo);
            ps.setInt(3,bookid);
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

    public boolean deleteBook(int bookid){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from t_book where bookid = ?";
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bookid);
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

    public Book findBook(int bookid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_book where bookid = ?";
        Book book = new Book();
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bookid);
            rs = ps.executeQuery();
            while(rs.next()){
                book.setBookid(bookid);
                book.setBookName(rs.getString("bookName"));
                book.setAuthor(rs.getString("author"));
                book.setPress(rs.getString("press"));
                book.setPublishDate(rs.getString("publishDate"));
                book.setIsbn(rs.getString("isbn"));
                book.setBookInfo(rs.getString("bookInfo"));
                book.setBookImg(rs.getString("bookImg"));
                book.setPrice(rs.getBigDecimal("price"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            return book;
        }
    }
}
