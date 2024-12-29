package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Observers.*;
import States.*;

public interface roomServiceRemote extends Remote {

    public void roomReserving(char c, int num) throws RemoteException;

    public void roomCleaning(char c) throws RemoteException;

    public void roomOccupying(char c) throws RemoteException;

    public void endOfOccupation(char c) throws RemoteException;

    public void userNeedsRepair(char c, String s) throws RemoteException;

    public void serverNeedsRepair(char c) throws RemoteException;

    public void completeRepair(char c) throws RemoteException;

    public String printState() throws RemoteException;

    public State getState(char c) throws RemoteException;

    public boolean getIsStop() throws RemoteException;

    public void registerObserver(String string, MyObserver o) throws RemoteException;

    public int get_S_num_of_people() throws RemoteException;

    public int get_C_num_of_people() throws RemoteException;

    public int get_L_num_of_people() throws RemoteException;

    public int get_S_num() throws RemoteException;

    public int get_C_num() throws RemoteException;

    public int get_L_num() throws RemoteException;

    public State getCleanNoReservedtState() throws RemoteException;

    public State getCleanReservedState() throws RemoteException;

    public State getOccupiedState() throws RemoteException;

    public State getToBeCleanState() throws RemoteException;

    public State getToBeRepairState() throws RemoteException;

    public void setIsStop(boolean input) throws RemoteException;

    public String get_S_situation() throws RemoteException;

    public String get_C_situation() throws RemoteException;

    public String get_L_situation() throws RemoteException;

}
