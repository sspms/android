package com.shanshui.smartcommunity.android.view;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.shanshui.smartcommunity.android.model.PropertyIssue;

import java.util.List;

/**
 * Created by I336253 on 4/14/2018.
 */

public class PropertyIssuePopupMenu extends PopupMenu {
    private MenuItem[] menuItems;

    public PropertyIssuePopupMenu(Context context, View anchor, MenuItem[] menuItems) {
        super(context, anchor);
        this.menuItems = menuItems;
        if (this.menuItems != null) {
            int size = this.menuItems.length;
            for (int i = 0; i < size; i++) {
                Menu menu = getMenu();
                menu.add(android.view.Menu.NONE, android.view.Menu.FIRST + i, i, menuItems[i].title);
            }
        }
    }

    public static class MenuItem {
        private String title;
        private OnMenuClick onClick;

        public MenuItem(String title, OnMenuClick onClick) {
            this.title = title;
            this.onClick = onClick;
        }

        public String getTitle() {
            return title;
        }

        public OnMenuClick getOnClick() {
            return onClick;
        }
    }

    public interface OnMenuClick {
        void OnClick(PropertyIssue propertyIssue);
    }
}
