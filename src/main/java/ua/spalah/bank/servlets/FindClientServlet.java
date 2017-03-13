package ua.spalah.bank.servlets;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.ClientService;

import javax.net.ssl.SSLSessionBindingEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by root on 02.03.2017.
 */
@WebServlet("/main")
public class FindClientServlet extends HttpServlet {
    //private ClientService clientService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getSession().getServletContext();
        ClientService clientService = (ClientService) context.getAttribute("clientService");

        String clientName = req.getParameter("clientname");
        Client client = new Client();
        try {
            client = clientService.findClientByName(clientName);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
//        if (client == null) {
//            req.setAttribute("message", "Client "+clientName+" not found");
//        } else {
//            req.setAttribute("clientname", client.getClientName());
//            req.setAttribute("clientcity", client.getCity());
//            req.setAttribute("client",client);
//
//        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
