package org.psu.acmchapter.networking.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServerDemo {
    
    public static void main(String []args){
        
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tT C P   S E R V E R\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n");
        
        //declare a TCP socket object and initialize it to null
        ServerSocket serverSocket=null;
        //create the port number
        int port = 1234;
        
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
                processClientRequest(clientSocket);
            } catch (IOException e) {
                //log exception and go on to next request.
            }
        }
        
    }
    
    public static void processClientRequest(Socket clientSocket) throws IOException {
        System.out.println("[TCP Server] processing the incoming request");
        try {
            PrintStream printStream = new PrintStream(clientSocket.getOutputStream());
            InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());
 
            //read the received message
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String message = null;
            message = bufferedReader.readLine();
            
            System.out.println("message received from client: \n\t"+message);
            

            //prepare the String. WARNING: add \n to make sure that the message is considered as one line
            String messageSend = "Hello World From TCP Server!\n";
            printStream.println(messageSend);
            printStream.close();
        }catch (Exception e){
            System.out.print("[TCP Server] The server cannot send the message");
        }
    }
    
    public static void processClientRequestHTML(Socket clientSocket) throws IOException {
        InputStream  input  = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();

        output.write(("HTTP/1.1 200 OK\n\n<html><body>" +
                "Singlethreaded Server: " +
                time +
                "<p>Salam wa rahmatou Allah</p></body></html>").getBytes());
        output.close();
        input.close();
        System.out.println("Request processed: " + time);
    }
    
}
