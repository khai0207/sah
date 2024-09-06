package com.kqg.main.model;

import android.icu.text.SimpleDateFormat;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.constant.KV;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public class PayInfor implements Serializable {
    private static final long serialVersionUID = -2517849384456241479L;
    private String billId;
    private String itemId;
    private String itemSort;
    private int mhtOrderAmt;
    private String mhtOrderAmtDes;
    private String mhtOrderDetail;
    private String mhtOrderFinishTimeDes;
    private String mhtOrderName;
    private String mhtOrderNo;
    private String mhtOrderProvider;
    private long mhtOrderStartTime;
    private String mhtOrderStartTimeDes;
    private int mhtOrderTimeOut;
    private String mhtReserved;
    private String notifyUrl;
    private String res;
    private String roleId;
    private String zoneId;
    private SimpleDateFormat ndateFormat = new SimpleDateFormat(KV.FORMAT_TIME, Locale.CHINA);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);

    public String getItemSort() {
        return this.itemSort;
    }

    public void setItemSort(String str) {
        this.itemSort = str;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String str) {
        this.itemId = str;
    }

    public String getBillId() {
        return this.billId;
    }

    public void setBillId(String str) {
        this.billId = str;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String str) {
        this.roleId = str;
    }

    public String getZoneId() {
        return this.zoneId;
    }

    public void setZoneId(String str) {
        this.zoneId = str;
    }

    public PayInfor(PayInfor payInfor) {
        setMhtOrderAmt(payInfor.getMhtOrderAmt());
        setMhtOrderAmtDes(payInfor.getMhtOrderAmtDes());
        setMhtOrderDetail(payInfor.getMhtOrderDetail());
        setMhtOrderNo(payInfor.getMhtOrderNo());
        setMhtOrderName(payInfor.getMhtOrderName());
        setMhtOrderStartTime(payInfor.getMhtOrderStartTime());
        setMhtOrderTimeOut(payInfor.getMhtOrderTimeOut());
        setMhtReserved(payInfor.getMhtReserved());
        setNotifyUrl(payInfor.getNotifyUrl());
        setMhtOrderProvider(payInfor.getMhtOrderProvider());
        setRes(payInfor.getRes());
    }

    public void setSupportPayType(PayType... payTypeArr) {
        PaySelectTypeManager.initPayList(payTypeArr);
    }

    public PayInfor copy(PayInfor payInfor) {
        PayInfor payInfor2 = new PayInfor(payInfor.getMhtOrderName(), payInfor.getMhtOrderAmt(), payInfor.getMhtOrderDetail(), payInfor.getMhtOrderProvider(), payInfor.getMhtReserved());
        payInfor2.setMhtOrderNo(payInfor.getMhtOrderNo());
        payInfor2.setMhtOrderFinishTimeDes(this.ndateFormat.format(new Date(this.mhtOrderStartTime * 1000)));
        return payInfor2;
    }

    public String getMhtOrderFinishTimeDes() {
        return this.mhtOrderFinishTimeDes;
    }

    public void setMhtOrderFinishTimeDes(String str) {
        this.mhtOrderFinishTimeDes = str;
    }

    public String getMhtOrderAmtDes() {
        return this.mhtOrderAmtDes;
    }

    public void setMhtOrderAmtDes(String str) {
        this.mhtOrderAmtDes = str;
    }

    public String getMhtOrderName() {
        return this.mhtOrderName;
    }

    public void setMhtOrderName(String str) {
        this.mhtOrderName = str;
    }

    public int getMhtOrderAmt() {
        return this.mhtOrderAmt;
    }

    public void setMhtOrderAmt(int i) {
        this.mhtOrderAmt = i;
    }

    public String getMhtOrderDetail() {
        return this.mhtOrderDetail;
    }

    public void setMhtOrderDetail(String str) {
        this.mhtOrderDetail = str;
    }

    public String getMhtReserved() {
        return this.mhtReserved;
    }

    public void setMhtReserved(String str) {
        this.mhtReserved = str;
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public void setNotifyUrl(String str) {
        this.notifyUrl = str;
    }

    public long getMhtOrderStartTime() {
        return this.mhtOrderStartTime;
    }

    public String getMhtOrderStartTimeDes() {
        return this.mhtOrderStartTimeDes;
    }

    public void setMhtOrderStartTime(long j) {
        this.mhtOrderStartTime = j;
        this.mhtOrderStartTimeDes = this.dateFormat.format(new Date(j * 1000));
    }

    public String getMhtOrderNo() {
        return this.mhtOrderNo;
    }

    public void setMhtOrderNo(String str) {
        this.mhtOrderNo = str;
    }

    public int getMhtOrderTimeOut() {
        return this.mhtOrderTimeOut;
    }

    public void setMhtOrderTimeOut(int i) {
        this.mhtOrderTimeOut = i;
    }

    public PayInfor(String str, int i, String str2, String str3, String str4) {
        this.mhtOrderName = str;
        this.mhtOrderAmt = i;
        this.mhtOrderProvider = str3;
        double d = i;
        Double.isNaN(d);
        this.mhtOrderAmtDes = ((float) (d / 100.0d)) + KV.PRICE_EXT;
        this.mhtOrderDetail = str2;
        this.mhtReserved = processForPay(str4);
    }

    private String processForPay(String str) {
        StringBuffer stringBuffer = new StringBuffer(KaiQiGuSdk.getInstance().getCfg().getAppId() + "");
        stringBuffer.append("--");
        stringBuffer.append(UserManager.getInstance().getCurrentUser().getUsername());
        stringBuffer.append("--");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public PayInfor() {
    }

    public String toString() {
        return "PayInfor [notifyUrl=" + this.notifyUrl + ", mhtOrderStartTime=" + this.mhtOrderStartTime + ", mhtOrderNo=" + this.mhtOrderNo + ", mhtOrderName=" + this.mhtOrderName + ", mhtOrderAmt=" + this.mhtOrderAmt + ", mhtOrderDetail=" + this.mhtOrderDetail + ", mhtOrderAmtDes=" + this.mhtOrderAmtDes + ", mhtReserved=" + this.mhtReserved + ", mhtOrderTimeOut=" + this.mhtOrderTimeOut + "]";
    }

    public String getMhtOrderProvider() {
        return this.mhtOrderProvider;
    }

    public void setMhtOrderProvider(String str) {
        this.mhtOrderProvider = str;
    }

    public String getRes() {
        return this.res;
    }

    public void setRes(String str) {
        this.res = str;
    }
}
