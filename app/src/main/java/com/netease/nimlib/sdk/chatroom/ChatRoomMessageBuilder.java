package com.netease.nimlib.sdk.chatroom;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.chatroom.model.ChatRoomMessageImpl;
import com.netease.nimlib.o.c;
import com.netease.nimlib.o.w;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.LocationAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.robot.model.RobotAttachment;
import java.io.File;

/* loaded from: classes.dex */
public class ChatRoomMessageBuilder {
    public static ChatRoomMessage createChatRoomTextMessage(String str, String str2) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.text.getValue());
        initSendMessage.setContent(str2);
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomCustomMessage(String str, MsgAttachment msgAttachment) {
        return createChatRoomCustomMessage(str, msgAttachment, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static ChatRoomMessage createChatRoomCustomMessage(String str, MsgAttachment msgAttachment, String str2) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.custom.getValue());
        if (msgAttachment != null && (msgAttachment instanceof FileAttachment)) {
            ((FileAttachment) msgAttachment).setNosTokenSceneKey(str2);
        }
        initSendMessage.setAttachment(msgAttachment);
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomImageMessage(String str, File file, String str2) {
        return createChatRoomImageMessage(str, file, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static ChatRoomMessage createChatRoomImageMessage(Context context, String str, Uri uri, String str2) {
        return createChatRoomImageMessage(context, str, uri, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static ChatRoomMessage createChatRoomImageMessage(String str, File file, String str2, String str3) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.image.getValue());
        initSendMessage.setAttachment(MessageBuilder.createImageAttachment(file, str2, str3));
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomImageMessage(Context context, String str, Uri uri, String str2, String str3) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.image.getValue());
        initSendMessage.setAttachment(MessageBuilder.createImageAttachment(context, uri, str2, str3));
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomAudioMessage(String str, File file, long j) {
        return createChatRoomAudioMessage(str, file, j, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static ChatRoomMessage createChatRoomAudioMessage(Context context, String str, Uri uri, long j) {
        return createChatRoomAudioMessage(context, str, uri, j, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static ChatRoomMessage createChatRoomAudioMessage(String str, File file, long j, String str2) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.audio.getValue());
        if (j > 0 && j < 1000) {
            j = 1000;
        }
        initSendMessage.setAttachment(MessageBuilder.createAudioAttachment(file, j, str2));
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomAudioMessage(Context context, String str, Uri uri, long j, String str2) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.audio.getValue());
        if (j > 0 && j < 1000) {
            j = 1000;
        }
        initSendMessage.setAttachment(MessageBuilder.createAudioAttachment(context, uri, j, str2));
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomLocationMessage(String str, double d, double d2, String str2) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.location.getValue());
        LocationAttachment locationAttachment = new LocationAttachment();
        locationAttachment.setLatitude(d);
        locationAttachment.setLongitude(d2);
        locationAttachment.setAddress(str2);
        initSendMessage.setAttachStatus(AttachStatusEnum.transferred);
        initSendMessage.setAttachment(locationAttachment);
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomVideoMessage(String str, File file, long j, int i, int i2, String str2) {
        return createChatRoomVideoMessage(str, file, j, i, i2, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static ChatRoomMessage createChatRoomVideoMessage(Context context, String str, Uri uri, long j, int i, int i2, String str2) {
        return createChatRoomVideoMessage(context, str, uri, j, i, i2, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static ChatRoomMessage createChatRoomVideoMessage(String str, File file, long j, int i, int i2, String str2, String str3) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.video.getValue());
        VideoAttachment createVideoAttachment = MessageBuilder.createVideoAttachment(file, j, i, i2, str2, str3);
        initSendMessage.setAttachment(createVideoAttachment);
        c.a(file.getPath(), createVideoAttachment.getThumbPathForSave());
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomVideoMessage(Context context, String str, Uri uri, long j, int i, int i2, String str2, String str3) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.video.getValue());
        VideoAttachment createVideoAttachment = MessageBuilder.createVideoAttachment(context, uri, j, i, i2, str2, str3);
        initSendMessage.setAttachment(createVideoAttachment);
        c.a(uri, createVideoAttachment.getThumbPathForSave());
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomFileMessage(String str, File file, String str2) {
        return createChatRoomFileMessage(str, file, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM);
    }

    public static ChatRoomMessage createChatRoomFileMessage(Context context, String str, Uri uri, String str2) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.file.getValue());
        initSendMessage.setAttachment(MessageBuilder.createFileAttachment(context, uri, str2, NimNosSceneKeyConstant.NIM_DEFAULT_IM));
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomFileMessage(String str, File file, String str2, String str3) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.file.getValue());
        initSendMessage.setAttachment(MessageBuilder.createFileAttachment(file, str2, str3));
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomFileMessage(Context context, String str, Uri uri, String str2, String str3) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.file.getValue());
        initSendMessage.setAttachment(MessageBuilder.createFileAttachment(context, uri, str2, str3));
        return initSendMessage;
    }

    public static ChatRoomMessage createChatRoomSpatialLocationTextMessage(String str, String str2, Double d, Double d2, Double d3) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.text.getValue());
        initSendMessage.setContent(str2);
        initSendMessage.setLocX(d);
        initSendMessage.setLocY(d2);
        initSendMessage.setLocZ(d3);
        return initSendMessage;
    }

    public static ChatRoomMessage createTipMessage(String str) {
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.tip.getValue());
        return initSendMessage;
    }

    public static ChatRoomMessage createRobotMessage(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (TextUtils.isEmpty(str4) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("Invalid param, type and robot account should not be null");
        }
        if (str4.equals("01") && str5 == null) {
            throw new IllegalArgumentException("Invalid param, content should not be null");
        }
        if (str4.equals("03") && TextUtils.isEmpty(str6)) {
            throw new IllegalArgumentException("Invalid param, target should not be null");
        }
        ChatRoomMessageImpl initSendMessage = initSendMessage(str);
        initSendMessage.setMsgType(MsgTypeEnum.robot.getValue());
        RobotAttachment robotAttachment = new RobotAttachment();
        robotAttachment.initSend(str2, str4, str5, str6, str7);
        initSendMessage.setAttachment(robotAttachment);
        initSendMessage.setContent(str3);
        return initSendMessage;
    }

    public static ChatRoomMessage createEmptyChatRoomMessage(String str, long j) {
        ChatRoomMessageImpl chatRoomMessageImpl = new ChatRoomMessageImpl();
        chatRoomMessageImpl.setSessionId(str);
        chatRoomMessageImpl.setSessionType(SessionTypeEnum.ChatRoom);
        chatRoomMessageImpl.setTime(j);
        return chatRoomMessageImpl;
    }

    private static final ChatRoomMessageImpl initSendMessage(String str) {
        ChatRoomMessageImpl chatRoomMessageImpl = new ChatRoomMessageImpl();
        chatRoomMessageImpl.setUuid(w.b());
        chatRoomMessageImpl.setSessionId(str);
        chatRoomMessageImpl.setFromAccount(com.netease.nimlib.c.o());
        chatRoomMessageImpl.setDirect(MsgDirectionEnum.Out);
        chatRoomMessageImpl.setStatus(MsgStatusEnum.sending);
        chatRoomMessageImpl.setSessionType(SessionTypeEnum.ChatRoom);
        chatRoomMessageImpl.setTime(y.a());
        return chatRoomMessageImpl;
    }
}
