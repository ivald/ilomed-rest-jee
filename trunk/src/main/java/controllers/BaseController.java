package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * Created by ivald79 on 25/09/2014.
 */
public class BaseController {

    @Context
    protected HttpServletRequest request;

    protected String message;

}
