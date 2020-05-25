package entities;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Destinations.findAll", query = "select d from Destination as d")
})
@Table(name = "destinations")
@Getter @Setter
public class Destination implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @Column(name = "distance_from_station")
    private float distanceFromStation;

    @Version
    @Column(name = "version")
    public int version;

    @JsonbTransient
    @OneToMany(mappedBy = "destination")
    Set<Schedule> schedule;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination dest = (Destination) o;
        return Objects.equals(name, dest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Destination(){
    }
}
