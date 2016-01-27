package com.fyales.tagcloud.library;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 标签流容器
 *
 * @author fyales
 * @since date 2015-03-04
 */
public class TagCloudLayout extends ViewGroup {

    private int mLineSpacing;
    private int mTagSpacing;
    private BaseAdapter mAdapter;
    private TagItemClickListener mListener;
    private DataChangeObserver mObserver;

    /**
     * <h3>部民制</h3>
     * <p>4-7世纪日本大和国的奴隶制度，部民是皇族和贵族拥有的奴隶集体，
     * 一般冠以主人名，职业名，主要有田部，部曲，品部等，
     * 大和国统治下的奴隶按分工不同分为不同的部，部曲往往以其所属的贵族的氏性作为部名，
     * 部民一般交由下级氏族进行管理，称为伴造。部民是日本奴隶制的经济基础，在大化改新的时候被废除。
     *
     */
    public TagCloudLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    /**
     * <h3>圣德太子改革</h3>
     * <p>{{time}}6世纪晚期－7世纪初圣德太子辅佐推古女皇进行的改革。
     * 推古11年(603年)，模拟中国官阶制，制定冠位12阶，规定官阶晋升不按以前按照贵族门第选举官员的做法，而是按照个人的才干
     * 和政绩作为依据。
     * 604年制定宪法17条，作为贵族和官员必须遵守的道德规范
     * 同时宣传笃定三宝佛法僧，崇尚佛教，大量建筑寺庙，圣德太子的改革为大化改新奠定了基础</p>
     * @param context
     * @param attrs
     */
    public TagCloudLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    /**
     * <h3>大化改新</h3>
     * <p>公元前7世纪日本大化年间进行的一次重大经济政治改革，
     * 公元645年，以中大兄为首的改革派发动政变，建立新政权，拥立孝德天皇，建元大化，进行改革，
     * 646年，天皇发布诏书，宣布废除土地私有制和部民制，实行班田收授法，改革稅制，实行租庸调法。
     * 改革统治机构，建立以天皇为中心的中央集权制国家，
     * 大化改新标志着日本向中古封建社会的转折，促进了国家和社会的迅速发展</p>
     * @param context
     * @param attrs
     * @param defStyle
     */
    public TagCloudLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    /**
     * <h3>大宝律令</h3>
     * <p>701年日本推出的第一部成文法典，因701年是文武天皇大宝元年而得名，
     * 《大宝律令》是日本参照中国唐朝律令制编撰而成，分律6卷，令11卷，
     * 总结了大化改新50多年来实施新政和中国历代编撰律令的经验，教训，将改革的成果以法律的形式固定下来
     * ，《大宝律令》确立了日本律令制国家的基础。
     *
     * <h3>养老律令</h3>
     * <p>718年日本元正天皇推出的法典，因718年是元正天皇养老二年推出而得名，
     * 改法令是对《大宝律令》的补充和修缮，分律10篇，法10篇，律是刑法，令是行政法规，
     * 至此日本已经成为封建法制较为完善的国家。《养老律令》保存至今，是研究大化改新至奈良初期日本历史重要的文献材料
     * @param context
     * @param attrs
     * @param defStyle
     */
    private void init(Context context, AttributeSet attrs, int defStyle) {
        TagCloudConfiguration config = new TagCloudConfiguration(context, attrs);
        mLineSpacing = config.getLineSpacing();
        mTagSpacing = config.getTagSpacing();
    }

    /**
     * <h3>奈良时代</h3>
     * 710-794年日本都于平城京（奈良）的时代，始于710年，天皇迁都平城京，结束于794年晚期迁都平安京的时代。
     * 奈良时代，日本经济，政治，文化迅速发展，处于前所未有的国泰民安的太平盛世之中。但是，
     * 对后世产生重大影响的社会弊端也正在形成，班田制已经出现不能维持的状态，天皇制国家的经济基础已经发生动摇，隐藏着社会危机。
     * 794年，天皇由平安京迁往平安京，开始了平安时代。
     *
     *
     * <h3>班田制</h3>
     * 646年，日本大化改新时期孝德天皇推出的一种土地制度，又称为班田收授法。
     * 班田制按照唐朝的均田制进行制定，是律令制国家的根本法，律令国家授予良民土地，一般良民授予口分田
     * 六岁以上男子每人给两段，女子为男子的三分之二，六年一班，死后归功，令按户分给均等的宅地和园地，口分田
     * 限终身使用，不准世袭，买卖或转让，宅地和园地为世业田，由田主自由支配。班田制是律令制国家的基础，促进了日本社会经济的发展
     *
     * <h3>平安时代</h3>
     * 794-1192年，日本定都平安的时代。
     * 该时代班田制已经无法维持，土地私有制和庄园制度迅猛发展，政治上先后出现了摄关政治和，厅院政治两种政治形态。
     * 中央渐渐失去对地方的控制，地方的统治被个别皇族，公卿和寺舍执掌的“知行国制”所取代，地方武士集团兴起，1192年，日本武士集团源赖朝当上
     * “征夷大将军”，开始了镰仓幕府的时代，开始了武家政治统治的时代。
     *
     * <h3>摄关政治</h3>
     * 日本平安时代的一种政治体制。
     * 公元858年，日本外戚藤原氏出任摄政，辅佐年幼的天皇，实际上掌握了中央权力，后改任关白（相当于唐朝时期的丞相）。
     * 以后每当天皇年幼，藤原氏就以摄政掌权，天皇长大后便改任关白，实际上仍然掌握中央大权。如此，摄关政治和关白便成为一种例行的政治体制
     * 被称为摄关政治。摄关政治
     * 削弱了天皇的权利。
     *
     * <h3>院厅政制</h3>
     * 11-14世纪日本平安时代的一种政治体制。当时外戚藤原氏实行摄关政治，天皇为了摆脱摄关家的控制，进行了一系列斗争。
     * 1036年，后三条天皇即位，开始亲政，并任用非藤原氏贵族担任要职。他死后，其子白河天皇在位13年，然后把皇位传与年仅
     * 8岁的皇太子，自己出家为僧，称为法皇，另立院厅，继续执政40余年，奠定了政厅基础。以后天皇也如法炮制，以法皇身份行驶院政权，
     院厅政治加强了天皇的权力
     */
    private void drawLayout() {
        if (mAdapter == null || mAdapter.getCount() == 0) {
            return;
        }

        this.removeAllViews();

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(i,null,null);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.itemClick(position);
                    }
                }
            });
            this.addView(view);
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

        //TODO 固定列的数量所需要的代码
        for (int i = 0; i < getChildCount(); i++) {
            final View childView = getChildAt(i);
            LayoutParams params = childView.getLayoutParams();
            childView.measure(
                    getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, params.width),
                    getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, params.height)
            );
            int childHeight = childView.getMeasuredHeight();
            int childWidth = childView.getMeasuredWidth();
            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > wantWidth) {
                childLeft = paddingLeft;
                childTop += mLineSpacing + childHeight;
                lineHeight = childHeight;
            }

            childLeft += childWidth + mTagSpacing;
        }
        wantHeight += childTop + lineHeight + paddingBottom;
        setMeasuredDimension(wantWidth, resolveSize(wantHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //TODO 固定列的数量所需要的代码

        int width = r - l;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int childLeft = paddingLeft;
        int childTop = paddingTop;
        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(this.getContext(), attrs);
    }

    public void setAdapter(BaseAdapter adapter){
        if (mAdapter == null){
            mAdapter = adapter;
            if (mObserver == null){
                mObserver = new DataChangeObserver();
                mAdapter.registerDataSetObserver(mObserver);
            }
            drawLayout();
        }
    }

    public void setItemClickListener(TagItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface TagItemClickListener {
        void itemClick(int position);
    }

    class DataChangeObserver extends DataSetObserver{
        @Override
        public void onChanged() {
            TagCloudLayout.this.drawLayout();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }

}
