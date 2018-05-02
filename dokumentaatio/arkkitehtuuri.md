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

<img src="https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Making%20action%20in%20game.png">
<img src="https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Saving%20new%20data.png">
# Ohjelmaa on myllätty tämän luomisen jälkeen enkä halua tehdä uutta ennen kuin olen varma minkälaiseksi haluan tämän tulevan
<img src="https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri.png">
