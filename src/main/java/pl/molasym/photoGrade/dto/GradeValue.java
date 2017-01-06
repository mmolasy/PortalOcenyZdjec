package pl.molasym.photoGrade.dto;

import pl.molasym.photoGrade.entities.Grade;

/**
 * Created by moliq on 26.12.16.
 */
public enum GradeValue {
    NoRate(0), One(1), Two(2), Three(3), Four(4), Five(5);

    private int i;

    private GradeValue(int i){
        this.i=i;
    }

    public Integer getGrade(){
        return i;
    }

    public String toString(){
        switch(this){
            case NoRate :
                return "NoRate";
            case One :
                return "One";
            case Two :
                return "Two";
            case Three :
                return "Three";
            case Four :
                return "Four";
            case Five :
                return "Five";
        }
        return null;
    }

    public static boolean valueOf(GradeValue enumType, String value){
        return enumType.toString().equals(value);
    }
}
