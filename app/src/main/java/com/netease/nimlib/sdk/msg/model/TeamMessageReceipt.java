package com.netease.nimlib.sdk.msg.model;

/* loaded from: classes.dex */
public class TeamMessageReceipt extends MessageReceipt {
    private int ackCount;
    private String messageId;
    private String newReaderAccount;
    private int unAckCount;

    public TeamMessageReceipt(TeamMsgAckInfo teamMsgAckInfo) {
        super(teamMsgAckInfo.getTeamId(), 0L);
        this.messageId = teamMsgAckInfo.getMsgId();
        this.ackCount = teamMsgAckInfo.getAckCount();
        this.unAckCount = teamMsgAckInfo.getUnAckCount();
        this.newReaderAccount = teamMsgAckInfo.getNewReaderAccount();
    }

    public String getMsgId() {
        return this.messageId;
    }

    public int getAckCount() {
        return this.ackCount;
    }

    public int getUnAckCount() {
        return this.unAckCount;
    }

    public String getNewReaderAccount() {
        return this.newReaderAccount;
    }
}
