package com.lunatic.covid19;

class CoronaItem {

    private String State;
    private String Death;
    private String Active;
    private String Recovered;
    private String Confirmed;


    CoronaItem(String state, String death, String active, String recovered, String confirmed) {
        State = state;
        Death = death;
        Active = active;
        Recovered = recovered;
        Confirmed = confirmed;
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
}
