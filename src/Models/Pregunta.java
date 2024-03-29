package Models;

import java.io.Serializable;
import java.util.List;

public class Pregunta {
    public String getEnunciado() {
        return enunciado;
    }

    public List<String> getListadoOpciones() {
        return listadoOpciones;
    }

    public String getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    private String enunciado;
    private List<String> listadoOpciones;
    private String opcionCorrecta;
    private String descripcion;
    private String estado;
    private String respondidoPor;
    private boolean esCorrecta;

    public Pregunta(String enunciado, List<String> listadoOpciones, String opcionCorrecta, String descripcion) {
        this.enunciado = enunciado;
        this.listadoOpciones = listadoOpciones;
        this.opcionCorrecta = opcionCorrecta;
        this.descripcion = descripcion;
        this.estado = "LIBRE";
        this.respondidoPor = "defecto";
        this.esCorrecta = false;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespondidoPor() {
        return respondidoPor;
    }

    public void setRespondidoPor(String respondidoPor) {
        this.respondidoPor = respondidoPor;
    }

    public boolean getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public void verificarOpcion(String opcion) {
        // codigo
    }

}