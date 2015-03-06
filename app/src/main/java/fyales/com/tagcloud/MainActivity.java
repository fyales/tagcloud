package fyales.com.tagcloud;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import fyales.com.tagcloud.library.TagCloudLayout;


public class MainActivity extends ActionBarActivity {

    TagCloudLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.test_btn);
        mContainer = (TagCloudLayout) findViewById(R.id.container);
        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        list.add("你好");
        list.add("three");
        list.add("four");
        list.add("haha");
        list.add("ninkfnsadf");
        list.add("fsadfsdgdsfasd");
        list.add("fasdgsdagfsdafdsfsadfsadf");
        list.add("adf");
        list.add("one");
        list.add("fasdfadfa");
        list.add("fads");
        list.add("中国");
        list.add("one");
        list.add("柴静");
        list.add("three");
        list.add("four");
        mContainer.addData(list);
        mContainer.drawLayout();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
