package com.proyecto.checkin.entidades;

public class CheckIn {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nacimiento;
    private String sexo;
    private String tipo_documento;
    private String num_documento;
    private String expedicion;
    private String nacionalidad;
    private String fechaHora;

    public CheckIn(int id, String nombre, String apellido1, String apellido2, String nacimiento, String sexo, String tipo_documento, String num_documento, String expedicion, String nacionalidad, String fechaHora) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
        this.tipo_documento = tipo_documento;
        this.num_documento = num_documento;
        this.expedicion = expedicion;
        this.nacionalidad = nacionalidad;
        this.id=id;
        this.fechaHora=fechaHora;
    }

    public CheckIn() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(String expedicion) {
        this.expedicion = expedicion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFechaHora(){
        return fechaHora;
    }

    public void setFechaHora(String fechaHora){
        this.fechaHora=fechaHora;
    }
}
