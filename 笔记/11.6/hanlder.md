Hnadler:用handleMessage()去合理message，用handler来把message发送给Messagequeue.

new一个handler,覆写handleMessage(Message msg)方法，在方法里调用switch（msg.what) case：来设置ui改变。在onclick里new一个message，设置这个message,what=case值，再调用handler.sendMesage(message)。将这个message对象发送出去。