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
    ESCAPE("escape"),
    VOL("volume"),

    VOLUME("volume"),
    MUSIC("music"),
    FX("sound"),
    SOUND("sound");


    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
