# OTM-harjoitustyö

## Dokumentaatio

[vaatimmusmäärittely](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[työaikakirjanpito](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[arkkitehtuuri](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[kayttöohje](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[testaus] 

## Releaset

[viikko 6](https://github.com/strajama/otm-harjoitustyo/releases/tag/viikko6)

[viikko 5](https://github.com/strajama/otm-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

Avaa sovellus komennolla mvn compile exec:java -Dexec.mainClass=seikkailupeli.ui.SeikkailuFXMain

### Testaus

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_


Checkstylen saa katsottua komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
JavaDoc on luotavissa komennolla

```
mvn javadoc:javadoc
```
