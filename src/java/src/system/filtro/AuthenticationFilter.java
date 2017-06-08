package src.system.filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import src.modelo.Usuario;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;
    private Usuario usuario;
    private final String urlContexto = "bigupetshop";

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        if (session != null) {
            usuario = (Usuario) session.getAttribute("usuario");
        }
        String urlRequest = req.getRequestURI();
        boolean check = false;
        switch (urlRequest) {
            case "/" + urlContexto + "/":
            case "/" + urlContexto + "/loginSigbase.jsp":
            case "/" + urlContexto + "/bigupetshop/Login.jsp"://servlet
            case "/" + urlContexto + "/redefinirSenha.jsp"://pagina
            case "/" + urlContexto + "/AltSenha.jsp"://servlet
            case "/" + urlContexto + "/LogOff.jsp"://servlet
            //=============================================================OFICINA BH-50
            case "/" + urlContexto + "/oficina/oficinabh50.jsp"://pagina
            case "/" + urlContexto + "/oficina/Oficinabh50.jsp"://servlet
            case "/" + urlContexto + "/oficina/Bh50Consult.jsp"://servlet
            case "/" + urlContexto + "/oficina/Bh50ConsultOs.jsp"://servlet
            //=============================================================OFICINA BH-34
            case "/" + urlContexto + "/telematica/telematica.jsp"://pagina
            case "/" + urlContexto + "/telematica/Oficinabh34.jsp"://servlet
            case "/" + urlContexto + "/telematica/SateConsult.jsp"://servlet
            case "/" + urlContexto + "/telematica/SateConsultOs.jsp"://servlet
                check = true;
                break;
        }
        if (usuario != null && check == false) {
            switch (usuario.getAcesso()) {
                case 0: //=============================================================ADMIN
                    switch (urlRequest) {
                        case "/" + urlContexto + "/admin/admin_home.jsp":
                        case "/" + urlContexto + "/admin/admin_cadastro.jsp":
                        case "/" + urlContexto + "/admin/ListarUsuario.jsp"://servlet
                        case "/" + urlContexto + "/admin/ExibirUsuario.jsp"://pagina
                        case "/" + urlContexto + "/admin/AttUsuario.jsp"://servlet
                        case "/" + urlContexto + "/admin/admin/CadastroUsuario.jsp"://servlet
                            check = true;
                            break;
                    }
                    break;
                case 104: //=============================================================MERCATO
                    switch (urlRequest) {
                        case "/" + urlContexto + "/bh10/mercato/mercato_home.jsp":
                            check = true;
                            break;
                    }
                    break;
                case 341: //=============================================================BH-34
                    switch (urlRequest) {
                        case "/" + urlContexto + "/bh30/bh34/bh34_home.jsp":
                            check = true;
                            break;
                    }
                    break;
                case 2:
                case 31:
                    check = true;
                    break;
            }
            if (usuario.getAdmin() == 1) {
                switch (urlRequest) {
                    case "/" + urlContexto + "/admin/admin_cadastro.jsp":
                    case "/" + urlContexto + "/admin/ListarUsuario.jsp"://servlet
                    case "/" + urlContexto + "/admin/ExibirUsuario.jsp"://pagina
                    case "/" + urlContexto + "/admin/AttUsuario.jsp"://servlet
                    case "/" + urlContexto + "/admin/CadastroUsuario.jsp"://servlet
                        check = true;
                        break;
                }
            }
        }
        if (check) {
            chain.doFilter(request, response);
        } else {
            System.out.print("FILTRADO ============================================================ SEM ACESSO");
        }
    }

    @Override
    public void destroy() {
        //close any resources here
    }

}
