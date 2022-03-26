/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborator2;

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
public class collaborator2 implements Runnable{
    Socket cSocket;
    static c2_gui c2_gui;
    public collaborator2(Socket cSocket){
        this.cSocket = cSocket;
    }
    @Override
    public void run(){
        try {
            ObjectInputStream in = new ObjectInputStream(cSocket.getInputStream());
            try {
                //attempting to read task object from c1
                task t = (task)in.readObject();
                //appending task to gui to show c1's revised payload
                t.setTask_id(task_counter);
                c2_gui.append_to_gui(t.getTask_id() + " " + t.getPayload() + " " + t.isFinished() + " ",1);
                System.out.println(t.getPayload());
                //sending task object to gui to revise payload and send to c3
                c2_gui.send_to_gui(t);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(collaborator2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(collaborator2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            public static void main(String[] args) throws IOException {
        c2_gui = new c2_gui();
        c2_gui.setVisible(true);
        ServerSocket sSocket = new ServerSocket(5000);
        while (true){
            Socket S = sSocket.accept();
            Thread Client = new Thread(new collaborator2(S));
            Client.start();
        }
    }
}
