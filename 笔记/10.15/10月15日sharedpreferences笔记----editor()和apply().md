1. 描述sharedpreferences:使用**键值对**的方式来存储。

2. 使用sharedpreferences存储:

   (1):获取SharePreferences对象。一般用Context的getSharedPreferences(文件名,0)来获取这个对象，0代表重名文件默认覆盖。这种方法在activity里可以直接写。

   (2):调用SharedPreferences对象的edit()方法来获取SharedPreferences.Editor对象。举个例子：SharedPreferences.Editor editor = getSharedferences(“filename”,0).edit().

   (3):想Editor对象中添加数据，用put+数据类型的方法来添加数据，可重复添加。举个例子：

   editor.putString(key，value);

   (4):调用apply()方法将添加的数据提交，完成数据存储操作。

3. 使用sharedferences读取：

   (1):获取Sharedpreferences，同上，一般用getSharedpreferences("filename",0)。

   (2):直接通过键值对的读取操作模式操作sharedpreferences，调用Sharedpreferences.get+数据类型（key，默认值）例如 sharedpreferences.getInt(num,0);





ps:MODEL_PRIVATE:默认操作重名覆盖原内容。MODEL_APPEND:重名追加内容。