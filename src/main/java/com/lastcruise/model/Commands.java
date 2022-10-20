package com.lastcruise.model;

public enum Commands {
    GO("go"),
    HELP("help"),
    GRAB("grab"),
    PICKUP("grab"),
    TAKE("grab"),
    DROP("drop"),
    INSPECT("inspect"),
    BUILD("craft"),
    CRAFT("craft"),
    QUIT("quit"),
    ESCAPE("escape");


    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
