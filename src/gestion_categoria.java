import java.sql.*;
import java.util.ArrayList;

public class gestion_categoria {
    conexion con = new conexion();
    private ArrayList<categoria> datos = new ArrayList<>();

    Statement st = null; // Preparar una consulta sin parametros;

    PreparedStatement ps = null;// Prepara la consulta con parametros;

    ResultSet res = null; // Almacenar el resultado de una consulta;

    Connection conec = null;

    public ArrayList<categoria> consultarCateg() {

        try {
            String sql = "select * from categoria";
            conec = con.conecta(); // abrir conexcion
            st = conec.createStatement();// Preparar consulta
            res = st.executeQuery(sql);// Ejecutar consulta
            while (res.next()) {
                categoria categ = new categoria(res.getInt(1),
                        res.getString(2), res.getString(3));
                datos.add(categ);
            }


        } catch (SQLException ex) {

            System.out.println("Error al Consultar: " + ex);
        }


        return datos;
    }

    public categoria buscarCategoria(int id_categoria) {
        categoria categ = null;
        try {
            conec = con.conecta();
            String sql = "select * from categoria where id_categoria=?";
            ps = conec.prepareStatement(sql);
            ps.setInt(1, id_categoria);
            res = ps.executeQuery();
            while (res.next()) {
                categ = new categoria(res.getInt(1),
                        res.getString(2), res.getString(3));

            }

        } catch (SQLException ex) {

            System.out.println("Error al Consultar: " + ex);
        }

        return categ;

    }

    public boolean insertarCategoria(categoria categ) {
        boolean resultado = false;
        try {
            if(this.buscarCategoria(categ.getIdCategoria()) ==null) {
                    conec = con.conecta();
                    String sql = "insert into categoria values(?,?,?)";
                    ps = conec.prepareStatement(sql);
                    ps.setString(1, categ.getIdCategoria());
                    ps.setString(2, categ.getNombre());
                    ps.setString(3, categ.getDescripcion());
                    resultado = ps.executeUpdate() > 0;
                } else {
                    System.out.println("La categoria ya esta registrada");
                }


        } catch (SQLException ex) {
            System.out.println("Error al insertar"+ex);


        }
        return resultado;
    }

    public void actualizarCategoria(String id_categoria, String nombre, String descripcion) {
        try {
            conec = con.conecta();
            String sql = "update categoria set nombre = ?, descripcion = ? where id_categoria = ? ";
            ps = conec.prepareStatement(sql);
            ps.setString(1,nombre);
            ps.setString(2, descripcion);
            ps.setString(3,id_categoria);
            ps.executeUpdate();
            System.out.println("La categoria fue actualizada correctamente");


        } catch (SQLException ex) {
            System.out.println("Error al actualizar datos" + ex);
        }


    }

    public void borrarCategoria(String id_categoria){
            try {
                String sql = "delete from categoria where id_categoria= ?";
                conec = con.conecta();
                PreparedStatement statement = conec.prepareStatement(sql);
                statement.setString(1, id_categoria);
                statement.executeUpdate();
                System.out.println("La categoria fue eliminada correctamente");
            } catch (SQLException ex) {
                System.out.println("Error al eliminar registro" + ex);
            }
        }
    }


