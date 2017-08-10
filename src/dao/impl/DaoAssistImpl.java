/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoAssist;
import database.ConnectDB;
import dto.Assist;
import dto.Staff;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public class DaoAssistImpl implements DaoAssist {

    private final ConnectDB db;
    private final StringBuilder sql;
    private String message;
    private Boolean ok = true;

    public DaoAssistImpl() {
        this.db = new ConnectDB();
        this.sql = new StringBuilder();
    }

    @Override
    public String message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean assistSve(Integer ass_id, Integer ass_state) {
        ok = true;
        switch (ass_state) {
            case 1:
                sql.delete(0, sql.length()).append("UPDATE assists SET ass_time_input = ?, ass_state = 2 WHERE ass_id = ?");
                try (Connection cn = db.getConnection();
                        PreparedStatement ps = cn.prepareStatement(sql.toString());) {
                    ps.setTime(1, getTime());
                    ps.setInt(2, ass_id);
                    int cto = ps.executeUpdate();
                    if (cto == 0) {
                        System.err.println("Error");
                    }else{
                        System.err.println("Guardado");
                    }
                } catch (SQLException e) {
                    message = e.getMessage();
                        System.err.println(message);
                }
                break;
            case 2:
                sql.delete(0, sql.length()).append("UPDATE assists SET ass_time_output = ?, ass_state = 3 WHERE ass_id = ?");
                try (Connection cn = db.getConnection();
                        PreparedStatement ps = cn.prepareStatement(sql.toString());) {
                    ps.setTime(1, getTime());
                    ps.setInt(2, ass_id);
                    int cto = ps.executeUpdate();
                    if (cto == 0) {
                        ok = false;
                        System.err.println("Error");
                    }
                } catch (SQLException e) {
                    ok = false;
                    message = e.getMessage();
                }
                break;
        }

        return ok;
    }

    private static java.sql.Timestamp getDateTime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

    private static java.sql.Date getDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    private static java.sql.Time getTime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Time(today.getTime());
    }

    @Override
    public void oppenAssist() {
        List<Staff> list = null;
        String sqlStaff = "SELECT sta_id, sta_code, sta_surname, sta_name FROM staffs WHERE sta_state = 1";
        String sqlAssist = "SELECT ass_id, ass_date, ass_time_input, ass_time_output, ass_state FROM assists WHERE sta_id = ? AND ass_date = ?";
        sql.delete(0, sql.length()).append("INSERT INTO assists "
                + "(sta_id, ass_date, ass_time_input, ass_time_output, ass_state, created_at, updated_at) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)");
        try (Connection cn = db.getConnection();
                PreparedStatement psStaff = cn.prepareStatement(sqlStaff);
                PreparedStatement psAssist = cn.prepareStatement(sqlAssist);
                ResultSet rsStaff = psStaff.executeQuery();) {
            list = new LinkedList<>();
            while (rsStaff.next()) {
                Staff staff = new Staff();

                staff.setSta_id(rsStaff.getInt(1));
                staff.setSta_code(rsStaff.getString(2));
                staff.setSta_surname(rsStaff.getString(3));
                staff.setSta_name(rsStaff.getString(4));

                psAssist.setInt(1, rsStaff.getInt(1));
                psAssist.setDate(2, getDate());
                
                try (ResultSet rsAssist = psAssist.executeQuery()) {                    
                    Assist assist = new Assist();
                    if (rsAssist.next()) {                        
                        assist.setAss_id(rsAssist.getInt(1));                 
                        assist.setAss_date(rsAssist.getDate(2));                 
                        assist.setAss_time_input(rsAssist.getTime(3));                 
                        assist.setAss_time_output(rsAssist.getTime(4));                 
                        assist.setAss_state(rsAssist.getInt(5));                       
                        staff.getAssist().add(assist);
                    }else{
                        try (PreparedStatement ps = cn.prepareStatement(sql.toString())){                               
                            ps.setInt(1, staff.getSta_id());
                            ps.setDate(2, getDate());
                            ps.setTime(3, null);
                            ps.setTime(4, null);
                            ps.setInt(5, 1);
                            ps.setTimestamp(6, getDateTime());
                            ps.setTimestamp(7, getDateTime());
                            
                            int cto = ps.executeUpdate();
                            if(cto == 0){
                                System.err.println("No se creo");
                            }else{                                
                                System.err.println("Si");
                            }
                            
                        } catch (Exception e) {
                        }
                    }
                } catch (SQLException e) {
                    message = e.getMessage();
                }

                list.add(staff);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
    }
}
