package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityResult;
import com.netease.nimlib.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.netease.nimlib.amazonaws.transform.StaxUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
public class AssumeRoleWithWebIdentityResultStaxUnmarshaller implements Unmarshaller<AssumeRoleWithWebIdentityResult, StaxUnmarshallerContext> {
    private static AssumeRoleWithWebIdentityResultStaxUnmarshaller instance;

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AssumeRoleWithWebIdentityResult unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        AssumeRoleWithWebIdentityResult assumeRoleWithWebIdentityResult = new AssumeRoleWithWebIdentityResult();
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i += 2;
        }
        while (true) {
            int nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent == 1) {
                break;
            }
            if (nextEvent == 2) {
                if (staxUnmarshallerContext.testExpression("Credentials", i)) {
                    assumeRoleWithWebIdentityResult.setCredentials(CredentialsStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("SubjectFromWebIdentityToken", i)) {
                    assumeRoleWithWebIdentityResult.setSubjectFromWebIdentityToken(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("AssumedRoleUser", i)) {
                    assumeRoleWithWebIdentityResult.setAssumedRoleUser(AssumedRoleUserStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("PackedPolicySize", i)) {
                    assumeRoleWithWebIdentityResult.setPackedPolicySize(SimpleTypeStaxUnmarshallers.IntegerStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("Provider", i)) {
                    assumeRoleWithWebIdentityResult.setProvider(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("Audience", i)) {
                    assumeRoleWithWebIdentityResult.setAudience(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("SourceIdentity", i)) {
                    assumeRoleWithWebIdentityResult.setSourceIdentity(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                break;
            }
        }
        return assumeRoleWithWebIdentityResult;
    }

    public static AssumeRoleWithWebIdentityResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AssumeRoleWithWebIdentityResultStaxUnmarshaller();
        }
        return instance;
    }
}
