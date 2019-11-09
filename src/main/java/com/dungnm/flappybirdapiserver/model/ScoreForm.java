/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnm.flappybirdapiserver.model;

import java.io.Serializable;

/**
 *
 * @author Tewi
 */
public class ScoreForm implements Serializable {
    
    private String account;
    
    private int score;

    public ScoreForm() {
    }

    public ScoreForm(String account, int score) {
        this.account = account;
        this.score = score;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
