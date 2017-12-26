## Android AIDL 进程间通信使用笔记

#### AIDL 
AIDL是Android Interface definition language的缩写,Android 接口定义语言

您可以利用它定义客户端与服务使用进程间通信 (IPC) 进行相互通信时都认可的编程接口

多用于App A 与 App B之间通信

#### aidl支持的数据类型
- Java 编程语言中的所有原语类型（如 int、long、char、boolean 等等）
- String、CharSequence、List、Map
- 实现Parcelable接口的对象，并创建对应的.aidl文件
- 其他的.aidl文件生成的接口

#### aidl的类型
- 对象aidl文件
  ```
  package com.mark.aidl;
  parcelable Entry;
  ```
- 接口aidl文件
  ```
  package com.mark.aidl;
  
  interface IRemoteService {

    int getPid();
    
  }
  ```

#### AIDL的使用

具体的使用官网已经说的很清楚，不再赘述,可以看下面的资料和我的源码

- [Android 接口定义语言 (AIDL)](https://developer.android.com/guide/components/aidl.html)
- [ android跨进程通信（IPC）：使用AIDL](http://blog.csdn.net/singwhatiwanna/article/details/17041691)

#### 笔记
1. List的实际接收的具体类始终是 ArrayList
2. Map的实际接收的具体类始终是 HashMap
3. 对象类型的包名需要和对应的aidl文件的包名一致
4. 客户端和服务端的aidl文件及包名必须一致

#### 绑定服务

```java
Intent i = new Intent("com.mark.aidl.action.AIDL_SERVICE");
i.setPackage("com.mark.aidl");
bindService(i, mConnection, BIND_AUTO_CREATE);
```

#### 定向TAG

非原语参数都需要指示数据走向的方向标记。可以是 in、out 或 inout（见以下示例）。
原语默认为 in，不能是其他方向

参考资料：[探索AIDL定向tag in out inout原理](https://www.jianshu.com/p/382633129b53)

1. 定向tag **in** 修饰的的参数，经序列化后传递服务端，服务端反序列化得到一个与之值相同的新的对象；
2. 定向tag **out** 修饰的参数，客户端不会序列化该参数，而是服务端调用无参构造方法新建了一个对象，待目标方法返回后，将参数写入reply返回给客户端；
3. 定向tag **inout** 基本上算是in、out的并集，为什么说基本上，因为out会在服务端通过new关键字来新建一个对象，而inout已经通过反序列化客户端传过来的数据得到一个新的对象，就没有别要再new一个了。


#### 源码奉上

##### [github地址](https://github.com/junzLiu/AidlTest)
 - AidlClient 为客户端
 - AidlTest 为服务端

---
### ps:如有问题 欢迎交流 愿与君共勉
---

#### 参考资料
- [Android 接口定义语言 (AIDL)](https://developer.android.com/guide/components/aidl.html)
- [ android跨进程通信（IPC）：使用AIDL](http://blog.csdn.net/singwhatiwanna/article/details/17041691)
- [探索AIDL定向tag in out inout原理](https://www.jianshu.com/p/382633129b53)
 
