package py.com.cuatroqstudios.persistenceapp.models;

import java.io.Serializable;

/**
 * Created by manuel on 7/8/16.
 */
public class Product implements Serializable{
    private Integer idProducto;
    private String nombre;
    private String precio;
    private String codigo;
    private Integer idProveedor;

    public Product() {
    }

    public Product(Integer idProducto, String nombre,
                   String precio, String codigo, Integer idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = codigo;
        this.idProveedor = idProveedor;
    }


    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                ", idProducto='" + idProducto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                ", codigo='" + codigo + '\'' +
                ", idProveedor='" + idProveedor + '\'' +
                '}';
    }
}
