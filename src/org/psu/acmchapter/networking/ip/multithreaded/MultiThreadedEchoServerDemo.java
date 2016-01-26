package org.psu.acmchapter.networking.ip.multithreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class MultiThreadedEchoServerDemo {
    public static void main(String []args){
        
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tE C H O   S E R V E R\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n");
        
        //declare a TCP socket object and initialize it to null
        ServerSocket serverSocket=null;
        //create the port number
        int port = 3030;
        
        try {
            //create the TCP socket server
            serverSocket = new ServerSocket(port);
            System.out.println("ECHO server created on port = "+port);
        } catch (IOException ex) {
            //will be executed when the server cannot be created
            System.out.println("Error: The server with port="+port+" cannot be created");
        }
        
        //starts an endless loop
        while(true){
            Socket clientSocket = null;
            try {
                //start listening to incoming client request (blocking function)
                System.out.println("[ECHO Server] waiting for the incoming request ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Error: cannot accept client request. Exit program");
                return;
            }
            try {
                //create a new thread for each incoming message
                new Thread(new MessageProcessorRunnable(clientSocket, "Multithreaded Server")).start();
            } catch (Exception e) {
                //log exception and go on to next request.
            }
        }
        
    }
    
 }
