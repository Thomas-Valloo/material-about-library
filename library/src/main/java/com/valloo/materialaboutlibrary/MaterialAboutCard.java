package com.valloo.materialaboutlibrary;


import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;

import java.util.ArrayList;

public class MaterialAboutCard {

    public MaterialAboutCard() {
    }


    public ArrayList<MaterialAboutItem> getItems() {
        return items;
    }

    private CharSequence title = null;
    @StringRes
    private int titleRes = 0;

    @ColorInt
    private int titleColor = 0;

    private ArrayList<MaterialAboutItem> items = new ArrayList<>();

    public MaterialAboutCard title(CharSequence title) {
        this.title = title;
        this.titleRes = 0;
        return this;
    }

    public MaterialAboutCard title(@StringRes int titleRes) {
        this.titleRes = titleRes;
        this.title = null;
        return this;
    }

    public MaterialAboutCard titleColor(@ColorInt int color) {
        this.titleColor = color;
        return this;
    }

    public MaterialAboutCard addItem(MaterialAboutItem item) {
        this.items.add(item);
        return this;
    }

    View buildView(ViewGroup parent, Context context) {
        ViewGroup cardview = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.mal_material_about_list_card, parent, false);

        TextView titleView = (TextView) cardview.findViewById(R.id.mal_list_card_title);
        if (title != null) {
            titleView.setText(title);
        } else if (titleRes != 0) {
            titleView.setText(titleRes);
        } else {
            titleView.setVisibility(View.GONE);
        }

        if (titleView.getVisibility() == View.VISIBLE) {
            if (titleColor != 0) {
                titleView.setTextColor(titleColor);
            } else {
                titleView.setTextColor(titleView.getTextColors().getDefaultColor());
            }
        }

        LinearLayout itemContainer = (LinearLayout) cardview.findViewById(R.id.mal_card_container);
        for (MaterialAboutItem item : items) {
            View itemView = item.buildView(itemContainer, context);
            itemContainer.addView(itemView);
        }

        return cardview;
    }
}
