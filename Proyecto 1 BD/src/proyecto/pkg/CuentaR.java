/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkg;

/**
 *
 * @author libreria6
 */
public class CuentaR {
     String Nombre;
    String Banco;
    Integer NoCuenta;

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setBanco(String Banco) {
        this.Banco = Banco;
    }

    public void setNoCuenta(Integer NoCuenta) {
        this.NoCuenta = NoCuenta;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getBanco() {
        return Banco;
    }

    public Integer getNoCuenta() {
        return NoCuenta;
    }
    
}
