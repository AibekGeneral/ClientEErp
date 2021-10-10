package com.example.clientup.Util;

public class MyConstants {
    public static final String PREFS_NAME = "nurik";

    public static final String DOMAIN = "http://94.103.91.178";
    public static final String API = DOMAIN+"/api/";
    // http://moidom.zhasai.kz
    public static final String register = API+"auth/register";
    public static final String confirm = API+"auth/confirm";
    public static final String sms = API+"auth/sms";
    public static final String password = API+"auth/password?token=";

    public static final String source = API+"auth/source?token=";
    public static final String login = "http://moidom.zhasai.kz/api/auth/login";
    public static final String reset = API+"auth/reset";
    public static final String is_own = API+"advert?is_own=1&token=";
    public static final String obiab = API+"advert?token=";
    public static final String admin = API+"admin";
    public static final String call = API+"call?phone=";
    public static final String image = API+"image?token=";
    public static final String service = API+"service";
    public static final String advert = API+"advert?token=";
    public static final String advert_single = API+"advert/";
    public static final String profile = API+"profile?token=";
    public static final String city = API+"city";
    public static final String adress = API+"profile/address?token=";
    public static final String newsid = API+"news/";
    public static final String review = API+"review";
    public static final String reviewToken = API+"review?token=";

    public static final String subscription = API+"subscription?token=";
    public static final String payment = API+"payment-type";
    public static final String subscriptiontype = API+"subscription-type";
    public static final String history = API+"history?token=";

}