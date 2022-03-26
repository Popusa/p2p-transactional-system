/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborator3;

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
public class collaborator3 implements Runnable{
    Socket cSocket;
    static c3_gui c3_gui;
    public collaborator3(Socket cSocket){
        this.cSocket = cSocket;
    }
        @Override
        public void run(){
        try {
            ObjectInputStream in = new ObjectInputStream(cSocket.getInputStream());
            try {
                //atempting to read task object from collab 2
                task t = (task)in.readObject();
                t.setTask_id(task_counter);
                //appending to gui to showcase collab 2's revised payload
                c3_gui.append_to_gui(t.getTask_id() + " " + t.getPayload() + " " + t.isFinished() + " ",1);
                System.out.println(t.getPayload());
                //sending task object to gui to revise payload and send back to initiator
                c3_gui.send_to_gui(t);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(collaborator3.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(collaborator3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public static void main(String[] args) throws IOException {
        c3_gui = new c3_gui();
        c3_gui.setVisible(true);
        ServerSocket sSocket = new ServerSocket(6000);
        while (true){
            Socket S = sSocket.accept();
            Thread Client = new Thread(new collaborator3(S));
            Client.start();
        }
    }
}
