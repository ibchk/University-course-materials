/**
 * Node class with participants.
 */
public class ParticipantNode {

    /**
     * Participant node.
     */
    private HackathonParticipant participant;
    /**
     * left node.
     */
    private ParticipantNode left;
    /**
     * right node.
     */
    private ParticipantNode right;
    /**
     * parent node.
     */
    private ParticipantNode parent;

    /**
     * Initialize new empty node.
     */
    public ParticipantNode() {
        participant = null;
        left = null;
        right = null;
        parent = null;
    }

    /**
     * @return get participant.
     */
    public HackathonParticipant getParticipant() {
        return participant;
    }

    /**
     * @return get left.
     */
    public ParticipantNode getLeft() {
        return left;
    }

    /**
     * @return get right.
     */
    public ParticipantNode getRight() {
        return right;
    }

    /**
     * @return get parent.
     */
    public ParticipantNode getParent() {
        return parent;
    }

    /**
     * @param newParticipant set new participant.
     */
    public void setParticipant(final HackathonParticipant newParticipant) {
        participant = newParticipant;
    }

    /**
     * @param newLeft set new left.
     */
    public void setLeft(final ParticipantNode newLeft) {
        left = newLeft;
    }

    /**
     * @param newRight set new right.
     */
    public void setRight(final ParticipantNode newRight) {
        right = newRight;
    }

    /**
     * @param newParent set new parent.
     */
    public void setParent(final ParticipantNode newParent) {
        parent = newParent;
    }
}
