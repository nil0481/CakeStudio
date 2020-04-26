package com.example.cakestudio;

public class Cake {
    private String cake_name;
    private String weight;
    private String price;
    private String imgUrl;


    public Cake(){}
    public Cake(String cake_name,String weight,String price,String imgUrl){
     this.cake_name=cake_name;
     this.weight=weight;
     this.price=price;
     this.imgUrl=imgUrl;
    }

    public String getCake_name() {
        return cake_name;
    }

    public void setCake_name(String cake_name) {
        this.cake_name = cake_name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
