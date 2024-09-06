package com.netease.nimlib.amazonaws.services.kms.model.transform;

import com.netease.nimlib.amazonaws.services.kms.model.Tag;
import com.netease.nimlib.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class TagJsonMarshaller {
    private static TagJsonMarshaller instance;

    TagJsonMarshaller() {
    }

    public void marshall(Tag tag, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (tag.getTagKey() != null) {
            String tagKey = tag.getTagKey();
            awsJsonWriter.name("TagKey");
            awsJsonWriter.value(tagKey);
        }
        if (tag.getTagValue() != null) {
            String tagValue = tag.getTagValue();
            awsJsonWriter.name("TagValue");
            awsJsonWriter.value(tagValue);
        }
        awsJsonWriter.endObject();
    }

    public static TagJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TagJsonMarshaller();
        }
        return instance;
    }
}
