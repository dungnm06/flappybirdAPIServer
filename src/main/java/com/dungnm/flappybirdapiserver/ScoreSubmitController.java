/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnm.flappybirdapiserver;

import com.dungnm.flappybirdapiserver.model.ScoreForm;
import com.dungnm.flappybirdapiserver.model.SubmitResult;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Tewi
 */
@Path("submit")
public class ScoreSubmitController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SubmitResult submit(){
        return new SubmitResult(true);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SubmitResult submit(ScoreForm form){
        return new SubmitResult(true);
    }
}
