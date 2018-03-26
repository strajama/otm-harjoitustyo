/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author strajama
 */
public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void kassassaRahaa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullisiaLounaitaMyytyNolla() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaitaLounaitaMyytyNolla() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void myyEdullinenLounasKateisellaOikeinMaara() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void myyEdullinenLounasKateisellaOikeinKassa() {
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void myyEdullinenLounasKateisellaOikeinTakaisin() {
        kassa.syoEdullisesti(500);
        assertEquals(260, kassa.syoEdullisesti(500));
    }

    @Test
    public void myyMaukasLounasKateisellaOikeinMaara() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void myyMaukasLounasKateisellaOikeinKassa() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void myyMaukasLounasKateisellaOikeinTakaisin() {
        kassa.syoMaukkaasti(500);
        assertEquals(100, kassa.syoMaukkaasti(500));
    }

    @Test
    public void alaMyyEdullistaLounastaLiianVahallaRahalla() {
        kassa.syoEdullisesti(100);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void alaMyyMaukastaLounastaLiianVahallaRahalla() {
        kassa.syoMaukkaasti(100);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void alaMyyEdullistaLounastaLiianVahallaRahallaKassasaldo() {
        kassa.syoEdullisesti(100);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void alaMyyMaukastaLounastaLiianVahallaRahallaKassasaldo() {
        kassa.syoMaukkaasti(100);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void annaLiianVahatRahatTakaisinEdullisesta() {
        assertEquals(100, kassa.syoEdullisesti(100));
    }

    @Test
    public void annaLiianVahatRahatTakaisinMaukkaasta() {
        assertEquals(100, kassa.syoMaukkaasti(100));
    }

    @Test
    public void myyEdullinenKortillaTrue() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void myyMaukasKortillaTrue() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void myyEdullinenKortillaFalse() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertFalse(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void myyMaukasKortillaFalse() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void myyEdullinenKortiltaRahat() {
        kassa.syoEdullisesti(kortti);
        assertEquals("Kortilla on rahaa 7.60 euroa", kortti.toString());
    }

    @Test
    public void myyMaukasKortiltaRahat() {
        kassa.syoMaukkaasti(kortti);
        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void myyEdullinenKortillaKassa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void myyMaukasKortillaKassa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassaanEiTuleRahaaKortistaEdullinen() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void kassaanEiTuleRahaaKortistaMaukas() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void myyEdullinenKortillaFalseMyydyt() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void myyMaukasKortillaFalseMyydyt() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    public void myyEdullinenKortillaFalseKortinSaldo() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }

    @Test
    public void myyMaukasKortillaFalseKortinSaldo() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    @Test
    public void lataaKorttiKortinSaldo() {
        kassa.lataaRahaaKortille(kortti, 2500);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }
    @Test
    public void lataaKorttiKassanSaldo() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000,kassa.kassassaRahaa());
    }
        @Test
    public void lataaNollaKorttiKortinSaldo() {
        kassa.lataaRahaaKortille(kortti, 0);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    @Test
    public void lataaNollaKorttiKassanSaldo() {
        kassa.lataaRahaaKortille(kortti, 0);
        assertEquals(100000,kassa.kassassaRahaa());
    }
        @Test
    public void lataaMiinusKorttiKortinSaldo() {
        kassa.lataaRahaaKortille(kortti, -2500);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    @Test
    public void lataaMiinusKorttiKassanSaldo() {
        kassa.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000,kassa.kassassaRahaa());
    }
}
/*


kortille rahaa ladattaessa kortin saldo muuttuu ja kassassa oleva rahamäärä kasvaa ladatulla summalla
 */
