/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnm.flappybirdapiserver.dao;

import com.dungnm.flappybirdapiserver.model.ScoreForm;
import java.util.List;


/**
 *
 * @author Tewi
 */
public interface ScoreDao {
    public boolean isExist(String account) throws Exception ;
    
    public boolean insert(ScoreForm form) throws Exception;
    
    public boolean update(ScoreForm form) throws Exception;
    
    public List<ScoreForm> getTop100() throws Exception;
    
    public ScoreForm getSelfBest(String account) throws Exception;
    
    public void closeConnection();
}
