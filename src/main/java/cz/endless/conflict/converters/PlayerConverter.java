package cz.endless.conflict.converters;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.services.PlayerService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by dobeji1 on 17.04.2019.
 */
@Named
public class PlayerConverter implements Converter {

    @Inject private PlayerService playerService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Player player = null;
        if (s!=null && !s.isEmpty()) {
            player = playerService.getPlayerByNickname(s);
        }
        return player;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Player) {
            return ((Player) o ).getNickname();
        }
        return "";
    }
}
