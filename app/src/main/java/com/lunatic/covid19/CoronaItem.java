package com.lunatic.covid19;

class CoronaItem {

    private final String state;
    private final String death;
    private final String active;
    private final String recovered;
    private final String confirmed;
    private final String lastUpdated;
    private final String todayDeath;
    private final String todayRecovered;
    private final String todayConfirmed;
    private final String vaccinated1;
    private final String vaccinated2;
    private final String todayTested;
    private final  String tested;

    CoronaItem(String state, String death, String active, String recovered, String confirmed,
               String lastUpdated, String todayDeath, String todayRecovered, String todayConfirmed,
               String vaccinated1, String vaccinated2, String tested, String todayTested
    ) {
        this.state = state;
        this.death = death;
        this.active = active;
        this.recovered = recovered;
        this.confirmed = confirmed;
        this.lastUpdated = lastUpdated;
        this.todayDeath = todayDeath;
        this.todayRecovered = todayRecovered;
        this.todayConfirmed = todayConfirmed;
        this.vaccinated1 = vaccinated1;
        this.vaccinated2 = vaccinated2;
        this.tested = tested;
        this.todayTested = todayTested;
    }

    String getState() {
        return this.state;
    }

    String getDeath() {
        return this.death;
    }

    String getActive() {
        return this.active;
    }

    String getRecovered() {
        return this.recovered;
    }

    String getConfirmed() {
        return this.confirmed;
    }

    String getLastUpdated() {
        return this.lastUpdated;
    }

    String getTodayDeath() {
        return this.todayDeath;
    }

    String getTodayRecovered() {
        return this.todayRecovered;
    }

    public String getTodayConfirmed() {
        return todayConfirmed;
    }

    public String getVaccinated1() {
        return vaccinated1;
    }

    public String getVaccinated2() {
        return vaccinated2;
    }

    public String getTodayTested() {
        return todayTested;
    }

    public String getTested() {
        return tested;
    }
}
