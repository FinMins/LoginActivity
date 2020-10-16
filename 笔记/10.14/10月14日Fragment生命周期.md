# Fragment生命周期

1. onAttach(Context context)：在Fragment和Activity建立关联时调用（可以理解为Activity要先敲下门才能被创建)，这个回调函数可以使fragment获取activity要传过来的参数。（（Activity类名)context).Activity里的方法。
2. onCreate(Bundle savedInstanceState):调用这个方法时还未收到ctivity的oncreate(已完成的通知。
3. onCreateView():当Fragment创建视图时被调用。
4. onActivityCreated()：在相关联的Activity的onCreate()方法调用后调用。这是在用户看到用户界面之前可对用户界面执行的最后调整的地方。
5. onStart():同Activity.
6. onResume()：要进入运行时调用。
7. onPause():和Activity一样。
8. onStop()：和Activity一样。
9. onDestroyView():当Fragment中的视图被移除时调用。
10. onDestroy()：不在使用fragment时调用，但fragment任然附加再activity上，可以被找到，但是不能执行其他操作。.
11. onDetach（）：在Fragment和Activity取消关联时调用，这个方法调用后，fragment与activity不再绑定。。