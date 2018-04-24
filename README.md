# OTM-harjoitustyö

## Dokumentaatio

[alustava määrittelydokumentti](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/alustavamaarittelydokumentti.md)

[työaikakirjanpito](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[arkkitehtuuri](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[github release]

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
