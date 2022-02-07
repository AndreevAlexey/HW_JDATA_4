package hibernate.hw_jdata_4.person;

import hibernate.hw_jdata_4.city.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
    @EmbeddedId
    private Identification id;
    private String phone_number;
    @ManyToOne
    private City city;


}
