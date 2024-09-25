package com.edu.co.uniquindio.Modelo;


import com.edu.co.uniquindio.Modelo.Enumeraciones.Dificultades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Especialidades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Estado;
import com.edu.co.uniquindio.Modelo.Enumeraciones.TiposDeMiembros;
import com.edu.co.uniquindio.Servicios.GestionSesiones;
import com.edu.co.uniquindio.Servicios.ServiciosSistema;
import com.edu.co.uniquindio.Utilidades.LoggerSistem;
import com.edu.co.uniquindio.Utilidades.Persistencia;
import lombok.Getter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

@Getter
public  class SistemaDeportes implements Serializable, GestionSesiones, ServiciosSistema {

    private ArrayList<SesionesEntrenamiento> listaSecionesEnElsistema;
    private ArrayList<Administrador> listaAdministradores;
    private ArrayList<Miembro> listaMiembros;
    private ArrayList<Entrenador>  listaEntrenadores;
    private ArrayList<Solicitud> listaSolicitudesMiembros;




    /**
     * Constructor para inicializar el sistema de deportes.
     * Inicializa las listas de sesiones, administradores, miembros y entrenadores.
     * Si no hay administradores cargados, crea un administrador por defecto.
     */

    public SistemaDeportes() {
        listaSecionesEnElsistema = new ArrayList<>();
        listaAdministradores = new ArrayList<>();
        listaMiembros = new ArrayList<>();
        listaEntrenadores = new ArrayList<>();
        listaSolicitudesMiembros = new ArrayList<>();


        try{
            cargarDatos();
            if(listaAdministradores.isEmpty()){
                Administrador administrador = new Administrador();
                administrador.setAccessCode("123");
                administrador.setNombre("Admi");
                administrador.setNumeroDeIdentificaciones("1");
                listaAdministradores.add(administrador);
                guardarDatos();
            }
            if(listaEntrenadores.isEmpty()){
                Entrenador entrenador= new Entrenador();
                entrenador.setNombre("1234");
                entrenador.setNumeroDeIdentificaciones("1234");
                entrenador.setEspecialidad_Entrenador(Especialidades.Badminton);
                entrenador.setSesiones(new ArrayList<>());
                listaEntrenadores.add(entrenador);
                guardarDatos();
            }
            if(listaMiembros.isEmpty()){
                Miembro miembro= new Miembro();
                miembro.setNombre("12345");
                miembro.setNumeroDeIdentificaciones("12345");
                miembro.setEdad(18);
                miembro.setTiposDeMiembros(TiposDeMiembros.Adultos);
                miembro.setCorreo("12345");
                miembro.setSesionesEntrenamientoEnUso(new ArrayList<>());
                listaMiembros.add(miembro);
            }
            if(listaSolicitudesMiembros.isEmpty()){
                Solicitud solicitud= new Solicitud();
                solicitud.setDescripcion("jajajja");
                solicitud.setMiembro(listaMiembros.get(0));
                solicitud.setEspecialidades(listaEntrenadores.get(0).getEspecialidad_Entrenador());
                listaSolicitudesMiembros.add(solicitud);
                System.out.println(listaSolicitudesMiembros.size());
            }
            if(listaSecionesEnElsistema.isEmpty()){
                SesionesEntrenamiento sesionesEntrenamiento= new SesionesEntrenamiento();
                sesionesEntrenamiento.setDeporte(new Deporte(listaEntrenadores.get(0).getEspecialidad_Entrenador(),"descripcion",Dificultades.ALTO));
                sesionesEntrenamiento.setDescripcion("jajja");
                sesionesEntrenamiento.setID("12345678");
                sesionesEntrenamiento.setEntrenadorSesion(listaEntrenadores.get(0));
                sesionesEntrenamiento.setEstado(Estado.Programada);
                sesionesEntrenamiento.setDuracion("12");
                sesionesEntrenamiento.setFecha(LocalDate.now());
                listaSecionesEnElsistema.add(sesionesEntrenamiento);
                listaMiembros.get(0).getSesionesEntrenamientoEnUso().add(sesionesEntrenamiento);
            }
        }catch(Exception E){
            E.printStackTrace();
        }
    }


    /**
     * @param msg
     * @param nivel
     */
    @Override
    public void guardarLog(String msg, Level nivel)  {
        LoggerSistem.log(msg, nivel,"accion");
    }

    /**
     * Carga los datos persistidos del sistema, incluyendo sesiones de entrenamiento, administradores,
     * miembros y entrenadores.
     *
     * @throws Exception Si ocurre un error al cargar los datos.
     */
    @Override
    public void cargarDatos() throws Exception {
        try {

            ArrayList<SesionesEntrenamiento> secionesEnElsistema = Persistencia.cargarSesiones();
            ArrayList<Administrador> administradores = Persistencia.cargarAdministradoores();
            ArrayList<Miembro> miembros = Persistencia.cargarMiembros();
            ArrayList<Entrenador> entrenadores = Persistencia.cargarEntrenadores();
            ArrayList<Solicitud> solicitudes = Persistencia.cargarSolicitudesMiembros();


            if (secionesEnElsistema != null) {
                listaSecionesEnElsistema.addAll(secionesEnElsistema);
            }
            if (administradores != null) {
                listaAdministradores.addAll(administradores);
            }
            if (miembros != null) {
                listaMiembros.addAll(miembros);
            }
            if (entrenadores != null) {
                listaEntrenadores.addAll(entrenadores);
            }
            if (solicitudes != null) {
                listaSolicitudesMiembros.addAll(solicitudes);
            }


        } catch (Exception e) {
            throw new Exception(e);
        }

    }
    /**
     * Guarda los datos actuales del sistema, incluyendo administradores, entrenadores, miembros,
     * y sesiones de entrenamiento.
     *
     * @throws Exception Si ocurre un error al guardar los datos.
     */
    @Override
    public void guardarDatos() throws Exception {
        try {
            Persistencia.guardarAdministradores(listaAdministradores);
            Persistencia.guardarEntrenador(listaEntrenadores);
            Persistencia.guardarMiembros(listaMiembros);
            Persistencia.guardarSesiones(listaSecionesEnElsistema);
            Persistencia.guardarSolicitudesMiembros(listaSolicitudesMiembros);


        } catch (IOException e) {
            throw new Exception(e);
        }

    }
    /**
     * Valida las credenciales de usuario proporcionadas.
     *
     * @param identificador El identificador del usuario.
     * @param nombre        El nombre del usuario.
     * @return La persona correspondiente al identificador proporcionado si es válida.
     * @throws Exception Si los datos de acceso son incorrectos.
     */
    @Override
    public Persona validarUsuario(String identificador, String nombre)throws Exception{
        Persona usuario = obtenerPersona(identificador,nombre);
        if(usuario != null){
            return usuario;
        }else{
            throw new Exception("Los datos de acceso son incorrectos");
        }
    }

    /**
     * Busca y obtiene una persona de las listas de miembros, administradores o entrenadores
     * basada en el identificador proporcionado.
     *
     * @param identificador El número de identificación de la persona a buscar.
     * @param nombre        El nombre de la persona a buscar (actualmente no utilizado en el método).
     * @return La persona correspondiente al identificador proporcionado si se encuentra en alguna
     *         de las listas; de lo contrario, retorna {@code null}.
     */
    @Override
    public Persona obtenerPersona(String identificador, String nombre){

        for(Miembro cliente : listaMiembros){
            if(cliente.getNumeroDeIdentificaciones().equals(identificador)){
                return cliente;
            }
        }
        for(Administrador admin : listaAdministradores){
            if(admin.getNumeroDeIdentificaciones().equals(identificador) ){
                return admin;
            }
        }
        for(Entrenador entrenador : listaEntrenadores){
            if(entrenador.getNumeroDeIdentificaciones().equals(identificador)){
                return entrenador;
            }
        }
        return null;
    }


    /**
     * Registra un nuevo entrenador con la información proporcionada y lo agrega a la lista de entrenadores.
     *
     * @param nombre                 El nombre del entrenador.
     * @param numeroDeIdentificacion El número de identificación del entrenador.
     * @param especialidades         La especialidad del entrenador.
     * @param sesiones               Una lista de sesiones de entrenamiento asignadas al entrenador.
     */
    @Override

    public Entrenador crearEntrenador(String nombre,
                                    String numeroDeIdentificacion,
                                    Especialidades especialidades,
                                    ArrayList<SesionesEntrenamiento> sesiones) throws Exception {

        if(!(nombre.isEmpty()||numeroDeIdentificacion.isEmpty()||especialidades==null||sesiones==null)){

            Entrenador entrenador = Entrenador.builder()
                    .especialidad_Entrenador(especialidades)
                    .sesiones(sesiones)
                    .build();

            entrenador.setNombre(nombre);
            entrenador.setNumeroDeIdentificaciones(numeroDeIdentificacion);

            listaEntrenadores.add(entrenador);
            guardarDatos();
            return entrenador;


        }else{
            throw new Exception("NO has ingresado todos los datos bien");
        }




    }


    /**
     * Crea un nuevo miembro en el sistema.
     *
     * @param identificador El identificador del miembro.
     * @param nombre        El nombre del miembro.
     * @param correo        El correo del miembro.
     * @param edad          La edad del miembro.
     * @throws Exception Si los datos ingresados no son correctos.
     */
    @Override

    public Miembro crearMiembro(String identificador, String nombre, String correo, Integer edad) throws Exception {


        if(obtenerPersona(identificador,nombre)==null){

            if (!(identificador.isEmpty() || nombre.isEmpty() || correo.isEmpty() || edad <= 0)) {
                Miembro miembro;
                if (edad >= 18) {
                    miembro = Miembro.builder()
                            .correo(correo)
                            .edad(edad)
                            .tiposDeMiembros(TiposDeMiembros.Adultos)
                            .sesionesEntrenamientoEnUso(new ArrayList<>())
                            .build();
                    for(SesionesEntrenamiento sesiones:listaSecionesEnElsistema ){
                        if(sesiones.getDeporte().getDificultad() == Dificultades.ALTO || sesiones.getDeporte().getDificultad() == Dificultades.MEDIO ){

                            miembro.getSesionesEntrenamientoEnUso().add(sesiones);

                        }
                    }

                    miembro.setNombre(nombre);
                    miembro.setNumeroDeIdentificaciones(identificador);
                    listaMiembros.add(miembro);
                    guardarDatos();
                    System.out.println("nivel 3");
                } else {
                    miembro = Miembro.builder()
                            .correo(correo)
                            .edad(edad)
                            .sesionesEntrenamientoEnUso(new ArrayList<>())
                            .tiposDeMiembros(TiposDeMiembros.Juveniles)
                            .build();

                    miembro.setNombre(nombre);
                    miembro.setNumeroDeIdentificaciones(identificador);
                    listaMiembros.add(miembro);
                    guardarDatos();


                    for (SesionesEntrenamiento sesiones : listaSecionesEnElsistema) {
                        if (sesiones.getDeporte().getDificultad() == Dificultades.BAJO) {

                            miembro.getSesionesEntrenamientoEnUso().add(sesiones);

                        }
                    }
                }
                return miembro;
            } else {
                throw new Exception("Asegúrate de haber ingresado correctamente todos los valores");
            }
        }else{
            throw new Exception("Este usuario ya esta en el sistema");
        }
    }




    /**
     * Crea una nueva sesión de entrenamiento con los detalles especificados.
     *
     * @param fecha            La fecha de la sesión de entrenamiento.
     * @param duracion         La duración de la sesión.
     * @param estado           El estado de la sesión.
     * @param entrenadorSesion El entrenador asignado a la sesión.
     * @param deporte          La especialidad deportiva de la sesión.
     * @throws Exception Si algún parámetro es inválido o no se puede crear la sesión.
     */
    @Override
    public void crearSesionesEntrenamiento(LocalDate fecha, String duracion,
                                           Estado estado, Entrenador entrenadorSesion, Deporte deporte, String descripcion) throws Exception {

        if(fecha == null || fecha.isBefore(LocalDate.now())|| duracion == null
               || estado == null || entrenadorSesion == null || deporte == null){
            throw new Exception("No se ha podido crea la sesion porque puede que falten alguna de estas opciones:"
                    + System.lineSeparator() + "La fecha: Puede que no la hayas colocado o hayas colocado una fecha anterior a la de hoy "
                    + System.lineSeparator()
                    + "La duracion o la colocaste en 0 o negativa" + System.lineSeparator()
                    + "El estado" + System.lineSeparator()+ System.lineSeparator() +
                    "El entrenador"
                    + System.lineSeparator()+
                    "El deporte");
        }
        SesionesEntrenamiento sesion = SesionesEntrenamiento.builder()
                .fecha(fecha)
                .duracion(duracion)
                .estado(estado)
                .entrenadorSesion(entrenadorSesion)
                .deporte(deporte)
                .ID(generarIdentificador())
                .descripcion(descripcion)
                .build();

        listaSecionesEnElsistema.add(sesion);
        guardarDatos();

    }


    /**
     * Elimina una sesión de entrenamiento especificada.
     *
     * @param sesionesEntrenamiento La sesión de entrenamiento a eliminar.
     * @throws Exception Si la sesión no se encuentra o no se puede eliminar.
     */
    @Override
    public void eliminarSesiones(SesionesEntrenamiento sesionesEntrenamiento) throws Exception {

        for(SesionesEntrenamiento sesion: listaSecionesEnElsistema){

            if (sesionesEntrenamiento == sesion){
                listaSecionesEnElsistema.remove(sesion);
                break;
            }else{
                throw new Exception("No se puede eliminar la sesion " + sesion);
            }
        }
        guardarDatos();

    }

    /**
     * Genera un identificador único para una sesión de entrenamiento.
     *
     * @return Un identificador único para la sesión.
     */
    @Override
    public String generarIdentificador(){

        String identificadorSesion = crearIdentificadorSesiones();

        while(validarIdentificadorSesiones(identificadorSesion) != null){
            identificadorSesion = crearIdentificadorSesiones();
        }

        return identificadorSesion;
    }

    /**
     * Crea un identificador de sesión aleatorio.
     *
     * @return Un identificador de sesión aleatorio de 6 dígitos.
     */
    @Override
    public String crearIdentificadorSesiones(){
        StringBuilder identificador = new StringBuilder();

        for(int i = 0; i < 6; i++){
            int numero = new Random().nextInt(6);
            identificador.append(numero);
        }

        return identificador.toString();
    }

    /**
     * Valida si un identificador de sesión ya existe.
     *
     * @param identificador El identificador de sesión a validar.
     * @return El identificador si ya existe; de lo contrario, {@code null}.
     */
    @Override
    public String validarIdentificadorSesiones(String identificador){
        for(int i = 0; i < listaSecionesEnElsistema.size() ; i++){
            if(listaSecionesEnElsistema.get(i).getID().equals(identificador)){
                return identificador;
            }
        }
        return null;
    }

    /**
     * Crea un nuevo deporte en el sistema.
     *
     * @param especialidad La especialidad del deporte.
     * @param dificultad   La dificultad del deporte.
     * @param descripcion  La descripción del deporte.
     * @return El objeto {@code Deporte} creado.
     * @throws Exception Si algún parámetro es inválido.
     */
    @Override

    public Deporte crearDeporte(Especialidades especialidad, Dificultades dificultad, String descripcion)throws Exception{

        if(especialidad == null || dificultad == null || descripcion == null){
            throw new Exception("Ingresa todos los valores");
        }else{
            Deporte deporte = Deporte.builder()
                    .descripcion(descripcion)
                    .especialidad(especialidad)
                    .dificultad(dificultad).build();

            return deporte;
        }




    }
    /**
     * Crea una nueva solicitud y la agrega a la lista de solicitudes de miembros.
     *
     * @param descripcion   La descripción de la solicitud.
     * @param especialidades La especialidad (o deporte) que el miembro desea practicar. No puede ser nulo.
     * @param miembro       El miembro que realiza la solicitud.
     * @throws Exception    Si la especialidad es nula, se lanza una excepción con un mensaje de error.
     */
    @Override
    public void crearSolicitud(String descripcion, Especialidades especialidades, Miembro miembro) throws Exception {
        if(especialidades== null){
            throw new Exception("Ingresa por lo menos el deporte que quieres practicar");

        }else{
            Solicitud solicitud = Solicitud.builder()
                    .descripcion(descripcion)
                    .miembro(miembro)
                    .especialidades(especialidades)
                    .build();
            listaSolicitudesMiembros.add(solicitud);
            System.out.println(listaSolicitudesMiembros.size());

           // guardarDatos();
        }

    }


    public List<SesionesEntrenamiento> obtenerListaSesionesCompletadas() {
        List<SesionesEntrenamiento> sesionesEntrenamientos= new ArrayList<>();
        for (int i = 0; i < sesionesEntrenamientos.size(); i++) {
            if(sesionesEntrenamientos.get(i).getEstado().equals(Estado.Completada)){
                sesionesEntrenamientos.add(sesionesEntrenamientos.get(i));
            }
        }
        return sesionesEntrenamientos;
    }
}
