
package com.example.cakestudio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WLP {

    @SerializedName("weight_id")
    @Expose
    private String weightId;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("layer_id")
    @Expose
    private String layerId;
    @SerializedName("layer")
    @Expose
    private String layer;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("pictures")
    @Expose
    private String pictures;

    public String getWeightId() {
        return weightId;
    }

    public void setWeightId(String weightId) {
        this.weightId = weightId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

}
