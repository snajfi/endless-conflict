package cz.endless.conflict.services;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.communication.Conversation;
import cz.endless.conflict.entities.communication.Message;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dobeji1 on 17.04.2019.
 */
@Stateless
public class ConversationServiceImpl implements ConversationService {

    @PersistenceContext(unitName = "endless_conflict")
    private EntityManager entityManager;

    @Override
    public Conversation createConversation(Conversation conversation, String messageText, Player sender) {
        List<Message> messages = new ArrayList<>();
        entityManager.persist(conversation);
        for(Player player : conversation.getRecipients()) {
            Message message = new Message();
            message.setText(messageText);
            message.setDate(new Date());
            message.setConversation(conversation);
            message.setRead(false);
            message.setSender(sender);
            message.setOwner(player);
            messages.add(message);
            player.getConversations().add(conversation);
        }
        conversation.getMessages().addAll(messages);
        return entityManager.merge(conversation);
    }

    @Override
    public Conversation sendMessage(Conversation conversation, String messageText, Player sender) {
        List<Message> messages = new ArrayList<>();
        for(Player player : conversation.getRecipients()) {
            Message message = new Message();
            message.setText(messageText);
            message.setDate(new Date());
            message.setConversation(conversation);
            message.setRead(false);
            message.setSender(sender);
            message.setOwner(player);
            messages.add(message);
        }
        conversation.getMessages().addAll(messages);
        return entityManager.merge(conversation);
    }

    @Override
    public Conversation getConversationById(Long id) {
        TypedQuery<Conversation> query = entityManager.createNamedQuery(Conversation.GET_CONVERSATION_BY_ID,Conversation.class);
        query.setParameter("id", id);
        List<Conversation> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<Message> getMessagesForConversationAndOwner(Conversation conversation, Player owner) {
        TypedQuery<Message> query = entityManager.createNamedQuery(Message.GET_MESSAGES_FOR_CONVERSATION_AND_OWNER,Message.class);
        query.setParameter("conversation", conversation);
        query.setParameter("owner", owner);
        return query.getResultList();
    }

    @Override
    public Message getLastMessageForConversationAndOwner(Conversation conversation, Player owner) {
        TypedQuery<Message> query = entityManager.createNamedQuery(Message.GET_MESSAGES_FOR_CONVERSATION_AND_OWNER,Message.class);
        query.setParameter("conversation", conversation);
        query.setParameter("owner", owner);
        return query.setMaxResults(1).getSingleResult();
    }
}
