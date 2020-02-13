package com.epam.domain;

public class Statistic {
    private int id;
    private int rightChoicesCount;
    private int wrongChoicesCount;

    public Statistic(int id, int rightChoicesCount, int wrongChoicesCount){
        this.id = id;
        this.rightChoicesCount = rightChoicesCount;
        this.wrongChoicesCount = wrongChoicesCount;
    }

    public int getId() {
        return id;
    }

    public int getRightChoicesCount() {
        return rightChoicesCount;
    }

    public void setRightChoicesCount(int rightChoicesCount) {
        this.rightChoicesCount = rightChoicesCount;
    }

    public int getWrongChoicesCount() {
        return wrongChoicesCount;
    }

    public void setWrongChoicesCount(int wrongChoicesCount) {
        this.wrongChoicesCount = wrongChoicesCount;
    }
}
