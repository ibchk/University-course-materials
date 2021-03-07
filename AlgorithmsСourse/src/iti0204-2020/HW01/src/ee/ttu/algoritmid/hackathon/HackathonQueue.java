import java.util.List;
import java.util.LinkedList;

/**
 * A HackathonQueue class, to find, delete, add participants.
 */
public class HackathonQueue {

    /**
     * Developer and analyst max extroversion difference.
     */
    public static final double EXTROVERSION_LVL = 7.5;

    /**
     * Hackathon tree with participants.
     */
    private final ParticipantNode tree;


    /**
     * Initialize a new HackathonQueue.
     */
    public HackathonQueue() {
        tree = new ParticipantNode();
    }

    /**
     * @return hackathon node;
     */
    public ParticipantNode getHackathonNode() {
        return tree;
    }

    /**
     * @param participant participant who needs partner.
     * @return partner.
     */
    public HackathonParticipant findPartner(final
                                            HackathonParticipant participant) {
        ParticipantNode partner = tree;
        ParticipantNode bestPartner = null;
        double participantExtroversion = participant.getExtroversionLevel();
        if (tree.getParticipant() != null) {
            do {
                double partnerExtroversion = partner.getParticipant().getExtroversionLevel();
                if (Math.abs(partnerExtroversion - participantExtroversion) <= EXTROVERSION_LVL
                        && participant.getRole()
                        != partner.getParticipant().getRole()
                        && ((bestPartner == null)
                        || Math.abs(bestPartner.getParticipant().getExtroversionLevel() - participantExtroversion) > Math.abs(partnerExtroversion - participantExtroversion)
                        || Math.abs(bestPartner.getParticipant().getExtroversionLevel() - participantExtroversion) == Math.abs(partnerExtroversion - participantExtroversion)
                        && bestPartner.getParticipant().getExtroversionLevel() >= partnerExtroversion)) {
                    bestPartner = partner;
                }
                if (partner.getParticipant().getExtroversionLevel() - participant.getExtroversionLevel() >= 0) {
                    partner = partner.getLeft();
                } else {
                    partner = partner.getRight();
                }
            } while (partner != null);
        }
        if (bestPartner != null) {
            HackathonParticipant newPartner = bestPartner.getParticipant();
            delete(bestPartner);
            return newPartner;
        }
        return null;
    }

    /**
     * @param partner participant to delete from waiting queue.
     */
    public void delete(final ParticipantNode partner) {
        ParticipantNode nodeForReplace;
        if (partner.getRight() != null) {
            nodeForReplace = partner.getRight();
            while (true) {
                if (nodeForReplace.getLeft() != null) {
                    nodeForReplace = nodeForReplace.getLeft();
                } else {
                    partner.setParticipant(nodeForReplace.getParticipant());
                    delete(nodeForReplace);
                    break;
                }
            }
        } else if (partner.getLeft() != null) {
            nodeForReplace = partner.getLeft();
            while (true) {
                if (nodeForReplace.getRight() != null) {
                    nodeForReplace = nodeForReplace.getRight();
                } else {
                    partner.setParticipant(nodeForReplace.getParticipant());
                    delete(nodeForReplace);
                    break;
                }
            }
        } else if (partner.getParent() == null) {
            partner.setParticipant(null);
        } else {
            if (partner.getParent().getLeft() == partner) {
                partner.getParent().setLeft(null);
            } else {
                partner.getParent().setRight(null);
            }
        }
    }


    /**
     * @param participant participant to add to node.
     */
    public void addToNode(final HackathonParticipant participant) {
        ParticipantNode participantNode = tree;
        ParticipantNode parent = null;
        double extroversionNew = participant.getExtroversionLevel();
        while (true) {
            if (participantNode.getParticipant() == null) {
                participantNode.setParent(parent);
                participantNode.setParticipant(participant);
                break;
            } else {
                parent = participantNode;
                if (participantNode.getParticipant().getExtroversionLevel()
                        - extroversionNew > 0) {
                    participantNode = participantNode.getLeft();
                    if (participantNode == null) {
                        participantNode = new ParticipantNode();
                        parent.setLeft(participantNode);
                    }
                } else {
                    participantNode = participantNode.getRight();
                    if (participantNode == null) {
                        participantNode = new ParticipantNode();
                        parent.setRight(participantNode);
                    }
                }
            }
        }
    }

    /**
     * @param participant node to make a list of participants.
     * @return list with participants without team.
     */
    public List<HackathonParticipant> participantsOrder(
            final ParticipantNode participant) {
        List<HackathonParticipant> participants = new LinkedList<>();
        if (participant.getParticipant() != null) {
            if (participant.getLeft() != null) {
                participants.addAll(participantsOrder(participant.getLeft()));
            }
            participants.add(participant.getParticipant());
            if (participant.getRight() != null) {
                participants.addAll(participantsOrder(participant.getRight()));
            }
        }
        return participants;
    }

    /**
     * @return list with participants without team.
     */
    public List<HackathonParticipant> participantsWithoutTeam() {
        return participantsOrder(tree);
    }
}
