package it.asansonne.storybe.constants;

import lombok.Getter;

@Getter
public enum CorporateNames {
    GOOGLE ("GOOGLE"),
    MICROSOFT ("MICROSOFT"),
    APPLE ("APPLE"),
    AMAZON ("AMAZON"),
    FACEBOOK ("FACEBOOK"),
    IBM ("IBM"),
    INTEL ("INTEL"),
    ORACLE ("ORACLE"),
    SAP ("SAP"),
    SALESFORCE ("SALESFORCE"),
    TESLA ("TESLA"),
    NETFLIX ("NETFLIX"),
    ADOBE ("ADOBE"),
    TWITTER ("TWITTER"),
    SPOTIFY ("SPOTIFY"),
    UBER ("UBER"),
    AIRBNB ("AIRBNB"),
    LINKEDIN ("LINKEDIN"),
    SNAPCHAT ("SNAPCHAT"),
    PAYPAL ("PAYPAL"),
    TIKTOK ("TIKTOK"),
    ZOOM ("ZOOM"),
    DROPBOX ("DROPBOX"),
    REDDIT ("REDDIT"),
    PINTEREST ("PINTEREST");

    private final String role;
    CorporateNames(String role) {
        this.role = role;
    }
}
