package cloud.shanu.dhanu;

import jakarta.persistence.*;

@Entity
public class Thought {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 1000)
    private String text;
    public Thought() {}

    public Thought(String name, String text) {
        this.name = name;
        this.text = text;
    }

    // getters / setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getText() { return text; }
    public void setName(String name) { this.name = name; }
    public void setText(String text) { this.text = text; }
}
