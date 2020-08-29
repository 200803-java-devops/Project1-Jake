package com.revature;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpRequest;


public class Ports extends Thread 
{   ServerSocket socket;
    InetAddress host;

    public Ports(String ip, int port) throws IOException
    {   host = InetAddress.getByName(ip);
        this.socket = new ServerSocket(port,0,host);
        System.out.println("Socket created: " + port);
    }

    public void run()
    {   while (true)    
        {   int port2 = socket.getLocalPort();
            System.out.println("Socket " + port2 + " listening!!!!!");
            Socket connection;
            try
            {   connection = this.socket.accept();
                System.out.println("Connection accepted on " + connection.getLocalPort() + "%%%%%%%");
                RequestHandler request = new RequestHandler(connection);
                System.out.println("Starting a new port thread.");
                Response response = new Response(connection.getOutputStream());
                response.send(port2);
            }   catch (IOException e)
            {   e.printStackTrace();
            }
        }
    }
}