package cn.edu.nwsuaf.model.entity;

import java.util.Date;

/**
 * Created by luxiaoping on 2016/8/8.
 */
public class Data {
    private Integer dataId;
    private Integer deviceId;
    private Integer senserId;
    private Double dataValue;
    private Integer sensorTypeId;
    private Date collectTime;
    private String unit;

    public Integer getDataId() {
        return dataId;
    }

    @Override
    public String toString() {
        return "Data{" +
                "dataId=" + dataId +
                ", deviceId=" + deviceId +
                ", senserId=" + senserId +
                ", dataValue=" + dataValue +
                ", sensorTypeId=" + sensorTypeId +
                ", collectTime=" + collectTime +
                ", unit='" + unit + '\'' +
                '}';
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getSenserId() {
        return senserId;
    }

    public void setSenserId(Integer senserId) {
        this.senserId = senserId;
    }

    public Double getDataValue() {
        return dataValue;
    }

    public void setDataValue(Double dataValue) {
        this.dataValue = dataValue;
    }

    public Integer getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(Integer sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
