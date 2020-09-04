package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;


/*Load Balancing App Jake Hernandez 9/4/2020
*This is a single main servlet that will call on other classes
*to check how many requests each server has and then redirect a 
*client to appropriate server that has the least number of requests
*currently. This is a least connections type of load balancer.
*This load balancer will redirect to the same app run on different
*servers. Redirecting will happen on this servlet.
*/

@WebServlet("/loadbalancer")
public class MainServlet extends HttpServlet
{   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {   new Time();
        Loadbalancer load = new Loadbalancer();
        int route = load.router();

        if (route == 1)
        {   response.sendRedirect("http://localhost:8081");
        }
        else if (route == 2)
        {   response.sendRedirect("http://localhost:8082");
        } 
        else if (route == 3)
        {   response.sendRedirect("http://localhost:8083");
        }
    }
}