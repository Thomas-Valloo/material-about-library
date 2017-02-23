package com.valloo.materialaboutlibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danielstone.materialaboutlibrary.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.View.GONE;

public class MaterialAboutItem {

    static final int GRAVITY_TOP = 0;
    static final int GRAVITY_MIDDLE = 1;
    static final int GRAVITY_BOTTOM = 2;

    View buildView(ViewGroup parent, Context context) {
        View view = LayoutInflater.from(context).inflate( R.layout.mal_material_about_action_item, parent, false);
        ImageView iconImageView = (ImageView) view.findViewById(R.id.mal_item_image);
        TextView textTextView = (TextView) view.findViewById(R.id.mal_item_text);
        TextView subTextTextView = (TextView) view.findViewById(R.id.mal_action_item_subtext);

        textTextView.setVisibility(View.VISIBLE);
        if (text != null) {
            textTextView.setText(text);
        } else if (textRes != 0) {
            textTextView.setText(textRes);
        } else {
            textTextView.setVisibility(GONE);
        }


        subTextTextView.setVisibility(View.VISIBLE);
        if (subText != null) {
            subTextTextView.setText(subText);
        } else if (subTextRes != 0) {
            subTextTextView.setText(subTextRes);
        } else {
            subTextTextView.setVisibility(GONE);
        }

        if (showIcon) {
            iconImageView.setVisibility(View.VISIBLE);
            if (icon != null) {
                iconImageView.setImageDrawable(icon);
            } else if (iconRes != 0) {
                iconImageView.setImageResource(iconRes);
            }
        } else {
            iconImageView.setVisibility(GONE);
        }

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iconImageView.getLayoutParams();
        switch (iconGravity) {
            case MaterialAboutItem.GRAVITY_TOP:
                params.gravity = Gravity.TOP;
                break;
            case MaterialAboutItem.GRAVITY_MIDDLE:
                params.gravity = Gravity.CENTER_VERTICAL;
                break;
            case MaterialAboutItem.GRAVITY_BOTTOM:
                params.gravity = Gravity.BOTTOM;
                break;
        }
        iconImageView.setLayoutParams(params);

        int pL = 0, pT = 0, pR = 0, pB = 0;
        if (Build.VERSION.SDK_INT < 21) {
            pL = view.getPaddingLeft();
            pT = view.getPaddingTop();
            pR = view.getPaddingRight();
            pB = view.getPaddingBottom();
        }

        if (onClickListener != null) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
            view.setBackgroundResource(outValue.resourceId);
            view.setOnClickListener(onClickListener);
            view.setSoundEffectsEnabled(true);
        } else {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, false);
            view.setBackgroundResource(outValue.resourceId);
            view.setOnClickListener(null);
            view.setSoundEffectsEnabled(false);
        }

        if (Build.VERSION.SDK_INT < 21) {
            view.setPadding(pL, pT, pR, pB);
        }

        return view;
    }


    public CharSequence getText() {
        return text;
    }

    public MaterialAboutItem setText(CharSequence text) {
        this.textRes = 0;
        this.text = text;
        return this;
    }

    public MaterialAboutItem setTextRes(int textRes) {
        this.text = null;
        this.textRes = textRes;
        return this;
    }

    public MaterialAboutItem setSubText(CharSequence subText) {
        this.subTextRes = 0;
        this.subText = subText;
        return this;
    }

    public MaterialAboutItem setSubTextRes(int subTextRes) {
        this.subText = null;
        this.subTextRes = subTextRes;
        return this;
    }

    public MaterialAboutItem setIcon(Drawable icon) {
        this.iconRes = 0;
        this.icon = icon;
        return this;
    }

    public MaterialAboutItem setIconRes(int iconRes) {
        this.icon = null;
        this.iconRes = iconRes;
        return this;
    }

    public MaterialAboutItem setShouldShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
        return this;
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GRAVITY_TOP, GRAVITY_MIDDLE, GRAVITY_BOTTOM})
    @interface IconGravity {
    }


    private View.OnClickListener onClickListener = null;
    private CharSequence text = null;
    @StringRes
    private int textRes = 0;
    private CharSequence subText = null;
    @StringRes
    private int subTextRes = 0;
    private Drawable icon = null;
    @DrawableRes
    private int iconRes = 0;
    private boolean showIcon = true;
    @IconGravity
    private int iconGravity = GRAVITY_MIDDLE;

    public MaterialAboutItem text(CharSequence text) {
        this.text = text;
        this.textRes = 0;
        return this;
    }

    public MaterialAboutItem text(@StringRes int text) {
        this.textRes = text;
        this.text = null;
        return this;
    }

    public MaterialAboutItem subText(CharSequence subText) {
        this.subText = subText;
        this.subTextRes = 0;
        return this;
    }

    public MaterialAboutItem subText(@StringRes int subTextRes) {
        this.subText = null;
        this.subTextRes = subTextRes;
        return this;
    }

    public MaterialAboutItem subTextHtml(String subTextHtml) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.subText = Html.fromHtml(subTextHtml, Html.FROM_HTML_MODE_LEGACY);
        } else {
            //noinspection deprecation
            this.subText = Html.fromHtml(subTextHtml);
        }
        this.subTextRes = 0;
        return this;
    }

    public MaterialAboutItem icon(Drawable icon) {
        this.icon = icon;
        this.iconRes = 0;
        return this;
    }

    public MaterialAboutItem icon(@DrawableRes int iconRes) {
        this.icon = null;
        this.iconRes = iconRes;
        return this;
    }

    public MaterialAboutItem showIcon(boolean showIcon) {
        this.showIcon = showIcon;
        return this;
    }

    public MaterialAboutItem setIconGravity(@IconGravity int iconGravity) {
        this.iconGravity = iconGravity;
        return this;
    }

    public MaterialAboutItem setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }
}
