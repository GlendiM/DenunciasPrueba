package com.UniversidaVeracruzana.buzondenuncias.config.security;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.UniversidaVeracruzana.buzondenuncias.model.Respuesta;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Respuesta> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Método no soportado");
        respuesta.setMensaje(String.format("El método %s no está soportado para esta URL", e.getMethod()));
        
        return new ResponseEntity<>(respuesta, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Respuesta> handleHttpMessageNotReableException(HttpMessageNotReadableException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Petición Errónea");
        String mensajeError = "El cuerpo JSON de la petición es requerido";
        
        if(e.getMessage() != null){
            // adaptado al los enum del sistema de denuncias
            if(e.getMessage().contains("TipoUsuario") || e.getMessage().contains("RolesEnum") ||
                e.getMessage().contains("Status_User") || e.getMessage().contains("EstadoDenuncia") ||
                e.getMessage().contains("TipoRegiones")){
                    mensajeError = "Valor no válido para un campo de enumeración. Verifique los valores permitidos";
                } else if (e.getMessage().contains("JSON parse error")){
                    mensajeError = "El formato JSON es inválido. Verifique la sintáxis";
                } else if (e.getMessage().contains("Cannot deserealize value of type")){
                    mensajeError = "Valor no válido para uno de los campos. Verifique los tipos de campos";
                }else if(e.getMessage().contains("Required request body is missing")){
                    mensajeError = "El cuerpo de la petición es requerido";
                }
        }
        respuesta.setMensaje(mensajeError);
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Respuesta> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo(0);
            respuesta.setTitulo("Datos Inválidos");

            StringBuilder mensajesError = new StringBuilder();
            e.getBindingResult().getFieldErrors().forEach(error -> {
                if(mensajesError.length() > 0){
                    mensajesError.append("; ");
                }
                mensajesError.append(error.getField()).append(": ").append(error.getDefaultMessage());
            });
            String mensajeFinal = mensajesError.length() > 0 ?
                    mensajesError.toString() : "Error de Validación en los datos de entrada";

            respuesta.setMensaje(mensajeFinal);
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
       }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Respuesta> handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Parámetro Faltante");
        respuesta.setMensaje(String.format("El parámetro '%s' es requerido", e.getParameterName()));
        return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Respuesta> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Tipo de Dato Incorrecto");
        respuesta.setMensaje(String.format("El tipo de dato del parámetro '%s' es incorrecto", e.getName()));

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<Respuesta> handleDataAccessResourceFailureException(DataAccessResourceFailureException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Error de conexión a la base de datos");
        respuesta.setMensaje("No se puede establecer conexión con la base de datos. Intente nuevamente");

        return new ResponseEntity<>(respuesta, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<Respuesta> handleBadSqlGrammarException(BadSqlGrammarException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Error de Consulta SQL");
        respuesta.setMensaje("Error interno en el procedimiento de datos");

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Respuesta> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Violación de Integridad de datos");

        String mensaje = "Error en los datos proporcionados";
        if(e.getMessage() != null){
            if(e.getMessage().contains("duplicate key") || e.getMessage().contains("Unique Index")){
                mensaje = "Ya existe un registro con los mismos datos (correo, matricula o número de personal)";
            }else if(e.getMessage().contains("Foreign Key")){
                mensaje = "Referencia a datos inexistentes (entidad académica, región, etc.)";
            }
        }
        respuesta.setMensaje(mensaje);
        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }

    // MANEJO DE EXCEPCIONES PERSONALIZADAS
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Respuesta> handleServiceException(ServiceException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(e.getCodigo());
        respuesta.setTitulo(e.getTitulo());
        respuesta.setMensaje(e.getMessage());
        return new ResponseEntity<>(respuesta, e.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Respuesta> handleValidationException(ValidationException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Error de validación");
        respuesta.setMensaje(e.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Respuesta> handleRuntimeException(RuntimeException e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Error del Sistema");
        respuesta.setMensaje(e.getMessage());

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Respuesta> handleGenericException(Exception e){
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(0);
        respuesta.setTitulo("Error interno del Servidor");
        respuesta.setMensaje("Ocurrió un error inesperado. Por favor, conecte al administrador");
        
        return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
