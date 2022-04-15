package com.revature.ers.models;

public enum Status {
    PENDING {
        @Override
        public String toString() {
            return "Pending";
        }

        @Override
        public Integer toInt(){
            return 1;
        }
    },
    APPROVED {
        @Override
        public String toString() {
            return "Approved";
        }

        @Override
        public Integer toInt(){
            return 2;
        }
    },
    DENIED {
        @Override
        public String toString() {
            return "Denied";
        }

        @Override
        public Integer toInt(){
            return 3;
        }
    };


    public abstract Integer toInt();
}
