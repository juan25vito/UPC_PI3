package com.distribuidora.appandroid.Model;

public class Cliente {

    String codigo;
    String documento;
    String razonSocial;
    String condicion;
    String direccion;
    String telefono;
    String email;

    public Cliente() {
    }

    public Cliente(String codigo, String documento, String razonSocial, String condicion, String direccion, String telefono, String email) {
        this.codigo = codigo;
        this.documento = documento;
        this.razonSocial = razonSocial;
        this.condicion = condicion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
