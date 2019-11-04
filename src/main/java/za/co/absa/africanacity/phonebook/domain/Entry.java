package za.co.absa.africanacity.phonebook.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor()
@Entity
@Table(name = "entries")
public class Entry implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private  String name;

    @Column
    @NonNull
    private String surname;

    @Column
    @NonNull
    private String number;

}
