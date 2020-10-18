# Server 

1. server类似于activity,只不过他是在后台运行，用户看不见。
2. server分为两种，一种是未绑定的服务，还有一种是绑定的服务。
   * 未绑定绑定的服务生命周期：oncreate()->onStartCommand()->运行->ondestroy()
   * 绑定的服务生命周期：oncreate()->onBind()->运行->onUnbind()->onDestroy()。注意，绑定的需要有绑定和解绑两个操作，绑定替代了未绑定的OnstarCommand()操作。
3. 继承server的类如同activity也需要在androdimanifest里面注册，不过androidstudio默认的帮我们注册了。
4. 开启未绑定服务的方法：```Intent intent = new Intent(this,服务名.class); startService(intent)```

