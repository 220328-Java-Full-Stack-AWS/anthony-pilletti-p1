package com.revature.ers.models;

public enum Status {
    PENDING {
        @Override
        public String toString() {
            return "Pending";
        }

        @Override
        public Integer toInt(){
            return 0;
        }
    },
    APPROVED {
        @Override
        public String toString() {
            return "Approved";
        }

        @Override
        public Integer toInt(){
            return 1;
        }
    },
    DENIED {
        @Override
        public String toString() {
            return "Denied";
        }

        @Override
        public Integer toInt(){
            return 2;
        }
    };


    public abstract Integer toInt();
}
