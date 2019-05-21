package py.com.cuatroqstudios.persistenceapp.models;

import java.io.Serializable;

import py.com.cuatroqstudios.persistenceapp.conn.JsonResponse;

/**
 * Created by manuel on 7/8/16.
 */
public class Product  implements Serializable{
    private Integer idProduct;
    private String name;
    private String price;
    private String code;
    private Integer idProvider;

    public Product() {
    }

    public Product(Integer idProduct, String name,
                   String price, String code, Integer idProvider) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.code = code;
        this.idProvider = idProvider;
    }


    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Integer idProvider) {
        this.idProvider = idProvider;
    }

    @Override
    public String toString() {
        return "Producto{" +
                ", idProduct='" + idProduct + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", code='" + code + '\'' +
                ", idProvider='" + idProvider + '\'' +
                '}';
    }
}
