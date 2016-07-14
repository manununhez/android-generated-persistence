package py.com.cuatroqstudios.persistenceapp.models;

/**
 * Created by manuel on 7/8/16.
 */
public class Provider {
    private Integer idProveedor;
    private String descripcion_local;
    private String proveedor;
    private String ruc;

    public Provider() {
    }

    public Provider(Integer idProveedor, String proveedor, String descripcion_local, String ruc ) {
        this.ruc = ruc;
        this.descripcion_local = descripcion_local;
        this.proveedor = proveedor;
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getDescripcion_local() {
        return descripcion_local;
    }

    public void setDescripcion_local(String descripcion_local) {
        this.descripcion_local = descripcion_local;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor='" + idProveedor + '\'' +
                ", descripcion_local='" + descripcion_local + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", ruc='" + ruc + '\'' +
                '}';
    }
}
