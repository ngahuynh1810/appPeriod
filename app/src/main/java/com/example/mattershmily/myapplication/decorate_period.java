package com.example.mattershmily.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;


/**
 * Created by HuynhNga on 02/12/2017.
 */

public class decorate_period implements DayViewDecorator {

    private final Drawable drawable;
    private final CalendarDay day;
    private final int color;

    public decorate_period(MaterialCalendarView view, Date date, int color) {
        this.day = CalendarDay.from(date);
        this.color =R.color.colorPredict;
        this.drawable = createTintedDrawable(view.getContext(), color);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        if (this.day.equals(day)) {
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }

    private static Drawable createTintedDrawable(Context context, int color) {
        return applyTint(createBaseDrawable(context), R.color.colorPredict);
    }

    private static Drawable applyTint(Drawable drawable, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }

    private static Drawable createBaseDrawable(Context context) {
        return ContextCompat.getDrawable(context, R.drawable.mood_on);
    }
}