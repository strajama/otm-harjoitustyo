# Käyttöohje

Lataa tiedosto [Seikkailupeli-1.0-SNAPSHOT.jar](https://github.com/strajama/otm-harjoitustyo/releases/tag/viikko6)

## Konfigurointi

Ohjelma tarvitsee toimiakseen SQLite-sovelluksen.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar Seikkailupeli-1.0-SNAPSHOT.jar 
```

## Aloitus

Sovellus käynnistyy valikkonäkymään, jossa on kolme nappia.
* ylimmästä napista pääsee pelaamaan
* keskimmäisestä lisäämään uusia asioita seuraavaan pelikertaan
* alin sulkee sovelluksen.

## Pelaaminen

Pelinäkymässä vasemmassa ylälaidassa näkyy pelaajan sijainti ja sen alapuolella kuvailua siitä mitä pelaaja näkee, mitä on juuri tapahtunut ja mikä on pelitilanne.

Keskellä näkyy tieto siitä mitä esineitä pelaajalla on repussaan.

Oikeassa reunassa näkyy viisi parasta tallennettua pistemäärää.

Liikkuminen pelissä tapahtuu vasemmassa reunassa olevan neljän nuolinäppäimen avulla.
Pelaajalla on neljä toimintonäppäintä:
* poimi
* anna
* puhu
* lyö

Tekstikenttään voi kirjoittaa oman pelinimensä ja sen alapuolelta nappia painamalla tallentaa pisteet. Peli tallentaa aina edellisten samalla pelinimellä tehtyjen pisteiden päälle.

Paluu valikkoon -nappi vie takaisin valikkoon.

### Kartta

Pelissä liikutaan alla olevalla kartalla, jonka alueiden sijainti arvotaan peliä rakennettaessa. Aloituspaikka on nimetty "KOTI".

<img src="https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/map.png">

### Pisteiden kertyminen

* Yksi onnistunut toiminta (liikkuminen, anna, lyö, puhu, poimi) vie yhden pisteen.
* Tavallisen apurin kanssa puhuminen tuo 5 pistettä, tavoiteapurin kanssa puhuminen 10 pistettä.
* Tavallisen esineen poimiminen tuo 5 pistettä, tavoite-esineen poimiminen 10 pistettä.
* Apurin kaipaaman esineen antaminen hänelle tuo 10 pistettä.
* Vaeltavan hirviön kukistaminen tuo 20 pistettä.

## Uusien asioiden lisääminen ja poistaminen

Valitse ensin valikosta, että minkä uuden tiedon haluat lisätä.

Kirjoita tämän jälkeen sille tekstikenttiin nimi ja kuvaus ja paina Lisää-nappia.

Jos haluat poistaa jonkin tiedon, niin kirjoita sen nimi ja paina Poista-nappia. Jos peli ei enää toimi poistojen jälkeen, niin alkuperäiset taulut saa palautettua poistamalla _adventure.db_ -tiedoston ohjelman kansiosta.

Palaa takaisin -nappi vie takaisin valikkoon.

Jos olet valinnut ylimmästä valikosta tietotyypin, niin alinna olevasta valikosta näet minkälaisia tietoja tietokannassa on ennestään.
