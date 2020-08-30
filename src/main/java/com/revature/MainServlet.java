package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Everything starts here
//Basically I'm making sockets on the Server class to fit them with a connection and 
//into various ports to listen in on client requests. Currently, accessing the ports
//directly through local host gives signals in the terminal, but nothing is happening
//on the client's browser. Also, the redirect function is broken, so I'll try to fix 
//that later.

@WebServlet("/loadbalancer")
public class MainServlet extends HttpServlet
{   int addPort;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {   response.getWriter().println("Hello World!");
        response.getWriter().println(" Redirecting");
        response.sendRedirect("localhost:8082");
    }
    
    {   Server server = new Server();
        try
        {   System.out.println("server ready.");
            server.run();
            System.out.print("server executing.");
        }   catch (Exception e)
        {   e.printStackTrace();    }
    }
}