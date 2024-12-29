package Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import Observers.*;
import States.*;

public class ServiceSystem {
    public static void main(String[] args) throws MalformedURLException {
        roomServiceRemote service = null;
        try {
            service = new roomService("127.0.0.1");
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//127.0.0.1/service", service);
            System.out.println("教室服务系统启动……");

            while (!service.getIsStop()) {
                try {
                    ((roomService) service).notifyObservers();
                    Thread.sleep(1000); // 等待1秒
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // 重新设置中断状态
                    System.out.println("!!!");
                    break; // 可以选择退出循环
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            try {
                if (service != null) {
                    Naming.unbind("//127.0.0.1/service");
                    UnicastRemoteObject.unexportObject(service, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("用户结束使用，教室服务系统终止运行。");
        }
    }
}
