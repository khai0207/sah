package com.components;

import android.app.Activity;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class ComponentStatistics implements StatisticsInterface {
    @Override // com.components.StatisticsInterface
    public void logToBI(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.StatisticsInterface
    public void onCoinChangedToBi(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.StatisticsInterface
    public void onEventToBi(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.StatisticsInterface
    public void onUserLevelChangeToBi(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.StatisticsInterface
    public void payBeginToBi(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.StatisticsInterface
    public void paySuccessToBi(Activity activity, JSONObject jSONObject) {
    }
}
