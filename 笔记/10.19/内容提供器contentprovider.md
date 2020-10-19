1. 继承ContentProvider,并覆写其抽象方法。

2. 在继承类里实例化uriMatcher，并通过urimatcher.addURI(com.example.app.provider,表，码)将期望匹配的URI格式（com.example.app.provider)传递进去。

3. 在覆写函数query()里写选择  ：switch(uriMatcher.match(uri)){

   case 码1：

   ​	查询操作;break;

   }

4. 在getType()函数里调用switch(uriMatcher.match(uri)）{

case 码1：return "vnd.android.cursor.dir/vnd.com.example.app......"

}