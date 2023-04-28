import java.sql.*;
import java.util.ArrayList;

public class Gestion_Sucursal {
    conexion con = new conexion();
    private ArrayList<Sucursal> datos = new ArrayList<>();

    Statement st = null; //Preparar la consulta sin parametros
    PreparedStatement ps = null; //Prepara la consulta (requiere parametros)
    ResultSet res = null; //Almacenar el resultado de una consulta
    Connection conec = null;


    public ArrayList<Sucursal> consultarSucursal() {

        try {
            String sql = "select * from sucursal";
            conec = con.conecta(); //Abrir conexion
            st = conec.createStatement();
            res = st.executeQuery(sql);

            while (res.next()) {
                Sucursal suc = new Sucursal(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7));
                datos.add(suc);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar: " + ex);
        }
        return datos;
    }

    public Sucursal buscarSucursal(String id_sucursal) {
        Sucursal suc = null;
        try {
            conec = con.conecta();
            String sql = "select * from sucursal where id_sucursal=?";
            ps = conec.prepareStatement(sql);
            ps.setString(1, id_sucursal);
            res = ps.executeQuery();
            while (res.next()) {
                suc = new Sucursal(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7));
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar: " + ex);
        }
        return suc;
    }

    public boolean insertarSucursal(Sucursal suc) {
        boolean resultado = false;
        Gestion_Cliente cli = new Gestion_Cliente();

        try {
            if (this.buscarSucursal(suc.getId_sucursal()) != null) {
                if (cli.buscarcliente(suc.getId_cliente()) != null) {
                    conec = con.conecta();
                    String sql = "insert into sucursal values(?,?,?,?,?,?,?)";
                    ps = conec.prepareStatement(sql);
                    ps.setString(1, suc.getId_sucursal());
                    ps.setString(2, suc.getDepartamento());
                    ps.setString(3, suc.getMunicipio());
                    ps.setString(4, suc.getDireccion());
                    ps.setString(5, suc.getNom_sucursal());
                    ps.setString(6, suc.getEmail());
                    ps.setString(7, suc.getId_cliente());
                    resultado = ps.executeUpdate() > 0;
                } else {
                    System.out.println("El id del cliente para la sucursal no esta registrado");
                }
            } else {
                System.out.println("El id de la sucursal no esta registrado");
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar" + ex);
        }
        return resultado;
    }

    /*--------BORRAR SUCURSAL--------*/

    public boolean existeSucursal(String id_Sucursal) {
        boolean existe = false;
        try {
            String sql = "SELECT COUNT(*) FROM sucursal WHERE id_Sucursal = ?";
            conec = con.conecta();
            ps = conec.prepareStatement(sql);
            ps.setString(1, id_Sucursal);
            res = ps.executeQuery();
            if (res.next()) {
                int numFilas = res.getInt(1);
                if (numFilas > 0) {
                    existe = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar si esxiste la sucursal"+ex);
        }
        return existe;
    }

    public void borrarSucursal(String id_Sucursal) {
        try {
            String query = "DELETE FROM sucursal WHERE id_sucursal = ?";
            conec = con.conecta();
            PreparedStatement statement = conec.prepareStatement(query);
            statement.setString(1, id_Sucursal);
            statement.executeUpdate();
            System.out.println("Sucursal eliminada correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar la sucursal " + ex);
        }
    }
}
