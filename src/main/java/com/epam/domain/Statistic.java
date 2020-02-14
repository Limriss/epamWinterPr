package com.epam.domain;

public class Statistic {
    private int id;
    private String name;
    private byte stat;

    public Statistic(int id, String name, byte stat){
        this.id = id;
        this.name = name;
        this.stat = stat;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getStat() {
        return stat;
    }

    public void setStat(byte stat) {
        this.stat = stat;
    }
}
