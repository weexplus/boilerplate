package com.weexplus.activity;

import com.weex.weexplus.R;

public class WxpPresentActivity extends WxpActivity {

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.dismiss);
    }

}
