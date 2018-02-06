package com.programa.visualizacion.android;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by jupar on 06/02/2018.
 */

public class Event implements Comparable {

    private Date beginningHour;
    private Date endingHour;
    private String title;

    public Event() {
        super();
    }

    public Date getBeginningHour() {
        return beginningHour;
    }

    public void setBeginningHour(Date beginningHour) {
        this.beginningHour = beginningHour;
    }

    public Date getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(Date endingHour) {
        this.endingHour = endingHour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean equals(Object obj) {

        if (obj instanceof Event) {

            Event event = (Event) obj;

            if (this.beginningHour.equals(event.beginningHour) && this.endingHour.equals(event.endingHour) && this.title.equals(event.title)) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    @Override
    public int compareTo(@NonNull Object o) {
        int resultado = 0;

        Event event = (Event) o;

        if (this.beginningHour.compareTo(event.beginningHour) == -1) {
            resultado = -1;
        } else if (this.beginningHour.compareTo(event.beginningHour) == 1) {
            resultado = 1;
        } else {
            if (this.endingHour.compareTo(event.endingHour) == -1) {
                resultado = -1;
            } else if (this.endingHour.compareTo(event.endingHour) == 1) {
                resultado = 1;
            } else {
                resultado = 0;
            }
        }
        return resultado;
    }
}
