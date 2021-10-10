package com.example.clientup.Util;

public class FeedItem {
    private int id;
    private int advert_id ,advert_price ,model_id,brand_id,item_id,item_price;
    private String advert_title,advert_desc,advert_date,phone,
            image_url,city,street,home,flat,is_main;
    private String text, name, desc, adress_id, message,bonus;
    private String item_title,item_code,status,status_id,status_name,status_color;
    boolean isSelected;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAdvert_id() {
        return advert_id;
    }

    public void setAdvert_id(int advert_id) {
        this.advert_id = advert_id;
    }

    public int getAdvert_price() {
        return advert_price;
    }

    public void setAdvert_price(int advert_price) {
        this.advert_price = advert_price;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getAdvert_title() {
        return advert_title;
    }

    public void setAdvert_title(String advert_title) {
        this.advert_title = advert_title;
    }

    public String getAdvert_desc() {
        return advert_desc;
    }

    public void setAdvert_desc(String advert_desc) {
        this.advert_desc = advert_desc;
    }

    public String getAdvert_date() {
        return advert_date;
    }

    public void setAdvert_date(String advert_date) {
        this.advert_date = advert_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public void setIs_main(String is_main) {
        this.is_main = is_main;
    }

    public String getIs_main() {
        return is_main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_color(String status_color) {
        this.status_color = status_color;
    }

    public String getStatus_color() {
        return status_color;
    }

    public void setAdress_id(String adress_id) {
        this.adress_id = adress_id;
    }

    public String getAdress_id() {
        return adress_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getBonus() {
        return bonus;
    }
}
