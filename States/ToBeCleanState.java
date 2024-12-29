package States;

import Observers.*;
import Server.*;

public class ToBeCleanState implements State {
    transient roomService room;

    public ToBeCleanState(roomService room) {
        this.room = room;
    }

    public void roomReserving(char c, int num) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室还没清扫，不能使用。");
    }

    public void roomCleaning(char c) {
        System.out.println("教室清理Cleaning（服务人员）。");
        room.setState(room.getCleanNoReservedtState(), c);
    }

    public void roomOccupying(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室还没清扫，不能使用。");
    }

    public void endOfOccupation(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室还没清扫，并无人员使用。");
    }

    public void userNeedsRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室还没清扫，无人使用，无法报修。");
    }

    public void serverNeedsRepair(char c) {
        System.out.println("报修Needs repair（服务人员）。");
        room.setState(room.getToBeRepairState(), c);
    }

    public void completeRepair(char c) {
        if (c == 'S')
            System.out.print("Seminar room ");
        else if (c == 'C')
            System.out.print("Case room ");
        else if (c == 'L')
            System.out.println("Lecture room ");
        System.out.println("此教室还没清扫，无法维修。");
    }

    public String toString() {
        return "此教室正等待清扫…………";
    }
}