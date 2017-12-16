package tr.edu.boun.secretary.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tr.edu.boun.secretary.util.BCryptPasswordDeserializer;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Table
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    private String username;

    @Size(min = 60, max = 60)
    @Column(name="password", nullable = false, length = 60)
    @JsonDeserialize(using = BCryptPasswordDeserializer.class )
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;
}
