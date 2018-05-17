package com.farwolf.jpush;

/**
 * @author xch
 * @version 1.0
 * @create_date 2018/4/20
 */

public class ExtParm {


    /**
     * activitRecordId : 5ad9d3c9671ec62bb8454df6
     * creator : 张三
     * start : 1524205431000
     * end : 1524205791000
     * recordImg : http://fanyi.bdstatic.com/static/translation/img/header/logo_cbfea26.png
     * speed : 5
     */

    private String activitRecordId;
    private String creator;
    private String startTime;
    private String endTime;
    private String recordImg;
    private int speed;
    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getActivitRecordId() {
        return activitRecordId;
    }

    public void setActivitRecordId(String activitRecordId) {
        this.activitRecordId = activitRecordId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRecordImg() {
        return recordImg;
    }

    public void setRecordImg(String recordImg) {
        this.recordImg = recordImg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "ExtParm{" +
                "activitRecordId='" + activitRecordId + '\'' +
                ", creator='" + creator + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", recordImg='" + recordImg + '\'' +
                ", speed=" + speed +
                ", area=" + area +
                '}';
    }
}
