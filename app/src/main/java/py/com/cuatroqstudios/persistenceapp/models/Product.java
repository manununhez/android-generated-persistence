package py.com.cuatroqstudios.persistenceapp.models;

/**
 * Created by manuel on 7/8/16.
 */
public class Product {
    private String idProducto;
    private String nombre;
    private String precio;
    private String codigo;
    private String idProveedor;

    public Product(String idProducto, String nombre,
                   String precio, String codigo, String idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = codigo;
        this.idProveedor = idProveedor;
    }


    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
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

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
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
