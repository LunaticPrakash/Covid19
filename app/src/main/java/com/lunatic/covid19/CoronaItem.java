package com.lunatic.covid19;

class CoronaItem {

    private String State;
    private String Death;
    private String Active;
    private String Recovered;
    private String Confirmed;
    private String LastUpdated;
    private String TodayDeath, TodayRecovered, TodayActive;


    CoronaItem(String state, String death, String active, String recovered, String confirmed,
               String lastUpdated, String todayDeath, String todayRecovered, String todayActive) {
        State = state;
        Death = death;
        Active = active;
        Recovered = recovered;
        Confirmed = confirmed;
        LastUpdated = lastUpdated;
        TodayDeath = todayDeath;
        TodayRecovered = todayRecovered;
        TodayActive = todayActive;

    }

    String getState() {
        return State;
    }

    String getDeath() {
        return Death;
    }

    String getActive() {
        return Active;
    }

    String getRecovered() {
        return Recovered;
    }

    String getConfirmed() {
        return Confirmed;
    }

    String getLastUpdated() {
        return LastUpdated;
    }

    String getTodayDeath() {
        return TodayDeath;
    }

    String getTodayRecovered() {
        return TodayRecovered;
    }

    String getTodayActive() {
        return TodayActive;
    }
}
