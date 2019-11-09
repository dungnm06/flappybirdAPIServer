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
public class SubmitResult implements Serializable {
    
    private boolean result;

    public SubmitResult() {
    }

    public SubmitResult(boolean result) {
        this.result = result;
    }
    
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SubmitResult{" + "result=" + result + '}';
    }
}
