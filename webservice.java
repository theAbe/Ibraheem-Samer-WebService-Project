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
     * This is a sample web service operation
     */
    @WebMethod(operationName = "connectToDB")
    public void connectToDB(@WebParam(name = "DBname") String DBname,@WebParam(name = "username")String username,
            @WebParam(name = "password")String password) {
         connection= DB_Connection.getConnection(DBname,username,password);
        
    }
    
    @WebMethod(operationName = "getAllBooks")
    public List<Book> getAllBooks() throws SQLException {
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
        
        
        return list;
    }
    
    
    
    
}
