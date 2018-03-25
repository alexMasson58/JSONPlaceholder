package com.masson.alex.jsonplaceholder.model;

import java.io.Serializable;

/**
 * Created by alex on 24/03/2018.
 */

public class Compagny implements Serializable{
    private String name;
    private String catchPhrase;
    private String bs;


    public Compagny() {

    }

    public Compagny(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
