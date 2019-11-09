/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnm.flappybirdapiserver;

import com.dungnm.flappybirdapiserver.dao.ScoreDaoImpl;
import com.dungnm.flappybirdapiserver.model.JsonResult;
import com.dungnm.flappybirdapiserver.model.ScoreForm;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @GET
    @Path("top100")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResult submit(){
        JsonResult result = new JsonResult();
        ScoreDaoImpl dao = new ScoreDaoImpl();
        List<ScoreForm> resultData = new ArrayList();
        try {
            resultData = dao.getTop100();
        } catch (Exception ex) {
            result.setResult(false);
            result.setResultData("DBConnectionError");
        } finally {
            dao.closeConnection();
        }
        result.setResultData(resultData);
        return result;
    }
    
    @GET
    @Path("checkExist")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResult checkExist(@QueryParam("account") String account){
        JsonResult result = new JsonResult();
        ScoreDaoImpl dao = new ScoreDaoImpl();
        try {
            if(dao.isExist(account)){
                result.setResult(true);
            } else {
                result.setResult(false);
            }
        } catch (Exception ex) {
            result.setResult(false);
            result.setResultData("DBConnectionError");
        } finally {
            dao.closeConnection();
        }
        return result;
    }
    
    
    @POST
    @Path("submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResult submit(ScoreForm form){
        JsonResult result = new JsonResult();
        ScoreDaoImpl dao = new ScoreDaoImpl();
        try {
            if(dao.isExist(form.getAccount())){
                dao.update(form);
            } else {
                dao.insert(form);
            }
        } catch (Exception ex) {
            result.setResult(false);
            result.setResultData("DBConnectionError");
        } finally {
            dao.closeConnection();
        }
        result.setResult(true);
        return result;
    }
    
    @GET
    @Path("selfBest")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResult getSelfTopScore(@QueryParam("account") String account){
        JsonResult result = new JsonResult();
        ScoreForm resultData = new ScoreForm();
        ScoreDaoImpl dao = new ScoreDaoImpl();
        try {
            resultData = dao.getSelfBest(account);
        } catch (Exception ex) {
            result.setResult(false);
            result.setResultData("DBConnectionError");
        } finally {
            dao.closeConnection();
        }
        result.setResult(true);
        result.setResultData(resultData);
        return result;
    }
}
