package it.asansonne.storybe.util.generator;

import static it.asansonne.storybe.constants.CountryCodes.CODE_AUSTRIA;
import static it.asansonne.storybe.constants.CountryCodes.CODE_BELGIUM;
import static it.asansonne.storybe.constants.CountryCodes.CODE_BULGARIA;
import static it.asansonne.storybe.constants.CountryCodes.CODE_CZECH_REPUBLIC;
import static it.asansonne.storybe.constants.CountryCodes.CODE_DENMARK;
import static it.asansonne.storybe.constants.CountryCodes.CODE_FINLAND;
import static it.asansonne.storybe.constants.CountryCodes.CODE_FRANCE;
import static it.asansonne.storybe.constants.CountryCodes.CODE_GERMANY;
import static it.asansonne.storybe.constants.CountryCodes.CODE_GREAT_BRITAIN;
import static it.asansonne.storybe.constants.CountryCodes.CODE_GREECE;
import static it.asansonne.storybe.constants.CountryCodes.CODE_HUNGARY;
import static it.asansonne.storybe.constants.CountryCodes.CODE_ITALY;
import static it.asansonne.storybe.constants.CountryCodes.CODE_LITHUANIA;
import static it.asansonne.storybe.constants.CountryCodes.CODE_NETHERLANDS;
import static it.asansonne.storybe.constants.CountryCodes.CODE_NORWAY;
import static it.asansonne.storybe.constants.CountryCodes.CODE_POLAND;
import static it.asansonne.storybe.constants.CountryCodes.CODE_PORTUGAL;
import static it.asansonne.storybe.constants.CountryCodes.CODE_ROMANIA;
import static it.asansonne.storybe.constants.CountryCodes.CODE_SPAIN;
import static it.asansonne.storybe.constants.CountryCodes.CODE_SWEDEN;
import static it.asansonne.storybe.constants.CountryCodes.CODE_SWITZERLAND;
import static org.apache.commons.lang3.RandomStringUtils.secure;

import it.asansonne.storybe.constants.Country;
import java.util.Random;


public class IbanGenerator {
  static final Random RANDOM = new Random();

  public static String generateRandomIban(String countryNames) {
    return chooseCountry(countryNames);
  }

  public static String randomDigits(int length) {
    StringBuilder digits = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      digits.append(RANDOM.nextInt(10));
    }
    return digits.toString();
  }

  private static String chooseCountry(String countryNames) {
    Country country = new Country();
    return switch (country.getCode(countryNames)) {
      case "IT" -> generateIbanForItaly();
      case "GB" -> generateIbanForUK();
      case "DE" -> generateIbanForGermany();
      case "FR" -> generateIbanForFrance();
      case "ES" -> generateIbanForSpain();
      case "PT" -> generateIbanForPortugal();
      case "NL" -> generateIbanForNetherlands();
      case "BE" -> generateIbanForBelgium();
      case "CH" -> generateIbanForSwitzerland();
      case "AT" -> generateIbanForAustria();
      case "SE" -> generateIbanForSweden();
      case "NO" -> generateIbanForNorway();
      case "FI" -> generateIbanForFinland();
      case "DK" -> generateIbanForDenmark();
      case "GR" -> generateIbanForGreece();
      case "PL" -> generateIbanForPoland();
      case "CZ" -> generateIbanForCzechRepublic();
      case "HU" -> generateIbanForHungary();
      case "RO" -> generateIbanForRomania();
      case "BG" -> generateIbanForBulgaria();
      case "LT" -> generateIbanForLithuania();
      default -> throw new IllegalArgumentException(
          "Unsupported country code: " + country.getCode(countryNames));
    };
  }

  private static String generateIbanForItaly() {
    return new Country().getCode(CODE_ITALY.getRole()) + randomDigits(2) + "A" + randomDigits(10) +
        secure().nextAlphanumeric(12).toUpperCase();
  }

  private static String generateIbanForUK() {
    return new Country().getCode(CODE_GREAT_BRITAIN.getRole()) + randomDigits(2) +
        secure().nextAlphanumeric(4).toUpperCase() + randomDigits(14);
  }

  private static String generateIbanForGermany() {
    return new Country().getCode(CODE_GERMANY.getRole()) + randomDigits(2) + randomDigits(18);
  }

  private static String generateIbanForFrance() {
    return new Country().getCode(CODE_FRANCE.getRole()) + randomDigits(2) + randomDigits(10) +
        secure().nextAlphanumeric(11).toUpperCase() + randomDigits(2);
  }

  private static String generateIbanForSpain() {
    return new Country().getCode(CODE_SPAIN.getRole()) + randomDigits(2) + randomDigits(20);
  }

  private static String generateIbanForPortugal() {
    return new Country().getCode(CODE_PORTUGAL.getRole()) + randomDigits(2) + randomDigits(21);
  }

  private static String generateIbanForNetherlands() {
    return new Country().getCode(CODE_NETHERLANDS.getRole()) + randomDigits(2) +
        secure().nextAlphanumeric(4).toUpperCase() + randomDigits(10);
  }

  private static String generateIbanForBelgium() {
    return new Country().getCode(CODE_BELGIUM.getRole()) + randomDigits(2) + randomDigits(12);
  }

  private static String generateIbanForSwitzerland() {
    return new Country().getCode(CODE_SWITZERLAND.getRole()) + randomDigits(2) +
        secure().nextAlphanumeric(5).toUpperCase() + randomDigits(12);
  }

  private static String generateIbanForAustria() {
    return new Country().getCode(CODE_AUSTRIA.getRole()) + randomDigits(2) + randomDigits(16);
  }

  private static String generateIbanForSweden() {
    return new Country().getCode(CODE_SWEDEN.getRole()) + randomDigits(2) + randomDigits(20);
  }

  private static String generateIbanForNorway() {
    return new Country().getCode(CODE_NORWAY.getRole()) + randomDigits(2) + randomDigits(13);
  }

  private static String generateIbanForFinland() {
    return new Country().getCode(CODE_FINLAND.getRole()) + randomDigits(2) + randomDigits(16);
  }

  private static String generateIbanForDenmark() {
    return new Country().getCode(CODE_DENMARK.getRole()) + randomDigits(2) + randomDigits(14);
  }

  private static String generateIbanForGreece() {
    return new Country().getCode(CODE_GREECE.getRole()) + randomDigits(2) +
        secure().nextAlphanumeric(7).toUpperCase() + randomDigits(16);
  }

  private static String generateIbanForPoland() {
    return new Country().getCode(CODE_POLAND.getRole()) + randomDigits(2) + randomDigits(24);
  }

  private static String generateIbanForCzechRepublic() {
    return new Country().getCode(CODE_CZECH_REPUBLIC.getRole()) + randomDigits(2) +
        randomDigits(20);
  }

  private static String generateIbanForHungary() {
    return new Country().getCode(CODE_HUNGARY.getRole()) + randomDigits(2) + randomDigits(26);
  }

  private static String generateIbanForRomania() {
    return new Country().getCode(CODE_ROMANIA.getRole()) + randomDigits(2) +
        secure().nextAlphanumeric(4).toUpperCase() + randomDigits(16);
  }

  private static String generateIbanForBulgaria() {
    return new Country().getCode(CODE_BULGARIA.getRole()) + randomDigits(2) +
        secure().nextAlphanumeric(4).toUpperCase() + randomDigits(16);
  }

  private static String generateIbanForLithuania() {
    return new Country().getCode(CODE_LITHUANIA.getRole()) + randomDigits(2) + randomDigits(18);
  }
}
