package org.example.libraryapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class BBDDConector {

    private String bd= "library_manage_system";
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/"+bd+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection conexion;

    public BBDDConector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
            if (conexion != null) {
                System.out.println("Conexión a base de datos "+bd+" OK\n");
                conexion.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BBDDConector();
    }
}
