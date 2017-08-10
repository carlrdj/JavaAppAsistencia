/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Assist;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public interface DaoAssist {
    //Message error
    public String message();
    //Open day assist
    public void oppenAssist();
    //Save assist
    public Boolean assistSve(Integer ass_id, Integer ass_state);
}
