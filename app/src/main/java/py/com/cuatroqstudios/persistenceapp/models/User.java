package py.com.cuatroqstudios.persistenceapp.models;

/**
 * Created by manuel on 7/14/16.
 */
public class User {
    private String nombre;
    private String password;

    public User(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
