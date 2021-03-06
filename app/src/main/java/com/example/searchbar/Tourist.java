package com.example.searchbar;

public class Tourist {

    private String CountryName;
    private String CapitalCity;
    private String Currencies;
    private String Language;
    private String CountryImg;



    public Tourist() {}

    public Tourist(String countryName, String capitalCity, String currencies, String language, String countryImg) {
        CountryName = countryName;
        CapitalCity = capitalCity;
        Currencies = currencies;
        Language = language;
        CountryImg = countryImg;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCapitalCity() {
        return CapitalCity;
    }

    public void setCapitalCity(String capitalCity) {
        CapitalCity = capitalCity;
    }

    public String getCurrencies() {
        return Currencies;
    }

    public void setCurrencies(String currencies) {
        Currencies = currencies;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
    public String getCountryImg() {
        return CountryImg;
    }

    public void setCountryImg(String countryImg) {
        CountryImg = countryImg;
    }


}
