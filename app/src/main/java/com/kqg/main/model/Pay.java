package com.kqg.main.model;

/* loaded from: classes.dex */
public class Pay extends CommonEntity {
    private PayInfor data;

    public PayInfor getData() {
        return this.data;
    }

    public void setData(PayInfor payInfor) {
        this.data = payInfor;
    }

    @Override // com.kqg.main.model.CommonEntity
    public String toString() {
        return "Pay [data=" + this.data + ", toString()=" + super.toString() + "]";
    }
}
