# 概要
本工程是TCC的一种实现，coordinator由调用端承担；
# 使用样例
## 介绍
工程中Test目录下面
有一个ManagerService，这个service需要调用一个本地服务和一个远程服务，本地服务和远程服务需要一起成功或者一起失败。
## 使用方式
### 1、调用方注解
在ManagerService中有一个demo方法，这个方法会调用一个本地服务和一个远程服务。
在demo方法上添加@TccTransaction注解
### 2、被调用方注解
在本地方法和远程方法上都加上@Tcc注解
### 3、被调用方规范
@Tcc注解需要有两个参数，confirmMethod和cancelMethod
所以被调用方需要实现这两个方法。
调用方在调用被调用方的方式其实是TCC中的try方法，其中执行了资源的预留。


