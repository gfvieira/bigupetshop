package src.system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.system.utilidades.ErroSql;

public class ConnectDataBase {

    private static Connection connection = null;
    private static Statement statement;
    private static final String nomeClasse = "ConnectDataBase";
//    ======================================================== banco postgresql
    private final String classForName = "org.postgresql.Driver";
    private final String conector = "postgresql";
    private final String password = "bodeverde2001";
    private final String user = "postgres";
    private final String dataBase = "sigbase";
    private final String host = "localhost";
    private final String port = "5432";
//    ======================================================== banco firebird
//    private final String classForName = "org.firebirdsql.jdbc.FBDriver";
//    private final String conector = "firebirdsql";
//    private final String password = "masterkey";
//    private final String user = "SYSDBA";
//    private final String dataBase = "c:\\sistemas\\sisfolhan\\bd\\sisfolhan.gdb";
//    private final String host = "10.5.176.4";
//    private final String port = "3050";

    private ConnectDataBase() {
        try {
            Class.forName("" + classForName + "");
            connection = DriverManager.getConnection("jdbc:" + conector + "://" + host + ":" + port + "/" + dataBase + "", "" + user + "", "" + password + "");
        } catch (ClassNotFoundException | SQLException ex) {
            ErroSql.Gravar(nomeClasse, "ConnectDataBase,", "Erro criar conexão", ex.getMessage());
        }
    }

    public static synchronized Statement getStatement() {
        try {
            if (connection == null) {
                new ConnectDataBase();
            }
//            if (connection.isValid(0) || connection.isClosed()) {
//                connection.close();
//            }
            statement = connection.createStatement();
        } catch (SQLException ex) {
            ErroSql.Gravar(nomeClasse, "ConnectDataBase,", "Erro abrir conexão", ex.getMessage());
        }
        return statement;
    }

    public static void closeConnection(){
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    try {
                        connection.close();
//                connection = null;
                    } catch (SQLException ex) {
                        ErroSql.Gravar(nomeClasse, "closeConnection,", "Erro fechar conexão", ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                        ErroSql.Gravar(nomeClasse, "closeConnection,", "Erro fechar conexão", ex.getMessage());
            }
        }
    }
}
