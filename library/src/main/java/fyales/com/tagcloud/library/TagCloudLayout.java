package fyales.com.tagcloud.library;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class TagCloudLayout<T> extends LinearLayout {

    private List<T> mTags;
    private int mWidth;
    private Context mContext;


    public TagCloudLayout(Context context) {
        super(context);
        this.mContext = context;
        init(null, 0);
    }

    public TagCloudLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs, 0);
    }

    public TagCloudLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        setOrientation(VERTICAL);
    }

    public void addData(List<T> tags){
        this.mTags = tags;
    }

    public void drawLayout(){
        if (mTags == null ||mTags.size() == 0){
            return;
        }
        Log.e("fyales","mWidth is " + mWidth);

        for (int i = 0 ;i < mTags.size();i++){
            if (mTags.get(i) instanceof String){
                TextView tv = new TextView(mContext);
                Log.e("fyales", tv.getWidth() + "");
                tv.setText((String)mTags.get(i));
                Log.e("fyales", tv.getWidth() + "");
                this.addView(tv);
            }else{
                TextView tv = new TextView(mContext);
                tv.setText(mTags.get(i).toString());
                this.addView(tv);
            }
        }



    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
