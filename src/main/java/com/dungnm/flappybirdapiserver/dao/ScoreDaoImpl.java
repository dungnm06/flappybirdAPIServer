/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnm.flappybirdapiserver.dao;

import com.dungnm.flappybirdapiserver.model.ScoreForm;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Tewi
 */
@Dependent
public class ScoreDaoImpl extends BaseDao implements ScoreDao {

    @Override
    public boolean isExist(String account) throws Exception {
        String sql = "SELECT * FROM [score_holder] WHERE [account] = ?";
        try {
            try (PreparedStatement pre = connection.prepareStatement(sql)) {
                pre.setString(1, account);
                ResultSet rs = pre.executeQuery();
                while(rs.next()){
                    pre.close();
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        } 
        return false;
    }

    @Override
    public boolean insert(ScoreForm form) throws Exception {
        String sql = "INSERT INTO [score_holder] VALUES(?,?,?)";
        boolean result = false;
        try {
            try (PreparedStatement pre = connection.prepareStatement(sql)) {
                pre.setString(1, form.getAccount());
                pre.setInt(2, form.getScore());
                pre.setString(3, form.getmDate());
                result = pre.executeUpdate() == 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
        return result;
    }

    @Override
    public boolean update(ScoreForm form) throws Exception {
        String sql = "UPDATE [score_holder] SET [score] = ?, [mdate] = ? WHERE [account] = ?";
        boolean result = false;
        try {
            try (PreparedStatement pre = connection.prepareStatement(sql)) {
                pre.setInt(1, form.getScore());
                pre.setString(2, form.getmDate());
                pre.setString(3, form.getAccount());
                result = pre.executeUpdate() == 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
        return result;
    }

    @Override
    public List<ScoreForm> getTop100() throws Exception {
        ResultSet rs = getData("SELECT TOP 100 * FROM score_holder ORDER BY score DESC");
        List<ScoreForm> list = new ArrayList<>();
        try {
            while(rs.next()){
                list.add(new ScoreForm(rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }        
        return list;
    }

    @Override
    public ScoreForm getSelfBest(String account) throws Exception {
        String sql = "SELECT * FROM [score_holder] WHERE [account] = ?";
        ScoreForm result = new ScoreForm();
        try {
            try (PreparedStatement pre = connection.prepareStatement(sql)) {
                pre.setString(1, account);
                ResultSet rs = pre.executeQuery();
                while(rs.next()){
                    result.setAccount(rs.getString(2));
                    result.setScore(rs.getInt(3));
                    result.setmDate(rs.getString(4));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        } 
        return result;
    }
    
}
