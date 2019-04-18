package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.communication.Conversation;
import cz.endless.conflict.entities.communication.Message;
import cz.endless.conflict.services.ConversationService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dobeji1 on 17.04.2019.
 */
@Named
@ViewScoped
public class ConversationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject private FacesContext facesContext;
    @Inject private ConversationService conversationService;
    @Inject private LoggedPlayerBean loggedPlayerBean;

    private Conversation conversation;
    private String message;

    @PostConstruct
    private void searchForParameter() {
        Map<String, String> parameterMap = facesContext.getExternalContext().getRequestParameterMap();
        String id = parameterMap.get("id");

        if (id!=null) {
            conversation = conversationService.getConversationById(Long.valueOf(id));
        }
    }

    public List<Message> getMessagesForConversationAndRecipient() {
        return conversationService.getMessagesForConversationAndRecipient(conversation, loggedPlayerBean.getLoggedPlayer());
    }

    public String formatRecipients() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player recipient : conversation.getRecipients()) {
            stringBuilder.append(recipient.getNickname());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString().substring(0, stringBuilder.length()-2);
    }

    public boolean isUserAllowedToReadConversation() {
        return conversation.getRecipients().contains(loggedPlayerBean.getLoggedPlayer());
    }

    public Message getLastMessageInConversation(Conversation conversation) {
        return conversationService.getLastMessageForConversationAndRecipient(conversation, loggedPlayerBean.getLoggedPlayer());
    }

    public void send() {
        conversationService.sendMessage(conversation, message, loggedPlayerBean.getLoggedPlayer());
        message="";
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
