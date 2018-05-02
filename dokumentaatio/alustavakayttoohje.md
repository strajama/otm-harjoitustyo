# Käyttöohje

Lataa tiedosto [Seikkailupeli-1.0-SNAPSHOT.jar](https://github.com/strajama/otm-harjoitustyo/releases/tag/viikko6)

## Konfigurointi

Ohjelma tarvitsee toimiakseen SQLite-sovelluksen.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar Seikkailupeli-1.0-SNAPSHOT.jar 
```

## Kirjautuminen

Sovellus käynnistyy valikkonäkymään, jossa on kolme nappia.
Ylimmästä pääsee pelaamaan, keskimmäisestä lisäämään uusia asioita seuraavaan pelikertaan ja alin sulkee sovelluksen.

## Pelaaminen

Pelinäkymässä vasemmassa ylälaidassa näkyy pelaajan sijainti ja sen alapuolella kuvailua siitä mitä pelaaja näkee, mitä on juuri tapahtunut ja mikä on pelitilanne.

Keskellä näkyy tieto siitä mitä esineitä pelaajalla on repussaan.

Oikeassa reunassa näkyy viisi parasta tallennettua pistemäärää.

Liikkuminen pelissä tapahtuu vasemmassa reunassa olevan neljän näppäimen avulla.
Pelaajalla on neljä toimintonäppäintä: poimi, anna, puhu ja lyö.
Tekstikenttään voi kirjoittaa oman pelinimensä ja sen alapuolelta nappia painamalla tallentaa pisteet. Peli tallentaa aina edellisten samalla pelinimellä tehtyjen pisteiden päälle.
Paluu valikkoon -nappi vie takaisin valikkoon.

### Pisteiden kertyminen

* Yksi onnistunut toiminta (liikkuminen, anna, lyö, puhu, poimi) vie yhden pisteen.
* Tavallisen apurin kanssa puhuminen tuo 5 pistettä, tavoiteapurin kanssa puhuminen 10 pistettä.
* Tavallisen esineen poimiminen tuo 5 pistettä, tavoite-esineen poimiminen 10 pistettä.
* Apurin kaipaaman esineen antaminen hänelle tuo 10 pistettä.
* Vaeltavan hirviön kukistaminen tuo 20 pistettä.

## Uusien asioiden lisääminen

Valitse ensin valikosta, että minkä uuden tiedon haluat lisätä.
Kirjoita tämän jälkeen sille tekstikenttiin nimi ja kuvaus ja paina Lisää-nappia.
Jos haluat tyhjentää kentät, niin paina Pyyhi-nappia.
Palaa takaisin -nappi vie takaisin valikkoon.
Jos olet valinnut ylimmästä valikosta tietotyypin, niin alinna olevasta valikosta näet minkälaisia tietoja tietokannassa on ennestään.
