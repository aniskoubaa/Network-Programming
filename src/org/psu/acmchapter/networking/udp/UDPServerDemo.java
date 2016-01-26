package org.psu.acmchapter.networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerDemo {
    public static void main(String args[]) throws IOException { 
        
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tU D P   S E R V E R\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n\n\n");
        
        //declare the UDP server socket
        DatagramSocket serverSocket =null;
        int port;
        
        port = 5555;
        
        //create the UDP server socket with a specific port number
        serverSocket = new DatagramSocket(port);
        System.out.println("[UDP Server] Datagram Socket started on port "+ port);
        
        
        //prepare a the packet structure for received packets
        int dataLength = 100; //must be large enough otherwise large message will not be fully received
        byte[] receiveData = new byte[dataLength];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        
        while(true){
            //wait for incoming packets
            System.out.println("[UDP Server] Waiting for incoming messages on port "+port +" ...");
            serverSocket.receive(receivePacket);

            //Extract received packet data 
            InetAddress IPAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            String message = new String(receivePacket.getData());
            //we must trim the message to remove empty bytes. Observe the output without the trim function
            message = message.trim();
            System.out.println ("[UDP Server] The message {"+message +"}.\n\t\tThe message is received from host: " + IPAddress + " on port" + port);

            //send response back to the client host
            byte[] sendData  = new byte[1024];
            String responseMessage = "Thank you client, I received your message";
            sendData = responseMessage.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
            serverSocket.send(sendPacket); 
        }
        
    }
    
}
