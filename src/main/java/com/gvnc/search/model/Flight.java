package com.gvnc.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by EXT01D3678 on 23.9.2016.
 */
@Document(indexName = "flights", type = "flights")
public class Flight {

    @Id
    private String id;
    private Integer year;
    private Integer month;
    private Integer dayofMonth;
    private Integer dayOfWeek;
    private String depTime;
    private String crsDepTime;
    private String arrTime;
    private String crsArrTime;

    @Field( type = FieldType.Object)
    private Carrier uniqueCarrier;

    private String flightNum;

    @Field( type = FieldType.Object)
    private Plane plane;

    private String actualElapsedTime;
    private String crsElapsedTime;
    private String airTime;
    private String arrDelay;
    private String depDelay;

    @Field( type = FieldType.Object)
    private Airport origin;

    @Field( type = FieldType.Object)
    private Airport dest;

    private Integer distance;
    private String taxiIn;
    private String taxiOut;
    private String cancelled;
    private String cancellationCode;
    private String diverted;
    private String carrierDelay;
    private String weatherDelay;
    private String nasDelay;
    private String securityDelay;
    private String lateAircraftDelay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDayofMonth() {
        return dayofMonth;
    }

    public void setDayofMonth(Integer dayofMonth) {
        this.dayofMonth = dayofMonth;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getCrsDepTime() {
        return crsDepTime;
    }

    public void setCrsDepTime(String crsDepTime) {
        this.crsDepTime = crsDepTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getCrsArrTime() {
        return crsArrTime;
    }

    public void setCrsArrTime(String crsArrTime) {
        this.crsArrTime = crsArrTime;
    }

    public Carrier getUniqueCarrier() {
        return uniqueCarrier;
    }

    public void setUniqueCarrier(Carrier uniqueCarrier) {
        this.uniqueCarrier = uniqueCarrier;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public String getActualElapsedTime() {
        return actualElapsedTime;
    }

    public void setActualElapsedTime(String actualElapsedTime) {
        this.actualElapsedTime = actualElapsedTime;
    }

    public String getCrsElapsedTime() {
        return crsElapsedTime;
    }

    public void setCrsElapsedTime(String crsElapsedTime) {
        this.crsElapsedTime = crsElapsedTime;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getArrDelay() {
        return arrDelay;
    }

    public void setArrDelay(String arrDelay) {
        this.arrDelay = arrDelay;
    }

    public String getDepDelay() {
        return depDelay;
    }

    public void setDepDelay(String depDelay) {
        this.depDelay = depDelay;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDest() {
        return dest;
    }

    public void setDest(Airport dest) {
        this.dest = dest;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getTaxiIn() {
        return taxiIn;
    }

    public void setTaxiIn(String taxiIn) {
        this.taxiIn = taxiIn;
    }

    public String getTaxiOut() {
        return taxiOut;
    }

    public void setTaxiOut(String taxiOut) {
        this.taxiOut = taxiOut;
    }

    public String getCancelled() {
        return cancelled;
    }

    public void setCancelled(String cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancellationCode() {
        return cancellationCode;
    }

    public void setCancellationCode(String cancellationCode) {
        this.cancellationCode = cancellationCode;
    }

    public String getDiverted() {
        return diverted;
    }

    public void setDiverted(String diverted) {
        this.diverted = diverted;
    }

    public String getCarrierDelay() {
        return carrierDelay;
    }

    public void setCarrierDelay(String carrierDelay) {
        this.carrierDelay = carrierDelay;
    }

    public String getWeatherDelay() {
        return weatherDelay;
    }

    public void setWeatherDelay(String weatherDelay) {
        this.weatherDelay = weatherDelay;
    }

    public String getNasDelay() {
        return nasDelay;
    }

    public void setNasDelay(String nasDelay) {
        this.nasDelay = nasDelay;
    }

    public String getSecurityDelay() {
        return securityDelay;
    }

    public void setSecurityDelay(String securityDelay) {
        this.securityDelay = securityDelay;
    }

    public String getLateAircraftDelay() {
        return lateAircraftDelay;
    }

    public void setLateAircraftDelay(String lateAircraftDelay) {
        this.lateAircraftDelay = lateAircraftDelay;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", dayofMonth=" + dayofMonth +
                ", dayOfWeek=" + dayOfWeek +
                ", depTime=" + depTime +
                ", crsDepTime=" + crsDepTime +
                ", arrTime=" + arrTime +
                ", crsArrTime=" + crsArrTime +
                ", uniqueCarrier=" + uniqueCarrier +
                ", flightNum='" + flightNum + '\'' +
                ", plane=" + plane +
                ", actualElapsedTime=" + actualElapsedTime +
                ", crsElapsedTime=" + crsElapsedTime +
                ", airTime=" + airTime +
                ", arrDelay=" + arrDelay +
                ", depDelay=" + depDelay +
                ", origin=" + origin +
                ", dest=" + dest +
                ", distance=" + distance +
                ", taxiIn='" + taxiIn + '\'' +
                ", taxiOut='" + taxiOut + '\'' +
                ", cancelled='" + cancelled + '\'' +
                ", cancellationCode='" + cancellationCode + '\'' +
                ", diverted='" + diverted + '\'' +
                ", carrierDelay='" + carrierDelay + '\'' +
                ", weatherDelay='" + weatherDelay + '\'' +
                ", nasDelay='" + nasDelay + '\'' +
                ", securityDelay='" + securityDelay + '\'' +
                ", lateAircraftDelay='" + lateAircraftDelay + '\'' +
                '}';
    }
}
