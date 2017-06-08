package src.system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import src.system.utilidades.ErroSql;

public class ConnectDataBase {

    public static Connection connection = null;
    ErroSql log = null;
    private final String nomeClasse = "ConnectDataBase";

    public ConnectDataBase() {
        try {
            Class.forName("org.postgresql.Driver");
            //Class.forName("org.firebirdsql.jdbc.FBDriver");
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (ClassNotFoundException e) {
            log = new ErroSql();
            log.Gravar(nomeClasse, "ConnectDataBase,", "Class.forName(\"org.postgresql.Driver\");", e.getMessage());

        }
        /*  catch (InstantiationException | IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(ConnectDataBase.class.getName()).log(Level.SEVERE, null, ex); /*  catch (InstantiationException | IllegalAccessException ex) {
         java.util.logging.Error.getLogger(ConnectDataBase.class.getName()).log(Level.SEVERE, null, ex); /*  catch (InstantiationException | IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(ConnectDataBase.class.getName()).log(Level.SEVERE, null, ex); /*  catch (InstantiationException | IllegalAccessException ex) {
         java.util.logging.ErroSql.getLogger(ConnectDataBase.class.getName()).log(Level.SEVERE, null, ex);
         }  */

    }

    public Connection openConection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://10.7.80.4:5432/sigbase", "postgres", "bodeverde2001");
                //connection = DriverManager.getConnection("jdbc:oracle:thin:@ip_servidor:1521:instancia","usuario","senha");  
                //connection = DriverManager.getConnection("jdbc:firebirdsql://localhost/3050:C:\\Firebird\\sisfolhan\\bd\\SISFOLHAN.GBD", "SYSDBA", "masterkey");  
                //connection = DriverManager.getConnection("jdbc:firebirdsql://10.5.176.4:3050/c:\\sistemas\\sisfolhan\\bd\\sisfolhan.gdb", "SYSDBA", "masterkey");  
            } catch (SQLException e) {
                log = new ErroSql();
                log.Gravar(nomeClasse, "openConection,", "Erro abrir conexão", e.getMessage());
                return null;
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                log = new ErroSql();
                log.Gravar(nomeClasse, "closeConnection,", "Erro fechar conexão", e.getMessage());
            }
        }
    }
}
