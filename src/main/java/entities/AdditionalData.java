package entities;

import enums.TrainAdditionalDataType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "TrainAdditionalData.findAll", query = "select t from AdditionalData as t")
})
@Table(name = "additional_data")
@Getter @Setter
public class AdditionalData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private TrainAdditionalDataType type;

    @Column(name = "value")
    private String value;

    @ManyToMany(mappedBy = "additionalData")
    private List<Train> posts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalData addData = (AdditionalData) o;
        return Objects.equals(type, addData.type) && Objects.equals(value, addData.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value + type.toString());
    }

    public AdditionalData(){}
}
