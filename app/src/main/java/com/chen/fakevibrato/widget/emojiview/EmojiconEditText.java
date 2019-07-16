package com.chen.fakevibrato.widget.emojiview;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * @author Created by CHEN on 2019/7/16
 * @email 188669@163.com
 */
public class EmojiconEditText extends EditText {

    private int mEmojiconSize;
    private int mEmojiconTextSize;
    private int mTextStart = 0;
    private int mTextLength = -1;
    private boolean mUseSystemDefault = false;

    public EmojiconEditText(Context context) {
        super(context);
        init(null);
    }

    public EmojiconEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmojiconEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    private void init(AttributeSet attrs) {
        mEmojiconTextSize = (int) getTextSize();
        mEmojiconSize = (int) getTextSize();
        setText(getText());
    }

    public void setTextWithWidth(CharSequence text, int limitedWidth) {
        if (TextUtils.isEmpty(text)) {
            super.setText(text);
            return;
        }
        if (limitedWidth < 0) {
            limitedWidth = this.getMeasuredWidth() - getPaddingRight() - getPaddingLeft();
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        EmojiconHandler.addEmojis(getContext(), builder, mEmojiconSize, mEmojiconTextSize, mTextStart, mTextLength, mUseSystemDefault);
        CharSequence trucatedText = TextUtils.ellipsize(builder, getPaint(), limitedWidth, getEllipsize());
        super.setText(trucatedText, BufferType.SPANNABLE);
    }

    @Override
    public void append(CharSequence text, int start, int end) {
        if (!TextUtils.isEmpty(text)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            EmojiconHandler.addEmojis(getContext(), builder, mEmojiconSize, mEmojiconTextSize, mTextStart, mTextLength, mUseSystemDefault);
            text = builder;
        }
        super.append(text, start, end);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!TextUtils.isEmpty(text)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            EmojiconHandler.addEmojis(getContext(), builder, mEmojiconSize, mEmojiconTextSize, mTextStart, mTextLength, mUseSystemDefault);
            text = builder;
        }
        super.setText(text, type);
    }


    /**
     * Set the size of emojicon in pixels.
     */
    public void setEmojiconSize(int pixels) {
        mEmojiconSize = pixels;
        super.setText(getText());
    }

    /**
     * Set whether to use system default emojicon
     */
    public void setUseSystemDefault(boolean useSystemDefault) {
        mUseSystemDefault = useSystemDefault;
    }
}
