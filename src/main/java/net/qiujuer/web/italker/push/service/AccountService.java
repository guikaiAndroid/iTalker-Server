package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/account")
public class AccountService {
    //Get 127.0.0.1/api/account/login
    @GET
    @Path("/login")
    public String get() {
        return "Service api Successful";
    }

    //Post 127.0.0.1/api/account/login
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User post() {
        User user = new User();
        user.setName("桂凯");
        user.setSex(1);
        return user;
    }

}
