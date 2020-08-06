package com.fm.designstar.events;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/10/17 16:07
 * @update : 2018/10/17
 */
public class ImageDeleteEvent {
    private String url;

    public ImageDeleteEvent(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
