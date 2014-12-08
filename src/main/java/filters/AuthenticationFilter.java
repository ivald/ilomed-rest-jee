package filters;

import constant.CommonConstant;
import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class.getName());
    private static final String INCORRECT_URI = "/restapp/pages/console/";
    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        context = fConfig.getServletContext();
        LOGGER.info("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI().toLowerCase();
        LOGGER.info("Requested Resource::" + uri);

        HttpSession session = req.getSession(true);

        if(session.isNew() || INCORRECT_URI.equals(uri) || session.getAttribute(CommonConstant.USER_TICKET) == null) {
            LOGGER.info("Unauthorized access request");
            session.invalidate();
            res.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        //close any resources here
    }

}
