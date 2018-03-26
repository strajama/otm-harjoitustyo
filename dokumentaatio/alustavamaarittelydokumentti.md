# Vaatimusmäärittely

## Soveluksen tarkoitus

Sovellus on seikkailupeli, jollaa voi pelata valmiiksi tehtyjä pelejä sekä randomisti luotuja. Peleissä liikutaan kartalla ja suoritetaan tehtäviä aikarajan puitteissa. Sovelluksella voi luoda myös omia seikkailuja itselle ja muille käyttäjille.

## Käyttäjät

Sovellusta voi käyttää pelaamalla rekisteröitymättä tai rekisteröityneenä. Rekisteröityneen pelaajan pelaamista peleistä pidetään kirjaa. Toinen käyttäjätyyppi on seikkailuntekijä, joka voi luoda seikkailuja valmiista osasista muille pelaajille pelattavaksi. 

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- pelaaja voi pelata kirjautumatta
  - valmiiksi luotuja seikkailuja
  - random-toiminnolla luodun seikkailun

- pelaaja voi luoda järjestelmään pelaajatunnuksen
  - pelaajatunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä

- pelaaja voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassaoleva pelaajatunnus kirjautumislomakkeelle
  - jos pelaajaa ei olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

#### Pelaaja

- pelaaja näkee pelaamansa pelit

- pelaaja voi kirjautua ulos järjestelmästä

#### Seikkailuntekijä

- seikkailuntekijä voi luoda uuden seikkailun itselle ja muille pelattavaksi

## Jatkokehitysideoita

Perusversion jälkeen sovellusta täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- kolmas käyttäjätyyppi luoja, joka voi lisätä peliin kokonaan uusia elementtejä
- taistelu-toiminnallisuus
- npc-hahmot
- pelaajien yhteyteen salasana, joka vaaditaan kirjautuessa
- pelaajatunnuksen ja pelitietojen poisto
