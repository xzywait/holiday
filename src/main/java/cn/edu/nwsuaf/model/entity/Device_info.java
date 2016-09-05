package cn.edu.nwsuaf.model.entity;

/**
 * Created by luxiaoping on 2016/8/4.
 */
public class Device_info {
    private Integer landId;
    private Integer deviceId;
    private String deviceName;
    private Integer deviceTypeId;
    private Integer sensorTypeId;
    private Integer sensorTag;

    public Integer getLandId() {
        return landId;
    }

    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public Integer getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(Integer sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public Integer getSensorTag() {
        return sensorTag;
    }

    public void setSensorTag(Integer sensorTag) {
        this.sensorTag = sensorTag;
    }
}
