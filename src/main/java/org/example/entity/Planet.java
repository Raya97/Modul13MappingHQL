package org.example.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

/**
 * The Planet class represents an entity of a planet in the database.
 * It is used to store and manage information about planets within the system.
 *
 * Hibernate annotations are used to map this class to the corresponding table in the database.
 * Lombok annotations (@Getter, @Setter) automatically generate getters and setters for all fields in the class.
 *
 * Implements:
 * - Serializable: Allows instances of this class to be serialized, which is useful for sending them over a network or saving them to files.
 */

@Entity // Indicates that this class is an entity that Hibernate will map to a table in the database.
@Getter // Generates getters for all fields in the class.
@Setter // Generates setters for all fields in the class.
@Table(name = "planet") // Specifies that this class is mapped to a table named "planet" in the database.
public class Planet implements Serializable {

    /**
     * The unique identifier for the planet.
     * It is a required field with a maximum length of 10 characters.
     * The identifier is typically a string consisting of uppercase letters and numbers (e.g., "MARS", "VEN").
     */
    @Id
    @Column(name = "id", length = 10, nullable = false)  // Maps this field to a column named "id" in the table.
    private String id;

    /**
     * The name of the planet.
     * A required field with a maximum length of 500 characters.
     */
    @Column(name = "name", length = 500, nullable = false) // Maps this field to a column named "name" in the table.
    private String name;
}
