package simonedangelo.mondovan.Address.Town;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import simonedangelo.mondovan.Address.Address;
import simonedangelo.mondovan.Address.Province.Province;

import java.util.List;

@Entity
@Table(name = "towns")
@Getter
@Setter
@ToString
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_towns", nullable = false)
    private long id;
    private String name;
    private int codeProv;
    private int codeTown;
    @ManyToOne
    @JoinColumn(name = "id_provinces")
    private Province province;
    @OneToMany(mappedBy = "town")
    @ToString.Exclude
    @JsonIgnore
    private List<Address> addresses;
}
