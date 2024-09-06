package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.ListRetirableGrantsResult;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.ListUnmarshaller;
import com.netease.nimlib.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class ListRetirableGrantsResultJsonUnmarshaller implements Unmarshaller<ListRetirableGrantsResult, JsonUnmarshallerContext> {
    private static ListRetirableGrantsResultJsonUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public ListRetirableGrantsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListRetirableGrantsResult listRetirableGrantsResult = new ListRetirableGrantsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Grants")) {
                listRetirableGrantsResult.setGrants(new ListUnmarshaller(GrantListEntryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("NextMarker")) {
                listRetirableGrantsResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Truncated")) {
                listRetirableGrantsResult.setTruncated(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listRetirableGrantsResult;
    }

    public static ListRetirableGrantsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListRetirableGrantsResultJsonUnmarshaller();
        }
        return instance;
    }
}
