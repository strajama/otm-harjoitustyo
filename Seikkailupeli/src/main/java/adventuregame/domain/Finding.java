package adventuregame.domain;

import java.util.Objects;
/**
 * Luokat Item ja Helper toteuttavat abstraktin luokan Finding
 * @author strajama
 */
public abstract class Finding {

    private String name;
    private String description;

    public Finding(String name, String description) {
        this.name = name;
        this.description = description;
    }
/**
 * Metodi kertoo onko kyseessä Item-olio ja se toteutetaan luokissa
 * @return palauttaa boolean-arvon
 */
    public abstract boolean isItem();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name.toUpperCase() + ", " + description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Finding other = (Finding) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
