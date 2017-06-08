package src.system.utilidades;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import src.system.dao.ConnectDataBase;

public class ErroSql {

    private ConnectDataBase connectDataBase = null;
    private Statement statement;
    HttpServletResponse response;

    public ErroSql() {
        connectDataBase = new ConnectDataBase();
    }

    public void Gravar(String classe, String metodo, String msgsql, String msg) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        msgsql = msgsql.replace("'", "");
        String insere2TableSQL = "INSERT INTO errosql("
                + "classe, "
                + "metodo, "
                + "sql, "
                + "mensagem, "
                + "data, "
                + "hora)"
                + " VALUES ("
                + "'" + classe + "', "
                + "'" + metodo + "', "
                + "'" + msgsql + "', "
                + "'" + msg + "', "
                + "'" + date + "', "
                + "'" + thisSec + "');";
        try {
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insere2TableSQL);
        } catch (SQLException e) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out;
            try {
                out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ERRO</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>ERRO!!!!</h1>");
                out.println("</body>");
                out.println("</html>");
            } catch (IOException ex) {
                Logger.getLogger(ErroSql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            connectDataBase.closeConnection();
        }
    }
}
