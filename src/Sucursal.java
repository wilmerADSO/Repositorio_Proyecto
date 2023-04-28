public class Sucursal {
    private String id_sucursal;
    private String departamento;
    private String municipio;
    private String direccion;
    private String nom_sucursal;
    private String email;
    private String id_cliente;

    public Sucursal(String id_sucursal, String departamento, String municipio, String direccion, String nom_sucursal, String email, String id_cliente) {
        this.id_sucursal = id_sucursal;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.nom_sucursal = nom_sucursal;
        this.email = email;
        this.id_cliente = id_cliente;
    }

    public String getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(String id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNom_sucursal() {
        return nom_sucursal;
    }

    public void setNom_sucursal(String nom_sucursal) {
        this.nom_sucursal = nom_sucursal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "id_sucursal='" + id_sucursal + '\'' +
                ", departamento='" + departamento + '\'' +
                ", municipio='" + municipio + '\'' +
                ", direccion='" + direccion + '\'' +
                ", nom_sucursal='" + nom_sucursal + '\'' +
                ", email='" + email + '\'' +
                ", id_cliente='" + id_cliente + '\'' +
                '}';
    }
}

