package com.example.harshkumar.mynews.data;

public class NewsSource {

    private String mId;
    private String mName;
    private String mDescription;
    private String mUrl;
    private String mCategory;
    private String mLanguageId;
    private String mCountryId;
    private String mImageUrl;

    public NewsSource(String id,String name,String desc,String url,String category,
                      String languageId,String countryId,String imageUrl){

        this.mId = id;
        this.mName = name;
        this.mDescription = desc;
        this.mUrl = url;
        this.mCategory = category;
        this.mLanguageId = languageId;
        this.mCountryId = countryId;
        this.mImageUrl = imageUrl;

    }

    public String getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmLanguageId() {
        return mLanguageId;
    }

    public String getmCountryId() {
        return mCountryId;
    }

    public String getLanguage(){
        String lang;

        switch (getmLanguageId()){

            case "ar":
                lang = "Arabic";
                break;
            case "de":
                lang = "German";
                break;
            case "en":
                lang = "English";
                break;
            case "es":
                lang = "Spanish";
                break;
            case "fr":
                lang = "French";
                break;
            case "he":
                lang = "Hebrew";
                break;
            case "it":
                lang = "Italian";
                break;
            case "nl":
                lang = "Dutch";
                break;
            case "no":
                lang = "Norwegian";
                break;
            case "pt":
                lang = "Portuguese";
                break;
            case "ru":
                lang = "Russian";
                break;
            case "se":
                lang = "Sami (Northern)";
                break;
            case "ud":
                lang = "Urdu";
                break;
            case "zh":
                lang = "Chinese";
                break;
                default:
                    lang = "English";


        }

        return lang;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }
}
