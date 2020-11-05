# viewpager创建

1. viewpager有点类似于recyclerview，需要有适配器和布局文件。
2. 创建过程：
   	1.  在activity界面里写入viewpager控件。
    	2. 创建所有需要的fragment布局.xml。
    	3. 创建一个Viewpager适配器adapter（有pageradapterdapter,fragmentPageradapter,fragmentStatePagerAdapter三种)。
    	4. 在activity里把viewpager适配器填充好(fragmentmannger,fragmentlist,0)。
    	5. viewpager.setadapter(adapter).

3. 使用过程：viewpager.addOnPageChangerListener(new ViewPager.OnPagerChangeListener())，覆写onchangerChangeKustener()d的三个抽象方法：
   1. onPageScrolled():页面滑动时会一直调用这个方法。
   2. onPagerSelected():页面滑动完后会调用这个方法。
   3. onpageScrollState(int state):state取值0.1.2;0什么也没做。1正在滑动。2滑动完毕。

