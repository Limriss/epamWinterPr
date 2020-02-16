package com.epam.domain;

public class Statistic {
    private String name;
    private byte stat;

    public Statistic(String name, byte stat){
        this.name = name;
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public byte getStat() {
        return stat;
    }
}
