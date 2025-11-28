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
@Table(name = "carrito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idUsuario;
    private int idPerfume;
    private int cantidad;
    private int precio;
    private int idEmpleado;

    // Getters y setters explícitos (compatibilidad con IDEs sin procesador Lombok)
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return this.idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdPerfume() { return this.idPerfume; }
    public void setIdPerfume(int idPerfume) { this.idPerfume = idPerfume; }

    public int getCantidad() { return this.cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getPrecio() { return this.precio; }
    public void setPrecio(int precio) { this.precio = precio; }

    public int getIdEmpleado() { return this.idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }
}
