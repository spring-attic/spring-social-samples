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
public class Follower {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private Long userId;

    private Long followerId;
}
