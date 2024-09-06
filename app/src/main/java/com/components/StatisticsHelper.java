package com.components;

import android.app.Activity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class StatisticsHelper implements StatisticsInterface {
    private List<ComponentStatistics> arr = new ArrayList();

    public void addComponent(ComponentStatistics componentStatistics) {
        this.arr.add(componentStatistics);
    }

    @Override // com.components.StatisticsInterface
    public void onResume(Activity activity) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().onResume(activity);
        }
    }

    @Override // com.components.StatisticsInterface
    public void onPause(Activity activity) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().onPause(activity);
        }
    }

    @Override // com.components.StatisticsInterface
    public void initChannel(Activity activity, String str) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().initChannel(activity, str);
        }
    }

    @Override // com.components.StatisticsInterface
    public void logToBI(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().logToBI(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void sdkInit(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().sdkInit(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void logIn(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().logIn(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void logOut(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().logOut(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void checkUpdate(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().checkUpdate(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void buyProduct(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().isLogined(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void showGameCenter(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().isLogined(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void isLogined(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().isLogined(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void sdkSwitchAccount(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().sdkSwitchAccount(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void onUserLevelChangeToBi(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().onUserLevelChangeToBi(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void payBeginToBi(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().payBeginToBi(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void paySuccessToBi(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().paySuccessToBi(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void onCoinChangedToBi(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().onCoinChangedToBi(activity, jSONObject);
        }
    }

    @Override // com.components.StatisticsInterface
    public void onEventToBi(Activity activity, JSONObject jSONObject) {
        Iterator<ComponentStatistics> it = this.arr.iterator();
        while (it.hasNext()) {
            it.next().onEventToBi(activity, jSONObject);
        }
    }
}
