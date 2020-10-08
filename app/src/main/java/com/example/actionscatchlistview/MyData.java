package com.example.actionscatchlistview;

import android.widget.Button;

public class MyData {
    public String header;
    public int  text;
    public Button button;

    public MyData(String header, int text) {
        this.header = header;
        this.text = text;
      //  this.button = button;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return String.valueOf(text);
    }

    public void setText(int text) {
        this.text = text;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
