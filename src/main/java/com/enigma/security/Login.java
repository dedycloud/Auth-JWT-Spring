package com.enigma.security;

public class Login {
private String jwt;
private String role;
private String idUser;
private String fullname;

    public Login(String jwt, String role, String idUser, String fullname) {
        this.jwt = jwt;
        this.role = role;
        this.idUser = idUser;
        this.fullname = fullname;
    }

    public Login(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
