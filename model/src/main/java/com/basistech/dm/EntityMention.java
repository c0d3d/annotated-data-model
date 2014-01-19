/******************************************************************************
 ** This data and information is proprietary to, and a valuable trade secret
 ** of, Basis Technology Corp.  It is given in confidence by Basis Technology
 ** and may only be used as permitted under the license agreement under which
 ** it has been distributed, and in no other way.
 **
 ** Copyright (c) 2014 Basis Technology Corporation All rights reserved.
 **
 ** The technical data and information provided herein are provided with
 ** `limited rights', and the computer software provided herein is provided
 ** with `restricted rights' as those terms are defined in DAR and ASPR
 ** 7-104.9(a).
 ******************************************************************************/

package com.basistech.dm;

/**
 * An entity
 */
public class EntityMention extends Attribute {
    private final String entityType;
    private final double confidence;
    private final int coreferenceChainId;
    private final int flags;
    private final String source;

    public EntityMention(int startOffset, int endOffset,
                         String entityType, int coreferenceChainId, double confidence, int flags,
                         String source) {
        super(EntityMention.class.getName(), startOffset, endOffset);
        this.entityType = entityType;
        this.confidence = confidence;
        this.coreferenceChainId = coreferenceChainId;
        this.flags = flags;
        this.source = source;
    }

    public String getEntityType() {
        return entityType;
    }

    public double getConfidence() {
        return confidence;
    }

    public int getCoreferenceChainId() {
        return coreferenceChainId;
    }

    public int getFlags() {
        return flags;
    }

    public String getSource() {
        return source;
    }

    /**
     * Fluent builder to deal with all these annoying facts.
     */
    public static class Builder extends Attribute.Builder {
        private String entityType;
        private double confidence;
        private int coreferenceChainId;
        private int flags;
        private String source;

        public Builder(int startOffset, int endOffset, String entityType) {
            super(EntityMention.class.getName(), startOffset, endOffset);
            this.entityType = entityType;
        }

        public Builder(EntityMention toCopy) {
            super(toCopy);
            this.entityType = toCopy.entityType;
            this.confidence = toCopy.confidence;
            this.coreferenceChainId = toCopy.coreferenceChainId;
            this.flags = toCopy.flags;
            this.source = toCopy.source;
        }

        public void entityType(String entityType) {
            this.entityType = entityType;
        }

        public void confidence(double confidence) {
            this.confidence = confidence;
        }

        public void coreferenceChainId(int coreferenceChainId) {
            this.coreferenceChainId = coreferenceChainId;
        }

        public void flags(int flags) {
            this.flags = flags;
        }

        public void source(String source) {
            this.source = source;
        }

        public EntityMention build() {
            EntityMention mention = new EntityMention(startOffset, endOffset, entityType, coreferenceChainId, confidence, flags, source);
            mention.extendedProperties.putAll(this.extendedProperties);
            return mention;
        }
    }
}