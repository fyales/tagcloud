package com.fyales.tagcloud.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * @author fyales
 * @since date 11/3/15
 */
public class TagCloudConfiguration {

    private static final int DEFAULT_LINE_SPACING = 5;
    private static final int DEFAULT_TAG_SPACING = 10;
    private static final int DEFAULT_TAG_MIN_NUMBER = 0;
    private static final int DEFAULT_TAG_MAX_NUMBER = 100;

    private int lineSpacing;
    private int tagSpacing;
    private int tagMinNumber;
    private int tagMaxNumber;

   public TagCloudConfiguration(Context context,AttributeSet attrs){
       TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TagCloudLayout);
       try {
           lineSpacing = a.getDimensionPixelSize(R.styleable.TagCloudLayout_lineSpacing, DEFAULT_LINE_SPACING);
           tagSpacing = a.getDimensionPixelSize(R.styleable.TagCloudLayout_tagSpacing, DEFAULT_TAG_SPACING);
           tagMaxNumber = a.getInteger(R.styleable.TagCloudLayout_tagMaxCount,DEFAULT_TAG_MAX_NUMBER);
           tagMinNumber = a.getInteger(R.styleable.TagCloudLayout_tagMinCount,DEFAULT_TAG_MIN_NUMBER);
       } finally {
           a.recycle();
       }
   }

    public int getTagMinNumber() {
        return tagMinNumber;
    }

    public void setTagMinNumber(int tagMinNumber) {
        this.tagMinNumber = tagMinNumber;
    }

    public int getTagMaxNumber() {
        return tagMaxNumber;
    }

    public void setTagMaxNumber(int tagMaxNumber) {
        this.tagMaxNumber = tagMaxNumber;
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public int getTagSpacing() {
        return tagSpacing;
    }

    public void setTagSpacing(int tagSpacing) {
        this.tagSpacing = tagSpacing;
    }
}
