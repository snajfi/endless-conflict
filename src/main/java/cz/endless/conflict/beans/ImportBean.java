package cz.endless.conflict.beans;

import cz.endless.conflict.services.ImportService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by dobeji1 on 12.04.2019.
 */

@Named
@ApplicationScoped
@ManagedBean(eager=true)
public class ImportBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject private ImportService importService;

    private boolean defaultDataImported = false;

    @PostConstruct
    public void init() {
        if (!isDefaultDataImported()) {
            importData();
        }
    }

    public void importData() {
        importService.importData();
        defaultDataImported = true;
    }

    public boolean isDefaultDataImported() {
        return defaultDataImported;
    }

    public void setDefaultDataImported(boolean defaultDataImported) {
        this.defaultDataImported = defaultDataImported;
    }
}
