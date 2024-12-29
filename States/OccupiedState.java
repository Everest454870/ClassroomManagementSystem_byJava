package States;

import Observers.*;
import Server.*;

public class OccupiedState implements State {
    transient roomService room;

    public OccupiedState(roomService room) {
        this.room = room;
    }

    public void roomReserving(char c, int num) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("正在被占用，无法预约。");
    }

    public void roomCleaning(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("正在被占用，不需要清洁。");
    }

    public void roomOccupying(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室正在被占用。");
    }

    public void endOfOccupation(char c) {
        if (c == 'S') {
            System.out.print("Seminar room ");
            room.S_num_of_people = 0;
        } else if (c == 'C') {
            System.out.print("Case room ");
            room.C_num_of_people = 0;
        } else if (c == 'L') {
            System.out.println("Lecture room ");
            room.L_num_of_people = 0;
        }
        System.out.println("使用结束End of occupation（使用人）。");
        room.setState(room.getToBeCleanState(), c);

    }

    public void userNeedsRepair(char c) {
        System.out.println("报修Needs repair（使用人）。");
        room.setState(room.getToBeRepairState(), c);
    }

    public void serverNeedsRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("正在被占用。");
    }

    public void completeRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("正在被占用，不需要维修。");
    }

    public String toString() {
        return "此教室已经被占用,无法预约，清扫和修理。";
    }
}