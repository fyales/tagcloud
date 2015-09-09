### tagcloud
根据服务器返回数据的数量，每个字段的大小进行自适应布局

### 用法

    <fyales.com.tagcloud.library.TagCloudLayout
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tagSpacing="15dp"
        app:lineSpacing="10dp"/>
        
* tagSpacing属性:各个标签之间的距离
* lineSpacing属性:行间距

Java代码

        mContainer = (TagCloudLayout) findViewById(R.id.container);
        mList = new ArrayList<>();
        mList.add("中华人名共和国");
        mList.add("大韩民国");
        mList.add("日本");
        mList.add("朝鲜");
        mList.add("台湾");
        mList.add("香港特别行政区");
        mList.add("澳门特别行政区");
        mList.add("越南");
        mList.add("老挝");
        mList.add("柬埔寨");
        mList.add("泰国");
        mList.add("缅甸");
        mList.add("马来西亚");
        mList.add("新加坡");
        mList.add("印度尼西亚");
        mList.add("文莱");
        mList.add("菲律宾");
        mAdapter = new TagBaseAdapter(this,mList);
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.add("东帝汶");
                mAdapter.notifyDataSetChanged();
            }
        });
        mContainer.setAdapter(mAdapter);
        mContainer.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                Toast.makeText(MainActivity.this,mList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        

### 实现效果
![图片](http://fyales.qiniudn.com/Screenshot_2015-03-04-20-31-12.png)

### gradle引用(仓库为JCenter)

	dependencies {
    	compile 'com.fyales.android:library:1.0.1'
	}
	
### 1.0.2更新
* 允许用户自定义,自己写一个adapter即可
* 重构代码




