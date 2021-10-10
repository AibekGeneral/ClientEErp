package com.example.clientup.Util;

public class Book2 {
    private int id;
    private int idka;
    private String name;
    private String num;
    private String imageUrl;

    public Book2(String name, String num, String imageUrl, int id, int idka) {
        this.imageUrl = imageUrl;
        this.id = id;
        this.name = name;
        this.num = num;
        this.idka = idka;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getIdka() {
        return idka;
    }

    public void setIdka(int idka) {
        this.idka = idka;
    }
}
