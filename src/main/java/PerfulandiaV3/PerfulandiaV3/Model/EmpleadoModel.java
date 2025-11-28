package PerfulandiaV3.PerfulandiaV3.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String correo;
    private String contraseña;
    private int idEmpleado;
    private String sucursal;
    private boolean activo = true;

    // Getters y setters explícitos (compatibilidad con IDEs sin procesador Lombok)
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return this.correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContraseña() { return this.contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public int getIdEmpleado() { return this.idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getSucursal() { return this.sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }

    public boolean isActivo() { return this.activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    
}
