package com.revature;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//Establish sockets into ports and run them so they keep listening
public final class Server 
{       public static void run() throws Exception
    {   int port = 8080;
        String ip = "127.0.0.1";
        InetAddress inet = InetAddress.getByName(ip);
        System.out.println("Port: " + port);
        ServerSocket socket = new ServerSocket(port,0,inet);
        System.out.println("Socket created.");

        int addPort = 8080;

        Ports sp1 = new Ports(ip, 8081);
        Ports sp2 = new Ports(ip, 8082);
        Ports sp3 = new Ports(ip, 8083);

        sp1.start();
        sp2.start();
        sp3.start();

        while (true)
        {   if (addPort == 8083)
            {   addPort = 8080; }
        
            addPort += 1;

            Socket connection = socket.accept();
            System.out.println("Connection accepted.");
            LoadBalancer request = new LoadBalancer(connection, addPort);
            Thread thread = new Thread(request);
            thread.start();
            thread.sleep(100);
            socket.close();
            System.out.println("Thread has started");
        }
    }
    
}