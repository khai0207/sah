package com.netease.nimlib.sdk.team.model;

import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.msg.attachment.NotificationAttachmentWithExtension;
import com.netease.nimlib.sdk.team.constant.TeamAllMuteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamBeInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamExtensionUpdateModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamFieldEnum;
import com.netease.nimlib.sdk.team.constant.TeamInviteModeEnum;
import com.netease.nimlib.sdk.team.constant.TeamUpdateModeEnum;
import com.netease.nimlib.sdk.team.constant.VerifyTypeEnum;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UpdateTeamAttachment extends NotificationAttachmentWithExtension {
    private HashMap<TeamFieldEnum, Object> updatedFields = new HashMap<>(1);

    @Override // com.netease.nimlib.sdk.msg.attachment.NotificationAttachmentWithExtension, com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public final void parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        JSONObject g = k.g(jSONObject, "tinfo");
        if (g == null) {
            return;
        }
        for (TeamFieldEnum teamFieldEnum : TeamFieldEnum.values()) {
            String valueOf = String.valueOf(teamFieldEnum.getValue());
            if (g.has(valueOf)) {
                Object obj = null;
                if (teamFieldEnum.getFieldType() == String.class) {
                    obj = k.e(g, valueOf);
                } else if (teamFieldEnum.getFieldType() == VerifyTypeEnum.class) {
                    obj = VerifyTypeEnum.typeOfValue(k.a(g, valueOf));
                } else if (teamFieldEnum.getFieldType() == TeamBeInviteModeEnum.class) {
                    obj = TeamBeInviteModeEnum.typeOfValue(k.a(g, valueOf));
                } else if (teamFieldEnum.getFieldType() == TeamInviteModeEnum.class) {
                    obj = TeamInviteModeEnum.typeOfValue(k.a(g, valueOf));
                } else if (teamFieldEnum.getFieldType() == TeamUpdateModeEnum.class) {
                    obj = TeamUpdateModeEnum.typeOfValue(k.a(g, valueOf));
                } else if (teamFieldEnum.getFieldType() == TeamExtensionUpdateModeEnum.class) {
                    obj = TeamExtensionUpdateModeEnum.typeOfValue(k.a(g, valueOf));
                } else if (teamFieldEnum.getFieldType() == Integer.class) {
                    obj = Integer.valueOf(k.a(g, valueOf));
                } else if (teamFieldEnum.getFieldType() == TeamAllMuteModeEnum.class) {
                    obj = TeamAllMuteModeEnum.typeOfValue(k.a(g, valueOf));
                }
                this.updatedFields.put(teamFieldEnum, obj);
            }
        }
    }

    public Object getValue() {
        if (this.updatedFields.size() > 0) {
            return this.updatedFields.entrySet().iterator().next().getValue();
        }
        return null;
    }

    public TeamFieldEnum getField() {
        if (this.updatedFields.size() > 0) {
            return this.updatedFields.entrySet().iterator().next().getKey();
        }
        return null;
    }

    public Map<TeamFieldEnum, Object> getUpdatedFields() {
        return this.updatedFields;
    }
}
