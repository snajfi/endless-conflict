package cz.endless.conflict.entities.communication;

import cz.endless.conflict.entities.Land;
import cz.endless.conflict.entities.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by dobeji1 on 16.04.2019.
 */

@NamedQueries({
@NamedQuery(name = Conversation.GET_CONVERSATION_BY_ID,
        query = "select c from Conversation c where c.id = :id")

})
@Table(name = "CONVERSATION")
@Entity
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String GET_CONVERSATION_BY_ID = "Conversation.GET_CONVERSATION_BY_ID";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SUBJECT")
    private String subject;

    @ManyToMany(mappedBy = "conversations", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Player> recipients;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(id, that.id);
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Player> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Player> recipients) {
        this.recipients = recipients;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
