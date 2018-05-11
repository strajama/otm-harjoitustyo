# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automaattiset [testit](https://github.com/strajama/otm-harjoitustyo/tree/master/Seikkailupeli/src/test/java) testaavat _domain_-ja _dao_-pakettien toimintaa yksittäisiä luokkia ja niiden metodeja testaamalla ja erilaisilla integraatiotesteillä, jotka simuloivat käyttöliittymän tapahtumia.

Käyttöliittymää tarvitsevien luokkien testaamista varten käytettiin _TestMain_-käyttöliittymää, jotta luokkien toimintaa pystyttiin testaamaan niiltä osin, jotka eivät päivittäneet käyttöliittymää. Käyttöliittymän päivittyminen oikein on testattu manuaalisesti.

### Tietokannan testaus

Tietokannan testaamista varten luotiin erillinen _testi.db_-tietokanta, jotta sovelluksen oma tietokanta ei vaikuttaisi testituloksiin.

### Testauskattavuus


### Toiminnallisuudet

Kaikki [vaatimusmäärittelyn](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja [käyttöohjeen](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.
