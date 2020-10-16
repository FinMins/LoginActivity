# Activity生命周期

* 用户操作一个应用是，Activity会经历不同的周期状态。

* Activity生命周期一共有六个核心回调：

  1. onCreate():这个回调会在活动被首次创建时触发，只有会进入已创建状态。大部分代码在这里写。***该回调只会在activity的整个生命周期中发生一次***，除非活动被毁灭并再次创建。

  1.1 onCreate()方法接受savedInstanceState参数，这个参数是包含Activity先前保存状态的Bundle对象。 

  2. onStart():当Activity进入“已开始”状态时，系统会调用此回调。这个方法是为了展示给屏幕做准备，再创建后就会被马上调用，而且非常快速的完成。***表示一个状态，及由不可见变成可见时调用***
  3. onResume():当Activity进入"已恢复"状态时来到前台，然后系统调用onResume()回调。***表示准备好和用户交互了，此时一定处于返回栈的栈顶，并处于一种运行状态***。
  4. onPause():此方法表示Activity不再位于前台（尽管多窗口模式下Activity任然可见），***一般在系统准备去启动和回复其他另一个应用的时候调用。通常在这个状态里将关键数据保存***。
  5. onStop():Activity***对用户不再可见***，进入“已停止”状态，系统将调用onStop()回调。
  6. onDestory():在Activity***被销毁之前***，回调这个onDestory()。
  7. onRestart():这个方法在活动***由停滞状态变为运行状态之前调用（onStop()-->onStart()***，也就是活动被重新启动了。

* 实际测试发现 （活动1）->onCreate->onStart()->onResume()->（跳转到活动2)->(活动1)onPause()->(活动2)onCreate()->(活动2)onStart()->(活动2)onResume()->(活动1)onStop（）。

  * 也就是一跳转到另一个活动，会有个跳变的过程，这个过程里活动1还存在部分界面，调用onPause()，然后活动2就开始onCreate(),onStart(),onResume().这时活动1完全看不见了，就调用onStop()。

* 配置发生更改时。比如由竖屏变成横屏时，生命周期调用的函数变化为：onResume()->onPause()->onStop()->onDestory()->onCreate()->onStart()->onResume()。
* 多窗口模式下：有焦点的应用A为onResume()，没焦点的应用B为onPause()。
* 应用点击返回或者调用finish()方法，活动会依次经理onPause(),onStop()和onDestory().应用不仅仅会被销毁，还会从返回堆栈中移除。

* onSaveInstanceStae()保存被系统destory的activity里的一些数据。onSaveInstanceState()为create里的参数，里面存有上次这个活动的一些数据，默认有但为空。