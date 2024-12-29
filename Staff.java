import java.rmi.*;
import Observers.*;
import Servers.*;
import States.*;

public class Staff {
    public static void main(String[] args) {
        String location = "//127.0.0.1/service";

        roomServiceController controller = null;
        StaffDisplay displayer = null;

        try {
            roomServiceRemote room = (roomServiceRemote) Naming.lookup(location);
            displayer = new StaffDisplay(room);
            controller = new roomServiceController(room);

            // 一系列操作……
            while (!room.getIsStop()) {
                try {
                    displayer.checkState();
                    displayer.display();
                    controller.Clean();
                    // controller.callRepairmentByStaff();
                    Thread.sleep(10000); // 等待10秒
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // 重新设置中断状态
                    break; // 可以选择退出循环
                }
            }
            displayer.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
