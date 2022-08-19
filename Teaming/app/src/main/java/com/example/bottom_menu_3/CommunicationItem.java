package com.example.bottom_menu_3;

public class CommunicationItem {

    public static final int TYPE_RECEIVE = 0;
    public static final int TYPE_SEND = 1;

    private int type;
    private String string;
    private Integer integer;

    public CommunicationItem(int type, String string, Integer integer) {
        this.type = type;
        this.string = string;
        this.integer = integer;
    }

    public int getType() {
        return type;
    }

    public String getString() {
        return string;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setString(String string) {
        this.string = string;
    }

}
