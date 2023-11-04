package agent.search.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MilitaryCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer year;

    private String manageLocation;

    private String location;

    private String phoneNumber;

    private String size;

    private Integer suppAssignedNum;

    private Integer suppEnlistNum;

    private Integer suppWorkingNum;

    private Integer activeAssignedNum;

    private Integer activeEnlistNum;

    private Integer activeWorkingNum;

    public Integer getRemainNumber() {
        return activeAssignedNum - activeEnlistNum;
    }
}
