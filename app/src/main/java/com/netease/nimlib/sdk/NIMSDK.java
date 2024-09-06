package com.netease.nimlib.sdk;

import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.event.EventSubscribeService;
import com.netease.nimlib.sdk.event.EventSubscribeServiceObserver;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.FriendServiceObserve;
import com.netease.nimlib.sdk.generic.CustomizedAPIService;
import com.netease.nimlib.sdk.lifecycle.SdkLifecycleObserver;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.nos.NosService;
import com.netease.nimlib.sdk.nos.NosServiceObserve;
import com.netease.nimlib.sdk.passthrough.PassthroughService;
import com.netease.nimlib.sdk.passthrough.PassthroughServiceObserve;
import com.netease.nimlib.sdk.redpacket.RedPacketService;
import com.netease.nimlib.sdk.robot.RobotService;
import com.netease.nimlib.sdk.robot.RobotServiceObserve;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.TeamServiceObserver;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.UserServiceObserve;

/* loaded from: classes.dex */
public final class NIMSDK {
    private NIMSDK() {
    }

    public static AuthServiceObserver getAuthServiceObserve() {
        return (AuthServiceObserver) NIMClient.getService(AuthServiceObserver.class);
    }

    public static AuthService getAuthService() {
        return (AuthService) NIMClient.getService(AuthService.class);
    }

    public static NosServiceObserve getNosServiceObserve() {
        return (NosServiceObserve) NIMClient.getService(NosServiceObserve.class);
    }

    public static NosService getNosService() {
        return (NosService) NIMClient.getService(NosService.class);
    }

    public static RobotServiceObserve getRobotServiceObserve() {
        return (RobotServiceObserve) NIMClient.getService(RobotServiceObserve.class);
    }

    public static RobotService getRobotService() {
        return (RobotService) NIMClient.getService(RobotService.class);
    }

    public static UserService getUserService() {
        return (UserService) NIMClient.getService(UserService.class);
    }

    public static UserServiceObserve getUserServiceObserve() {
        return (UserServiceObserve) NIMClient.getService(UserServiceObserve.class);
    }

    public static FriendServiceObserve getFriendServiceObserve() {
        return (FriendServiceObserve) NIMClient.getService(FriendServiceObserve.class);
    }

    public static FriendService getFriendService() {
        return (FriendService) NIMClient.getService(FriendService.class);
    }

    public static TeamService getTeamService() {
        return (TeamService) NIMClient.getService(TeamService.class);
    }

    public static TeamServiceObserver getTeamServiceObserve() {
        return (TeamServiceObserver) NIMClient.getService(TeamServiceObserver.class);
    }

    public static SdkLifecycleObserver getSdkLifecycleObserve() {
        return (SdkLifecycleObserver) NIMClient.getService(SdkLifecycleObserver.class);
    }

    public static PassthroughService getPassthroughService() {
        return (PassthroughService) NIMClient.getService(PassthroughService.class);
    }

    public static PassthroughServiceObserve getPassthroughServiceObserve() {
        return (PassthroughServiceObserve) NIMClient.getService(PassthroughServiceObserve.class);
    }

    public static RedPacketService getRedPacketService() {
        return (RedPacketService) NIMClient.getService(RedPacketService.class);
    }

    public static MsgService getMsgService() {
        return (MsgService) NIMClient.getService(MsgService.class);
    }

    public static MsgServiceObserve getMsgServiceObserve() {
        return (MsgServiceObserve) NIMClient.getService(MsgServiceObserve.class);
    }

    public static EventSubscribeService getEventSubscribeService() {
        return (EventSubscribeService) NIMClient.getService(EventSubscribeService.class);
    }

    public static EventSubscribeServiceObserver getEventSubscribeServiceObserve() {
        return (EventSubscribeServiceObserver) NIMClient.getService(EventSubscribeServiceObserver.class);
    }

    public static CustomizedAPIService getCustomizedAPIService() {
        return (CustomizedAPIService) NIMClient.getService(CustomizedAPIService.class);
    }

    public static void main(String[] strArr) {
        System.out.println("Hello, NIM Android SDK!");
    }
}
