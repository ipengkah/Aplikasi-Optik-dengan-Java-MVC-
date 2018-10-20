/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author sandy
 */
public class Kwitansi {

    public String getNonota() {
        return nonota;
    }

    public void setNonota(String nonota) {
        this.nonota = nonota;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDp() {
        return dp;
    }

    public void setDp(Double dp) {
        this.dp = dp;
    }

    public Double getSisa() {
        return sisa;
    }

    public void setSisa(Double sisa) {
        this.sisa = sisa;
    }
    private String nokwitansi;
    private String tglkwitansi;
    private String nonota;
    private Double total;
    private Double dp;
    private Double sisa;

    public Double getLunas() {
        return lunas;
    }

    public void setLunas(Double lunas) {
        this.lunas = lunas;
    }
    private Double lunas;

    public String getNokwitansi() {
        return nokwitansi;
    }

    public void setNokwitansi(String nokwitansi) {
        this.nokwitansi = nokwitansi;
    }

    public String getTglkwitansi() {
        return tglkwitansi;
    }

    public void setTglkwitansi(String tglkwitansi) {
        this.tglkwitansi = tglkwitansi;
    }
    
}
