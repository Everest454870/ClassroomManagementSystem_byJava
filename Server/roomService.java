package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Set;

import Observers.*;
import States.*;

public class roomService extends UnicastRemoteObject implements roomServiceRemote, Subject {
    State clean_not_reserved;
    State clean_reserved;
    State occupied;
    State to_be_repair;
    State to_be_clean;

    State S_state;
    State C_state;
    State L_state;
    static int S_num = 20;
    static int C_num = 75;
    static int L_num = 150;
    String location;// 服务器IP

    public int S_num_of_people;
    int C_num_of_people;
    int L_num_of_people;
    String S_situation;
    String C_situation;
    String L_situatuin;

    boolean isStop;

    private HashMap<String, MyObserver> observers;

    public roomService(String location) throws RemoteException {
        clean_not_reserved = new CleanNotReservedState(this);
        clean_reserved = new CleanReservedState(this);
        occupied = new OccupiedState(this);
        to_be_repair = new ToBeRepairState(this);
        to_be_clean = new ToBeCleanState(this);

        observers = new HashMap<>();

        S_state = clean_not_reserved;
        C_state = clean_not_reserved;
        L_state = clean_not_reserved;
        this.location = location;
        S_num_of_people = 0;
        C_num_of_people = 0;
        L_num_of_people = 0;
        S_situation = "";
        C_situation = "";
        L_situatuin = "";
        isStop = false;
    }

    // 前面能否加上"public"？
    public void setState(State state, char c) {
        if (c == 'S')
            this.S_state = state;
        else if (c == 'C')
            this.C_state = state;
        else if (c == 'L')
            this.L_state = state;
    }

    // 这个函数是否要在 roomServiceRemote接口中声明呢？
    public State getState(char c) {
        if (c == 'S')
            return S_state;
        else if (c == 'C')
            return C_state;
        else if (c == 'L')
            return L_state;
        else
            return null;
    }

    public void setIsStop(boolean input) {
        this.isStop = input;
    }

    public boolean getIsStop() {
        return this.isStop;
    }

    // ---------------------------------------------------------------------
    // 此部分实现 state状态接口中声明的函数。

    public void roomReserving(char c, int num) {
        if (c == 'S')
            S_state.roomReserving(c, num);
        else if (c == 'C')
            C_state.roomReserving(c, num);
        else if (c == 'L')
            L_state.roomReserving(c, num);
    }

    public void roomCleaning(char c) {
        if (c == 'S')
            S_state.roomCleaning(c);
        else if (c == 'C')
            C_state.roomCleaning(c);
        else if (c == 'L')
            L_state.roomCleaning(c);
    }

    public void roomOccupying(char c) {
        if (c == 'S')
            S_state.roomOccupying(c);
        else if (c == 'C')
            C_state.roomOccupying(c);
        else if (c == 'L')
            L_state.roomOccupying(c);
    }

    public void endOfOccupation(char c) {
        if (c == 'S')
            S_state.endOfOccupation(c);
        else if (c == 'C')
            C_state.endOfOccupation(c);
        else if (c == 'L')
            L_state.endOfOccupation(c);
    }

    public void userNeedsRepair(char c, String s) {
        if (c == 'S') {
            S_state.userNeedsRepair(c);
            S_situation = s;
        } else if (c == 'C') {
            C_state.userNeedsRepair(c);
            C_situation = s;
        } else if (c == 'L') {
            L_state.userNeedsRepair(c);
            L_situatuin = s;
        }
    }

    public void serverNeedsRepair(char c) {
        if (c == 'S')
            S_state.serverNeedsRepair(c);
        else if (c == 'C')
            C_state.serverNeedsRepair(c);
        else if (c == 'L')
            L_state.serverNeedsRepair(c);
    }

    public void completeRepair(char c) {
        if (c == 'S') {
            S_state.completeRepair(c);
            System.out.println("已成功修复 Seminar room “" + S_situation + "”");
        } else if (c == 'C') {
            C_state.completeRepair(c);
            System.out.println("已成功修复 Case room “" + C_situation + "”");
        } else if (c == 'L') {
            L_state.completeRepair(c);
            System.out.println("已成功修复 Lecture room “" + L_situatuin + "”");
        }
    }

    // -----------------------------------------------------------------------
    // 此部分实现不同state的状态获取函数

    public State getCleanNoReservedtState() {
        return clean_not_reserved;
    }

    public State getCleanReservedState() {
        return clean_reserved;
    }

    public State getOccupiedState() {
        return occupied;
    }

    public State getToBeCleanState() {
        return to_be_clean;
    }

    public State getToBeRepairState() {
        return to_be_repair;
    }

    // ------------------------------------------------------------------------
    // 此部分实现toString()函数，之后会改为观察者模式

    public String toString() {
        StringBuffer condition = new StringBuffer();
        condition.append("当前所有教室的状态为：\n");
        condition.append("Seminar room: " + S_state.toString());
        condition.append("Case room: " + C_state.toString());
        condition.append("Lecture room: " + L_state.toString());
        return condition.toString();
    }

    public String printState() {
        return this.toString();
    }

    public void registerObserver(String key, MyObserver o) {
        observers.put(key, o);
    }

    public void removeObserver(String key_to_remove) {
        observers.remove(key_to_remove);
    }

    public void notifyObservers() {
        Set<String> keys = observers.keySet();
        for (String key : keys) {
            MyObserver o = observers.get(key);
            o.checkState();
        }
    }

    public int get_S_num_of_people() {
        return S_num_of_people;
    }

    public int get_C_num_of_people() {
        return C_num_of_people;
    }

    public int get_L_num_of_people() {
        return L_num_of_people;
    }

    public int get_S_num() {
        return S_num;
    }

    public int get_C_num() {
        return C_num;
    }

    public int get_L_num() {
        return L_num;
    }

    public String get_S_situation() {
        return S_situation;
    }

    public String get_C_situation() {
        return C_situation;
    }

    public String get_L_situation() {
        return L_situatuin;
    }
}
