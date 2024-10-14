package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Vista.empleadoForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CrtlEmpleado implements ActionListener {
    
    private final Empleado modelo;
    private final EmpleadoDAO dao;
    private final empleadoForm vista;
    
    public CrtlEmpleado(Empleado modelo, EmpleadoDAO dao, empleadoForm vista) {
        this.modelo = modelo;
        this.dao = dao;
        this.vista = vista;
        
        // Asignamos los listeners a los botones
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Gestión de Empleados");
        vista.setLocationRelativeTo(null);
        vista.txtId.setVisible(false);

        // Limpiar los ítems de los JComboBox antes de agregar nuevos
        vista.ComboTipoDocu.removeAllItems();
        vista.ComboSexo.removeAllItems();
        vista.ComboDepartamento.removeAllItems();

        // Agregar ítems a ComboTipoDocu
        vista.ComboTipoDocu.addItem("DNI");
        vista.ComboTipoDocu.addItem("Pasaporte");
        vista.ComboTipoDocu.addItem("Carnet de Extranjería");

        // Agregar ítems a ComboSexo
        vista.ComboSexo.addItem("Masculino");
        vista.ComboSexo.addItem("Femenino");

        // Agregar ítems a ComboDepartamento
        vista.ComboDepartamento.addItem("Ventas");
        vista.ComboDepartamento.addItem("Recursos Humanos");
        vista.ComboDepartamento.addItem("IT");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setApellido(vista.txtApellido.getText());
            modelo.setTipoDocumento(vista.ComboTipoDocu.getSelectedItem().toString());  // ComboBox
            modelo.setNumDocumento(vista.txtNumDocu.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setSexo(vista.ComboSexo.getSelectedItem().toString()); // ComboBox
            modelo.setCargo(vista.txtCargo.getText());
            modelo.setDepartamento(vista.ComboDepartamento.getSelectedItem().toString()); // ComboBox
            modelo.setSalario(vista.txtSalario.getText());
            
            // Obtener fechas de los JDateChooser
            java.util.Date fechaNacimiento = vista.dcFechaNacimiento.getDate();
            java.util.Date fechaIngreso = vista.dcFechaIngreso.getDate();

            // Verificar que las fechas no sean nulas
            if (fechaNacimiento != null) {
                modelo.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime())); // Establecer fecha de nacimiento
            } else {
                JOptionPane.showMessageDialog(null, "Por favor selecciona una fecha de nacimiento.");
                return; // Detener ejecución si no hay fecha
            }

            if (fechaIngreso != null) {
                modelo.setFechaIngreso(new java.sql.Date(fechaIngreso.getTime())); // Establecer fecha de ingreso
            } else {
                JOptionPane.showMessageDialog(null, "Por favor selecciona una fecha de ingreso.");
                return; // Detener ejecución si no hay fecha
            }
            
            // Estado Civil
            if (vista.rbCasado.isSelected()) {
                modelo.setEstadoCivil("Casado");
            } else if (vista.rbSoltero.isSelected()) {
                modelo.setEstadoCivil("Soltero");
            }

            // Estado (Activo o Inactivo)
            modelo.setEstado(vista.chkActivo.isSelected()); // Solo una línea

            if (dao.crearEmpleado(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
            }
        }

        if (e.getSource() == vista.btnModificar) {
            modelo.setId(vista.txtId.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setApellido(vista.txtApellido.getText());
            modelo.setTipoDocumento(vista.ComboTipoDocu.getSelectedItem().toString());  // ComboBox
            modelo.setNumDocumento(vista.txtNumDocu.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setSexo(vista.ComboSexo.getSelectedItem().toString()); // ComboBox
            modelo.setCargo(vista.txtCargo.getText());
            modelo.setDepartamento(vista.ComboDepartamento.getSelectedItem().toString()); // ComboBox
            modelo.setSalario(vista.txtSalario.getText());

            // Obtener fechas de los JDateChooser
            java.util.Date fechaNacimiento = vista.dcFechaNacimiento.getDate();
            java.util.Date fechaIngreso = vista.dcFechaIngreso.getDate();

            // Establecer las fechas en el modelo
            if (fechaNacimiento != null) {
                modelo.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime())); // Establecer fecha de nacimiento
            }
            if (fechaIngreso != null) {
                modelo.setFechaIngreso(new java.sql.Date(fechaIngreso.getTime())); // Establecer fecha de ingreso
            }

            // Estado Civil
            if (vista.rbCasado.isSelected()) {
                modelo.setEstadoCivil("Casado");
            } else if (vista.rbSoltero.isSelected()) {
                modelo.setEstadoCivil("Soltero");
            }

            // Estado (Activo o Inactivo)
            modelo.setEstado(vista.chkActivo.isSelected()); // Solo una línea

            if (dao.actualizarEmpleado(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            String numeroDocumento = vista.txtNumDocu.getText();  // Eliminar por numero_documento

            if (dao.eliminarEmpleado(numeroDocumento)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        }

        if (e.getSource() == vista.btnBuscar) {
            String numeroDocumento = vista.txtBuscar.getText().trim();  // Buscar por numero_documento
            if (numeroDocumento.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El documento no puede estar vacío.");
                return; // Detiene la ejecución
            }
            
            modelo.setNumDocumento(numeroDocumento);

            if (dao.buscar(modelo)) {
                vista.txtId.setText(String.valueOf(modelo.getId()));
                vista.txtNombre.setText(modelo.getNombre());
                vista.txtApellido.setText(modelo.getApellido());
                vista.ComboTipoDocu.setSelectedItem(modelo.getTipoDocumento());  // Seleccionamos el valor en ComboBox
                vista.txtNumDocu.setText(modelo.getNumDocumento());
                vista.txtDireccion.setText(modelo.getDireccion());
                vista.txtTelefono.setText(modelo.getTelefono());
                vista.txtCorreo.setText(modelo.getCorreo());
                vista.ComboSexo.setSelectedItem(modelo.getSexo());  // Seleccionamos el valor en ComboBox
                vista.txtCargo.setText(modelo.getCargo());
                vista.ComboDepartamento.setSelectedItem(modelo.getDepartamento());  // Seleccionamos el valor en ComboBox
                vista.txtSalario.setText(modelo.getSalario());
                
                // Establecer las fechas en los JDateChooser
                if (modelo.getFechaNacimiento() != null) {
                    vista.dcFechaNacimiento.setDate(modelo.getFechaNacimiento());
                }
                if (modelo.getFechaIngreso() != null) {
                    vista.dcFechaIngreso.setDate(modelo.getFechaIngreso());
                }

                // Estado Civil
                if ("Casado".equals(modelo.getEstadoCivil())) {
                    vista.rbCasado.setSelected(true);
                } else if ("Soltero".equals(modelo.getEstadoCivil())) {
                    vista.rbSoltero.setSelected(true);
                }

                // Estado (Activo o Inactivo)
                vista.chkActivo.setSelected(modelo.isEstado());
                vista.chkInactivo.setSelected(!modelo.isEstado());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró registro");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
    }

    // Método para limpiar los campos de la vista
    public void limpiar() {
        vista.txtNombre.setText(null);
        vista.txtApellido.setText(null);
        vista.ComboTipoDocu.setSelectedIndex(0);
        vista.txtNumDocu.setText(null);
        vista.txtDireccion.setText(null);
        vista.txtTelefono.setText(null);
        vista.txtCorreo.setText(null);
        vista.ComboSexo.setSelectedIndex(0);
        vista.txtCargo.setText(null);
        vista.ComboDepartamento.setSelectedIndex(0);
        vista.txtSalario.setText(null);
        vista.dcFechaNacimiento.setDate(null); // Limpiar JDateChooser
        vista.dcFechaIngreso.setDate(null); // Limpiar JDateChooser
        vista.rbCasado.setSelected(false);
        vista.rbSoltero.setSelected(false);
        vista.chkActivo.setSelected(false);
        vista.chkInactivo.setSelected(false);
        vista.txtId.setText(null);
        vista.txtBuscar.setText(null);
    }
}
