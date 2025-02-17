package it.asansonne.storybe.util.generator;

import static it.asansonne.storybe.constants.CorporateNames.ADOBE;
import static it.asansonne.storybe.constants.CorporateNames.AIRBNB;
import static it.asansonne.storybe.constants.CorporateNames.AMAZON;
import static it.asansonne.storybe.constants.CorporateNames.APPLE;
import static it.asansonne.storybe.constants.CorporateNames.DROPBOX;
import static it.asansonne.storybe.constants.CorporateNames.FACEBOOK;
import static it.asansonne.storybe.constants.CorporateNames.GOOGLE;
import static it.asansonne.storybe.constants.CorporateNames.IBM;
import static it.asansonne.storybe.constants.CorporateNames.INTEL;
import static it.asansonne.storybe.constants.CorporateNames.LINKEDIN;
import static it.asansonne.storybe.constants.CorporateNames.MICROSOFT;
import static it.asansonne.storybe.constants.CorporateNames.NETFLIX;
import static it.asansonne.storybe.constants.CorporateNames.ORACLE;
import static it.asansonne.storybe.constants.CorporateNames.PAYPAL;
import static it.asansonne.storybe.constants.CorporateNames.PINTEREST;
import static it.asansonne.storybe.constants.CorporateNames.REDDIT;
import static it.asansonne.storybe.constants.CorporateNames.SALESFORCE;
import static it.asansonne.storybe.constants.CorporateNames.SAP;
import static it.asansonne.storybe.constants.CorporateNames.SNAPCHAT;
import static it.asansonne.storybe.constants.CorporateNames.SPOTIFY;
import static it.asansonne.storybe.constants.CorporateNames.TESLA;
import static it.asansonne.storybe.constants.CorporateNames.TIKTOK;
import static it.asansonne.storybe.constants.CorporateNames.TWITTER;
import static it.asansonne.storybe.constants.CorporateNames.UBER;
import static it.asansonne.storybe.constants.CorporateNames.ZOOM;
import static it.asansonne.storybe.constants.PersonNames.AARON;
import static it.asansonne.storybe.constants.PersonNames.ALICE;
import static it.asansonne.storybe.constants.PersonNames.BELLA;
import static it.asansonne.storybe.constants.PersonNames.BOB;
import static it.asansonne.storybe.constants.PersonNames.CATHERINE;
import static it.asansonne.storybe.constants.PersonNames.CHARLIE;
import static it.asansonne.storybe.constants.PersonNames.DAVID;
import static it.asansonne.storybe.constants.PersonNames.DEREK;
import static it.asansonne.storybe.constants.PersonNames.ELLA;
import static it.asansonne.storybe.constants.PersonNames.EMMA;
import static it.asansonne.storybe.constants.PersonNames.FIONA;
import static it.asansonne.storybe.constants.PersonNames.FRANK;
import static it.asansonne.storybe.constants.PersonNames.GEORGE;
import static it.asansonne.storybe.constants.PersonNames.GINA;
import static it.asansonne.storybe.constants.PersonNames.HANNAH;
import static it.asansonne.storybe.constants.PersonNames.HARRY;
import static it.asansonne.storybe.constants.PersonNames.IAN;
import static it.asansonne.storybe.constants.PersonNames.IRIS;
import static it.asansonne.storybe.constants.PersonNames.JASON;
import static it.asansonne.storybe.constants.PersonNames.JULIA;
import static it.asansonne.storybe.constants.PersonNames.KATE;
import static it.asansonne.storybe.constants.PersonNames.KEVIN;
import static it.asansonne.storybe.constants.PersonNames.LEO;
import static it.asansonne.storybe.constants.PersonNames.LUCY;
import static it.asansonne.storybe.constants.PersonNames.MARIA;
import static it.asansonne.storybe.constants.PersonNames.MICHAEL;
import static it.asansonne.storybe.constants.PersonNames.NATHAN;
import static it.asansonne.storybe.constants.PersonNames.NINA;
import static it.asansonne.storybe.constants.PersonNames.OLIVER;
import static it.asansonne.storybe.constants.PersonNames.OSCAR;
import static it.asansonne.storybe.constants.PersonNames.PAULA;
import static it.asansonne.storybe.constants.PersonNames.PHOEBE;
import static it.asansonne.storybe.constants.PersonNames.QUENTIN;
import static it.asansonne.storybe.constants.PersonNames.QUINN;
import static it.asansonne.storybe.constants.PersonNames.RACHEL;
import static it.asansonne.storybe.constants.PersonNames.RUBY;
import static it.asansonne.storybe.constants.PersonNames.SAMUEL;
import static it.asansonne.storybe.constants.PersonNames.STEPHEN;
import static it.asansonne.storybe.constants.PersonNames.TERESA;
import static it.asansonne.storybe.constants.PersonNames.TINA;
import static it.asansonne.storybe.constants.PersonNames.ULYSSES;
import static it.asansonne.storybe.constants.PersonNames.URSULA;
import static it.asansonne.storybe.constants.PersonNames.VICTORIA;
import static it.asansonne.storybe.constants.PersonNames.VINCENT;
import static it.asansonne.storybe.constants.PersonNames.WENDY;
import static it.asansonne.storybe.constants.PersonNames.WILLIAM;
import static it.asansonne.storybe.constants.PersonNames.XANDER;
import static it.asansonne.storybe.constants.PersonNames.XAVIER;
import static it.asansonne.storybe.constants.PersonNames.YARA;
import static it.asansonne.storybe.constants.PersonNames.YVONNE;
import static it.asansonne.storybe.constants.PersonNames.ZACHARY;
import static it.asansonne.storybe.constants.PersonNames.ZOE;

import it.asansonne.storybe.constants.CorporateNames;
import it.asansonne.storybe.constants.PersonNames;
import java.util.Random;


public class NameSelector {
  public static String getRandomPersonalName() {
    PersonNames[] names = {
        ALICE, BOB, CHARLIE, DAVID, EMMA, FIONA, GEORGE, HANNAH, IAN, JULIA, KEVIN, LUCY, MICHAEL,
        NINA, OLIVER,
        PAULA, QUINN, RACHEL, SAMUEL, TINA, ULYSSES, VICTORIA, WILLIAM, XANDER, YVONNE, ZACHARY,
        AARON, BELLA, CATHERINE, DEREK, ELLA, FRANK, GINA, HARRY, IRIS, JASON, KATE, LEO, MARIA,
        NATHAN, OSCAR,
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