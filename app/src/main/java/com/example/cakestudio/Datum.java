
package com.example.cakestudio;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cake_name")
    @Expose
    private String cakeName;
    @SerializedName("cake_info")
    @Expose
    private String cakeInfo;
    @SerializedName("cake_info_image")
    @Expose
    private String cakeInfoImage;
    @SerializedName("flavour_id")
    @Expose
    private String flavourId;
    @SerializedName("flavour_name")
    @Expose
    private String flavourName;
    @SerializedName("w_l_p")
    @Expose
    private List<WLP> wLP = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getCakeInfo() {
        return cakeInfo;
    }

    public void setCakeInfo(String cakeInfo) {
        this.cakeInfo = cakeInfo;
    }

    public String getCakeInfoImage() {
        return cakeInfoImage;
    }

    public void setCakeInfoImage(String cakeInfoImage) {
        this.cakeInfoImage = cakeInfoImage;
    }

    public String getFlavourId() {
        return flavourId;
    }

    public void setFlavourId(String flavourId) {
        this.flavourId = flavourId;
    }

    public String getFlavourName() {
        return flavourName;
    }

    public void setFlavourName(String flavourName) {
        this.flavourName = flavourName;
    }

    public List<WLP> getWLP() {
        return wLP;
    }

    public void setWLP(List<WLP> wLP) {
        this.wLP = wLP;
    }

}
