public class ToolStatus {
    private String feedback;
    private boolean success;

    public ToolStatus() {
        this.success = false;
        this.feedback = null;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public boolean getSuccess() {
        return this.success;
    }
}
