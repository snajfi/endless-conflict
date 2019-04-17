package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.communication.Conversation;
import cz.endless.conflict.services.ConversationService;
import cz.endless.conflict.services.PlayerService;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dobeji1 on 17.04.2019.
 */
@Named
@ViewScoped
public class NewConversationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject private ConversationService conversationService;
    @Inject private PlayerService playerService;
    @Inject private LoggedPlayerBean loggedPlayerBean;

    private Conversation newConversation;
    private String message;

    @PostConstruct
    private void init() {
        newConversation = new Conversation();
        newConversation.setRecipients(new ArrayList<>());
        newConversation.setMessages(new ArrayList<>());
    }

    public List<Player> completePossibleRecipients(String query) {
        List<Player> filteredPlayers = new ArrayList<>();

        for (Player player : playerService.getPlayerByHintNickname(query)) {
            if (!loggedPlayerBean.getLoggedPlayer().equals(player) && !newConversation.getRecipients().contains(player)) {
                filteredPlayers.add(player);
            }
        }

        return filteredPlayers;
    }

    public String create() {
        newConversation.getRecipients().add(loggedPlayerBean.getLoggedPlayer());
        Conversation conversation = conversationService.createConversation(newConversation, message, loggedPlayerBean.getLoggedPlayer());
        return "/logged/conversation.xhtml?faces-redirect=true&id="+conversation.getId();
    }

    public void addRecipient(AjaxBehaviorEvent event) {
        newConversation.getRecipients().add((Player)((SelectEvent) event).getObject());
    }

    public Conversation getNewConversation() {
        return newConversation;
    }

    public void setNewConversation(Conversation newConversation) {
        this.newConversation = newConversation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
