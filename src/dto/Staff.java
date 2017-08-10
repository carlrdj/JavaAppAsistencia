/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author SEVEN
 */
public class Staff {
    private int sta_id;
    private String sta_code;
    private String sta_surname;
    private String sta_name;
    private String sta_email;
    private String sta_phone_number;
    private int sta_state;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Set<Assist> assist = new HashSet<>();

    public Staff() {
    }

    public int getSta_id() {
        return sta_id;
    }

    public void setSta_id(int sta_id) {
        this.sta_id = sta_id;
    }

    public String getSta_code() {
        return sta_code;
    }

    public void setSta_code(String sta_code) {
        this.sta_code = sta_code;
    }

    public String getSta_surname() {
        return sta_surname;
    }

    public void setSta_surname(String sta_surname) {
        this.sta_surname = sta_surname;
    }

    public String getSta_name() {
        return sta_name;
    }

    public void setSta_name(String sta_name) {
        this.sta_name = sta_name;
    }

    public String getSta_email() {
        return sta_email;
    }

    public void setSta_email(String sta_email) {
        this.sta_email = sta_email;
    }

    public String getSta_phone_number() {
        return sta_phone_number;
    }

    public void setSta_phone_number(String sta_phone_number) {
        this.sta_phone_number = sta_phone_number;
    }

    public int getSta_state() {
        return sta_state;
    }

    public void setSta_state(int sta_state) {
        this.sta_state = sta_state;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Set<Assist> getAssist() {
        return assist;
    }

    public void setAssist(Set<Assist> assist) {
        this.assist = assist;
    }

    
    
    
    
}
