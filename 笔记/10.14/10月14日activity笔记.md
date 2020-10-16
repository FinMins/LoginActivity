​		

1. 声明Activity:创建活动时android自动声明

```<manifest> <application>  <activity android:name = ".activityName"> <application></manifest>```

2. Intent过滤器：启动不明确的activity(***隐式intent***)，找到需要启动的activity匹配机制是依靠android系统中的intent过滤器（***intent Filter***) 来实现的。

   2.1 过滤器在活动被创建时会被自动创建（***前提是勾选了launcherActivity***)。

   2.2intent-filter代码段：

   ``` <intent-filter> ``` 

   ```必须有 ：<action android:name=" 响应的action名字"> //action指定该acivity会发送数据``` 

   ``` 可选 <category android:name="附加信息名字">   //catgory声明DEFAULT可使activity能偶接受启动请求```

   ``` 可选 <data android:mimeType=" text/plain" >//指定Activity可以发送的数据类型```  

   ```</intent-filter>```

   2.3 只有<action>和<catgory>同时匹配intent中指定的action和category时，才能响应该intent。一个intent-filter只能有一个action。但是可以有多个catgory.

   2.4

   

   

   

