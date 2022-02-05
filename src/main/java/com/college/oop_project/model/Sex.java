package com.college.oop_project.model;

public enum Sex {
    ŽENSKI(0),
    MUŠKI(1),
    NEPOZNAT(2);

    private final int sexID;

    Sex(int sexID) {
        this.sexID = sexID;
    }

    public static Sex fromInt(int sexID) {
        switch (sexID) {
            case 0: return ŽENSKI;
            case 1: return MUŠKI;
            case 2:
            default: return NEPOZNAT;
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
