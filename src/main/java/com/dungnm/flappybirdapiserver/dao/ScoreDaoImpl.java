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
import java.text.SimpleDateFormat;
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
    
    private final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        ResultSet rs = getData("SELECT TOP 100 s.*, ROW_NUMBER() OVER(ORDER BY s.score DESC, s.mdate ASC) AS 'Rank' FROM score_holder s ORDER BY s.score DESC, s.mdate ASC");
        List<ScoreForm> list = new ArrayList<>();
        try {
            while(rs.next()){
                list.add(new ScoreForm(rs.getInt(5), rs.getString(2), rs.getInt(3), formater.format(rs.getTimestamp(4))));
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
        String sql = "SELECT count(*) AS 'rank', s.account, s.score, s.mdate\n" +
                        "FROM score_holder p CROSS JOIN\n" +
                        "     (SELECT account, score, mdate FROM score_holder WHERE account = ?) s\n" +
                        "WHERE p.score > s.score or\n" +
                        "      (p.score = s.score and p.mdate <= s.mdate)\n" +
                        "GROUP BY s.account, s.score, s.mdate;";
        ScoreForm result = new ScoreForm();
        try {
            try (PreparedStatement pre = connection.prepareStatement(sql)) {
                pre.setString(1, account);
                ResultSet rs = pre.executeQuery();
                while(rs.next()){
                    result.setRank(rs.getInt(1));
                    result.setAccount(rs.getString(2));
                    result.setScore(rs.getInt(3));
                    result.setmDate(formater.format(rs.getTimestamp(4)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        } 
        return result;
    }
    
}
