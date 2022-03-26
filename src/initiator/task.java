/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package initiator;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class task implements Serializable{
    private static int task_id;
    private String payload;
    private boolean finished = false;

    public task(int task_id, String payload) {
        this.task_id = task_id;
        this.payload = payload;
        this.finished = false;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    
}
