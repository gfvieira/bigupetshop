package src.system.utilidades;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import src.system.dao.ConnectDataBase;

public class ErroSql {

    HttpServletResponse response;

    public static void Gravar(String classe, String metodo, String msgsql, String msg) {
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
              ConnectDataBase.getStatement().executeUpdate(insere2TableSQL);
        } catch (SQLException e) {
            
        } finally {
            ConnectDataBase.closeConnection();
        }
    }
}
