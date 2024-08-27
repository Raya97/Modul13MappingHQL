package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The Client class represents an entity of a client in the database.
 * It is used to store and manage client information within the system.
 *
 * Hibernate annotations are used to map this class to the corresponding table in the database.
 * Lombok annotations (@Getter, @Setter) automatically generate getters and setters for all fields in the class.
 *
 * Implements:
 * - Serializable: Allows instances of this class to be serialized, which is useful for sending them over a network or saving them to files.
 */

@Entity  // Indicates that this class is an entity that Hibernate will map to a table in the database.
@Getter // Generates getters for all fields in the class.
@Setter // Generates setters for all fields in the class.
@Table(name = "client")  // Specifies that this class is mapped to a table named "client" in the database.
public class Client implements Serializable {

    /**
     * The unique identifier for the client.
     * It is automatically generated as the primary key for the table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the id value is automatically generated.
    private Long id;

    /**
     * The name of the client.
     * A required field with a maximum length of 200 characters.
     */
    @Column(name = "name", length = 200, nullable = false) // Maps this field to a column named "name" in the table.
    private String name;
}
