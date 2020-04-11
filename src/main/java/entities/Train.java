package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Trains.findAll", query = "select t from Train as t")
})
@Table(name = "trains")
@Getter @Setter
public class Train implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "train_additionaldata",
        joinColumns = @JoinColumn(name = "train_id"),
        inverseJoinColumns = @JoinColumn(name = "additionaldata_id")
    )
    private List<AdditionalData> additionalData = new ArrayList<>();

    @OneToMany(mappedBy = "train")
    Set<Schedule> schedule;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(name, train.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Train(){ }

    public Train(String name){
        this.name = name;
    }
}
