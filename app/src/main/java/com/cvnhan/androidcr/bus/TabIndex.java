package com.cvnhan.androidcr.bus;

/**
 * Created by nhancao on 6/12/16.
 */
public enum TabIndex {
    TAB1(0),
    TAB2(1),
    TAB3(2);

    private int tabPosition;

    TabIndex(int tabPosition) {
        this.tabPosition = tabPosition;
    }

    public static TabIndex getTabIndex(int tabPosition) {
        for (TabIndex index : TabIndex.values()) {
            if (index.tabPosition == tabPosition)
                return index;
        }
        return TabIndex.TAB1;
    }
}
