package py.com.cuatroqstudios.persistenceapp.models;

import java.io.Serializable;

import py.com.cuatroqstudios.persistenceapp.conn.JsonResponse;

/**
 * Created by manuel on 7/8/16.
 */
public class Provider extends JsonResponse implements Serializable {
    private Integer idProvider;
    private String description;
    private String proveedor;
    private String ruc;

    public Provider() {
    }

    public Provider(Integer idProvider, String proveedor, String description, String ruc) {
        this.ruc = ruc;
        this.description = description;
        this.proveedor = proveedor;
        this.idProvider = idProvider;
    }

    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                "idProvider='" + idProvider + '\'' +
                ", description='" + description + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", ruc='" + ruc + '\'' +
                '}';
    }
}
