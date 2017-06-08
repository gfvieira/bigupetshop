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
import src.system.dao.DaoLogin;

public class AttUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int reset = 0;
            Usuario usuario, usuario2 = null;
            DaoAdmin daoadmin = null;
            DaoLogin daologin = null;
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
                        usuario.setNome(usuario.getNome().replaceAll("'", "''"));
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
                    case "ramal":
                        usuario.setRamal(req.getParameterValues(key)[0]);
                        break;
                    case "type":
                        usuario.setAcesso(Integer.parseInt(req.getParameterValues(key)[0]));
                        usuario.setHome(setHome(usuario.getAcesso()));
                        usuario.setTitulo(setTitulo(usuario.getAcesso()));
                        break;
                    case "grupo":
                        usuario.setTipoAcesso(Integer.parseInt(req.getParameterValues(key)[0]));
                        break;
                    case "reset":
                        reset = Integer.parseInt(req.getParameterValues(key)[0]);
                        break;
                    case "active":
                        usuario.setAtivo(Integer.parseInt(req.getParameterValues(key)[0]));
                        break;
                }
            }
            daoadmin = new DaoAdmin();
            daologin = new DaoLogin();
            usuario2 = new Usuario();// criar auditoria usuario
            usuario.setIp_access(req.getRemoteAddr());
            check = daoadmin.atualizaUsuario(usuario);
            if (check) {
                if (reset != 0) {
                    daologin.zeraSenha(usuario);
                    check = daologin.insereSenha(usuario);
                    check = daoadmin.auditoria(usuario.getNip(), "SENHA ATUALIZADA PARA PADRÃO", usuario.getIp_access());
                } else {
                    req.setAttribute("mensagem", "1");
                }
                if (check) {
                    req.setAttribute("mensagem", "0");
                } else {
                    req.setAttribute("mensagem", "1");
                }
            } else {
                req.setAttribute("mensagem", "1");
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
