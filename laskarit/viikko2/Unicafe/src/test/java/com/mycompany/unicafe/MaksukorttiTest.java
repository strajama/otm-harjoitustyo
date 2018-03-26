package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Maksukortti kortti2;
    Kassapaate kassa;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
        kortti2 = new Maksukortti(1200);
        kassa = new Kassapaate();
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void konstruktoriAsettaaToisenKortinSaldonOikein() {
        assertEquals("Kortilla on rahaa 12.0 euroa", kortti2.toString());
    }

    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        kassa.syoEdullisesti(kortti);
        assertEquals("Kortilla on rahaa 7.60 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kassa.syoMaukkaasti(kortti);
        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiVahentaaToisenKortinSaldoaOikein() {
        kassa.syoEdullisesti(kortti2);
        assertEquals("Kortilla on rahaa 9.60 euroa", kortti2.toString());
    }

    @Test
    public void syoMaukkaastiVahentaaToisenKortinSaldoaOikein() {
        kassa.syoMaukkaasti(kortti2);
        assertEquals("Kortilla on rahaa 8.0 euroa", kortti2.toString());
    }

    @Test
    public void syoEdullisestiOnnistuuKunSaldoaVainHinnanVerran() {
        kassa.syoEdullisesti(kortti2);
        kassa.syoEdullisesti(kortti2);
        kassa.syoEdullisesti(kortti2);
        kassa.syoEdullisesti(kortti2);
        kassa.syoEdullisesti(kortti2);
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti2.toString());
    }

    @Test
    public void syoMaukkaastiOnnistuuKunSaldoaVainHinnanVerran() {
        kassa.syoMaukkaasti(kortti2);
        kassa.syoMaukkaasti(kortti2);
        kassa.syoMaukkaasti(kortti2);
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti2.toString());
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(2500);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortilleEiVoiLadataNegatiivistaRahaa() {
        kortti.lataaRahaa(-25);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(20000);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }

    @Test
    public void korttiPalauttaaTrueOikein() {
        assertEquals(true, kortti.otaRahaa(500));
    }

    @Test
    public void korttiPalauttaaFalseOikein() {
        assertEquals(false, kortti.otaRahaa(1500));
    }
}
