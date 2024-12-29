# ClassroomManagementSystem_byJava
### 程序结构

+ **Observers** :==观察者模式==，实现 observe 和 display
	+ **Subject** (rigister, remove, notify)
	+ **MyDisplay**(checkState，更新状态) & **MyObserver**(display，打印状态)
		+ ApproverDisplay
		+ UserDisplay
		+ StaffDisplay
		+ MaintainerDisplay

+ **Server**: 服务器，默认地址：127.0.0.1，默认端口：1099
	+ roomServiceRemote
	+ roomService
	+ ServiceSystem：在终端中执行
+ **States**：==状态模式==，在简化教室状态图的基础上，讲“清洁”状态分为已预定和未预定
	+ State
	+ CleanNotReservedState
	+ CleanReservedState
	+ OccupiedState
	+ ToBeCleanState
	+ ToBeRepairState
+ **roomServiceController**：==代理模式==，通过远程访问的方式改变**roomService** 的状态
+ **四类用户**，这四类均在终端中执行，并且==组合==了对应的**display**类和**roomServiceController**
	+ User
	+ Approver
	+ Staff
	+ Maintainer
