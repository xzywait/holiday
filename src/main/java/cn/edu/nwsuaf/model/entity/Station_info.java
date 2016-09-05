package cn.edu.nwsuaf.model.entity;

/**
 * Created by luxiaoping on 2016/8/2.
 */
public class Station_info {
    private Integer stationId;
    private String stationName;
    private Integer landId;
    private String landName;
    private Double longituade;
    private Double latitude;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getLandId() {
        return landId;
    }

    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public Double getLongituade() {
        return longituade;
    }

    public void setLongituade(Double longituade) {
        this.longituade = longituade;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Station_info{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", landId=" + landId +
                ", landName='" + landName + '\'' +
                ", longituade=" + longituade +
                ", latitude=" + latitude +
                '}';
    }
}
