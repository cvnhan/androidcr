package com.cvnhan.core.ui.view;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cvnhan.core.R;

public class NCMCViewNoNetwork extends RelativeLayout {

    private ImageView ivIcon;

    public NCMCViewNoNetwork(Context context, AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_no_network, this);
    }

    public NCMCViewNoNetwork(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NCMCViewNoNetwork(Context context) {
        this(context, null, 0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivIcon = (ImageView) this.findViewById(R.id.ivIcon);
    }

    public ImageView getIconView() {
        return ivIcon;
    }
}
