package fyales.com.tagcloud.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * 标签流容器
 * @author fyales
 * @date 2015-03-04
 */
public class TagCloudLayout<T> extends ViewGroup {

    private static final int DEFAULT_LINE_SPACING = 5;
    private static final int DEFAULT_TAG_SPACING = 10;

    private List<T> mTags;
    private List<Boolean> mChooses;
    private int mLineSpacing;
    private int mTagSpacing;


    public TagCloudLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public TagCloudLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TagCloudLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TagCloudLayout);
        try {
            mLineSpacing = a.getDimensionPixelSize(R.styleable.TagCloudLayout_lineSpacing, DEFAULT_LINE_SPACING);
            mTagSpacing = a.getDimensionPixelSize(R.styleable.TagCloudLayout_tagSpacing, DEFAULT_TAG_SPACING);
        } finally {
            a.recycle();
        }

    }

    public void addData(List<T> tags) {
        this.mTags = tags;
        mChooses = new ArrayList<>();
        for (int i = 0; i < mTags.size(); i++) {
            mChooses.add(i, false);
        }
    }

    public void drawLayout() {
        if (mTags == null || mTags.size() == 0) {
            return;
        }

        for (int i = 0; i < mTags.size(); i++) {
            String text;
            if (mTags.get(i) instanceof String) {
                text = (String) mTags.get(i);
            } else {
                text = mTags.get(i).toString();
            }
            View view = LayoutInflater.from(getContext()).inflate(R.layout.tagview,null);
            Button btn = (Button) view.findViewById(R.id.tag_btn);
            btn.setText(text);
            final int j = i;
            final Button tempBtn = btn;
            btn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mChooses.get(j)) {
                        mChooses.set(j,false);
                        tempBtn.setSelected(false);
                        tempBtn.setTextColor(getResources().getColor(R.color.primary_text));
                    } else {
                        mChooses.set(j, true);
                        tempBtn.setSelected(true);
                        tempBtn.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            });
            this.addView(btn);

        }


    }

    @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int wantHeight = 0;
            int wantWidth = resolveSize(0, widthMeasureSpec);

            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();

            int childLeft = paddingLeft;
            int childTop = paddingTop;
            int lineHeight = 0;

            for (int i = 0; i < getChildCount(); i++) {
                final View childView = getChildAt(i);
                if (childView.getVisibility() == View.GONE) {
                    continue;
                }

                LayoutParams params = childView.getLayoutParams();
                childView.measure(
                        getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, params.width),
                        getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, params.height)
                );

                int childHeight = childView.getHeight();
                int childWidth = childView.getWidth();
                lineHeight = Math.max(childHeight, lineHeight);

                if (childLeft + childWidth + paddingRight > wantWidth) {
                    childLeft = paddingLeft;
                    childTop += mLineSpacing + childHeight;
                    lineHeight = childHeight;
                } else {
                    childLeft += childWidth + mTagSpacing;
                }


            }
            wantHeight = childTop + lineHeight + paddingBottom;
            setMeasuredDimension(wantWidth, resolveSize(wantHeight, heightMeasureSpec));

        }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l;

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        int lineHeight = 0;

        for (int i = 0, childCount = getChildCount(); i < childCount; ++i) {

            final View childView = getChildAt(i);

            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > width) {
                childLeft = paddingLeft;
                childTop += mLineSpacing + lineHeight;
                lineHeight = childHeight;
            }

            childView.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + mTagSpacing;
        }
    }

    public List<T> getChooseList(){
        List<T> list = new ArrayList<>();
        for (int i = 0;i< mTags.size();i++){
            if(mChooses.get(i)){
                list.add(mTags.get(i));
            }
        }
        return list;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("fyales", "TagCloudLayout--->onDraw");
        super.onDraw(canvas);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(this.getContext(), attrs);
    }


    public interface TagItemClickListener {
        void itemClick(int position);
    }


}
