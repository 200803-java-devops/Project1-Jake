package com.revature;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class RequestHandler extends Thread 
{   Socket socket;

    public RequestHandler(Socket socket)
    {   this.socket = socket;
    }

    public void run()
    {   InputStream is;

        // Write incoming socket's input and output stream to a file
        try
        {   is = socket.getInputStream();
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String requestLine = br.readLine();
            StringTokenizer tokens = new StringTokenizer(requestLine);
            tokens.nextToken();
            String fileName = tokens.nextToken();

            //add . to file name and check if it exists
            fileName = "." + fileName;
            FileInputStream fis = null;
            boolean fileExists = true;
            try
            {   fis = new FileInputStream(fileName);
            }   catch (FileNotFoundException e)
            {   fileExists = false; }

            System.out.println("Displaying Header Line.");
            System.out.println(requestLine);
            String headerLine = null;
            while ((headerLine = br.readLine()).length() != 0)
            {   System.out.println(headerLine); }

            //Write bytes to output stream
            String statusLine = null;
            String contextTypeLine = null;
            String entityBody = null;
            if (fileExists)
            {   statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
                contextTypeLine = "Context-Type:text/html" + "\r\n";
                entityBody = "<HTML>" + "<HEAD><TITLE>Not Found</TITLE></HEAD>" + "<BODY>Not Found</BODY>";
            }

            os.writeBytes(statusLine);

            os.writeBytes(contextTypeLine+"\r\n");
            System.out.println(contextTypeLine);
            os.writeBytes("\r\n");


            if(fileExists)
            {   try
                {   sendBytes(fis, os);
                }   catch(Exception e)
                {   e.printStackTrace();    }
            fis.close();
            }   else
            {   os.writeBytes(entityBody);
            }

            os.close();
            br.close();
            socket.close();
            System.out.println("Thread Done");

        }   catch (IOException e)
        {   e.printStackTrace();    }
    }

    //Read the bytes
    private static void sendBytes(FileInputStream fis, OutputStream os) throws Exception
    {   byte[] buffer = new byte[1024];
        int bytes = 0;

        while ((bytes = fis.read(buffer)) != -1)
        {   os.write(buffer, 0, bytes); }
    }
}