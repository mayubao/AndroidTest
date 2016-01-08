# AndroidTest (Andriod测试工程)

## 1.Android进程间通信(IPC)的一个例子
项目的Module工程中有两个是AIDL的测试工程，
一个是AIDLServer的Module工程，
一个是AIDLClient的Module工程。

####1.1用法
#####1.启动AIDLServer工程，然后bindService.
#####2.启动AIDLClient工程，绑定AIDLServer中remote Service(此时是跨进程之间的通信)



