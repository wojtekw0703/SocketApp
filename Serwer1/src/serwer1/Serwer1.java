/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serwer1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Wojtek
 */
public class Serwer1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    // SERWER
    public static void main(String[] args) throws IOException {
      
       String host = "127.0.0.1"; // nazwa hosta 
        int port = 50000; // numer portu 
        try {
            InetSocketAddress isa = new InetSocketAddress(host, port);
            ServerSocket serverSock = new ServerSocket(); 
            serverSock.bind(isa); 
            boolean serverIsRunning = true;
            while (serverIsRunning) { 
                Socket conn = serverSock.accept();
                OutputStream sockOut = conn.getOutputStream();
                InputStream sockIn = conn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(sockIn));
                PrintWriter out = new PrintWriter(sockOut, true); 
                String line = in.readLine();
                
                if ("DATE".equals(line)) {
                      Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    out.println(formatter.format(date));
                }
                conn.close();
            }
            serverSock.close(); // zamknięcie gniazda serwera
        } catch (IOException e) {
            System.out.println("Błąd IO");
        }
    }
    
}
