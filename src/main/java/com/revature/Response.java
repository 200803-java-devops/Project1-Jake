package com.revature;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Response extends HttpServlet
{   private static OutputStream output;
    private static String status;
    private static Map<String, String> headers;
    private static byte[] body;
    int port2;

    public Response(OutputStream output, int port2) {
        this.output = output;
        this.headers = new HashMap<String, String>();
        this.port2 = port2;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public void setBody(byte[] body) {
        this.body = body;
        headers.put("Content-Length", String.valueOf(body.length));
    }

    public static void send(int port2) 
    {   PrintWriter writer = new PrintWriter(output, true);
        writer.println("HTTP/1.1 " + status);
        headers.forEach((key, value) -> writer.println(key + ": " + value));
        writer.println();
        if (body != null) {
            try {
                output.write(body);
                writer.println();
            } catch (IOException e) {
                System.err.println("Error writing response body");
            }
        }
        writer.println("Welcome to Port: " + port2 + "!");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {   PrintWriter writer = new PrintWriter(output, true);
        writer.println("HTTP/1.1 " + status);
        headers.forEach((key, value) -> writer.println(key + ": " + value));
        writer.println();
        if (body != null) {
            try {
                output.write(body);
                writer.println();
            } catch (IOException e) {
                System.err.println("Error writing response body");
            }
        }
        writer.println("Welcome to Port: " + port2 + "!");
        writer.flush();
        writer.close();
        
    }
    
    @Override
    public String toString() 
    {   return "Response [headers=" + headers + ", output=" + output + ", status="
            + status + "]";
    }
}