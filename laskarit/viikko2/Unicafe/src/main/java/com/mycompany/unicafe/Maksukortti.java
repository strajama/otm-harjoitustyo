package com.mycompany.unicafe;

public class Maksukortti {

    private int saldo;

    public Maksukortti(int saldo) {
        this.saldo = saldo;
    }

    public int saldo() {
        return saldo;
    }

    public void lataaRahaa(int lisays) {
        if (lisays > 0) {
            this.saldo += lisays;
            if (this.saldo > 15000) {
                this.saldo = 15000;
            }
        }
    }

    public boolean otaRahaa(int maara) {
        if (this.saldo < maara) {
            return false;
        }

        this.saldo = this.saldo - maara;
        return true;
    }

    @Override
    public String toString() {
        int euroa = saldo / 100;
        int senttia = saldo % 100;
        return "Kortilla on rahaa " + euroa + "." + senttia + " euroa";
    }

}
