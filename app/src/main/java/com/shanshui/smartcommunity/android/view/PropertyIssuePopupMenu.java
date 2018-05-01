package com.shanshui.smartcommunity.android.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.shanshui.smartcommunity.android.R;
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
            Menu menu = getMenu();
            for (int i = 0; i < size; i++) {
                // something like the html markup...
                SpannableStringBuilder builder = new SpannableStringBuilder(menuItems[i].title);
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLACK);
                builder.setSpan(colorSpan, 0, menuItems[i].title.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                menu.add(Menu.NONE, Menu.FIRST + i, i, builder);
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
