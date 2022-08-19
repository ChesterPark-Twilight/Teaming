package com.example.bottom_menu_3;

public class ListItem {

    private String label;
    private String number;
    private String date;
    private String time;
    private String text;
    private Integer icon;

    public ListItem(String label, String number, String date, String time, String text, Integer icon) {
        this.label = label;
        this.number = number;
        this.date = date;
        this.time = time;
        this.text = text;
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public Integer getIcon() {
        return icon;
    }
}
