# OTM-harjoitustyö

## Dokumentaatio

[vaatimmusmäärittely](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[työaikakirjanpito](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[arkkitehtuuri](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[kayttöohje](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[testaus](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/testaus.md)

## Releaset

[viikko 6](https://github.com/strajama/otm-harjoitustyo/releases/tag/viikko6)

[viikko 5](https://github.com/strajama/otm-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

Avaa sovellus komennolla mvn compile exec:java -Dexec.mainClass=seikkailupeli.ui.SeikkailuFXMain

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```
generoi hakemistoon _target_ suoritettavan jar-tiedoston Seikkailupeli-1.0-SNAPSHOT.jar

### Checkstyle

Checkstylen saa katsottua komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

### Javadoc

JavaDoc on luotavissa komennolla 

```
mvn javadoc:javadoc
```
