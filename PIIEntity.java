package com.example.comment;

import java.io.Serializable;
import java.util.Objects;

public class PIIEntity implements Serializable {

    public float score;
    public String entityType;
    public int beginOffSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PIIEntity piiEntity = (PIIEntity) o;

        if (Float.compare(piiEntity.score, score) != 0) return false;
        if (beginOffSet != piiEntity.beginOffSet) return false;
        if (endOffSet != piiEntity.endOffSet) return false;
        return Objects.equals(entityType, piiEntity.entityType);
    }

    @Override
    public int hashCode() {
        int result = (score != +0.0f ? Float.floatToIntBits(score) : 0);
        result = 31 * result + (entityType != null ? entityType.hashCode() : 0);
        result = 31 * result + beginOffSet;
        result = 31 * result + endOffSet;
        return result;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public void setBeginOffSet(int beginOffSet) {
        this.beginOffSet = beginOffSet;
    }

    public void setEndOffSet(int endOffSet) {
        this.endOffSet = endOffSet;
    }

    public float getScore() {
        return score;
    }

    public String getEntityType() {
        return entityType;
    }

    public int getBeginOffSet() {
        return beginOffSet;
    }

    public int getEndOffSet() {
        return endOffSet;
    }

    public int endOffSet;

    public PIIEntity(float score, String entityType, int beginOffSet, int endOffSet) {
        this.score = score;
        this.entityType = entityType;
        this.beginOffSet = beginOffSet;
        this.endOffSet = endOffSet;
    }
}
