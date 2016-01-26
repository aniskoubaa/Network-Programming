
package org.psu.acmchapter.networking.ip.multithreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.psu.acmchapter.networking.ip.multithreaded.SingleThreadEchoServerDemo.processingDelay;


public class MessageProcessorRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public MessageProcessorRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            PrintStream printStream = new PrintStream(output);
            InputStreamReader inputStream = new InputStreamReader(input);
            long time = System.currentTimeMillis();
            
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String message = null;
            message = bufferedReader.readLine();
            
            System.out.println("message received from client: \n\t"+message);
            processingDelay(1000);
            System.out.println("Send back the same message "+message);  
            

            output.write(message.getBytes());
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
    public static void processingDelay(int msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException ex) {
            
        }
    }
}
