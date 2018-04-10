# OTM-harjoitustyö

## Dokumentaatio

[alustava määrittelydokumentti](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/alustavamaarittelydokumentti.md)

[työaikakirjanpito](https://github.com/strajama/otm-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Komentorivitoiminnot

Avaa sovellus komennolla mvn compile exec:java -Dexec.mainClass=seikkailupeli.ui.SeikkailuFXMain

###Testaus

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

 
