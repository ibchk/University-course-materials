/**
 * Team class.
 */
public class TeamImplementation implements HackathonTeam {

    /**
     * Developer.
     */
    private final HackathonParticipant developer;
    /**
     * Business analyst.
     */
    private final HackathonParticipant businessAnalyst;
    /**
     * An extroversion of 2 participant.
     */
    private final Double extroversionSum;

    /**
     * @param participant1 participant 1.
     * @param participant2 participant 2.
     */
    public TeamImplementation(final HackathonParticipant participant1,
                              final HackathonParticipant participant2) {
        if (participant1
                .getRole()
                .equals(HackathonParticipant.Role.BUSINESS_ANALYST)) {
            businessAnalyst = participant1;
            developer = participant2;
        } else {
            businessAnalyst = participant2;
            developer = participant1;
        }
        extroversionSum = developer.getExtroversionLevel()
                + businessAnalyst.getExtroversionLevel();
    }

    /**
     * @return extroversion of the team.
     */
    public Double getExtroversionSum() {
        return extroversionSum;
    }

    /**
     * @return developer.
     */
    @Override
    public HackathonParticipant getDeveloper() {
        return developer;
    }

    /**
     * @return business analyst.
     */
    @Override
    public HackathonParticipant getBusinessAnalyst() {
        return businessAnalyst;
    }
}
