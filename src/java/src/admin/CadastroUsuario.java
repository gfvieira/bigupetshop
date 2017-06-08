package src.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.system.dao.DaoAdmin;
import src.modelo.Usuario;

public class CadastroUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Usuario usuario, usuario2 = null;
            DaoAdmin daoadmin = null;
            boolean check;
            Map mapRequest = req.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            String key;
            usuario = new Usuario();
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                switch (key) {
                    case "nome":
                        usuario.setNome(req.getParameterValues(key)[0].toUpperCase());
                        break;
                    case "admin":
                        usuario.setAdmin(Integer.parseInt(req.getParameterValues(key)[0]));
                        break;
                    case "nip":
                        usuario.setNip(req.getParameterValues(key)[0]);
                        usuario.setNip(usuario.getNip().replaceAll(" ", ""));
                        usuario.setNip(usuario.getNip().replaceAll("/.", ""));
                        break;
                    case "guerra":
                        usuario.setGuerra(req.getParameterValues(key)[0].toUpperCase());
                        usuario.setGuerra(usuario.getGuerra().replaceAll("'", "''"));
                        break;
                    case "grad":
                        usuario.setGraduacao(Integer.parseInt(req.getParameterValues(key)[0]));
                        break;
                    case "setor":
                        usuario.setSetor(req.getParameterValues(key)[0].toUpperCase());
                        break;
                    case "ramal":
                        usuario.setRamal(req.getParameterValues(key)[0].toUpperCase());
                        break;
                    case "grupo":
                        usuario.setTipoAcesso(Integer.parseInt(req.getParameterValues(key)[0]));
                        break;
                    case "type":
                        usuario.setAcesso(Integer.parseInt(req.getParameterValues(key)[0]));
                        usuario.setHome(setHome(usuario.getAcesso()));
                        usuario.setTitulo(setTitulo(usuario.getAcesso()));
                        break;
                }
            }
            usuario.setIp_access(req.getRemoteAddr());
            daoadmin = new DaoAdmin();
            usuario2 = daoadmin.buscaUsuario(usuario);
            if (usuario2 != null) {
                req.setAttribute("mensagem", "2");
            } else {
                check = daoadmin.insereUsuario(usuario);
                if (check) {
                    check = daoadmin.insereSenha(usuario.getNip(), "Marinha123@", usuario.getIp_access());
                    if (check) {
                        check = daoadmin.auditoria(usuario.getNip(), "USUARIO CADASTRADO", usuario.getIp_access());
                        check = daoadmin.auditoria(usuario.getNip(), "SENHA PADRÃO CADASTRADA", usuario.getIp_access());
                        if (check) {
                            req.setAttribute("mensagem", "0");
                        } else {
                            req.setAttribute("mensagem", "1");
                        }
                    } else {
                        req.setAttribute("mensagem", "1");
                    }
                } else {
                    req.setAttribute("mensagem", "1");
                }
            }
            RequestDispatcher rd = req.getRequestDispatcher("admin_cadastro.jsp");
            rd.forward(req, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String setHome(int acesso) {
        String retornoHome = "";
        switch (acesso) {
            case 341:
                retornoHome = "bh30/bh34/bh34_home.jsp";
        }
        return retornoHome;
    }

    private String setTitulo(int acesso) {
        String retornoTitulo = "";
        switch (acesso) {
            case 341:
                retornoTitulo = "Divisão de Telemática BH-34";
        }
        return retornoTitulo;
    }

}
