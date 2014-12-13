package filters;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(RequestLoggingFilter.class.getName());

    public void init(FilterConfig fConfig) throws ServletException {
        //	this.context = fConfig.getServletContext();
        LOGGER.info("RequestLoggingFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest) request;
//        Enumeration<String> params = req.getParameterNames();
//        while (params.hasMoreElements()) {
//            String name = params.nextElement();
//            String value = request.getParameter(name);
//            LOGGER.info(req.getRemoteAddr() + "::Request Params::{" + name + "=" + value + "}");
//        }
//
//        Cookie[] cookies = req.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                LOGGER.info(req.getRemoteAddr() + "::Cookie::{" + cookie.getName() + "," + cookie.getValue() + "}");
//            }
//        }

//        HttpServletResponse resp = (HttpServletResponse) response;
//        resp.setHeader("Expires", "Sat, 12 Oct 1991 05:00:00 GMT");
//        resp.setHeader("Etag", "");
//        resp.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate, max-age=0, proxy-revalidate, s-maxage=0");
//        resp.setHeader("Pragma", "no-cache");

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    public void destroy() {
        //we can close resources here
    }

}
