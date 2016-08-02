package py.com.cuatroqstudios.persistenceapp.models;

/**
 * Created by manuel on 7/8/16.
 */
public class ProductImage {
    private String idProductoImagen;
    private String imagen;
    private String idProducto;
    private String img_src;

    public ProductImage(String idProductoImagen, String imagen, String idProducto, String img_src) {
        this.idProductoImagen = idProductoImagen;
        this.imagen = imagen;
        this.idProducto = idProducto;
        this.img_src = img_src;
    }

    public String getIdProductoImagen() {
        return idProductoImagen;
    }

    public void setIdProductoImagen(String idProductoImagen) {
        this.idProductoImagen = idProductoImagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    @Override
    public String toString() {
        return "ImagenProducto{" +
                "idProductoImagen='" + idProductoImagen + '\'' +
                ", imagen='" + imagen + '\'' +
                ", idProducto='" + idProducto + '\'' +
                ", img_src='" + img_src + '\'' +
                '}';
    }
}
