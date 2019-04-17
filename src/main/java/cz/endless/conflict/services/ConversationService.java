package cz.endless.conflict.services;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.communication.Conversation;
import cz.endless.conflict.entities.communication.Message;

import java.util.List;

/**
 * Created by dobeji1 on 17.04.2019.
 */

public interface ConversationService {

    Conversation createConversation(Conversation conversation, String messageText, Player sender);

    Conversation sendMessage(Conversation conversation, String messageText, Player sender);

    Conversation getConversationById(Long id);

    List<Message> getMessagesForConversationAndRecipient(Conversation conversation, Player recipient);

    Message getLastMessageForConversationAndRecipient(Conversation conversation, Player recipient);

}
