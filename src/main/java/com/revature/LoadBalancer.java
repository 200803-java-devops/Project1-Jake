package com.revature;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class LoadBalancer implements Runnable
{   Socket socket;
    int addPort;
    
    public LoadBalancer(Socket socket, int addPort)
    {   this.socket = socket;
        this.addPort = addPort;
    }
    
    
    @Override
    public void run()
    {   try
        {   processRequest();
        }   catch (Exception e)
        {   System.out.println(e);  }
    }

    private void processRequest() throws Exception
    {   InputStream is = socket.getInputStream();
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String requestLine = br.readLine();

        StringTokenizer tokens = new StringTokenizer(requestLine);
        tokens.nextToken();
        String fileName = tokens.nextToken();

        String statusLine = null;

        statusLine = "HTTP/1.0 301 Redirecting" + "\r\n";

        os.writeBytes(statusLine);
    
        String reply = "http://localhost:" + this.addPort + fileName;
        System.out.println("Redirect to " + reply);
        os.writeBytes(reply);
        os.writeBytes("\r\n");

        os.close();
        br.close();
        socket.close();
    }
}