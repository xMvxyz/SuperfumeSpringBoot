package PerfulandiaV3.PerfulandiaV3.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String correo;
    private String rol;
    private String contraseña;

    // Getters y setters explícitos (compatibilidad con IDEs sin procesador Lombok)
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return this.correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getRol() { return this.rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getContraseña() { return this.contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
}
