/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnm.flappybirdapiserver;

import com.dungnm.flappybirdapiserver.dao.ScoreDao;
import com.dungnm.flappybirdapiserver.model.JsonResult;
import com.dungnm.flappybirdapiserver.model.ScoreForm;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Tewi
 */
@Path("score")
public class ScoreSubmitController {
    
    @Inject
    private ScoreDao dao;
    
    @GET
    @Path("top100")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResult submit(){
        JsonResult result = new JsonResult();
        List<ScoreForm> resultData = new ArrayList();
        try {
            resultData = dao.getTop100();
        } catch (Exception ex) {
            result.setResult(false);
            result.setResultData("DBConnectionError");
        } finally {
//            dao.closeConnection();
        }
        result.setResult(true);
        result.setResultData(resultData);
        return result;
    }
    
    @GET
    @Path("checkExist")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResult checkExist(@QueryParam("account") String account){
        JsonResult result = new JsonResult();
        try {
            result.setResult(dao.isExist(account));
        } catch (Exception ex) {
            result.setResult(false);
            result.setResultData("DBConnectionError");
        } finally {
//            dao.closeConnection();
        }
        return result;
    }
    
    
    @POST
    @Path("submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResult submit(ScoreForm form){
        JsonResult result = new JsonResult();
        boolean submitResult = false;
        try {
            ScoreForm selfBest = dao.getSelfBest(form.getAccount());
            if(selfBest.getScore() >= form.getScore()){
                result.setResult(true);
                return result;
            }
            submitResult = dao.isExist(form.getAccount()) ? dao.update(form) : dao.insert(form);
        } catch (Exception ex) {
            result.setResult(false);
            result.setResultData("DBConnectionError");
        } finally {
//            dao.closeConnection();
        }
        result.setResult(submitResult);
        return result;
    }
    
    @GET
    @Path("selfBest")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResult getSelfTopScore(@QueryParam("account") String account){
        JsonResult result = new JsonResult();
        ScoreForm resultData = new ScoreForm();
        try {
            resultData = dao.getSelfBest(account);
        } catch (Exception ex) {
            result.setResult(false);
            result.setResultData("DBConnectionError");
        } finally {
//            dao.closeConnection();
        }
        result.setResult(true);
        result.setResultData(resultData);
        return result;
    }
}
