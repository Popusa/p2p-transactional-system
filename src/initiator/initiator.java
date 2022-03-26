/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package initiator;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrator
 */
public class initiator implements Runnable {
    Socket cSocket;
    static initiator_gui i_gui;
    public initiator(Socket cSocket){
        this.cSocket = cSocket;
    }
    @Override
    public void run(){
        try {
            ObjectInputStream in = new ObjectInputStream(cSocket.getInputStream());
            try {
                //attempt to read c3's revised payload
                task t = (task)in.readObject();
                //add the revised payload to the text area
                i_gui.append_to_gui(t.getTask_id() + " " + t.getPayload() + " " + t.isFinished() + " ", 2);
                System.out.println(t.getPayload());
                //send revised payload to gui for display
                i_gui.send_to_gui(t);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(initiator.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(initiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) throws IOException {
        i_gui = new initiator_gui();
        i_gui.setVisible(true);
        ServerSocket sSocket = new ServerSocket(3000);
        while (true){
            Socket S = sSocket.accept();
            Thread Client = new Thread(new initiator(S));
            Client.start();
        }
    }
}
