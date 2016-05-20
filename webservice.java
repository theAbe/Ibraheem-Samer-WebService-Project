/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DB_Connection.DB_Connection;
import classes.Book;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author User
 */
@WebService(serviceName = "webservice")
public class webservice {
    protected Connection connection;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public String operation() {
        //TODO write your implementation code here:
        return "nana";
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "connectToDB")
    public String connectToDB(@WebParam(name = "DBname") String DBname,@WebParam(name = "username")String username,
            @WebParam(name = "password")String password) {
         connection= DB_Connection.getConnection(DBname,username,password);
         return "Connected to database";
    }
    
    @WebMethod(operationName = "getAllBooks")
    public String getAllBooks(@WebParam(name = "DBname") String DBname,@WebParam(name = "username")String username,
            @WebParam(name = "password")String password)  {
        try {
            connection= DB_Connection.getConnection(DBname,username,password);
            Statement stat = connection.createStatement();
            List<Book> list = new ArrayList<>();
            ResultSet res = stat.executeQuery("select * from book");
            while(res.next()){
                Book book = new Book();
                book.setBookID(res.getInt("bookID"));
                book.setBookName(res.getString("bookName"));
                book.setAuthor(res.getString("author"));
                book.setEmployeeID(res.getString("employeeID"));
                book.setSection(res.getString("section"));
                book.setState(res.getString("state"));
                list.add(book);
                
            }
            
            
            String rs ="";
            for(Book book:list){
                rs += book.toString()+"\n";
            }
            
            
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(webservice.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
    
    
     @WebMethod(operationName = "getAvailableBooks")
    public String getAvailableBooks(@WebParam(name = "DBname") String DBname,@WebParam(name = "username")String username,
            @WebParam(name = "password")String password)  {
        try {
            connection= DB_Connection.getConnection(DBname,username,password);
            Statement stat = connection.createStatement();
            List<Book> list = new ArrayList<>();
            ResultSet res = stat.executeQuery("select * from book where state=1");
            while(res.next()){
                Book book = new Book();
                book.setBookID(res.getInt("bookID"));
                book.setBookName(res.getString("bookName"));
                book.setAuthor(res.getString("author"));
                book.setEmployeeID(res.getString("employeeID"));
                book.setSection(res.getString("section"));
                book.setState(res.getString("state"));
                list.add(book);
                
            }
            
            
            String rs ="";
            for(Book book:list){
                rs += book.toString()+"\n";
            }
            
            
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(webservice.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
    
    @WebMethod(operationName = "getBorrowedBooks")
    public String getBorrowedBooks(@WebParam(name = "DBname") String DBname,@WebParam(name = "username")String username,
            @WebParam(name = "password")String password)  {
        try {
            connection= DB_Connection.getConnection(DBname,username,password);
            Statement stat = connection.createStatement();
            List<Book> list = new ArrayList<>();
            ResultSet res = stat.executeQuery("select * from book where state=0");
            while(res.next()){
                Book book = new Book();
                book.setBookID(res.getInt("bookID"));
                book.setBookName(res.getString("bookName"));
                book.setAuthor(res.getString("author"));
                book.setEmployeeID(res.getString("employeeID"));
                book.setSection(res.getString("section"));
                book.setState(res.getString("state"));
                list.add(book);
                
            }
            
            
            String rs ="";
            for(Book book:list){
                rs += book.toString()+"\n";
            }
            
            
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(webservice.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
    
    
    
    @WebMethod(operationName = "deleteBook")
    public String deleteBook(@WebParam(name = "DBname") String DBname,@WebParam(name = "username")String username,
            @WebParam(name = "password")String password,@WebParam(name = "bookID")int bookID)
            {
        try {
            connection= DB_Connection.getConnection(DBname,username,password);
            Statement stat = connection.createStatement();
            List<Book> list = new ArrayList<>();
            
            stat.executeUpdate("delete from book where bookID="+bookID);
            return "the book with "+bookID+" ID is deleted";
        } catch (SQLException ex) {
            Logger.getLogger(webservice.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }
    
    @WebMethod(operationName = "addBook")
    public String addBook(@WebParam(name = "DBname") String DBname,@WebParam(name = "username")String username,
            @WebParam(name = "password")String password,@WebParam(name = "bookID")String bookID
    ,@WebParam(name = "bookName")String bookName,@WebParam(name = "section")String section,
    @WebParam(name = "state")String state
    ,@WebParam(name = "auther")String auther,@WebParam(name = "employeeID")String employeeID)
            {
        try {
            connection= DB_Connection.getConnection(DBname,username,password);
            Statement stat = connection.createStatement();
            List<Book> list = new ArrayList<>();
            stat.executeUpdate("INSERT INTO `librarywebservice`.`book` "
                    + "(`bookID`, `bookName`, `section`, `state`, `author`, `employeeID`) "
                    + "VALUES ('"+bookID+"', '"+bookName+"', '"+section+"', '"+state+"', '"+auther+"', '"+employeeID+"'); ");
           
            
            
            return "new book id added";
        } catch (SQLException ex) {
            Logger.getLogger(webservice.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
            }
        
        @WebMethod(operationName = "returnBook")
    public String returnBook(@WebParam(name = "DBname") String DBname,@WebParam(name = "username")String username,
            @WebParam(name = "password")String password,@WebParam(name = "bookID")int bookID)
            {   
        try {
            connection= DB_Connection.getConnection(DBname,username,password);
            Statement stat = connection.createStatement();
            List<Book> list = new ArrayList<>();
            ResultSet res = stat.executeQuery("select clientID from reserved where bookID="+bookID);
            int clientID=0;
            while(res.next()){
                 clientID = res.getInt("clientID");
            }
            stat.executeUpdate("update book set state=1 where bookID="+bookID);
            stat.executeUpdate("delete from reserved where bookID="+bookID);
            stat.executeUpdate("update client set numberOfBooksBorrowed=numberOfBooksBorrowed-1 where clientID="+clientID);
            
            
            
            return "the book with "+bookID+" ID is returned";
        } catch (SQLException ex) {
            Logger.getLogger(webservice.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
        }
    
    
        @WebMethod(operationName = "borrowBook")
    public String borrowBook(@WebParam(name = "DBname") String DBname,@WebParam(name = "username")String username,
            @WebParam(name = "password")String password,@WebParam(name = "bookID")int bookID
             ,@WebParam(name = "clientID")int clientID,@WebParam(name = "employeeID")String employeeID,
             @WebParam(name = "fromDate")String fromDate,@WebParam(name = "toDate")String toDate)
            {   
        try {
            connection= DB_Connection.getConnection(DBname,username,password);
            Statement stat = connection.createStatement();
            List<Book> list = new ArrayList<>();
            ResultSet res = stat.executeQuery("select capacity,numberOfBooksBorrowed from client where clientID="+clientID);
            int capacity=0,numberOfBooksBorrowed=0;
            while(res.next()){
                 capacity = res.getInt("capacity");
                 numberOfBooksBorrowed = res.getInt("numberOfBooksBorrowed");
            }
            if(numberOfBooksBorrowed+1 > capacity)
                return "This client with ID: "+clientID+" has the borrowed the maximum number of books";
            
            stat.executeUpdate("INSERT INTO `librarywebservice`.`reserved` "
                    + "(`bookID`, `fromDate`, `toDate`, `employeeID`, `clientID`) "
                    + "VALUES ('"+bookID+"', '"+fromDate+"', '"+toDate+"', '"+employeeID+"', '"+clientID+"');");
            
//            stat.executeUpdate("insert into reserved "
//                    + "('bookID','fromDate','toDate','employeeID','clientID')"
//                    + "values ('"+bookID+"','"+fromDate+"','"+toDate+"','"+employeeID+"','"+clientID+"')");
            stat.executeUpdate("update book set state=0 where bookID="+bookID);
            stat.executeUpdate("update client set numberOfBooksBorrowed=numberOfBooksBorrowed+1 where clientID="+clientID);
            
            
            
            return "the book with "+bookID+" ID is borrowed";
        } catch (SQLException ex) {
            Logger.getLogger(webservice.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
        }
    
    
    
    
    
    
    
    
    }
    
    

