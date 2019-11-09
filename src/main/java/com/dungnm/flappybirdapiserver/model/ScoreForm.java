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
    
    private int rank;
    
    private String account;
    
    private int score;
    
    private String mDate;

    public ScoreForm() {
    }

    public ScoreForm(int rank, String account, int score, String mDate) {
        this.rank = rank;
        this.account = account;
        this.score = score;
        this.mDate = mDate;
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

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
