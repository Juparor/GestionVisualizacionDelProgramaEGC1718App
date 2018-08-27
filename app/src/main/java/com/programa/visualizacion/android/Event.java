package com.programa.visualizacion.android;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jupar on 06/02/2018.
 */

public class Event implements Comparable, Serializable {

    private Integer day;
    private Date beginningHour;
    private Date endingHour;
    private String title;
    private String speakers;
    private String description;

    public Event() {
        super();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeakers() {
        return speakers;
    }

    public void setSpeakers(String speakers) {
        this.speakers = speakers;
    }

    public boolean equals(Object obj) {

        if (obj instanceof Event) {

            Event event = (Event) obj;

            if (this.day.equals(event.day) && this.beginningHour.equals(event.beginningHour) 
                    && this.endingHour.equals(event.endingHour) && this.title.equals(event.title) 
                    && this.description.equals(event.description) && this.speakers.equals(event.speakers)) {
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
        int result = 0;

        Event event = (Event) o;

        if (this.beginningHour.compareTo(event.beginningHour) == -1) {
            result = -1;
        } else if (this.beginningHour.compareTo(event.beginningHour) == 1) {
            result = 1;
        } else {
            if (this.endingHour.compareTo(event.endingHour) == -1) {
                result = -1;
            } else if (this.endingHour.compareTo(event.endingHour) == 1) {
                result = 1;
            } else {
                result = 0;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "day=" + day +
                ", beginningHour=" + beginningHour +
                ", endingHour=" + endingHour +
                ", title='" + title + '\'' +
                ", speakers='" + speakers + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
