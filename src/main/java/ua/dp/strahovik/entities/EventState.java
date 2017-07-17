package ua.dp.strahovik.entities;


public enum EventState {
    WAITING ("Waiting"),
    IN_PROGRESS ("In progress"),
    DONE ("Done");

    public final String label;
    EventState(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
