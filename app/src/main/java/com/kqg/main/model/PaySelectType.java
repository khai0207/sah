package com.kqg.main.model;

/* loaded from: classes.dex */
public class PaySelectType {
    private String paySelectDes;
    private int paySelectLogo;
    private boolean selected = false;
    private int type;

    public PaySelectType(int i, int i2, String str) {
        this.type = i;
        this.paySelectLogo = i2;
        this.paySelectDes = str;
    }

    public int getPaySelectLogo() {
        return this.paySelectLogo;
    }

    public void setPaySelectLogo(int i) {
        this.paySelectLogo = i;
    }

    public String getPaySelectDes() {
        return this.paySelectDes;
    }

    public void setPaySelectDes(String str) {
        this.paySelectDes = str;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
