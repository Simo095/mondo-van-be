package simonedangelo.mondovan.Province;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
}
