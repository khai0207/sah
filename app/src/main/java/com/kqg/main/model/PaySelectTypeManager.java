package com.kqg.main.model;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PaySelectTypeManager {
    private static List<PaySelectType> types = new ArrayList();

    public static void initPayList(PayType... payTypeArr) {
        types.clear();
        int length = payTypeArr.length;
        int i = 0;
        while (i < length) {
            PaySelectType paySelectType = payTypeArr[i].toPaySelectType();
            paySelectType.setSelected(i == 0);
            types.add(paySelectType);
            i++;
        }
    }

    public static List<PaySelectType> getTypes() {
        return types;
    }

    public static boolean isNoPayTypeSet() {
        return types.size() == 0;
    }

    public static void clearSelect() {
        int size = types.size();
        for (int i = 0; i < size; i++) {
            PaySelectType paySelectType = types.get(i);
            if (i == 0) {
                paySelectType.setSelected(true);
            } else {
                paySelectType.setSelected(false);
            }
        }
    }
}
