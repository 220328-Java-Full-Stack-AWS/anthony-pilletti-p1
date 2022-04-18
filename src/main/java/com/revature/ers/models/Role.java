package com.revature.ers.models;

public enum Role {
    EMPLOYEE {
        @Override
        public String toString() {
            return "Employee";
        }

        public Integer toInt() {return 0;}
    },
    FINANCE_MANAGER {
        @Override
        public String toString() {
            return "Finance Manager";
        }

        public Integer toInt() {return 1;}
    }
}
