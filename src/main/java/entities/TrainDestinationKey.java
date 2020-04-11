package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
@Getter @Setter
public class TrainDestinationKey implements Serializable {
    @Column(name = "train_id")
    private Integer trainId;

    @Column(name = "destination_id")
    private Integer destinationId;

    @Column(name = "date")
    private Date date;

    public TrainDestinationKey(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainDestinationKey key = (TrainDestinationKey) o;
        return Objects.equals(trainId, key.trainId)
                && Objects.equals(destinationId, key.destinationId)
                && Objects.equals(date, key.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, destinationId, date);
    }
}
