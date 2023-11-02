package cu.repository.service.dronewebservice.model.enums;

public enum Models {
    LIGHTWEIGHT("Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISERWEIGHT("Cruiserweight"),
    HEAVYWEIGHT("Heavyweight");

    private String value;

    Models(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
