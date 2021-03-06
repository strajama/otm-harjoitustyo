/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author strajama
 */
public class MaksukorttiTest {

    Maksukortti kortti;
    Maksukortti kortti2;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
        kortti2 = new Maksukortti(12);
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
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiVahentaaToisenKortinSaldoaOikein() {
        kortti2.syoEdullisesti();
        assertEquals("Kortilla on rahaa 9.5 euroa", kortti2.toString());
    }

    @Test
    public void syoMaukkaastiVahentaaToisenKortinSaldoaOikein() {
        kortti2.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 8.0 euroa", kortti2.toString());
    }

    @Test
    public void syoEdullisestiOnnistuuKunSaldoaVainHinnanVerran() {
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiOnnistuuKunSaldoaVainHinnanVerran() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kortti2.syoMaukkaasti();
        kortti2.syoMaukkaasti();
        kortti2.syoEdullisesti();
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 4.0 euroa", kortti.toString());
    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortilleEiVoiLadataNegatiivistaRahaa() {
        kortti.lataaRahaa(-25);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }


}
