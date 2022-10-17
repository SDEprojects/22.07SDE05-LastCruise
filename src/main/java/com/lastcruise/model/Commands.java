package com.lastcruise.model;

public enum Commands {
    GO("go"),
    HELP("help"),
    GRAB("grab"),
    DROP("drop"),
    INSPECT("inspect"),
    CRAFT("craft"),
    QUIT("quit");


    private final String value;

    Commands(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
