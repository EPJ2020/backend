package ch.LFG.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Event")
@Table(name = "groupprofil")
//@TypeDef(   //findets noch nicht?
//        name = "list-array",
//        typeClass = ListArrayType.class
//)
public class GroupProfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    @OneToOne(optional=false)
    @JoinColumn(name="id")
    @Column(name="ownerid")
    private User owner;

    private String name;
    private String description;
    @Column(name="isactive")
    private boolean isActive;

    // See Anleitung https://vladmihalcea.com/how-to-map-java-and-sql-arrays-with-jpa-and-hibernate/  er hat auch noch eine Anleitung für eine Liste anstatt Array? (siehe Kommentar oben, findet das aber noch nicht in der dependency drin
    @Type(type = "string-array" )
    @Column(
            name = "tags",
            columnDefinition = "text[]"
    )
    private List<String> tags;

    public GroupProfil() {}

}
