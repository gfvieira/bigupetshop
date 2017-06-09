package src.system.filtro;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TimerFilter implements Filter {

    private ServletContext context = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("TimerFilter initialized");

    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String ip = req.getRemoteAddr();
        long inicio = System.currentTimeMillis();
        chain.doFilter(request, response);
        long fim = System.currentTimeMillis();

        String nome = "";
        if (request instanceof HttpServletRequest) {
            nome = ((HttpServletRequest) request).getRequestURI();
        }
        if (!nome.endsWith("jsp")
                || nome.endsWith("html")) {

        } else {
//            context.log("====== TEMPO DE : "+ (fim - inicio) + "ms PARA: " +nome+" NO IP: " + ip);
            System.out.print("====== TEMPO DE : " + (fim - inicio) + "ms PARA: " + nome + " NO IP: " + ip);
        }
    }
}
