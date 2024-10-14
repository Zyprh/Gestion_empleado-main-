package CRUD;

import Vista.empleadoForm;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Controlador.CrtlEmpleado;
import javax.swing.SwingUtilities;

public class Gestion_Empleados {

    public static void main(String[] args) {
        // Ejecutar en el hilo del Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Crear modelo, DAO, y vista
            Empleado modelo = new Empleado();
            EmpleadoDAO dao = new EmpleadoDAO();
            empleadoForm vista = new empleadoForm();
            
            // Crear el controlador y pasarle el modelo, DAO, y vista
            CrtlEmpleado controlador = new CrtlEmpleado(modelo, dao, vista);

            // Iniciar el controlador (agrega ítems a los ComboBox)
            controlador.iniciar();

            // Mostrar la vista
            vista.setVisible(true);
        });
    }
}
