/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnm.flappybirdapiserver;

import com.dungnm.flappybirdapiserver.dao.ScoreDao;
import com.dungnm.flappybirdapiserver.dao.ScoreDaoImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author Tewi
 */
public class ScoreApplicationBinder extends AbstractBinder {
    
    @Override
    protected void configure() {
        bind(ScoreDaoImpl.class).to(ScoreDao.class);
    }
}