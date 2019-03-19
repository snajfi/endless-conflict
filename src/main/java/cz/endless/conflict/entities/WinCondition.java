package cz.endless.conflict.entities;

import cz.endless.conflict.enums.WinConditionType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by dobeji1 on 18.03.2019.
 */

@Table(name = "WIN_CONDITION")
@Entity
public class WinCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(targetClass=WinConditionType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable
    List<WinConditionType> winConditionTypes;

}
