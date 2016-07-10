package com.aming.translate.view.frag;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aming.translate.R;
import com.aming.translate.main.BaseFragment;
import com.aming.translate.model.biz.QueryView;
import com.aming.translate.presenter.QueryPresenter;
import com.aming.translate.tools.DensityUtil;
import com.aming.translate.tools.MyLog;
import com.aming.translate.tools.NetUtil;
import com.aming.translate.view.view.QueryAnimateView;

/**
 * Created by wenming on 2016/6/27.
 */
public class QueryFragment extends BaseFragment implements QueryView {

    private QueryPresenter mPresenter;

    private View recordView, showView;

    private TextView tv_result;
    private EditText et_input;
    private QueryAnimateView queryView;
    private Button btn_add;

    private boolean isBackToThis;
    private boolean isSearchBegin = true;

    private String curQueryText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new QueryPresenter(this);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.frag_query;
    }

    @Override
    protected void initFindView() {

        recordView = rootView.findViewById(R.id.data_layout);
        showView = rootView.findViewById(R.id.show_layout);
        tv_result = (TextView) rootView.findViewById(R.id.tv_show_result);
        et_input = (EditText) rootView.findViewById(R.id.et_input);
        queryView = (QueryAnimateView) rootView.findViewById(R.id.queryView);
        btn_add = (Button) rootView.findViewById(R.id.btn_add_to_favorite);

        queryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetUtil.isNetConnect(mContext)) {
                    MyLog.v("无网络");
                    return;
                }
                curQueryText = et_input.getText().toString();
                if (TextUtils.isEmpty(curQueryText)) {
                    MyLog.v("不能为空");
                    return;
                }
                if (checkIsNotContain(curQueryText)) {
                    MyLog.v("很抱歉，该词典解释不完善，不予显示");
                    return;
                }
                if (queryView.isCanQuery()) {
                    queryView.query();
                    mPresenter.search(curQueryText);
                    closeInputMethod();
                }
            }
        });

        recordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateToRecordPage(true);
            }
        });

        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isSearchBegin) {
                    if (!s.toString().equals(curQueryText)) {
                        queryView.reQuery();
                        animateShowAdd(true);
                        isSearchBegin = true;
                    }
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addToFavorite();
            }
        });
    }

    @Override
    protected void initSet() {

    }

    private boolean checkIsNotContain(String text) {
        if (text.equals("nope")) {
            return true;
        }
        return false;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isBackToThis) {
            animateToRecordPage(false);


        }
    }

    private void animateToRecordPage(boolean isToRecordPage) {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(recordView,"translationY",0,-DensityUtil.dpToPx(mContext,100));
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(recordView,"scaleY",1,2.5f);
        ObjectAnimator showAnimator = ObjectAnimator.ofFloat(recordView,"translationY",0,300);
//        ObjectAnimator showAnimator = ObjectAnimator.ofFloat(showView,"alpha",1,0);



        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator).with(showAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.content, new RecordFragment());
//                ft.addToBackStack(null);
//                ft.commit();
//                isBackToThis = true;
            }
        });

        if (isToRecordPage){
            animatorSet.start();
        }else {
            animatorSet.reverse();
        }
    }

    private void closeInputMethod() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
            // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//没有显示则显示
            imm.hideSoftInputFromWindow(et_input.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void searchComplete(String result) {
        rootView.findViewById(R.id.result_tip).setVisibility(View.GONE);
        tv_result.setText(result);
        queryView.complete();
        isSearchBegin = false;
        animateShowAdd(false);
    }

    private void animateShowAdd(boolean reverse) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(btn_add, "translationY", 0, -DensityUtil.dpToPx(mContext, 55));
        animator.setDuration(500);
        if (reverse) {
            animator.reverse();
        } else {
            animator.start();
        }
    }
}
