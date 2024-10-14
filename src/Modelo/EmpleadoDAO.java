package Modelo;

import Modelo.Empleado;
import Modelo.Conexion_1;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class EmpleadoDAO extends Conexion_1 {

    // Método para crear un empleado
    public boolean crearEmpleado(Empleado empleado) {
    String sql = "INSERT INTO empleados (nombre, apellido, tipo_documento, numero_documento, direccion, telefono, correo_electronico, sexo, cargo, departamento, salario, fecha_nacimiento, estado_civil, estado, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = Conexion_1.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, empleado.getNombre());
        ps.setString(2, empleado.getApellido());
        ps.setString(3, empleado.getTipoDocumento());
        ps.setString(4, empleado.getNumDocumento()); // Asegúrate de usar el número de documento
        ps.setString(5, empleado.getDireccion());
        ps.setString(6, empleado.getTelefono());
        ps.setString(7, empleado.getCorreo());
        ps.setString(8, empleado.getSexo());
        ps.setString(9, empleado.getCargo());
        ps.setString(10, empleado.getDepartamento()); // Asegúrate de que esto sea correcto
        ps.setBigDecimal(11, new BigDecimal(empleado.getSalario())); // Salario como BigDecimal
        ps.setDate(12, new java.sql.Date(empleado.getFechaNacimiento().getTime())); // fecha de nacimiento
        ps.setString(13, empleado.getEstadoCivil());
        ps.setBoolean(14, empleado.isEstado()); // Estado activo o inactivo
        ps.setDate(15, new java.sql.Date(empleado.getFechaIngreso().getTime())); // fecha de ingreso
        
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



    // Método para leer empleados
    public ArrayList<Empleado> listarEmpleados() {
    ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    String sql = "SELECT * FROM empleados";
    try (Connection conn = Conexion_1.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Empleado empleado = new Empleado(
                    rs.getString("id"), // id
                    rs.getString("nombre"), // nombre
                    rs.getString("apellido"), // apellido
                    rs.getString("tipo_documento"), // tipoDocumento
                    rs.getString("numero_documento"), // numDocumento
                    rs.getString("direccion"), // direccion
                    rs.getString("telefono"), // telefono
                    rs.getString("correo_electronico"), // correo
                    rs.getString("sexo"), // sexo
                    rs.getString("cargo"), // cargo
                    rs.getString("departamento"), // departamento
                    rs.getBigDecimal("salario").toString(), // Convertimos BigDecimal a String
                    rs.getString("EstadoCivil"),
                    rs.getDate("fecha_nacimiento"), // fechaNacimiento
                    rs.getDate("fecha_ingreso"), // fechaIngreso
                    rs.getBoolean("estado") // estado (activo/inactivo)
            );
            listaEmpleados.add(empleado);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listaEmpleados;
}



        public boolean actualizarEmpleado(Empleado empleado) {
    String sql = "UPDATE empleados SET nombre=?, apellido=?, tipo_documento=?, direccion=?, telefono=?, correo_electronico=?, sexo=?, cargo=?, departamento=?, salario=?, fecha_nacimiento=?, estado_civil=?, estado=?, fecha_ingreso=? WHERE numero_documento=?";

    try (Connection conn = Conexion_1.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
        // Imprimir valores para depuración
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Apellido: " + empleado.getApellido());
        System.out.println("Tipo de Documento: " + empleado.getTipoDocumento());
        System.out.println("Dirección: " + empleado.getDireccion());
        System.out.println("Teléfono: " + empleado.getTelefono());
        System.out.println("Correo: " + empleado.getCorreo());
        System.out.println("Sexo: " + empleado.getSexo());
        System.out.println("Cargo: " + empleado.getCargo());
        System.out.println("Departamento: " + empleado.getDepartamento());
        System.out.println("Salario: " + empleado.getSalario());
        System.out.println("Fecha de Nacimiento: " + empleado.getFechaNacimiento());
        System.out.println("Estado Civil: " + empleado.getEstadoCivil());
        System.out.println("Estado: " + empleado.isEstado());
        System.out.println("Fecha de Ingreso: " + empleado.getFechaIngreso());
        System.out.println("Número de Documento: " + empleado.getNumDocumento());

        ps.setString(1, empleado.getNombre());
        ps.setString(2, empleado.getApellido());
        ps.setString(3, empleado.getTipoDocumento());
        ps.setString(4, empleado.getDireccion());
        ps.setString(5, empleado.getTelefono());
        ps.setString(6, empleado.getCorreo());
        ps.setString(7, empleado.getSexo());
        ps.setString(8, empleado.getCargo());
        ps.setString(9, empleado.getDepartamento());
        ps.setBigDecimal(10, new BigDecimal(empleado.getSalario()));

        // Asignar fechas
        if (empleado.getFechaNacimiento() != null) {
            ps.setDate(11, new java.sql.Date(empleado.getFechaNacimiento().getTime()));
        } else {
            ps.setNull(11, java.sql.Types.DATE);
        }

        ps.setString(12, empleado.getEstadoCivil());
        ps.setBoolean(13, empleado.isEstado());

        if (empleado.getFechaIngreso() != null) {
            ps.setDate(14, new java.sql.Date(empleado.getFechaIngreso().getTime()));
        } else {
            ps.setNull(14, java.sql.Types.DATE);
        }

        ps.setString(15, empleado.getNumDocumento());  // Usamos numero_documento como clave

        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // Método para eliminar empleado
    public boolean eliminarEmpleado(String numeroDocumento) {
    String sql = "DELETE FROM empleados WHERE numero_documento=?";
    try (Connection conn = Conexion_1.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, numeroDocumento); // Cambié a numero_documento en lugar de id
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
    public boolean buscar(Empleado empleado) {
    String sql = "SELECT * FROM empleados WHERE numero_documento=?";
    try (Connection con = Conexion_1.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, empleado.getNumDocumento());  // Buscamos por numero_documento
        System.out.println("Buscando documento: "+ empleado.getNumDocumento());
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            empleado.setId(rs.getString("id"));
            empleado.setNombre(rs.getString("nombre"));
            empleado.setApellido(rs.getString("apellido")); // Corregí apellido a String
            empleado.setTipoDocumento(rs.getString("tipo_documento"));
            empleado.setNumDocumento(rs.getString("numero_documento"));
            empleado.setDireccion(rs.getString("direccion"));
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setCorreo(rs.getString("correo_electronico"));
            empleado.setSexo(rs.getString("sexo"));
            empleado.setCargo(rs.getString("cargo"));
            empleado.setDepartamento(rs.getString("departamento"));
            empleado.setSalario(rs.getBigDecimal("salario").toString()); // Salario como String
            empleado.setEstado(rs.getBoolean("estado"));
            empleado.setEstadoCivil(rs.getString("estado_civil"));

            // Establecer las fechas
            empleado.setFechaNacimiento(rs.getDate("fecha_nacimiento")); // fecha de nacimiento
            empleado.setFechaIngreso(rs.getDate("fecha_ingreso")); // fecha de ingreso
            
            return true;
        }else{
            System.out.println("No se encontro registro para : "+ empleado.getNumDocumento());
        }
        return false;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}
