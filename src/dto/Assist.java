/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author SEVEN
 */
public class Assist {
    private int ass_id;
    private int sta_id;
    private Date ass_date;
    private Time ass_time_input;
    private Time ass_time_output;
    private int ass_state;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Assist() {
    }

    public Assist(int ass_id, int sta_id, Date ass_date, Time ass_time_input, Time ass_time_output, int ass_state, Timestamp created_at, Timestamp updated_at) {
        this.ass_id = ass_id;
        this.sta_id = sta_id;
        this.ass_date = ass_date;
        this.ass_time_input = ass_time_input;
        this.ass_time_output = ass_time_output;
        this.ass_state = ass_state;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getAss_id() {
        return ass_id;
    }

    public void setAss_id(int ass_id) {
        this.ass_id = ass_id;
    }

    public int getSta_id() {
        return sta_id;
    }

    public void setSta_id(int sta_id) {
        this.sta_id = sta_id;
    }

    public Date getAss_date() {
        return ass_date;
    }

    public void setAss_date(Date ass_date) {
        this.ass_date = ass_date;
    }

    public Time getAss_time_input() {
        return ass_time_input;
    }

    public void setAss_time_input(Time ass_time_input) {
        this.ass_time_input = ass_time_input;
    }

    public Time getAss_time_output() {
        return ass_time_output;
    }

    public void setAss_time_output(Time ass_time_output) {
        this.ass_time_output = ass_time_output;
    }

    public int getAss_state() {
        return ass_state;
    }

    public void setAss_state(int ass_state) {
        this.ass_state = ass_state;
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
    
    
}
