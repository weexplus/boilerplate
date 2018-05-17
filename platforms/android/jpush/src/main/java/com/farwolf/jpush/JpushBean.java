package com.farwolf.jpush;

/**
 * @author xch
 * @version 1.0
 * @create_date 2018/4/20
 */

public class JpushBean {

    private String msgType;
    private String notificationBarDisplay;
    private String remindId;
    private String remindType;
    private String summary;
    private String url;
    private String farmId;
    private String extParm;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getNotificationBarDisplay() {
        return notificationBarDisplay;
    }

    public void setNotificationBarDisplay(String notificationBarDisplay) {
        this.notificationBarDisplay = notificationBarDisplay;
    }

    public String getRemindId() {
        return remindId;
    }

    public void setRemindId(String remindId) {
        this.remindId = remindId;
    }

    public String getRemindType() {
        return remindType;
    }

    public void setRemindType(String remindType) {
        this.remindType = remindType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getExtParm() {
        return extParm;
    }

    public void setExtParm(String extParm) {
        this.extParm = extParm;
    }

    @Override
    public String toString() {
        return "JpushBean{" +
                "msgType='" + msgType + '\'' +
                ", notificationBarDisplay='" + notificationBarDisplay + '\'' +
                ", remindId='" + remindId + '\'' +
                ", remindType='" + remindType + '\'' +
                ", summary='" + summary + '\'' +
                ", url='" + url + '\'' +
                ", farmId='" + farmId + '\'' +
                ", extParm=" + extParm +
                ", title='" + title + '\'' +
                '}';
    }
}
