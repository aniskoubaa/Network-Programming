package org.psu.acmchapter.networking.ip.multithreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadEchoServerDemo {
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
                processClientRequest(clientSocket);
            } catch (IOException e) {
                //log exception and go on to next request.
            }
        }
        
    }
    
    public static void processClientRequest(Socket clientSocket) throws IOException {
        System.out.println("[ECHO Server] processing the incoming request");
        try {
            PrintStream printStream = new PrintStream(clientSocket.getOutputStream());
            InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());
 
            //read the received message
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String message = null;
            message = bufferedReader.readLine();
            
            System.out.println("message received from client: \n\t"+message);
            processingDelay(1000);
            System.out.println("Send back the same message "+message);       
            printStream.println(message+"\n");
            printStream.close();
        }catch (Exception e){
            System.out.print("[ECHO Server] The server cannot send the message");
        }
    }
    
    public static void processingDelay(int msec) throws InterruptedException{
        Thread.sleep(msec);
    }
    
}
