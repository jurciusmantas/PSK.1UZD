package entities;

import enums.TrainAdditionalDataType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter
public class TrainAdditionalDataKey implements Serializable {
    @Column(name = "train_id")
    private Integer trainId;

    @Column(name = "type")
    private TrainAdditionalDataType type;

    public TrainAdditionalDataKey(){}

    public TrainAdditionalDataKey(Integer trainId){
        this.trainId = trainId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainAdditionalDataKey key = (TrainAdditionalDataKey) o;
        return Objects.equals(trainId, key.trainId)
                && Objects.equals(type, key.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, type);
    }
}
