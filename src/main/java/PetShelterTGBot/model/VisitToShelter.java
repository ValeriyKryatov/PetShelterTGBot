package PetShelterTGBot.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "visit_to_shelter")
public class VisitToShelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private long chatId;
    @Column(name = "name_user")
    private String nameUser;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    private int status;
    @Column(name = "telephone")
    private long telephone;
    @Column(name = "text")
    private String text;

    public VisitToShelter(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTelephone() {
        return telephone; }

    public void setTelephone(long telephone) {
        this.telephone = telephone; }
}