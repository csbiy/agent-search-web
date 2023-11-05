package agent.search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobPlanetCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 2000)
    private String originLink;

    @ManyToOne(fetch = FetchType.LAZY)
    private MilitaryCompany company;

    @OneToMany(mappedBy = "jobPlanetCompany")
    private List<JobPlanetReview> reviews = new ArrayList<>();

    public String getAverageReviews(){
        OptionalDouble average = reviews.stream().mapToDouble(JobPlanetReview::getReviewScore).average();
        if (average.isEmpty()){
            return null;
        }
        return String.valueOf(average.getAsDouble());
    }
}
