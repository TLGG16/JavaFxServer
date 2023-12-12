module Server {
    requires org.hibernate.orm.core;
    requires com.google.gson;
    requires java.naming;
    requires static jdk.unsupported;
    requires java.sql;
    requires jakarta.persistence;

    opens condorcet.Utility to org.hibernate.orm.core, com.google.gson;
    opens condorcet.Models.Entities to org.hibernate.orm.core, com.google.gson;
    opens condorcet.Models.TCP to org.hibernate.orm.core, com.google.gson;
    opens condorcet.Enums to org.hibernate.orm.core, com.google.gson;
    opens condorcet.Services to org.hibernate.orm.core, com.google.gson;


    exports condorcet.Services;
    exports condorcet.Models.Entities;
}