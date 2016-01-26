package org.psu.acmchapter.networking.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServer {
    public static void main(String []args){
        
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tT C P   S E R V E R\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n");
        
        //declare a TCP socket object and initialize it to null
        ServerSocket serverSocket=null;
        //create the port number
        int port = 8080;
        
        try {
            //create the TCP socket server
            serverSocket = new ServerSocket(port);
            System.out.println("TCP server created on port = "+port);
        } catch (IOException ex) {
            //will be executed when the server cannot be created
            System.out.println("Error: The server with port="+port+" cannot be created");
        }
        
        //starts an endless loop
        while(true){
            Socket clientSocket = null;
            try {
                //start listening to incoming client request (blocking function)
                System.out.println("[TCP Server] waiting for the incoming request ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Error: cannot accept client request. Exit program");
                return;
            }
            try {
                processClientRequestHTML(clientSocket);
            } catch (IOException e) {
                //log exception and go on to next request.
            }
        }
        
    }
    
       
    public static void processClientRequestHTML(Socket clientSocket) throws IOException {
        InputStream  input  = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();

        String htmlMessage = "HTTP/1.1 200 OK\n\n<html>"
                + "<body>"
                + "<h1> Java Programming Workshop Series </h1>"
                + "<h2> Networking Programming with Java</h2>"
                + "<h3>Presented by: Dr. Anis Koubaa</h3>"
                + "<p> This is an example of a Web server with Java </p>"
                + "<p>The current server time is <b>"+time+"</b></p>"
                + "</body>"
                + "</html>";
        
        output.write(htmlMessage.getBytes());
        output.close();
        input.close();
        System.out.println("Request processed: " + time);
    }
    
}
