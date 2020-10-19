# Sqlite学习使用

1. ***创建一个类继承SQLiteOpenHelper抽象类（他可以叫做帮助类）***，并实现他的构造方法和抽象方法（onCreate创建和onUpgrade更新)。
   * onCreate()里的sqlitedatavase 参数可以调用它来对数据库进行初始化操作。用db,execSQL(String)来操作sql语句。
2. ***在活动中创建一个自己的类dbhelper，然后调用dbhelper.getwritabledatabase()来创建一个数据库，并返回一个可以CRUD的工具类对象SQLiteDatabase db(没错，这才是我们需要的）***。用db.execSQL(SQL语句)来操作这个继承类的数据操作。里面的sql语句为直接操作的语句。查询为
3. ***查询时，语句改为db.rawQuery(SQL查询语句，null)，返回一个cursor（光标）对象***，这个指向获取的数据集的位置，可以通过移动它来获取位置，比如cursor,moveToFirst()移动到获取的所有行的第一行，cursor.moveToNext()移动到当前行的下一行。然后用cursor.get+类型(cursor.getColumnIndex("属性名"))来获取当前行的某一列的值。一般用do-while语句来查询。
4. 可以用***cursor.moveToFirst()来判断是否查询到数据***。
5. 原生的sql语句：增删改：db.execSQL(sql语句)，无返回值。查：db.rawQuery(sql语句，null)，返回cursor对象，返回的数据都在这个corsor里。
6. 原生sql语句id自增问题解决方案：不插入id或者这是id为null。
7. 使用sdk原生方法来操作cursor:
   * ContentValues:一个容器，里面存数据。new ContentValues values ;调用values.put("key",value)来存储数据，一行的数据输入完后调用db.insert("表名",null,values)来将一行数据存进去，然后用values.clear()来清空容器，重复上述步骤录入下一行。***注意,puth后面无需加数据类别，如string,int等***)
   * 更新数据update:db.update("表名",values,"name=?",new String[] {"约束"}) 参数3为where 参数4为?的值。注意为Sring[].