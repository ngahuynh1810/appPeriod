package com.example.mattershmily.myapplication;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by HuynhNga on 29/11/2017.
 */

public class Event_ovilation implements DayViewDecorator {

    private final int color;
    private final HashSet<CalendarDay> dates;
    private final Drawable drawable;
    public Event_ovilation(int color, Collection<CalendarDay> dates, Activity context) {
        this.color = R.color.colorPrimary;
        this.dates = new HashSet<>(dates);
        drawable = context.getResources().getDrawable(R.drawable.mood_on);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5,color));
       // view.setSelectionDrawable(getDrawable(R.drawable.select_date));
        //view.addSpan(new ForegroundColorSpan(Color.RED));
      // Drawable highlightDrawable = new ColorDrawable(Color.parseColor("#F77D83"));
//        view.setBackgroundDrawable(highlightDrawable);
//        view.setSelectionDrawable(drawable);
    }



}