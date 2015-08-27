package com.fyales.tagcloud;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.fyales.tagcloud.library.TagBaseAdapter;
import com.fyales.tagcloud.library.TagCloudLayout;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private TagCloudLayout mContainer;
    private TagBaseAdapter mAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.add_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.add("美利坚合众国");
                mAdapter.notifyDataSetChanged();
            }
        });
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
        mContainer.setAdapter(mAdapter);
        mContainer.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                Toast.makeText(MainActivity.this,mList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
