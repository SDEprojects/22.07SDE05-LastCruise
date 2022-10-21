package com.lastcruise.view;

public enum Colors {
    RED{
        public String toString(){
            return "\u001B[31m";
        }
    },
    BLUE{
        public String toString(){
            return "\u001B[34m";
        }
    },
    GREEN{
        public String toString(){
            return "\u001B[32m";
        }
    },
    RESET{
        public String toString(){
            return "\u001B[0m";
        }
    }
}
