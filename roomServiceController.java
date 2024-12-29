import java.rmi.*;
import Observers.*;
import Servers.*;
import States.*;

public class roomServiceController {
    roomServiceRemote service;

    public roomServiceController(roomServiceRemote service) {
        this.service = service;
    }

    // ---------------------------------------------------------------
    // User 有权调用的函数

    public void reserve(char c, int num) {
        try {
            System.out.println("---------Classroom Reservation---------");
            String name;
            if (c == 'S')
                name = "Seminar room";
            else if (c == 'C')
                name = "Case room";
            else if (c == 'L')
                name = "Lecture room";
            else
                name = "";
            System.out.println("Reserve: " + name);
            System.out.println("Number of perple: " + num);
            service.roomReserving(c, num);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // public void callRepairment(char c, String s),可能需要加上报修情况描述
    public void callRepairmentByUser(char c, String s) {
        try {
            System.out.println("---------Calling Repairment---------");
            String name;
            if (c == 'S')
                name = "Seminar room";
            else if (c == 'C')
                name = "Case room";
            else if (c == 'L')
                name = "Lecture room";
            else
                name = "";
            System.out.println("Classroom: " + name);
            // 打印报修信息
            service.userNeedsRepair(c, s);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void finish(char c) {
        try {
            System.out.println("---------Occupation Finished---------");
            String name;
            if (c == 'S')
                name = "Seminar room";
            else if (c == 'C')
                name = "Case room";
            else if (c == 'L')
                name = "Lecture room";
            else
                name = "";
            System.out.println("Classroom: " + name);
            System.out.println("Occupation is end.");
            service.endOfOccupation(c);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void Stop() throws RemoteException {
        service.setIsStop(true);
    }

    // ---------------------------------------------------------------
    // Approver 有权调用的函数

    public void check() {
        try {
            if (service.get_S_num_of_people() <= service.get_S_num())
                service.roomOccupying('S');
            if (service.get_C_num_of_people() <= service.get_C_num())
                service.roomOccupying('C');
            if (service.get_L_num_of_people() <= service.get_L_num())
                service.roomOccupying('L');
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------
    // Staff 有权调用的函数

    public void Clean() {
        try {
            service.roomCleaning('S');
            service.roomCleaning('C');
            service.roomCleaning('L');
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // 有问题，但还没修改。修改时应该在roomService和roomService加上getSituation函数
    public void callRepairmentByStaff() {
        try {
            roomService room = (roomService) (service);
            if (room.S_situation != "")
                service.serverNeedsRepair('S');
            if (room.C_situation != "")
                service.serverNeedsRepair('C');
            if (room.L_situatuin != "")
                service.serverNeedsRepair('L');
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    // ---------------------------------------------------------------
    // Maintainer 有权调用的函数

    public void Repair() {
        try {
            if (service.get_S_situation() != "")
                service.completeRepair('S');
            if (service.get_C_situation() != "")
                service.completeRepair('C');
            if (service.get_L_situation() != "")
                service.completeRepair('L');
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
