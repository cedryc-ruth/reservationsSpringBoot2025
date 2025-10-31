package be.iccbxl.pid.reservations_springboot.model;

public enum Language {
    EN("Anglais"),
    FR("Français"),
    NL("Néerlandais");

    private String description;

    private Language(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
