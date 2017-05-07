package Domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andrei on 2017-04-28.
 */
@Entity
@Table(name = "agecategories")
public class AgeCategory implements HasId<String>, Serializable {
    @Id
    @Column(name = "categoryName")
    private String name;

    @Column(name = "minAge")
    private Integer minAge;

    @Column(name = "maxAge")
    private Integer maxAge;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ageCategory")
    @Fetch(FetchMode.SELECT)
    private Set<Participant> participants = new HashSet<>();

    public AgeCategory() {
    }

    public AgeCategory(String name, Integer minAge, Integer maxAge) {
        this.name = name;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public String getId() {
        return name;
    }

    public void setId(String id) {
        name = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgeCategory)) return false;

        AgeCategory that = (AgeCategory) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getMinAge() != null ? !getMinAge().equals(that.getMinAge()) : that.getMinAge() != null) return false;
        return getMaxAge() != null ? getMaxAge().equals(that.getMaxAge()) : that.getMaxAge() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getMinAge() != null ? getMinAge().hashCode() : 0);
        result = 31 * result + (getMaxAge() != null ? getMaxAge().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AgeCategory{" +
                "name='" + name + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                '}';
    }
}
