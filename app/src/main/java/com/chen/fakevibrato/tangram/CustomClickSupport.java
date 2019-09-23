package com.chen.fakevibrato.tangram;

import android.view.View;
import android.widget.Toast;

import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.support.SimpleClickSupport;

public class CustomClickSupport extends SimpleClickSupport {
    public CustomClickSupport() {
        setOptimizedMode(true);
    }

    @Override
    public void defaultClick(View targetView, BaseCell cell, int eventType) {
        super.defaultClick(targetView, cell, eventType);
        Toast.makeText(targetView.getContext(),
                "您点击了组件，type=" + cell.stringType + ", pos=" + cell.pos, Toast.LENGTH_SHORT).show();
    }
}
