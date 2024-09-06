package com.netease.nimlib.sdk.msg;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.j.a;
import com.netease.nimlib.o.c;
import com.netease.nimlib.o.w;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.msg.attachment.AudioAttachment;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.LocationAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageRobotInfo;
import com.netease.nimlib.sdk.robot.model.RobotAttachment;
import com.netease.nimlib.sdk.util.UriUtils;
import com.netease.nimlib.session.IMMessageImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MessageBuilder {
    public static IMMessage createTextMessage(String str, SessionTypeEnum sessionTypeEnum, String str2) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.text.getValue());
        initSendMessage.setContent(str2);
        return initSendMessage;
    }

    public static IMMessage createImageMessage(String str, SessionTypeEnum sessionTypeEnum, File file) {
        return createImageMessage(str, sessionTypeEnum, file, (String) null, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createImageMessage(Context context, String str, SessionTypeEnum sessionTypeEnum, Uri uri) {
        return createImageMessage(context, str, sessionTypeEnum, uri, null, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createImageMessage(String str, SessionTypeEnum sessionTypeEnum, File file, String str2) {
        return createImageMessage(str, sessionTypeEnum, file, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createImageMessage(Context context, String str, SessionTypeEnum sessionTypeEnum, Uri uri, String str2) {
        return createImageMessage(context, str, sessionTypeEnum, uri, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createImageMessage(String str, SessionTypeEnum sessionTypeEnum, File file, String str2, String str3) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.image.getValue());
        initSendMessage.setAttachment(createImageAttachment(file, str2, str3));
        return initSendMessage;
    }

    public static IMMessage createImageMessage(Context context, String str, SessionTypeEnum sessionTypeEnum, Uri uri, String str2, String str3) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.image.getValue());
        initSendMessage.setAttachment(createImageAttachment(context, uri, str2, str3));
        return initSendMessage;
    }

    public static IMMessage createAudioMessage(String str, SessionTypeEnum sessionTypeEnum, File file, long j) {
        return createAudioMessage(str, sessionTypeEnum, file, j, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createAudioMessage(Context context, String str, SessionTypeEnum sessionTypeEnum, Uri uri, long j) {
        return createAudioMessage(context, str, sessionTypeEnum, uri, j, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createAudioMessage(String str, SessionTypeEnum sessionTypeEnum, File file, long j, String str2) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.audio.getValue());
        if (j > 0 && j < 1000) {
            j = 1000;
        }
        initSendMessage.setAttachment(createAudioAttachment(file, j, str2));
        return initSendMessage;
    }

    public static IMMessage createAudioMessage(Context context, String str, SessionTypeEnum sessionTypeEnum, Uri uri, long j, String str2) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.audio.getValue());
        if (j > 0 && j < 1000) {
            j = 1000;
        }
        initSendMessage.setAttachment(createAudioAttachment(context, uri, j, str2));
        return initSendMessage;
    }

    public static IMMessage createLocationMessage(String str, SessionTypeEnum sessionTypeEnum, double d, double d2, String str2) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.location.getValue());
        LocationAttachment locationAttachment = new LocationAttachment();
        locationAttachment.setLatitude(d);
        locationAttachment.setLongitude(d2);
        locationAttachment.setAddress(str2);
        initSendMessage.setAttachStatus(AttachStatusEnum.transferred);
        initSendMessage.setAttachment(locationAttachment);
        return initSendMessage;
    }

    public static IMMessage createVideoMessage(String str, SessionTypeEnum sessionTypeEnum, File file, long j, int i, int i2, String str2) {
        return createVideoMessage(str, sessionTypeEnum, file, j, i, i2, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createVideoMessage(Context context, String str, SessionTypeEnum sessionTypeEnum, Uri uri, long j, int i, int i2, String str2) {
        return createVideoMessage(context, str, sessionTypeEnum, uri, j, i, i2, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createVideoMessage(String str, SessionTypeEnum sessionTypeEnum, File file, long j, int i, int i2, String str2, String str3) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.video.getValue());
        VideoAttachment createVideoAttachment = createVideoAttachment(file, j, i, i2, str2, str3);
        initSendMessage.setAttachment(createVideoAttachment);
        c.a(file.getPath(), createVideoAttachment.getThumbPathForSave());
        return initSendMessage;
    }

    public static IMMessage createVideoMessage(Context context, String str, SessionTypeEnum sessionTypeEnum, Uri uri, long j, int i, int i2, String str2, String str3) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.video.getValue());
        VideoAttachment createVideoAttachment = createVideoAttachment(context, uri, j, i, i2, str2, str3);
        initSendMessage.setAttachment(createVideoAttachment);
        c.a(uri, createVideoAttachment.getThumbPathForSave());
        return initSendMessage;
    }

    public static IMMessage createFileMessage(String str, SessionTypeEnum sessionTypeEnum, File file, String str2) {
        return createFileMessage(str, sessionTypeEnum, file, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createFileMessage(Context context, String str, SessionTypeEnum sessionTypeEnum, Uri uri, String str2) {
        return createFileMessage(context, str, sessionTypeEnum, uri, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createFileMessage(String str, SessionTypeEnum sessionTypeEnum, File file, String str2, String str3) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.file.getValue());
        initSendMessage.setAttachment(createFileAttachment(file, str2, str3));
        return initSendMessage;
    }

    public static IMMessage createFileMessage(Context context, String str, SessionTypeEnum sessionTypeEnum, Uri uri, String str2, String str3) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.file.getValue());
        initSendMessage.setAttachment(createFileAttachment(context, uri, str2, str3));
        return initSendMessage;
    }

    public static IMMessage createTipMessage(String str, SessionTypeEnum sessionTypeEnum) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.tip.getValue());
        return initSendMessage;
    }

    public static IMMessage createRobotTextMessage(String str, SessionTypeEnum sessionTypeEnum, String str2, MessageRobotInfo messageRobotInfo) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.text.getValue());
        initSendMessage.setContent(str2);
        initSendMessage.setRobotInfo(messageRobotInfo);
        return initSendMessage;
    }

    public static IMMessage createRobotTipMessage(String str, SessionTypeEnum sessionTypeEnum, MessageRobotInfo messageRobotInfo) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.tip.getValue());
        initSendMessage.setRobotInfo(messageRobotInfo);
        return initSendMessage;
    }

    public static IMMessage createCustomMessage(String str, SessionTypeEnum sessionTypeEnum, MsgAttachment msgAttachment) {
        return createCustomMessage(str, sessionTypeEnum, null, msgAttachment, null);
    }

    public static IMMessage createCustomMessage(String str, SessionTypeEnum sessionTypeEnum, String str2, MsgAttachment msgAttachment) {
        return createCustomMessage(str, sessionTypeEnum, str2, msgAttachment, null);
    }

    public static IMMessage createCustomMessage(String str, SessionTypeEnum sessionTypeEnum, String str2, MsgAttachment msgAttachment, CustomMessageConfig customMessageConfig) {
        return createCustomMessage(str, sessionTypeEnum, str2, msgAttachment, customMessageConfig, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static IMMessage createCustomMessage(String str, SessionTypeEnum sessionTypeEnum, String str2, MsgAttachment msgAttachment, CustomMessageConfig customMessageConfig, String str3) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.custom.getValue());
        initSendMessage.setContent(str2);
        if (msgAttachment != null && (msgAttachment instanceof FileAttachment)) {
            ((FileAttachment) msgAttachment).setNosTokenSceneKey(str3);
        }
        initSendMessage.setAttachment(msgAttachment);
        initSendMessage.setConfig(customMessageConfig);
        return initSendMessage;
    }

    public static IMMessage createRobotMessage(String str, SessionTypeEnum sessionTypeEnum, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (TextUtils.isEmpty(str4) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("Invalid param, type and robot account should not be null");
        }
        if (str4.equals("01") && str5 == null) {
            throw new IllegalArgumentException("Invalid param, content should not be null");
        }
        if (str4.equals("03") && TextUtils.isEmpty(str6)) {
            throw new IllegalArgumentException("Invalid param, target should not be null");
        }
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.robot.getValue());
        RobotAttachment robotAttachment = new RobotAttachment();
        robotAttachment.initSend(str2, str4, str5, str6, str7);
        initSendMessage.setAttachment(robotAttachment);
        initSendMessage.setContent(str3);
        return initSendMessage;
    }

    public static IMMessage createNrtcNetcallMessage(String str, SessionTypeEnum sessionTypeEnum, MsgAttachment msgAttachment) {
        IMMessageImpl initSendMessage = initSendMessage(str, sessionTypeEnum);
        initSendMessage.setMsgType(MsgTypeEnum.nrtc_netcall.getValue());
        initSendMessage.setAttachment(msgAttachment);
        return initSendMessage;
    }

    public static IMMessage createEmptyMessage(String str, SessionTypeEnum sessionTypeEnum, long j) {
        IMMessageImpl iMMessageImpl = new IMMessageImpl();
        iMMessageImpl.setSessionId(str);
        iMMessageImpl.setSessionType(sessionTypeEnum);
        iMMessageImpl.setTime(j);
        return iMMessageImpl;
    }

    public static IMMessage createForwardMessage(IMMessage iMMessage, String str, SessionTypeEnum sessionTypeEnum) {
        if (iMMessage.getMsgType() == MsgTypeEnum.notification || iMMessage.getMsgType() == MsgTypeEnum.avchat || iMMessage.getMsgType() == MsgTypeEnum.robot) {
            return null;
        }
        IMMessageImpl deepClone = ((IMMessageImpl) iMMessage).deepClone();
        if (deepClone != null) {
            deepClone.setSessionId(str);
            deepClone.setSessionType(sessionTypeEnum);
            deepClone.setUuid(w.b());
            deepClone.setFromAccount(com.netease.nimlib.c.n());
            deepClone.setDirect(MsgDirectionEnum.Out);
            deepClone.setStatus(MsgStatusEnum.sending);
            deepClone.setTime(y.a());
            deepClone.setServerId(0L);
            deepClone.setMessageId(0L);
            deepClone.setMsgAck(false);
            deepClone.setHasSendAck(false);
            deepClone.setTeamMsgAckCount(0);
            deepClone.setTeamMsgUnAckCount(0);
            MsgAttachment attachment = deepClone.getAttachment();
            if (attachment != null && (attachment instanceof FileAttachment) && !TextUtils.isEmpty(((FileAttachment) attachment).getUrl())) {
                deepClone.setAttachStatus(AttachStatusEnum.def);
            }
        }
        return deepClone;
    }

    private static IMMessageImpl initSendMessage(String str, SessionTypeEnum sessionTypeEnum) {
        IMMessageImpl iMMessageImpl = new IMMessageImpl();
        iMMessageImpl.setUuid(w.b());
        iMMessageImpl.setSessionId(str);
        iMMessageImpl.setFromAccount(com.netease.nimlib.c.n());
        iMMessageImpl.setDirect(MsgDirectionEnum.Out);
        iMMessageImpl.setStatus(MsgStatusEnum.sending);
        iMMessageImpl.setSessionType(sessionTypeEnum);
        iMMessageImpl.setTime(y.a());
        return iMMessageImpl;
    }

    public static String createForwardMessageListFileDetail(List<? extends IMMessage> list) {
        String sessionId;
        if (list == null || list.isEmpty() || (sessionId = list.get(0).getSessionId()) == null) {
            return "";
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (IMMessage iMMessage : list) {
            if (sessionId.equals(iMMessage.getSessionId()) && iMMessage.getMsgType() != MsgTypeEnum.notification && iMMessage.getMsgType() != MsgTypeEnum.avchat && iMMessage.getMsgType() != MsgTypeEnum.robot) {
                arrayList.add(iMMessage);
            }
        }
        return buildHeader(0, arrayList.size()) + "\n" + buildBody(arrayList);
    }

    private static String buildHeader(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", i);
            jSONObject.put("terminal", 1);
            jSONObject.put("sdk_version", "9.17.0");
            jSONObject.put("app_version", 91700);
            jSONObject.put("message_count", i2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private static String buildBody(List<IMMessage> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (IMMessage iMMessage : list) {
            sb.append("\n");
            sb.append(a.a(iMMessage));
        }
        return sb.substring(1);
    }

    public static IMMessage createFromJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return a.a(str);
    }

    public static String convertMessageToJson(IMMessage iMMessage) {
        if (iMMessage == null) {
            return null;
        }
        return a.a(iMMessage);
    }

    public static ImageAttachment createImageAttachment(File file, String str, String str2) {
        ImageAttachment imageAttachment = new ImageAttachment();
        imageAttachment.setPath(file.getPath());
        imageAttachment.setSize(file.length());
        int[] a = c.a(file);
        imageAttachment.setWidth(a[0]);
        imageAttachment.setHeight(a[1]);
        imageAttachment.setDisplayName(str);
        imageAttachment.setExtension(w.c(file.getName()));
        imageAttachment.setNosTokenSceneKey(str2);
        return imageAttachment;
    }

    public static ImageAttachment createImageAttachment(Context context, Uri uri, String str, String str2) {
        ImageAttachment imageAttachment = new ImageAttachment();
        imageAttachment.setUri(uri);
        imageAttachment.setSize(UriUtils.getFileSizeFromUri(context, uri));
        int[] a = c.a(context, uri);
        imageAttachment.setWidth(a[0]);
        imageAttachment.setHeight(a[1]);
        imageAttachment.setDisplayName(str);
        imageAttachment.setExtension(UriUtils.getFileExtensionFromUri(context, uri));
        imageAttachment.setNosTokenSceneKey(str2);
        return imageAttachment;
    }

    public static AudioAttachment createAudioAttachment(File file, long j, String str) {
        AudioAttachment audioAttachment = new AudioAttachment();
        audioAttachment.setPath(file.getPath());
        audioAttachment.setSize(file.length());
        audioAttachment.setDuration(j);
        audioAttachment.setExtension(w.c(file.getName()));
        audioAttachment.setNosTokenSceneKey(str);
        return audioAttachment;
    }

    public static AudioAttachment createAudioAttachment(Context context, Uri uri, long j, String str) {
        AudioAttachment audioAttachment = new AudioAttachment();
        audioAttachment.setUri(uri);
        audioAttachment.setSize(UriUtils.getFileSizeFromUri(context, uri));
        audioAttachment.setDuration(j);
        audioAttachment.setExtension(UriUtils.getFileExtensionFromUri(context, uri));
        audioAttachment.setNosTokenSceneKey(str);
        return audioAttachment;
    }

    public static VideoAttachment createVideoAttachment(File file, long j, int i, int i2, String str, String str2) {
        VideoAttachment videoAttachment = new VideoAttachment();
        videoAttachment.setPath(file.getPath());
        videoAttachment.setSize(file.length());
        videoAttachment.setDuration(j);
        videoAttachment.setWidth(i);
        videoAttachment.setHeight(i2);
        videoAttachment.setDisplayName(str);
        videoAttachment.setExtension(w.c(file.getName()));
        videoAttachment.setNosTokenSceneKey(str2);
        return videoAttachment;
    }

    public static VideoAttachment createVideoAttachment(Context context, Uri uri, long j, int i, int i2, String str, String str2) {
        VideoAttachment videoAttachment = new VideoAttachment();
        videoAttachment.setUri(uri);
        videoAttachment.setSize(UriUtils.getFileSizeFromUri(context, uri));
        videoAttachment.setDuration(j);
        videoAttachment.setWidth(i);
        videoAttachment.setHeight(i2);
        videoAttachment.setDisplayName(str);
        videoAttachment.setExtension(UriUtils.getFileExtensionFromUri(context, uri));
        videoAttachment.setNosTokenSceneKey(str2);
        return videoAttachment;
    }

    public static FileAttachment createFileAttachment(File file, String str, String str2) {
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setPath(file.getPath());
        fileAttachment.setSize(file.length());
        fileAttachment.setDisplayName(str);
        fileAttachment.setExtension(w.c(file.getName()));
        fileAttachment.setNosTokenSceneKey(str2);
        return fileAttachment;
    }

    public static FileAttachment createFileAttachment(Context context, Uri uri, String str, String str2) {
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setUri(uri);
        fileAttachment.setSize(UriUtils.getFileSizeFromUri(context, uri));
        fileAttachment.setDisplayName(str);
        fileAttachment.setExtension(UriUtils.getFileExtensionFromUri(context, uri));
        fileAttachment.setNosTokenSceneKey(str2);
        return fileAttachment;
    }
}
