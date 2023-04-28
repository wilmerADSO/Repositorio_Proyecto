import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion{

        private String bd="distribuidora_18";
        private String url="jdbc:mysql://localhost:3306/"+bd;
        private String user="root";
        private String pass="12024";
        Connection conexion=null;
        public Connection conecta(){
            try{
                conexion= DriverManager.getConnection(url,user,pass);

            }catch(SQLException e){
                System.out.println("Eror en la conexion: "+e);
            }
            return conexion;
        }

    }

