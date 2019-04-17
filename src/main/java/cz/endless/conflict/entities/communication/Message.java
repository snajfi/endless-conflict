package cz.endless.conflict.entities.communication;

import cz.endless.conflict.entities.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by dobeji1 on 17.04.2019.
 */
@NamedQueries({
        @NamedQuery(name = Message.GET_MESSAGES_FOR_CONVERSATION_AND_RECIPIENT,
                query = "select m from Message m where m.conversation = :conversation and m.recipient = :recipient order by m.date desc ")

})
@Table(name = "MESSAGE")
@Entity
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String GET_MESSAGES_FOR_CONVERSATION_AND_RECIPIENT = "Message.GET_MESSAGES_FOR_CONVERSATION_AND_RECIPIENT";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CONVERSATION_ID")
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SENDER_ID")
    private Player sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPIENT_ID")
    private Player recipient;

    @Column(name = "DATE")
    private Date date;

    @Lob
    @Column(length = 10000, name = "TEXT")
    private String text;

    @Column(name = "IS_READ")
    private boolean isRead;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public Player getRecipient() {
        return recipient;
    }

    public void setRecipient(Player recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
