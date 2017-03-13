package ua.spalah.bank.servlets;

import ua.spalah.bank.dao.AccountDao;
import ua.spalah.bank.dao.ClientDao;
import ua.spalah.bank.dao.impl.AccountDaoImpl;
import ua.spalah.bank.dao.impl.ClientDaoImpl;
import ua.spalah.bank.exceptions.DataBaseException;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountServiceImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by root on 02.03.2017.
 */
public class AbstractServlet extends HttpServlet {
//
//    public static Connection connection;
//    protected ClientDao clientDao = new ClientDaoImpl();
//    protected AccountDao accountDao = new AccountDaoImpl();
//    protected ClientService clientService = new ClientServiceImpl(clientDao, accountDao);
//    protected AccountService accountService = new AccountServiceImpl(clientDao, accountDao);
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        try {
//            Class.forName("org.h2.Driver");
//            String url = "jdbc:h2:tcp://localhost/~/test";
//            Connection connection = DriverManager.getConnection(url, "sa", "");
//
//            this.connection = connection;
//        } catch (SQLException e) {
//            throw new DataBaseException(e);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
