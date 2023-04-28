import java.sql.*;
import java.util.ArrayList;

public class Gestion_Cliente {
    conexion con = new conexion();
    private ArrayList<Cliente> datos = new ArrayList<>();

    Statement st = null; //Preparar la consulta sin parametros
    PreparedStatement ps = null; //Prepara la consulta (requiere parametros)
    ResultSet res = null; //Almacenar el resultado de una consulta
    Connection conec = null;

    public ArrayList<Cliente> consultarCliente() {

        try {
            String sql = "select * from cliente";
            conec = con.conecta(); //Abrir conexion
            st = conec.createStatement();
            res = st.executeQuery(sql);

            while (res.next()) {
                Cliente cli = new Cliente(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
                datos.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar: " + ex);
        }
        return datos;
    }

    public Cliente buscarcliente(String id_cliente) {
        Cliente cli = null;
        try {
            conec = con.conecta();
            String sql = "select * from cliente where id_cliente=?";
            ps = conec.prepareStatement(sql);
            ps.setString(1, id_cliente);
            res = ps.executeQuery();
            while (res.next()) {
                cli = new Cliente(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar: " + ex);
        }
        return cli;
    }

    public boolean insertaCliente(Cliente cli) {
        boolean resultado = false;
        try {
            if (this.buscarcliente(cli.getId_cliente()) == null) {
                conec = con.conecta();
                String sql = "insert into cliente" +
                        " values(?,?,?,?,?)";
                ps = conec.prepareStatement(sql);
                ps.setString(1, cli.getId_cliente());
                ps.setString(2, cli.getNombre());
                ps.setString(3, cli.getApellido());
                ps.setString(4, cli.getEmail());
                ps.setString(5, cli.getTelefono());
                /* para actualizar y agregar datos*/
                resultado = ps.executeUpdate() > 0;

            } else {
                System.out.println("El cliente ya esta registrado. ");
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar: " + ex);
        }
        return resultado;
    }

    /*-----------------------------------Actualizar-----------------------------------------*/
    public boolean existeCliente(String id_cliente) {
        boolean existe = false;
        try {
            String sql = "SELECT COUNT(*) FROM cliente WHERE id_cliente = ?";
            conec = con.conecta();
            ps = conec.prepareStatement(sql);
            ps.setString(1, id_cliente);
            res = ps.executeQuery();
            if (res.next()) {
                int numFilas = res.getInt(1);
                if (numFilas > 0) {
                    existe = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar si existe cliente: " + ex);;
        }
        return existe;
    }

    public void actualizarCliente(String id_cliente, String nombre, String apellido, String email, String telefono) {
        try {
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_cliente = ?";
            conec = con.conecta();
            ps = conec.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4, telefono);
            ps.setString(5, id_cliente);
            ps.executeUpdate();
            System.out.println("Cliente actualizado correctamente");

        } catch (SQLException ex) {
            System.out.println("Error al actualizar cliente: " + ex);
        }

    }
    /*-----------------------------------Borrar-----------------------------------------*/
    public void borrarCliente(String id_cliente) {
        try {
            String query = "DELETE FROM cliente WHERE id_cliente = ?";
            conec = con.conecta();
            PreparedStatement statement = conec.prepareStatement(query);
            statement.setString(1, id_cliente);
            statement.executeUpdate();
            System.out.println("Cliente eliminado correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar al cliente " + ex);
        }
    }
}

