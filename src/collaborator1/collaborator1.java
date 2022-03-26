/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborator1;

import initiator.initiator_gui;
import static initiator.initiator_gui.task_counter;
import initiator.task;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class collaborator1 implements Runnable{
        Socket cSocket;
        static c1_gui c1_gui;
    public collaborator1(Socket cSocket){
        this.cSocket = cSocket;
    }
        @Override
    public void run(){
        try {
            ObjectInputStream in = new ObjectInputStream(cSocket.getInputStream());
            try {
                //attempts to read initiator task object
                task t = (task)in.readObject();
                //adds task object to gui
                t.setTask_id(task_counter);
                c1_gui.append_to_gui(t.getTask_id() + " " + t.getPayload() + " " + t.isFinished() + " ",1);
                System.out.println(t.getPayload());
                //sends task object to gui for processing and sending to collab 2
                c1_gui.send_to_gui(t);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(collaborator1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(collaborator1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public static void main(String[] args) throws IOException {
        c1_gui = new c1_gui();
        c1_gui.setVisible(true);
        ServerSocket sSocket = new ServerSocket(4000);
        while (true){
            Socket S = sSocket.accept();
            Thread Client = new Thread(new collaborator1(S));
            Client.start();
        }
    }
}
