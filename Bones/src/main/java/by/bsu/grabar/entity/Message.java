package by.bsu.grabar.entity;


import java.time.LocalDateTime;

/**
 * The type Message.
 */
public class Message {
    private int id;
    private int userId;
    private String text;
    private LocalDateTime date;

    /**
     * Instantiates a new Message.
     */
    public Message() {
        this.userId = 0;
        this.text = "";
        this.date = LocalDateTime.now();
    }

    /**
     * Instantiates a new Message.
     *
     * @param user_id the user id
     * @param text    the text
     */
    public Message(int user_id, String text) {
        this.userId = user_id;
        this.text = text;
        this.date = LocalDateTime.now();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (userId != message.userId) return false;
        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        return date != null ? date.equals(message.date) : message.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
