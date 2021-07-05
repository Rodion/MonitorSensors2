package by.gourianova.monitorsensors.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter("/controller")
public class EncodingFilter implements Filter{

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {}
    public void destroy() {}
}
