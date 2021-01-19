/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Wojtek
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML   //KLIENT
    private void handleButtonAction(ActionEvent event) {
        try {
            String serverHost = "127.0.0.1"; 
            int serverPort = 50000; 
            Socket socket = new Socket(serverHost, serverPort); 
            OutputStream sockOut = socket.getOutputStream(); 
            InputStream sockIn = socket.getInputStream(); 
            BufferedReader in = new BufferedReader(new InputStreamReader(sockIn));
            PrintWriter out = new PrintWriter(sockOut, true); 
            
            out.println("DATE"); 
            String line = in.readLine();
            label.setText(line);
            
            sockOut.close(); 
            sockIn.close(); 
            socket.close(); 
        } catch (UnknownHostException exc) {
            System.out.println("Nieznany host");
        } catch (SocketException exc) {
            System.out.println("Wyjątki związane z komunikacją przez gniazda ");
        } catch (IOException exc) {
            System.out.println("inne wyjątki we/wy");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    }     
}
