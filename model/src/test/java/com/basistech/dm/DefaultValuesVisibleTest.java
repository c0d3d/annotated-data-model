package com.basistech.dm;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * Test that default values are rendered into the Json.
 */
public class DefaultValuesVisibleTest extends Assert {

    @Test
    public void checkVisible() throws Exception {
        Text text = new Text("George Washington slept here.");
        ListAttribute.Builder<EntityMention> mentionListBuilder = new ListAttribute.Builder<EntityMention>(EntityMention.class);
        //int startOffset, int endOffset, String entityType)
        EntityMention.Builder mentionBuilder = new EntityMention.Builder(0, 17, "politician");
        mentionBuilder.coreferenceChainId(-1); // -1 is the declared default.
        mentionListBuilder.add(mentionBuilder.build());
        text.getAttributes().put(EntityMention.class.getName(), mentionListBuilder.build());

        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, text); // serialize

        // now bring it back
        JsonNode tree = mapper.readTree(byteArrayOutputStream.toByteArray());
        // and navigate to the problem at hand.
        ObjectNode attrsNode = (ObjectNode) tree.get("attributes");
        ObjectNode mentionsNode = (ObjectNode) attrsNode.get(EntityMention.class.getName());
        ObjectNode mentionNode = (ObjectNode) mentionsNode.get("items").get(0);
        assertTrue(mentionNode.has("coreferenceChainId"));
    }
}