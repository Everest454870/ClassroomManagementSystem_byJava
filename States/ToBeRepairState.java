package States;

import Observers.*;
import Server.*;

public class ToBeRepairState implements State {
    transient roomService room;

    public ToBeRepairState(roomService room) {
        this.room = room;
    }

    public void roomReserving(char c, int num) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("需要修理，现在无法预约。");
    }

    public void roomCleaning(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("需要修理，现在无法清扫。");
    }

    public void roomOccupying(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("需要修理，现在无法使用。");
    }

    public void endOfOccupation(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室需要修理。");
    }

    public void userNeedsRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室需要修理。");
    }

    public void serverNeedsRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室需要修理。");
    }

    public void completeRepair(char c) {
        System.out.println("修理完成Repair completed（维护人员）。");
        room.setState(room.getToBeCleanState(), c);
    }

    public String toString() {
        return "此教室正等待维修…………";
    }
}