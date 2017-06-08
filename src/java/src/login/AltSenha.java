package src.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import src.system.dao.DaoLogin;
import src.modelo.Usuario;

public class AltSenha extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String senha1 = "";
            String senha2 = "";
            Usuario usuario = new Usuario();;
            DaoLogin daologin = null;
            Map mapRequest = request.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            String key;
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                switch (key) {
                    case "senha1":
                        senha1 = request.getParameterValues(key)[0];
                        break;
                    case "senha2":
                        senha2 = request.getParameterValues(key)[0];
                        break;
                    case "nip":
                        usuario.setNip(request.getParameterValues(key)[0]);
                        break;
                }
            }
            if (senha1.equals(senha2)) {
                usuario.setSenha(senha1);
                usuario.setIp_access(request.getRemoteAddr());
                daologin = new DaoLogin();
                boolean check = daologin.zeraSenha(usuario);
                if (check) {
                    check = daologin.insereSenha(usuario);
                    daologin.auditoria(usuario, "SENHA ALTERADA COM SUCESSO");
                    if (check) {
                        usuario = daologin.loginUsuario(usuario);
                        if (usuario == null) {
                            request.setAttribute("mensagem", "Usuario ou Senha inválida.");
                            RequestDispatcher rd = request.getRequestDispatcher("loginSigbase.jsp");
                            rd.forward(request, response);
                        } else if (usuario.getAtivo() == 0) {
                            request.setAttribute("mensagem", "Este usuario não possui acesso ao sistema.");
                            RequestDispatcher rd = request.getRequestDispatcher("loginSigbase.jsp");
                            rd.forward(request, response);
                        } else {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("usuario", usuario);
                            session.setAttribute("nome", usuario.getGraduacaoNome() + usuario.getGuerra());
                            session.setAttribute("nip", usuario.getNip());
                            usuario.setIp_access(request.getRemoteAddr());
                            usuario.setId_session(session.getId());
                            if (usuario.getSenha().equals("Marinha123@")) {
                                request.setAttribute("mensagem", "Foi detectada senha PADRÃO, por favor altere sua senha!");
                                response.sendRedirect("redefinirSenha.jsp");
                            }
                            if (dias(usuario.getDataSenha()) >= 60) {
                                request.setAttribute("mensagem", "Sua senha expirou, por favor altere sua senha!");
                                response.sendRedirect("redefinirSenha.jsp");
                            }
                            daologin.lastAccess(usuario);
                            switch (usuario.getTipoAcesso()) {
                                case 0:
                                    response.sendRedirect("admin/admin_home.jsp");
                                    break;
                            }
                        }
                    }
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("503.html");
                    rd.forward(request, response);
                }
            }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AltSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AltSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private int dias(Date dataSenha) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();
        Calendar dataEnd = Calendar.getInstance();
        Calendar dataStart = Calendar.getInstance();
        Date date = new Date();
        data1.setTime(format.parse(new SimpleDateFormat("dd/MM/yyyy").format(dataSenha)));
        data2.setTime(format.parse(new SimpleDateFormat("dd/MM/yyyy").format(date)));
        int dias = 0;
        if (data2.get(Calendar.YEAR) != data1.get(Calendar.YEAR)) {
            dataEnd.setTime(format.parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date(format.parse("31/12/" + data1.get(Calendar.YEAR)).getTime()))));
            dataStart.setTime(format.parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date(format.parse("01/01/" + data2.get(Calendar.YEAR)).getTime()))));
            dias = (dataEnd.get(Calendar.DAY_OF_YEAR) - data1.get(Calendar.DAY_OF_YEAR))
                    + (data2.get(Calendar.DAY_OF_YEAR) - dataStart.get(Calendar.DAY_OF_YEAR));
            dias++;
        } else {
            dias = data2.get(Calendar.DAY_OF_YEAR) - data1.get(Calendar.DAY_OF_YEAR);
        }
        return dias;
    }
}
