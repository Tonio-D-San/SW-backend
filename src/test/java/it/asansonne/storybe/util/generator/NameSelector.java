package it.asansonne.storybe.util.generator;

import static it.cybsec.app.constants.CorporateNames.*;
import static it.cybsec.app.constants.PersonNames.*;

import it.cybsec.app.constants.CorporateNames;
import it.cybsec.app.constants.PersonNames;
import java.util.Random;


public class NameSelector{
    public static String getRandomPersonalName() {
        PersonNames[] names = {
                ALICE, BOB, CHARLIE, DAVID, EMMA, FIONA, GEORGE, HANNAH, IAN, JULIA, KEVIN, LUCY, MICHAEL, NINA, OLIVER,
                PAULA, QUINN, RACHEL, SAMUEL, TINA, ULYSSES, VICTORIA, WILLIAM, XANDER, YVONNE, ZACHARY,
                AARON, BELLA, CATHERINE, DEREK, ELLA, FRANK, GINA, HARRY, IRIS, JASON, KATE, LEO, MARIA, NATHAN, OSCAR,
                PHOEBE, QUENTIN, RUBY, STEPHEN, TERESA, URSULA, VINCENT, WENDY, XAVIER, YARA, ZOE
        };

        return names[new Random().nextInt(names.length)].toString();
    }
    public static String getRandomCorporateName() {
        CorporateNames[] names = {
                GOOGLE, MICROSOFT, APPLE, AMAZON, FACEBOOK, IBM, INTEL, ORACLE,
                SAP, SALESFORCE, TESLA, NETFLIX, ADOBE, TWITTER, SPOTIFY, UBER,
                AIRBNB, LINKEDIN, SNAPCHAT, PAYPAL, TIKTOK, ZOOM, DROPBOX, REDDIT,
                PINTEREST
        };
        return names[new Random().nextInt(names.length)].toString();
    }
}