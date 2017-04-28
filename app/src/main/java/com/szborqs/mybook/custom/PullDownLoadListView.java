package com.szborqs.mybook.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.szborqs.mybook.R;
import com.szborqs.mybook.system.MyApplication;

import java.util.Date;


public class PullDownLoadListView extends ListView implements OnScrollListener {
    private final static int RELEASE_To_REFRESH = 0;
    private final static int PULL_To_REFRESH = 1;
    // 正在刷新
    private final static int REFRESHING = 2;
    // 刷新完成
    private final static int DONE = 3;
    private final static int LOADING = 4;

    private final static int RATIO = 2;

    private final static int REFRESH_MODE_STATIC = 0;
    private final static int REFRESH_MODE_TOP = 1;

    private int refreshMode = REFRESH_MODE_STATIC; // top 1 bottom 2
    private LayoutInflater inflater;
    private LinearLayout headView;
    private TextView tipsTextview;
    private TextView lastUpdatedTextView;
    private ImageView arrowImageView;
    private ProgressBar progressBar;

    private RotateAnimation animation;
    private RotateAnimation reverseAnimation;
    private boolean isRecored;
    private int headContentHeight;
    private int startY;
    private int firstItemIndex;
    private int lastItemIndex;
    private int totalItemCount;
    private long itemTotal;
    private int state;
    private boolean isBack;
    private OnTopRefreshListener topRefreshListener;
    private boolean isRefreshable;

    public PullDownLoadListView(Context context) {
        super(context);
        init(context);
    }

    public PullDownLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        setCacheColorHint(context.getResources().getColor(R.color.transparent));
        inflater = LayoutInflater.from(context);
        headView = (LinearLayout) inflater.inflate(R.layout.head, null);
        arrowImageView = (ImageView) headView
                .findViewById(R.id.head_arrowImageView);
        arrowImageView.setMinimumWidth(70);
        arrowImageView.setMinimumHeight(50);
        progressBar = (ProgressBar) headView
                .findViewById(R.id.head_progressBar);
        tipsTextview = (TextView) headView.findViewById(R.id.head_tipsTextView);
        lastUpdatedTextView = (TextView) headView
                .findViewById(R.id.head_lastUpdatedTextView);

        measureView(headView);
        headContentHeight = headView.getMeasuredHeight();
        headView.setPadding(0, -1 * headContentHeight, 0, 0);
        headView.invalidate();

        addHeaderView(headView, null, false);
        setOnScrollListener(this);

        animation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);

        reverseAnimation = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(200);
        reverseAnimation.setFillAfter(true);

        state = DONE;
        isRefreshable = false;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        this.firstItemIndex = firstVisibleItem;
        this.lastItemIndex = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isRefreshable) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (firstItemIndex == 0 && lastItemIndex != totalItemCount
                            && !isRecored) {
                        isRecored = true;
                        refreshMode = REFRESH_MODE_TOP;
                    } else if (firstItemIndex != 0
                            && lastItemIndex == totalItemCount && !isRecored) {
                        isRecored = true;
                    }
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    if (refreshMode == REFRESH_MODE_TOP && state != REFRESHING
                            && state != LOADING) {
                        if (state == DONE) {
                        }
                        if (state == PULL_To_REFRESH) {
                            state = DONE;
                            changeHeaderViewByState();
                        }
                        if (state == RELEASE_To_REFRESH) {
                            state = REFRESHING;
                            changeHeaderViewByState();
                            onTopRefresh();
                        }
                    }
                    isRecored = false;
                    isBack = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    int tempY = (int) event.getY();
                    if (!isRecored && firstItemIndex == 0 && (tempY - startY > 0)) {
                        isRecored = true;
                        refreshMode = REFRESH_MODE_TOP;
                        break;
                    } else if (!isRecored && lastItemIndex == totalItemCount
                            && (tempY - startY < 0)) {
                        isRecored = true;
                        break;
                    }
                    if (refreshMode == REFRESH_MODE_TOP && state != REFRESHING
                            && isRecored && state != LOADING) {
                        if (state == RELEASE_To_REFRESH || state == PULL_To_REFRESH) {
                            setSelection(0);
                        }
                        if (state == RELEASE_To_REFRESH) {
                            if (((tempY - startY) / RATIO < headContentHeight)
                                    && (tempY - startY) > 0) {
                                state = PULL_To_REFRESH;
                                changeHeaderViewByState();
                            } else if (tempY - startY <= 0) {
                                state = DONE;
                                changeHeaderViewByState();
                            }
                        }
                        if (state == PULL_To_REFRESH) {
                            if ((tempY - startY) / RATIO >= headContentHeight) {
                                state = RELEASE_To_REFRESH;
                                isBack = true;
                                changeHeaderViewByState();
                            } else if (tempY - startY <= 0) {
                                state = DONE;
                                changeHeaderViewByState();
                            }

                        }
                        if (state == DONE) {
                            if ((tempY - startY > 0) && (firstItemIndex == 0)) {
                                state = PULL_To_REFRESH;
                                changeHeaderViewByState();
                            }
                        }
                        if (state == PULL_To_REFRESH) {
                            headView.setPadding(0, -1 * headContentHeight
                                    + (tempY - startY) / RATIO, 0, 0);
                        }
                        if (state == RELEASE_To_REFRESH) {
                            headView.setPadding(0, (tempY - startY) / RATIO
                                    - headContentHeight, 0, 0);
                        }
                    }
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getY();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void changeHeaderViewByState() {
        switch (state) {
            case RELEASE_To_REFRESH:
                arrowImageView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tipsTextview.setVisibility(View.VISIBLE);
                lastUpdatedTextView.setVisibility(View.VISIBLE);
                arrowImageView.clearAnimation();
                arrowImageView.startAnimation(animation);
                tipsTextview.setText(MyApplication.getInstance().getString(
                        R.string.release_refresh));
                break;
            case PULL_To_REFRESH:
                progressBar.setVisibility(View.GONE);
                tipsTextview.setVisibility(View.VISIBLE);
                lastUpdatedTextView.setVisibility(View.VISIBLE);
                arrowImageView.clearAnimation();
                arrowImageView.setVisibility(View.VISIBLE);
                if (isBack) {
                    isBack = false;
                    arrowImageView.clearAnimation();
                    arrowImageView.startAnimation(reverseAnimation);
                    tipsTextview.setText(MyApplication.getInstance().getString(
                            R.string.cancel_refresh));
                } else {
                    tipsTextview.setText(MyApplication.getInstance().getString(
                            R.string.cancel_refresh));
                }
                break;
            case REFRESHING:
                headView.setPadding(0, 0, 0, 0);
                progressBar.setVisibility(View.VISIBLE);
                arrowImageView.clearAnimation();
                arrowImageView.setVisibility(View.GONE);
                tipsTextview.setText(MyApplication.getInstance().getString(
                        R.string.loading));
                lastUpdatedTextView.setVisibility(View.VISIBLE);
                break;
            case DONE:
                headView.setPadding(0, -1 * headContentHeight, 0, 0);
                progressBar.setVisibility(View.GONE);
                arrowImageView.clearAnimation();
                arrowImageView.setImageResource(R.mipmap.ic_z_arrow_down);
                tipsTextview.setText(MyApplication.getInstance().getString(
                        R.string.last_updata));
                lastUpdatedTextView.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setonTopRefreshListener(OnTopRefreshListener topRefreshListener) {
        this.topRefreshListener = topRefreshListener;
        isRefreshable = true;
    }

    public void onRefreshComplete() {
        lastUpdatedTextView.setText(MyApplication.getInstance().getString(
                R.string.last_updata)
                + new Date().toLocaleString());
        state = DONE;
        if (refreshMode == REFRESH_MODE_TOP) {
            changeHeaderViewByState();
        }
        refreshMode = REFRESH_MODE_STATIC;
    }

    private void onTopRefresh() {
        if (topRefreshListener != null) {
            topRefreshListener.onRefresh();
        }
    }

    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    public void setAdapter(BaseAdapter adapter) {
        super.setAdapter(adapter);
    }

    public long getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(long itemTotal) {
        this.itemTotal = itemTotal;
    }

    public interface OnTopRefreshListener {
        public void onRefresh();
    }
}
