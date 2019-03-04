package TP33.entities;

import TP33.entities.Livre;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.0.v20170811-rNA", date="2019-03-04T17:21:56")
@StaticMetamodel(Auteur.class)
public class Auteur_ { 

    public static volatile SingularAttribute<Auteur, Integer> num;
    public static volatile SingularAttribute<Auteur, String> nom;
    public static volatile CollectionAttribute<Auteur, Livre> livres;

}