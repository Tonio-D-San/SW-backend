package it.asansonne.storybe.constants;

import lombok.Getter;

@Getter
public enum PersonNames {
    ALICE ("ALICE"),
    BOB ("BOB"),
    CHARLIE ("CHARLIE"),
    DAVID ("DAVID"),
    EMMA ("EMMA"),
    FIONA ("FIONA"),
    GEORGE ("GEORGE"),
    HANNAH ("HANNAH"),
    IAN ("IAN"),
    JULIA ("JULIA"),
    KEVIN ("KEVIN"),
    LUCY ("LUCY"),
    MICHAEL ("MICHAEL"),
    NINA ("NINA"),
    OLIVER ("OLIVER"),
    PAULA ("PAULA"),
    QUINN ("QUINN"),
    RACHEL ("RACHEL"),
    SAMUEL ("SAMUEL"),
    TINA ("TINA"),
    ULYSSES ("ULYSSES"),
    VICTORIA ("VICTORIA"),
    WILLIAM ("WILLIAM"),
    XANDER ("XANDER"),
    YVONNE ("YVONNE"),
    ZACHARY ("ZACHARY"),
    AARON ("AARON"),
    BELLA ("BELLA"),
    CATHERINE ("CATHERINE"),
    DEREK ("DEREK"),
    ELLA ("ELLA"),
    FRANK ("FRANK"),
    GINA ("GINA"),
    HARRY ("HARRY"),
    IRIS ("IRIS"),
    JASON ("JASON"),
    KATE ("KATE"),
    LEO ("LEO"),
    MARIA ("MARIA"),
    NATHAN ("NATHAN"),
    OSCAR ("OSCAR"),
    PHOEBE ("PHOEBE"),
    QUENTIN ("QUENTIN"),
    RUBY ("RUBY"),
    STEPHEN ("STEPHEN"),
    TERESA ("TERESA"),
    URSULA ("URSULA"),
    VINCENT ("VINCENT"),
    WENDY ("WENDY"),
    XAVIER ("XAVIER"),
    YARA ("YARA"),
    ZOE ("ZOE");
    private final String role;
    PersonNames(String role){
        this.role = role;
    }
}