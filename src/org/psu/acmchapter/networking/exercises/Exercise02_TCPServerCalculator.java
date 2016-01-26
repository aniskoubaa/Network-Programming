/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.psu.acmchapter.networking.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author akoubaa
 */
public class Exercise02_TCPServerCalculator {
    public static void main(String []args){
        
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tT C P   S E R V E R     C A L C U L A T O R\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */\n");
        
        //declare a TCP socket object and initialize it to null
        ServerSocket serverSocket=null;
        //create the port number
        int port = 2225;
        
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
                System.out.println("[Calculator Server] waiting for the incoming request ...");
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
        System.out.println("[Calculator Server] processing the incoming request ...");
        try {
            /**
            **  Task1 : create PrintStream and InputStreamReader objects
            **/



            /**
            **  Task2 : read the received message
            **/
            String message = null;

            /**
            **  Task3 : parse the received message: extract x and y from the string
            **/
            System.out.printf("[Calculator Server]message received:%s \n\n",message);
            double x = 0;
            double y = 0;
            System.out.printf("x= %.2f, y=%.2f \n\t",x,y);
            
            Double sum = x+y;

            /**
            **  Task4 : create and send the response back to the client
            **/
            
            
            System.out.printf("[TCP Server] Sum=%.2f sent back\n\n", sum);
            printStream.close();
        }catch (Exception e){
            System.out.print("[TCP Server] The server cannot send the message\n\n");
        }
    }
    
}
