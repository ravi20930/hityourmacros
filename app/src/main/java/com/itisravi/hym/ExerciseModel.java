package com.itisravi.hym;

public class ExerciseModel {

//  variables for plan exercise list
    int typeId;
    String name;
    int sets;
    int reps;
    int rest;
    String load;

//  variables for routine exercise list
    String exName;
    int setsR;
    String repsR;


    // getters for routine exercise list
    public String getExName() {
        return exName;
    }

    public int getSetsR() {
        return setsR;
    }

    public String getRepsR() {
        return repsR;
    }


    // constructor for routine exercise list
    public ExerciseModel(String exName, int setsR, String repsR) {
        this.exName = exName;
        this.setsR = setsR;
        this.repsR = repsR;
    }

//    null constructor
    public ExerciseModel() {
    }


//    constructor for plan exercise list
    public ExerciseModel(int typeId, String name, int sets, int reps, int rest, String load) {
        this.typeId = typeId;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
        this.load = load;
    }

//    getters for plan exercise list
    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int getRest() {
        return rest;
    }

    public String getLoad() {
        return load;
    }
}
