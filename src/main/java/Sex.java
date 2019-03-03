public enum Sex {
    MALE("Мужской"),
    FEMALE("Женский");

    private String description;

    Sex(String description) {

        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
