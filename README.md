### tagcloud
根据服务器返回数据的数量，每个字段的大小进行自适应布局

### 用法

    <fyales.com.tagcloud.library.TagCloudLayout
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tagSpacing="15dp"
        app:lineSpacing="10dp"/>
        
tagSpacing属性:各个标签之间的距离
lineSpacing属性:行间距

### 实现效果
![图片](http://fyales.qiniudn.com/Screenshot_2015-03-04-20-31-12.png)

### gradle引用(仓库为JCenter)

	dependencies {
    	compile 'com.fyales.android:library:1.0.0'
	}



