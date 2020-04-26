package com.hari.mvvm.models;

public class NicePlace {

    String placeId;
    String placeName;
    String placeImageUrl;

    public NicePlace() {
    }

    public NicePlace(String placeId, String placeName, String placeImageUrl) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeImageUrl = placeImageUrl;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceImageUrl() {
        return placeImageUrl;
    }

    public void setPlaceImageUrl(String placeImageUrl) {
        this.placeImageUrl = placeImageUrl;
    }
}
