import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Gestion_Cliente cliente= new Gestion_Cliente();
        ArrayList<Cliente> resultado= new ArrayList<>();
        resultado=cliente.consultarCliente();

        Gestion_Sucursal sucursal=new Gestion_Sucursal();
        ArrayList<Sucursal> resultado1= new ArrayList<>();
        resultado1=sucursal.consultarSucursal();

        gestion_categoria gcategoria = new gestion_categoria();

        Scanner entrada=new Scanner(System.in);
        boolean salir=false;
        while (!salir) {
            System.out.println("Ingrese una opcion:  ");
            System.out.println("1. Registrar un Cliente");
            System.out.println("2. Actualizar un Cliente");
            System.out.println("3. Eliminar un Cliente");
            System.out.println("4. Consultar Clientes");
            System.out.println("5. Registrar una Sucursal");
            System.out.println("6. Eliminar una Sucursal");
            System.out.println("7. Consultar Sucursales");
            System.out.println("8. Ingresar Categoria");
            System.out.println("8. Salir");
            int opcion;
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    String id_cliente,nombre,apellido,email,telefono;

                    System.out.println("Ingrese el id del cliente");
                    id_cliente=entrada.next();
                    System.out.println("Ingrese el nombre del cliente");
                    nombre= entrada.next();
                    System.out.println("Ingrese el apellido del cliente");
                    apellido= entrada.next();
                    System.out.println("Ingrese el email del cliente");
                    email= entrada.next();
                    System.out.println("Ingrese el telofono del cliente");
                    telefono= entrada.next();
                    Cliente c= new Cliente(id_cliente,nombre,apellido,email,telefono);
                    if(cliente.insertaCliente(c)){
                        System.out.println("El cliente se registro exitosamente. ");
                    }else System.out.println("El cliente no se registro. ");
                    break;
                case 2:
                    System.out.println("Ingrese el ID del cliente que desea actualizar:");
                    id_cliente = entrada.next();
                    if (cliente.existeCliente(id_cliente)) {
                        System.out.println("Ingrese los nuevos datos del cliente: ");
                        System.out.println("Ingrese Nombre");
                        nombre= entrada.next();
                        System.out.println("Ingrese Apellido");
                        apellido= entrada.next();
                        System.out.println("Ingrese Email");
                        email= entrada.next();
                        System.out.println("Ingrese Telefono");
                        telefono= entrada.next();
                        cliente.actualizarCliente(id_cliente, nombre, apellido, email, telefono);
                    } else {
                        System.out.println("El id del cliente ingresado no se encuentra en la base de datos");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el id del cliente que desea borrar: ");
                    id_cliente= entrada.next();
                    if (cliente.existeCliente(id_cliente)) {
                        cliente.borrarCliente(id_cliente);
                        System.out.println("cliente con ID " + id_cliente + " eliminado exitosamente.");
                    } else {
                        System.out.println("El ID del cliente ingresado no se encuentra en la base de datos");
                    }
                    break;
                case 4:
                    System.out.println(resultado);
                    break;
                case 5:
                    String id_sucursal,departamento,municipio,direccion,nom_sucursal,email1,id_cliente1;
                    System.out.println("Ingrese el numero de la habitacion");
                    id_sucursal=entrada.next();
                    System.out.println("Ingrese el codigo de la reserva");
                    departamento= entrada.next();
                    System.out.println("Ingrese el id del servicio");
                    municipio= entrada.next();
                    System.out.println("Ingrese el tipo de pago");
                    direccion= entrada.next();
                    System.out.println("Ingrese el monto");
                    nom_sucursal= entrada.next();
                    System.out.println("Ingrese la fecha y hora");
                    email1= entrada.next();
                    System.out.println("Ingrese el numero de factura");
                    id_cliente1= entrada.next();
                    Sucursal s= new Sucursal(id_sucursal,departamento,municipio,direccion,nom_sucursal,email1,id_cliente1);
                    if(sucursal.insertarSucursal(s)){
                        System.out.println("La sucursal se registro exitosamente. ");
                    }else System.out.println("La sucursal no se registro. ");
                case 6:
                    System.out.println("Ingrese el ID de la sucursal que desea borrar: ");
                    id_sucursal= entrada.next();
                    if (sucursal.existeSucursal(id_sucursal)) {
                        sucursal.borrarSucursal(id_sucursal);
                        System.out.println("Sucursal con ID " + id_sucursal + " eliminada exitosamente.");
                    } else {
                        System.out.println("La sucursal ingresada no se encuentra en la base de datos");
                    }
                    break;
                case 7:
                    System.out.println(resultado1);
                    break;
                case 8:
                    String nombre1,descripcioon;
                    System.out.println("Ingrese el nombre de la categoria");
                    nombre1= entrada.nextLine();
                    System.out.println("Ingrese la descripcion");
                    descripcioon= entrada.next();
                    categoria categ = new categoria(0,nombre1,descripcioon);
                    if(gcategoria.insertarCategoria(categ)){
                        System.out.println("La categoria se registro exitosamente. ");
                    }else System.out.println("La categoria no se registro. ");
                    break;
                case 9:
                    salir=true;
                    System.out.println("Ha finalizado con exito");
                    break;
                default:
                    System.out.println("INGRESE UNA OPCION VALIDA!!!");
            }
        }
    }
}
