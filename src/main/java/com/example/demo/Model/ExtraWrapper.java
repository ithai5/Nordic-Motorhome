package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

public class ExtraWrapper {
    private List<Extra> extras;

    public ExtraWrapper() {}

    public ExtraWrapper(List<Extra> extras) {
        this.extras = extras;
    }

    public void addExtra(Extra extra) {
        this.extras.add(extra);
    }

    public Extra getExtra(int i) {
        return this.extras.get(i);
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "ExtraWrapper{" +
                "extras=" + extras +
                '}';
    }
}
