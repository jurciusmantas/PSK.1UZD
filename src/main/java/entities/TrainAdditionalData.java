package entities;

import enums.TrainAdditionalDataType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "TrainAdditionalData.findAll", query = "select t from TrainAdditionalData as t")
})
@Table(name = "train_additional_data")
@Getter @Setter
public class TrainAdditionalData implements Serializable {
    @EmbeddedId
    TrainAdditionalDataKey id;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Train train;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainAdditionalData addData = (TrainAdditionalData) o;
        return Objects.equals(id, addData.id)
                && Objects.equals(value, addData.value)
                && Objects.equals(train, addData.train);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, id);
    }

    public TrainAdditionalData(){}

    public TrainAdditionalData(Train t){
        this.train = t;
        this.id = new TrainAdditionalDataKey(t.getId());
    }
}
