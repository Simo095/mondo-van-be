package simonedangelo.mondovan.Address.Province;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import simonedangelo.mondovan.Address.Town.Town;

import java.util.List;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@ToString
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_provinces", nullable = false)
    private long id;
    private String name;
    @Column(name = "abb")
    private String abbreviation;
    private String region;
    @OneToMany(mappedBy = "province")
    @ToString.Exclude
    @JsonIgnore
    private List<Town> cities;
}
