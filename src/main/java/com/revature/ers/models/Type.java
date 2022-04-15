package com.revature.ers.models;

public enum Type {
    FOOD {
        @Override
        public String toString() {return "Food";}

        @Override
        public Integer toInt(){
            return 1;
        }
    },
    LODGING {
        @Override
        public String toString() {return "Lodging";}

        @Override
        public Integer toInt(){
            return 2;
        }
    },
    TRAVEL{
        @Override
        public String toString() { return "Travel";}

        @Override
        public Integer toInt(){
            return 3;
        }
    };

    public abstract Integer toInt();
}
