# Fragment创建过程

* 静态创建：1.创建两个Fragment类，并写好这两个fragment类的XML布局，***Fragment注意要覆写oncreateView,返回布局***。

  ​					2.在Activity界面写把fragment当作普通控件来写，就像textview一样，控件名字就时fragment，不过一定要写***android:name="com.example.fragment名字"***这样才能把碎片导入进去！！！

* 动态创建：

  	1. 创建一个fragment类，并写好他的布局XML。注意事项同上。
   	2. 在活动布局里把位置流出来，布局名是framelayout(默认向左上角靠的布局）。通过getSupportFragmentManager()来获取FragmentManager对象。
   	3. 再用FragmentManager.beginTransaction()方法来获取一个业务对象（transaction的意思是业务，交易).                                      
   	4. 用transaction业务对象的replace(id，Fragment)来换掉布局成为fragment。
   	5. 调用transaction的commit()对象来提交事务。

  总结：***获取FragmentManager这个Fragment碎片管理员对象，然后再调用这个管理员对象的beginTransaction()方法来获取一个业务对象，在这个业务对象里调用函数来对碎片进行各种操作。最后调用业务对象的commit()方法来执行***。

          1. 创建碎片。
             2. 后去碎片管理员对象。再活动中直接调用getSupportFragmentManager()方法来获取。
             3. 用管理员碎片的beginTransaction()方法来开启一个业务对象。
             4. 用业务对对象的方法来操作碎片。
             5. 用业务对象的commit()方法来提交业务。

  * 设置碎片的返回栈：业务对象.***setToBackStack(null)***。

  

  

  

  

  

   

  

  

  

  ​	