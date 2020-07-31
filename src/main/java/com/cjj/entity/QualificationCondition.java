package com.cjj.entity;

/**
 * @author cjj
 * @date 2020/7/20
 * @description
 */
public class QualificationCondition {
    //将查询条件封装
    private String type;
    private String check;
    private String startData;
    private String endData;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    @Override
    public String toString() {
        return "QualificationCondition{" +
                "type='" + type + '\'' +
                ", check='" + check + '\'' +
                ", startData='" + startData + '\'' +
                ", endData='" + endData + '\'' +
                '}';
    }
}
