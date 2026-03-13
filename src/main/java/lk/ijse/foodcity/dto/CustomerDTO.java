package lk.ijse.foodcity.dto;

public class CustomerDTO {
    private int cus_id;
    private String name;
    private String contact;
    private String email;
    private String id;

    public CustomerDTO() {}

    public CustomerDTO(int cus_id, String name, String contact, String email, String id) {
        this.cus_id = cus_id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.id = id;
    }

    public int getCus_id() {
        return cus_id;
    }
    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}