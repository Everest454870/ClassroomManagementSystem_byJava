import java.rmi.*;
import java.util.Scanner;
import Observers.*;
import Servers.*;
import States.*;
// User类实现观察者接口，以实时观察系统实况
// User类调用roomServiceController，以实现对系统的远程操作

public class User {
    public static void main(String[] args) {
        String location = "//127.0.0.1/service";

        UserDisplay displayer = null;
        roomServiceController controller = null;

        try {
            roomServiceRemote room = (roomServiceRemote) Naming.lookup(location);

            displayer = new UserDisplay(room);
            controller = new roomServiceController(room);

            // 一系列操作……
            System.out.println("教室管理系统已就绪：");
            displayer.checkState();
            displayer.display();
            Scanner input = new Scanner(System.in);
            System.out.println("请选择你要执行的操作");
            System.out.println("R: 预定教室；| C: 报修 | F: 结束使用 | S: 退出");
            char c = input.next().charAt(0);

            int count = 0;
            try {
                while (true) {
                    switch (c) {
                        case 'R':
                            System.out.print("请选择要预约的教室：");
                            char c1 = input.next().charAt(0);
                            System.out.print("输入预约人数：");
                            int num1 = input.nextInt();
                            controller.reserve(c1, num1);
                            count += 1;
                            break;
                        case 'C':
                            System.out.print("请选择要报修的教室：");
                            char c2 = input.next().charAt(0);
                            input.nextLine();
                            System.out.print("请简要陈述报修信息：");
                            String s = input.nextLine();
                            controller.callRepairmentByUser(c2, s);
                            break;
                        case 'F':
                            System.out.println("请选择已使用结束的教室：");
                            char c3 = input.next().charAt(0);
                            input.nextLine();
                            controller.finish(c3);
                            break;
                        case 'S':
                            System.out.println("您已退出用户界面。");
                            Thread.sleep(5000);
                            System.exit(0);
                        default:
                            System.out.println("非法输入，合法输入只有(R, C, F, S)");
                            break;
                    }

                    Thread.sleep(5000);
                    displayer.checkState();
                    displayer.display();

                    if (count >= 5)
                        throw new toManyException();

                    System.out.println("请继续选择你要执行的操作");
                    System.out.println("R: 预定教室；| C: 报修 | F: 结束使用 | S: 退出");
                    c = input.next().charAt(0);
                }
            } catch (toManyException ex) {
                System.exit(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class toManyException extends Exception {
    public toManyException() {
        System.out.println("您的预约次数已达上限(5次), 已被系统强制下线。");
    }
}