package Observers;

import java.rmi.RemoteException;
import java.io.Serializable;

import Server.*;
import States.*;

public class MatainerDisplay implements MyObserver, MyDisplay, Serializable {
    State S_state_now;
    State C_state_now;
    State L_state_now;
    private roomServiceRemote roomservice;

    public MatainerDisplay(roomServiceRemote roomservice) throws RemoteException {
        this.roomservice = roomservice;
        roomservice.registerObserver("Matainner", this);
    }

    public void checkState() {
        try {
            S_state_now = roomservice.getState('S');
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            C_state_now = roomservice.getState('C');
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            L_state_now = roomservice.getState('L');
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void display() {
        System.out.println("---------当前所有教室的状态如下---------");
        System.out.println("Seminar room: " + S_state_now.toString());
        System.out.println("Case room: " + C_state_now.toString());
        System.out.println("Lecture room: " + L_state_now.toString());
    }
}
