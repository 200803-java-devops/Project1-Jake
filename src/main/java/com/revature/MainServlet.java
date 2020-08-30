package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

//A radically new and experimental branch.
//Still a little broken.
//I'm making servlets that act as different servers 
//for ideally the same app. Still working on loadbalancing
//calculations.

@WebServlet("/loadbalancer")
public class MainServlet extends HttpServlet
{   int addPort;
    Random rand = new Random();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {   ServletCounter counter = new ServletCounter();

        counter.getTheCounts();

        int temp1 = counter.getCount1();
        int temp2 = counter.getCount2();
        int temp3 = counter.getCount3();
        int[] array = new int[]{temp1,temp2,temp3};

        int num = array.length;     
        for (int i=0; i < num-1; i++)
        {   for (int j=0; j < num-i-1;j++)
            {   if (array[j] > array[j+1])
                {   int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        if (temp1 == array[0])
        {   counter.add1();
            response.sendRedirect("/LoadBalancer-1.0-SNAPSHOT/servlet1");
        }
        else if (temp2 == array[0])
        {   counter.add2();
            response.sendRedirect("/LoadBalancer-1.0-SNAPSHOT/servlet2");
        } 
        else if (temp3 == array[0])
        {   counter.add3();
            response.sendRedirect("/LoadBalancer-1.0-SNAPSHOT/servlet3");
        }
    }
}