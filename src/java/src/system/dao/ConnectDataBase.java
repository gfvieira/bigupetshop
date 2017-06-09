package src.system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import src.system.utilidades.ErroSql;

public class ConnectDataBase {

    private static ConnectDataBase conexao = null;
    private static Connection connection = null;
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

    public ConnectDataBase() {
        try {
            Class.forName("" + classForName + "");
            connection = DriverManager.getConnection("jdbc:" + conector + "://" + host + ":" + port + "/" + dataBase + "", "" + user + "", "" + password + "");
        } catch (ClassNotFoundException | SQLException ex) {
            ErroSql.Gravar(nomeClasse, "ConnectDataBase,", "criar conexao", ex.getMessage());
        }
    }

    public static Connection openConection() {
        if (connection == null) {
            conexao = new ConnectDataBase();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
//                connection = null;
            } catch (SQLException e) {
                ErroSql.Gravar(nomeClasse, "closeConnection,", "Erro fechar conexão", e.getMessage());
            }
        }
    }
}
