package States;

import Observers.*;
import Server.*;

public class CleanNotReservedState implements State {
    transient roomService room;

    public CleanNotReservedState(roomService room) {
        this.room = room;
    }

    public void roomReserving(char c, int num) {
        System.out.println("补充：申请人申请使用。");
        room.setState(room.getCleanReservedState(), c);
        if (c == 'S')
            room.S_num_of_people = num;
        else if (c == 'C')
            room.C_num_of_people = num;
        else if (c == 'L')
            room.L_num_of_people = num;
    }

    public void roomCleaning(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.print("Lecture room ");
        System.out.println("已经清扫干净，不需要再清扫。");
    }

    public void roomOccupying(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.print("Lecture room ");
        System.out.println("此教室尚未被预约。");
    }

    public void endOfOccupation(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.print("Lecture room ");
        System.out.println("此教室尚未被预约。");
    }

    public void userNeedsRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.print("Lecture room ");
        System.out.println("此教室尚未被预约。");
    }

    public void serverNeedsRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.print("Lecture room ");
        System.out.println("此教室尚未被预约。");
    }

    public void completeRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.print("Lecture room ");
        System.out.println("此教室尚未被预约。");
    }

    public String toString() {
        return "此教室暂时无人预约。";
    }
}
