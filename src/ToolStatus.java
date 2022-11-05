/**
 * Displays the status of the tool when used by the farmer
 * @version 1.0
 */
public class ToolStatus {
    private String feedback;
    private boolean success;

    /**
     * Constructor that instantiates the ToolStatus object
     */
    public ToolStatus() {
        this.success = false;
        this.feedback = null;
    }
    
    /**
     * Sets the feedback of the tool
     * @param feedback The feedback given to the farmer when using the tool
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * Sets the use success of the tool
     * @param success Checker if the tool is used successfully
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Gets the feedback of the tool
     * @return feedback
     */
    public String getFeedback() {
        return this.feedback;
    }

    /**
     * Gets the use success of the tool
     * @return success
     */
    public boolean getSuccess() {
        return this.success;
    }
}
