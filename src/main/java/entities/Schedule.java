package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Schedule.findAll", query = "select s from Schedule as s")
})
@Table(name = "schedule")
@Getter @Setter
public class Schedule implements Serializable {
    @EmbeddedId
    TrainDestinationKey id;

    @ManyToOne
    @MapsId("trainId")
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @MapsId("destinationId")
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(train, schedule.train) && Objects.equals(destination, schedule.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, train);
    }

    public Schedule(){
    }
}
