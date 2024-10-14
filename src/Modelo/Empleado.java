package Modelo;

import java.util.Date;

public class Empleado {
    private String id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numDocumento;
    private String direccion;
    private String telefono;
    private String correo;
    private String sexo;
    private String cargo;
    private String departamento;
    private String salario;
    private String estadoCivil;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private boolean estado; // Activo/Inactivo

    // Constructor vacío
    public Empleado() {
    }

    // Constructor con todos los campos
    public Empleado(String id, String nombre, String apellido, String tipoDocumento,
                    String numDocumento, String direccion, String telefono,
                    String correo, String sexo, String cargo, String departamento,
                    String estadoCivil, String salario, Date fechaNacimiento, Date fechaIngreso, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.sexo = sexo;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salario = salario;
        this.estadoCivil = estadoCivil;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
    }

    // Getters y Setters para todos los atributos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

     public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public boolean isEstado() {
        return estado; // Devuelve si el empleado está activo o inactivo
    }
}
