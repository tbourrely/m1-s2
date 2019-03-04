package TP31.entities;

import TP31.entities.DetailsLivre;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.0.v20170811-rNA", date="2019-03-04T16:59:00")
@StaticMetamodel(Livre.class)
public class Livre_ { 

    public static volatile SingularAttribute<Livre, String> titre;
    public static volatile SingularAttribute<Livre, String> isbn;
    public static volatile SingularAttribute<Livre, DetailsLivre> details;

}