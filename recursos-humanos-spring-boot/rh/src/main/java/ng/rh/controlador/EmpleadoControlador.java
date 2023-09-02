package ng.rh.controlador;


import ng.rh.excepcion.RecursoNoEncontradoExcepcion;
import ng.rh.modelo.Empleado;
import ng.rh.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000/")
public class EmpleadoControlador {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        var empleados = empleadoServicio.listarEmpleados();
        empleados.forEach(empleado -> LOGGER.info(empleado.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        LOGGER.info("Empleado a agreagr: " + empleado);
        return empleadoServicio.guardarEmpleado(empleado);

    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null){
            throw new RecursoNoEncontradoExcepcion("no se encontro el id: " + id);
        }
        return ResponseEntity.ok(empleado);
    }



}
