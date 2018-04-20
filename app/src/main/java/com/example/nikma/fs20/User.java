package com.example.nikma.fs20;

public class User {
        private String usrName;
        private boolean usrAcc;


        public User() {}

        public User(String usrName, boolean usrAcc) {
            this.usrAcc = usrAcc;
            this.usrName = usrName;
        }


        public String getusrName() {
            return usrName;
        }

        public void setusrName(String usrName) {
            this.usrName = usrName;
        }

        public boolean getusrAcc() {
            return usrAcc;
        }

        public void setusrAcc(boolean usrAcc) {
            this.usrAcc = usrAcc;
        }


    }


