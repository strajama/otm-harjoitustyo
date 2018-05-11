# Arkkitehtuurikuvaus

## Rakenne


Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/rakenne.png">

Pakkaus _adventuregame.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _adventuregame.domain_ sovelluslogiikan ja _adventuregame.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kolme erillistä näkymää
- valikko
- pelitilanne
- uusien asioiden lisääminen pelimuuttujiksi

jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen Stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa [adventuregame.ui.SeikkailuFXMain.java](https://github.com/strajama/otm-harjoitustyo/blob/master/Seikkailupeli/src/main/java/adventuregame/ui/SeikkailuFXMain.java).

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta. Ohjelma luo Adventure-olion, joka pitää kirjaa pelin tilanteesta ja Action-olioita, joiden metodit muuttavat pelin tilannetta. Tietokannan ja käyttöliittymän välillä toimii DaoService-olio.

## Sovelluslogiikka

Sovelluslogiikassa on kaksi erillistä osa-aluetta: itse peli ja tietokannat. Pelilogiikan toteutuminen ja pelinäkymän päivittäminen tapahtuu Action-luokan avulla. Tietokantoihin liittyvät tapahtumat päivittyvät DaoService-luokan avulla.

### Domain-paketti
* World-luokka ylläpitää tietoa mitä on missäkin.
* Adventure-luokan tarkoitus on ylläpitää tietoa seikkailun tavoitteista ja niiden toteutumisesta.
* Action-luokkaa käytetään pelimuutosten tekemiseen ja käyttöliittymän päivittämiseen.
* Area-luokka ylläpitää tietoa siitä mitä yksittäisellä alueella on.
* Helper-luokka on tarkoitettu pelaajan tapaamien apureiden ylläpitämiseen.
* Item-luokka on tarkoitettu esineiden ylläpitämiseen.
* Monster-luokka ylläpitää hirviön tietoja.
* Player-luokka ylläpitää tietoa siitä mitä pelaaja on tehnyt.
* Score-luokka on pisteiden tallentamista ja hallinnointia varten.

### DaoService
* Database-luokka ottaa yhteyden tietokantaan
* Dao-rajapinnan toteuttavat luokat tallentavat ja ylläpitävät tietoa oikein.
* DaoService-luokka kutsuu Dao-rajapinnan toteuttavia luokkia ja päivittää käyttöliittymää

## Tietojen pysyväistallennus

Pakkauksen _adventuregame.dao_ luokat _DaoService_, _AreaDao_, _ItemDao_, _HelperDao_, _MonsterDao_ ja _ScoreDao_ huolehtivat tietojen tallettamisesta tietokantatiedostoon _adventure.db_.

Luokat noudattavat Data Access Object-suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat on eristetty rajapinnan _Dao_ taakse ja sovelluslogiikan toteutus ei käytä luokkia suoraan vaan kutsuu _DaoService_-oliota.

Jos sovelluksen käyttäjä haluaa kerralla poistaa kaiken lisäämänsä tiedon, niin pelin perustoimivuuden antava tietokanta on kirjoitettu sovelluskoodiin mukaan ja _adventure.db_ luodaan uudestaan.

Sovelluslogiikan testauksessa käytetään erillistä tietokantatiedostoa _test.db_. 

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

Käyttäjä klikkaa käyttöliittymän POIMI-nappia pelinäkymässä, joka kutsuu Action-oliota, joka tekee muutoksia Player-olion reppuun. Player-olio antaa Action-oliolle tiedon, että mitä käyttöliittymän tekstikenttiä kuuluu päivittää.

<img src="https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Making%20action%20in%20game.png">

Käyttäjä klikkaa käyttöliittymän LISÄÄ-nappia luomisnäkymässä, joka kutsuu DaoService-luokkaa, joka tekee lisäyksen tietokantaan ja päivittää käyttöliittymän näkymää.

<img src="https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Saving%20new%20data.png">

## Ohjelman rakenteeseen jääneet heikkoudet

Ohjelmaa ei ole kokonaan parametrisoitu kielen puolesta, joten ohjelman kääntäminen toiselle kielelle on vaivalloista.

Yhteyden sovelluslogiikan ja käyttöliittymän välillä sisältää kehittämisen varaa.

Käyttöliittymän asettelu on kömpelö.
