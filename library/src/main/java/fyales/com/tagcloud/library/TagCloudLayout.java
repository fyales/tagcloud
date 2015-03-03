package fyales.com.tagcloud.library;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class TagCloudLayout<T> extends ViewGroup {

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

    }

    public void addData(List<T> tags){
        this.mTags = tags;
    }

    public void drawLayout(){
        if (mTags == null ||mTags.size() == 0){
            return;
        }
        mWidth = getWidth();
        Log.e("fyales","mWidth is " + mWidth);

        for (int i = 0 ;i < mTags.size();i++){
            if (mTags.get(i) instanceof String){
                TextView tv = new TextView(mContext);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                tv.setLayoutParams(params);
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int myWidth = resolveSize(0, widthMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        int lineHeight = 0;

        // Measure each child and put the child to the right of previous child
        // if there's enough room for it, otherwise, wrap the line and put the child to next line.
        for (int i = 0, childCount = getChildCount(); i < childCount; ++i) {
            View childView = getChildAt(i);
            LayoutParams childLayoutParams = childView.getLayoutParams();
            childView.measure(
                    getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childLayoutParams.width),
                    getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childLayoutParams.height));
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > myWidth) {
                childLeft = paddingLeft;
                childTop += 20 + lineHeight;
                lineHeight = childHeight;
            } else {
                childLeft += childWidth + 10;
            }
        }

        int wantedHeight = childTop + lineHeight + paddingBottom;
        setMeasuredDimension(myWidth, resolveSize(wantedHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int myWidth = r - l;

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        int lineHeight = 0;

        for (int i = 0, childCount = getChildCount(); i < childCount; ++i) {
            View childView = getChildAt(i);

            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > myWidth) {
                childLeft = paddingLeft;
                childTop += 20 + lineHeight;
                lineHeight = childHeight;
            }

            childView.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + 20;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("fyales","TagCloudLayout--->onDraw");
        super.onDraw(canvas);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(this.getContext(),attrs);
    }


}
