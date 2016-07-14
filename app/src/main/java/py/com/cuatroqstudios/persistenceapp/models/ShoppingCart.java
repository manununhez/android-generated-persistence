package py.com.cuatroqstudios.persistenceapp.models;

/**
 * Created by manuel on 7/8/16.
 */
public class ShoppingCart {
    private String idProducto;
    private String descripcion;
    private String idProveedor;
    private String marca;
    private String porcentaje_impuesto;
    private String precio;
    private String unidad_minima_venta;
    private String cantidad_producto;
    private String sync_time;

    public ShoppingCart(String idProducto, String descripcion,
                        String idProveedor, String marca, String porcentaje_impuesto,
                        String precio, String unidad_minima_venta, String cantidad_producto, String sync_time) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.idProveedor = idProveedor;
        this.marca = marca;
        this.porcentaje_impuesto = porcentaje_impuesto;
        this.precio = precio;
        this.unidad_minima_venta = unidad_minima_venta;
        this.cantidad_producto = cantidad_producto;
        this.sync_time = sync_time;
    }


    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPorcentaje_impuesto() {
        return porcentaje_impuesto;
    }

    public void setPorcentaje_impuesto(String porcentaje_impuesto) {
        this.porcentaje_impuesto = porcentaje_impuesto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUnidad_minima_venta() {
        return unidad_minima_venta;
    }

    public void setUnidad_minima_venta(String unidad_minima_venta) {
        this.unidad_minima_venta = unidad_minima_venta;
    }

    public String getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(String cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public String getSync_time() {
        return sync_time;
    }

    public void setSync_time(String sync_time) {
        this.sync_time = sync_time;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                ", idProducto='" + idProducto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idProveedor='" + idProveedor + '\'' +
                ", marca='" + marca + '\'' +
                ", porcentaje_impuesto='" + porcentaje_impuesto + '\'' +
                ", precio='" + precio + '\'' +
                ", unidad_minima_venta='" + unidad_minima_venta + '\'' +
                ", cantidad_producto='" + cantidad_producto + '\'' +
                ", sync_time='" + sync_time + '\'' +
                '}';
    }


}
