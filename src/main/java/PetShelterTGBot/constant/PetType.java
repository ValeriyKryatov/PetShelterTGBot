package PetShelterTGBot.constant;

public enum PetType {
    DOG("#dog"),
    CAT("#cat");

    private final String title;

    PetType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}