package States;

import Observers.*;
import Server.*;

public class CleanReservedState implements State {
    transient roomService room;

    public CleanReservedState(roomService room) {
        this.room = room;
    }

    public void roomReserving(char c, int num) {
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
            System.out.println("Lecture room ");
        System.out.println("已经清扫干净，不需要再清扫。");
    }

    public void roomOccupying(char c) {
        System.out.println("教室占用Occupancy（审批人审批后）。");
        room.setState(room.getOccupiedState(), c);
    }

    public void endOfOccupation(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室还没被使用。");
    }

    public void userNeedsRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室还没被使用。");
    }

    public void serverNeedsRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室还没被使用。");
    }

    public void completeRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室还没被使用。");
    }

    public String toString() {
        return "此教室已被预约，但审核尚未通过。";
    }
}
