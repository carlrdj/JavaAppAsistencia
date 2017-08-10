/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoStaff;
import database.ConnectDB;
import dto.Assist;
import dto.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public class DaoStaffImpl implements DaoStaff {

    private final ConnectDB db;
    private final StringBuilder sql;
    private String message;
    private Boolean ok = true;

    public DaoStaffImpl() {
        this.db = new ConnectDB();
        this.sql = new StringBuilder();
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public Boolean staffIns(Staff staff) {
        ok = true;
        sql.delete(0, sql.length()).append("INSERT INTO staffs "
                + "(sta_code, sta_surname, sta_name, sta_email, sta_phone_number, sta_state, created_at, updated_at) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            ps.setString(1, staff.getSta_code());
            ps.setString(2, staff.getSta_surname());
            ps.setString(3, staff.getSta_name());
            ps.setString(4, staff.getSta_email());
            ps.setString(5, staff.getSta_phone_number());
            ps.setInt(6, 1);
            ps.setTimestamp(7, getDateTime());
            ps.setTimestamp(8, getDateTime());

            int cto = ps.executeUpdate();
            if (cto == 0) {
                ok = false;
            } else {
                message = "Se registro correctamente";
            }

        } catch (SQLException e) {
            ok = false;
            message = e.getMessage();
        }
        System.err.println(message);
        return ok;
    }

    @Override
    public Staff staffGetById(Integer id) {
        Staff staff = new Staff();
        sql.delete(0, sql.length()).append("SELECT sta_code, sta_surname, sta_name, sta_email, sta_phone_number FROM staffs WHERE sta_id = ?");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    staff.setSta_id(id);
                    staff.setSta_code(rs.getString(1));
                    staff.setSta_surname(rs.getString(2));
                    staff.setSta_name(rs.getString(3));
                    staff.setSta_email(rs.getString(4));
                    staff.setSta_phone_number(rs.getString(5));
                }
            } catch (Exception e) {
                message = e.getMessage();
            }
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return staff;
    }

    @Override
    public Boolean staffUpd(Staff staff) {
        ok = true;
        sql.delete(0, sql.length()).append("UPDATE staffs SET sta_surname = ?, sta_name = ?, sta_email = ?, sta_phone_number = ?, updated_at = ? WHERE sta_id = ?");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());) {
            ps.setString(1, staff.getSta_surname());
            ps.setString(2, staff.getSta_name());
            ps.setString(3, staff.getSta_email());
            ps.setString(4, staff.getSta_phone_number());
            ps.setTimestamp(5, getDateTime());
            ps.setInt(6, staff.getSta_id());

            int cto = ps.executeUpdate();
            if (cto == 0) {
                ok = false;
                message = "No se pudo actualizar";
                throw new SQLException("Error");
            }
        } catch (SQLException e) {
            ok = false;
            message = e.getMessage();
        }
        return ok;
    }

    @Override
    public Boolean staffDel(List<Integer> ids) {
        ok = true;
        sql.delete(0, sql.length()).append("UPDATE staffs SET sta_state = 2, updated_at = ? WHERE sta_id = ?");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());) {
            boolean check = true;
            cn.setAutoCommit(false);

            for (Integer id : ids) {
                ps.setTimestamp(1, getDateTime());
                ps.setInt(2, id);
                int cto = ps.executeUpdate();
                if (cto == 0) {
                    check = false;
                    message = "No se pudo eliminar personal";
                }
                if (check) {
                    cn.commit();
                } else {
                    ok = false;
                    cn.rollback();
                }
                cn.setAutoCommit(true);
            }

        } catch (Exception e) {

        }
        return ok;
    }

    @Override
    public List<Staff> staffQry(String search) {
        List<Staff> list = null;
        //sql.delete(0, sql.length()).append("SELECT * FROM staffs WHERE sta_surname LIKE '%" + search + "%'");
        sql.delete(0, sql.length()).append("SELECT sta_id, sta_code, sta_surname, sta_name, sta_email, sta_phone_number, sta_state, created_at, updated_at FROM staffs WHERE (sta_surname LIKE ? OR sta_name LIKE ? OR sta_code LIKE ?) AND sta_state = 1");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());) {
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            try (ResultSet rs = ps.executeQuery();) {
                list = new LinkedList<>();
                while (rs.next()) {
                    Staff staff = new Staff();

                    staff.setSta_id(rs.getInt(1));
                    staff.setSta_code(rs.getString(2));
                    staff.setSta_surname(rs.getString(3));
                    staff.setSta_name(rs.getString(4));
                    staff.setSta_email(rs.getString(5));
                    staff.setSta_phone_number(rs.getString(6));
                    staff.setSta_state(rs.getInt(7));
                    staff.setCreated_at(rs.getTimestamp(8));
                    staff.setUpdated_at(rs.getTimestamp(9));

                    list.add(staff);
                }
            } catch (Exception e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
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
    public String staffGenerateCde() {
        String code = "";
        sql.delete(0, sql.length()).append("SELECT MAX(sta_id) AS max_id FROM staffs");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                int new_id = rs.getInt("max_id") + 1;
                String format = "S0000";
                String max = "" + new_id;
                int digit = max.length();
                String f_format = format.substring(0, 5 - digit);
                code = f_format + max;
            }
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return code;
    }

    @Override
    public List<Staff> staffList() {
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
        return list;
    }

}
