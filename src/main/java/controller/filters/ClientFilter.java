package controller.filters;

import controller.constants.Const;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Filter to prevent illegal access
 * to client pages
 */
public class ClientFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(ClientFilter.class.getName());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Client filter...");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if ((int) session.getAttribute(Const.ROLE) == 1) {
            chain.doFilter(request, response);
        } else {
            logger.info("client role != 1");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
