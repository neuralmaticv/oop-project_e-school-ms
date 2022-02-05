package com.college.oop_project.model;

public enum SchoolGrade {
    NaN(0),
    I(1),
    II(2),
    III(3),
    IV(4),
    V(5),
    VI(6),
    VII(7),
    VIII(8),
    IX(9);

    private final int schoolGrade;

    SchoolGrade(int schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public static SchoolGrade fromInt(int schoolGrade) {
        switch (schoolGrade) {
            case 1: return I;
            case 2: return II;
            case 3: return III;
            case 4: return IV;
            case 5: return V;
            case 6: return VI;
            case 7: return VII;
            case 8: return VIII;
            case 9: return IX;
            case 0:
            default: return NaN;
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
