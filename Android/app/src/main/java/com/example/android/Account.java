package com.example.android;

import java.io.Serializable;

public class Account implements Serializable {

        private int id;
        private String firstname;
        private String lastname;
        private String password;
        private String username;
        private String DOB;
        private String securityQuestion;
        private String securityAnswer;
        private String address;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getSecurityQuestion() {
            return securityQuestion;
        }

        public void setSecurityQuestion(String securityQuestion) {
            this.securityQuestion = securityQuestion;
        }

        public String getSecurityAnswer() {
            return securityAnswer;
        }

        public void setSecurityAnswer(String securityAnswer) {
            this.securityAnswer = securityAnswer;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

