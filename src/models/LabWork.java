package models;

import java.util.Date;

/**
 * Основная сущность коллекции.
 * LabWork хранится в Hashtable и управляется приложением.
 */
public class LabWork implements Comparable<LabWork> {

    private int id; // > 0, уникальный, генерируется автоматически
    private String name; // not null, not empty
    private Coordinates coordinates; // not null
    private Date creationDate; // not null, генерируется автоматически
    private Integer minimalPoint; // null allowed, > 0 if not null
    private float personalQualitiesMinimum; // > 0
    private Difficulty difficulty; // not null
    private Person author; // not null

    public LabWork() {}

    public LabWork(int id, String name, Coordinates coordinates, Date creationDate,
                   Integer minimalPoint, float personalQualitiesMinimum,
                   Difficulty difficulty, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.personalQualitiesMinimum = personalQualitiesMinimum;
        this.difficulty = difficulty;
        this.author = author;
    }

    // --- getters / setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(Integer minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public float getPersonalQualitiesMinimum() {
        return personalQualitiesMinimum;
    }

    public void setPersonalQualitiesMinimum(float personalQualitiesMinimum) {
        this.personalQualitiesMinimum = personalQualitiesMinimum;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    /**
     * Сортировка по умолчанию:
     * сначала по personalQualitiesMinimum,
     * затем по id.
     */
    @Override
    public int compareTo(LabWork other) {
        int cmp = Float.compare(this.personalQualitiesMinimum, other.personalQualitiesMinimum);
        if (cmp == 0) {
            return Integer.compare(this.id, other.id);
        }
        return cmp;
    }

    @Override
    public String toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", personalQualitiesMinimum=" + personalQualitiesMinimum +
                ", difficulty=" + difficulty +
                ", author=" + author +
                '}';
    }
}