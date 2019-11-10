/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnm.flappybirdapiserver;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Tewi
 */
public class ScoreApplication extends ResourceConfig {
    
    public ScoreApplication() {
        register(new ScoreApplicationBinder());
        packages(true, "com.dungnm.flappybirdapiserver");
    }
}
