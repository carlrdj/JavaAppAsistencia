/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Staff;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public interface DaoStaff {
    //Message error
    public String message();
    //Gemerate code
    public String staffGenerateCde();
    //Insert staff
    public Boolean staffIns(Staff staff);
    //Get staff
    public Staff staffGetById(Integer id);
    //Update staff
    public Boolean staffUpd(Staff staff);
    //Remove staff
    public Boolean staffDel(List<Integer> ids);
    //Listar staff
    public List<Staff> staffQry(String search);
    //Listar staff
    public List<Staff> staffList();
}
