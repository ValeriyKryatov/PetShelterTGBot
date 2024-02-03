package PetShelterTGBot.theenumconstants;

public enum Animals {
    DOG("#dog"),
    CAT("#cat");

    private String title;

    Animals(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}