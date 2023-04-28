import java.sql.*;
import java.util.ArrayList;

public class gestion_productos {
          conexion con = new conexion();
        private ArrayList<productos> datos = new ArrayList<>();

        Statement st = null; // Preparar una consulta sin parametros;

        PreparedStatement ps = null;// Prepara la consulta con parametros;

        ResultSet res = null; // Almacenar el resultado de una consulta;

        Connection conec = null;

        public ArrayList<productos> consultarprod() {

            try {
                String sql = "select * from producto";
                conec = con.conecta(); // abrir conexcion
                st = conec.createStatement();// Preparar consulta
                res = st.executeQuery(sql);// Ejecutar consulta
                while (res.next()) {
                    productos prod = new productos(res.getString(1),
                            res.getString(2), res.getString(3), res.getInt(4));
                    datos.add(prod);
                }


            } catch (SQLException ex) {

                System.out.println("Error al Consultar: " + ex);
            }


            return datos;
        }

        public productos buscarProducto(String id_producto) {
            productos prod = null;
            try {
                conec = con.conecta();
                String sql = "select * from producto where id_producto=?";
                ps = conec.prepareStatement(sql);
                ps.setString(1, id_producto);
                res = ps.executeQuery();
                while (res.next()) {
                    prod = new productos(res.getString(1),
                            res.getString(2), res.getString(3), res.getInt(4));
                }

            } catch (SQLException ex) {

                System.out.println("Error al Consultar: " + ex);
            }

            return prod;

        }

        public boolean insertarproduto(productos prod) {
            boolean resultado = false;
            gestion_categoria gcateg = new gestion_categoria();

            try{
            if (this.buscarProducto(prod.getIdProducto()) == null) {
                if (gcateg.buscarCategoria(prod.getIdCategoria()) != null) {
                    conec = con.conecta();
                    String sql = "insert into producto values(?,?,?,?)";
                    ps = conec.prepareStatement(sql);
                    ps.setString(1, prod.getIdProducto());
                    ps.setString(2, prod.getNombre());
                    ps.setString(3, prod.getDescripcion());
                    ps.setInt(4, prod.getIdCategoria());
                    resultado = ps.executeUpdate() > 0;
                } else {
                    System.out.println("La categoria no existe");
                }
                } else {
                System.out.println("El producto ya se encuentra registrado");
                }
            } catch (SQLException ex) {
                System.out.println("Error al insertar"+ex);


            }
            return resultado;
        }

        public void actualizarProductos(String id_producto, String nombre, String descripcion, String id_categoria) {
            try {
                conec = con.conecta();
                String sql = "update producto set nombre = ?, descripcion = ? id_categoria =? where id_producto = ? ";
                ps = conec.prepareStatement(sql);
                ps.setString(1,nombre);
                ps.setString(2, descripcion);
                ps.setString(3,id_categoria);
                ps.setString(4,id_producto);
                ps.executeUpdate();
                System.out.println("El producto fue actualizado correctamente");


            } catch (SQLException ex) {
                System.out.println("Error al actualizar datos" + ex);
            }


        }

        public void borrarProductos(String id_productos){
            try {
                String sql = "delete from producto where id_producto= ?";
                conec = con.conecta();
                PreparedStatement statement = conec.prepareStatement(sql);
                statement.setString(1, id_productos);
                statement.executeUpdate();
                System.out.println("El producto fue eliminada correctamente");
            } catch (SQLException ex) {
                System.out.println("Error al eliminar registro" + ex);
            }
        }
    }

