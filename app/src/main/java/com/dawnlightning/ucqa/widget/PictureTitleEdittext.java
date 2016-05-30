package com.dawnlightning.ucqa.widget;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/15.
 */
public class PictureTitleEdittext extends EditText {
    private ArrayList<TextWatcher> mListeners = null;
    public PictureTitleEdittext(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher)
    {
        if (mListeners == null)
        {
            mListeners = new ArrayList<TextWatcher>();
        }
        mListeners.add(watcher);

        super.addTextChangedListener(watcher);
    }

    @Override
    public void removeTextChangedListener(TextWatcher watcher)
    {
        if (mListeners != null)
        {
            int i = mListeners.indexOf(watcher);
            if (i >= 0)
            {
                mListeners.remove(i);
            }
        }

        super.removeTextChangedListener(watcher);
    }

    public void clearTextChangedListeners()
    {
        if(mListeners != null)
        {
            for(TextWatcher watcher : mListeners)
            {
                super.removeTextChangedListener(watcher);
            }

            mListeners.clear();
            mListeners = null;
        }
    }
}
