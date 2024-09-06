package com.android.pc.ioc.view;

import com.android.pc.ioc.event.EventBus;

/* loaded from: classes.dex */
public class PullToRefreshManager {
    private static PullToRefreshManager manager;
    EventBus eventBus = EventBus.getDefault();
    private String pull_label = "下拉刷新";
    private String release_label = "松开后刷新";
    private String footer_pull_label = "上拉加载更多";
    private String footer_refreshing_label = "加载中...";
    private String refreshing_label = "刷新中...";
    private String updateTime = "刚刚刷新";
    private int limit = 0;

    public static PullToRefreshManager getInstance() {
        if (manager == null) {
            manager = new PullToRefreshManager();
        }
        return manager;
    }

    public String getPull_label() {
        return this.pull_label;
    }

    public void setPull_label(String str) {
        this.pull_label = str;
    }

    public String getRelease_label() {
        return this.release_label;
    }

    public void setRelease_label(String str) {
        this.release_label = str;
    }

    public String getFooter_pull_label() {
        return this.footer_pull_label;
    }

    public void setFooter_pull_label(String str) {
        this.footer_pull_label = str;
    }

    public String getFooter_refreshing_label() {
        return this.footer_refreshing_label;
    }

    public void setFooter_refreshing_label(String str) {
        this.footer_refreshing_label = str;
    }

    public String getRefreshing_label() {
        return this.refreshing_label;
    }

    public void setRefreshing_label(String str) {
        this.refreshing_label = str;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public void onFooterRefreshComplete() {
        RefershEntity refershEntity = new RefershEntity();
        refershEntity.setType(1);
        this.eventBus.post(refershEntity);
    }

    public void onHeaderRefreshComplete() {
        RefershEntity refershEntity = new RefershEntity();
        refershEntity.setType(2);
        this.eventBus.post(refershEntity);
    }

    public void footerEnable() {
        RefershEntity refershEntity = new RefershEntity();
        refershEntity.setType(5);
        this.eventBus.post(refershEntity);
    }

    public void headerEnable() {
        RefershEntity refershEntity = new RefershEntity();
        refershEntity.setType(6);
        this.eventBus.post(refershEntity);
    }

    public void footerUnable() {
        RefershEntity refershEntity = new RefershEntity();
        refershEntity.setType(3);
        this.eventBus.post(refershEntity);
    }

    public void headerUnable() {
        RefershEntity refershEntity = new RefershEntity();
        refershEntity.setType(4);
        this.eventBus.post(refershEntity);
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int i) {
        this.limit = i;
    }
}
