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

package com.basistech.rosette.dm;

import com.basistech.util.ISO15924;
import com.basistech.util.LanguageCode;
import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test {@link Object#equals(Object)} and {@link Object#hashCode()} methods across the DM.
 * It seems clear that really comprehensive testing here should be automated somehow, either
 * with a code generator or some reflection. So, while the {@link #morphAnalysisBase()} is
 * manually comprehensive, the rest are focused on areas where we know that we have some
 * null pointer exposure. The first method could be thought of as a target for future automation.
 * <p/>
 * Note the extensive use of {@link org.junit.Assert#assertFalse} and {@link org.junit.Assert#assertTrue} in here.
 * First, this makes it explicit that we are, in fact, testing the result of {@link Object#equals(Object)}.
 * Then, in cases like {@code assertFalse(x1.hashCode() == x2.hashCode())}, neither value is 'correct', so
 * this seems more explicit to me than some use of {@code assertNotSame}.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class EqualsTest {

    /*
     * For testing morph analysis, assume that we can use quite basic tokens inside, since
     * we'll be testing token equality in detail further down.
     */
    private void componentList(MorphoAnalysis.Builder maBuilder, String ... strings) {
        for (String text : strings) {
            maBuilder.addComponent(new Token.Builder(0, text.length(), text).build());
        }
    }

    @Test
    public void morphAnalysisBase() throws Exception {
        MorphoAnalysis.Builder maBuilder = new MorphoAnalysis.Builder();
        componentList(maBuilder, "beam", "post");
        maBuilder.lemma("orange");
        maBuilder.partOfSpeech("woof");
        maBuilder.raw("cooked");
        MorphoAnalysis ma1 = maBuilder.build();

        assertTrue(ma1.equals(ma1));
        ma1.hashCode(); // please don't throw.

        maBuilder = new MorphoAnalysis.Builder();
        componentList(maBuilder, "door", "post");
        maBuilder.lemma("orange");
        maBuilder.partOfSpeech("woof");
        maBuilder.raw("cooked");
        MorphoAnalysis ma2 = maBuilder.build();
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());

        assertFalse(ma1.equals(ma2));

        maBuilder = new MorphoAnalysis.Builder();
        componentList(maBuilder, "beam", "post");
        maBuilder.lemma("pear");
        maBuilder.partOfSpeech("woof");
        maBuilder.raw("cooked");
        ma2 = maBuilder.build();
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());

        assertFalse(ma1.equals(ma2));

        maBuilder = new MorphoAnalysis.Builder();
        componentList(maBuilder, "beam", "post");
        maBuilder.lemma("orange");
        maBuilder.partOfSpeech("meow");
        maBuilder.raw("cooked");
        ma2 = maBuilder.build();
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());

        assertFalse(ma1.equals(ma2));

        maBuilder = new MorphoAnalysis.Builder();
        componentList(maBuilder, "beam", "post");
        maBuilder.lemma("orange");
        maBuilder.partOfSpeech("woof");
        maBuilder.raw("hide");
        ma2 = maBuilder.build();
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());

        assertFalse(ma1.equals(ma2));

        // ok, that's all the one-field-differences.

        // null tests

        maBuilder = new MorphoAnalysis.Builder();
        // components null
        maBuilder.lemma("orange");
        maBuilder.partOfSpeech("woof");
        maBuilder.raw("cooked");
        ma2 = maBuilder.build();
        assertNull(ma2.getComponents());
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());

        assertFalse(ma1.equals(ma2));
        assertFalse(ma2.equals(ma1));

        maBuilder = new MorphoAnalysis.Builder();
        componentList(maBuilder, "beam", "post");
        maBuilder.lemma(null);
        maBuilder.partOfSpeech("woof");
        maBuilder.raw("cooked");
        ma2 = maBuilder.build();
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());

        assertFalse(ma1.equals(ma2));
        assertFalse(ma2.equals(ma1));

        maBuilder = new MorphoAnalysis.Builder();
        componentList(maBuilder, "beam", "post");
        maBuilder.lemma("orange");
        maBuilder.partOfSpeech(null);
        maBuilder.raw("cooked");
        ma2 = maBuilder.build();
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());

        assertFalse(ma1.equals(ma2));
        assertFalse(ma2.equals(ma1));

        maBuilder = new MorphoAnalysis.Builder();
        componentList(maBuilder, "beam", "post");
        maBuilder.lemma("orange");
        maBuilder.partOfSpeech("woof");
        maBuilder.raw(null);
        ma2 = maBuilder.build();
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());

        assertFalse(ma1.equals(ma2));
        assertFalse(ma2.equals(ma1));
    }

    @Test
    public void hanMorphoAnalysis() throws Exception {
        HanMorphoAnalysis.Builder maBuilder = new HanMorphoAnalysis.Builder();
        maBuilder.addReading("r1");
        HanMorphoAnalysis ma1 = maBuilder.build();
        assertTrue(ma1.equals(ma1));
        ma1.hashCode();

        maBuilder = new HanMorphoAnalysis.Builder();
        maBuilder.addReading("r2");
        HanMorphoAnalysis ma2 = maBuilder.build();
        assertFalse(ma1.equals(ma2));
        assertFalse(ma2.equals(ma1));
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());

        maBuilder = new HanMorphoAnalysis.Builder();
        // leave reading null
        ma2 = maBuilder.build();
        assertNull(ma2.getReadings());
        assertFalse(ma1.equals(ma2));
        assertFalse(ma2.equals(ma1));
        ma2.hashCode();
        assertFalse(ma1.hashCode() == ma2.hashCode());
    }

    @Test
    public void token() throws Exception {
        Token.Builder tokBuilder = new Token.Builder(0, 10, "token");
        tokBuilder.addNormalized("norm");
        MorphoAnalysis.Builder maBuilder = new MorphoAnalysis.Builder();
        componentList(maBuilder, "beam", "post");
        maBuilder.lemma("orange");
        maBuilder.partOfSpeech("woof");
        maBuilder.raw("cooked");
        MorphoAnalysis ma1 = maBuilder.build();
        tokBuilder.addAnalysis(ma1);
        tokBuilder.source("nile");
        Token tok1 = tokBuilder.build();
        tok1.hashCode();
        assertTrue(tok1.equals(tok1));

        tokBuilder = new Token.Builder(0, 10, "token");
        // no normalized
        tokBuilder.addAnalysis(ma1);
        tokBuilder.source("nile");
        Token tok2 = tokBuilder.build();
        assertFalse(tok1.equals(tok2));
        assertFalse(tok2.equals(tok1));
        assertFalse(tok1.hashCode() == tok2.hashCode());

        tokBuilder = new Token.Builder(0, 10, "token");
        tokBuilder.addNormalized("norm");
        // no analysis
        tokBuilder.source("nile");
        tok2 = tokBuilder.build();
        assertFalse(tok1.equals(tok2));
        assertFalse(tok2.equals(tok1));
        assertFalse(tok1.hashCode() == tok2.hashCode());
    }

    @Test
    public void entityMention() throws Exception {
        EntityMention.Builder emBuilder = new EntityMention.Builder(0, 10, "something");
        emBuilder.confidence(Double.MIN_VALUE);
        emBuilder.coreferenceChainId(42);
        emBuilder.flags(3);
        emBuilder.source("nile");
        emBuilder.subsource("alexandria");
        emBuilder.normalized("ab");
        EntityMention em1 = emBuilder.build();
        em1.hashCode();
        assertTrue(em1.equals(em1));

        emBuilder = new EntityMention.Builder(0, 10, "something");
        // no confidence
        emBuilder.coreferenceChainId(42);
        emBuilder.flags(3);
        emBuilder.source("nile");
        emBuilder.subsource("alexandria");
        emBuilder.normalized("ab");
        EntityMention em2 = emBuilder.build();
        assertNull(em2.getConfidence());
        assertFalse(em1.equals(em2));
        assertFalse(em2.equals(em1));
        assertFalse(em1.hashCode() == em2.hashCode());

        emBuilder = new EntityMention.Builder(0, 10, "something");
        emBuilder.confidence(Double.MIN_VALUE);
        // no coref
        emBuilder.flags(3);
        emBuilder.source("nile");
        emBuilder.subsource("alexandria");
        emBuilder.normalized("ab");
        em2 = emBuilder.build();
        assertNull(em2.getCoreferenceChainId());
        assertFalse(em1.equals(em2));
        assertFalse(em2.equals(em1));
        assertFalse(em1.hashCode() == em2.hashCode());

        emBuilder = new EntityMention.Builder(0, 10, "something");
        emBuilder.confidence(Double.MIN_VALUE);
        emBuilder.coreferenceChainId(42);
        // no flags.
        emBuilder.source("nile");
        emBuilder.subsource("alexandria");
        emBuilder.normalized("ab");
        em2 = emBuilder.build();
        assertNull(em2.getFlags());
        assertFalse(em1.equals(em2));
        assertFalse(em2.equals(em1));
        assertFalse(em1.hashCode() == em2.hashCode());
    }

    @Test
    public void languageDetection() throws Exception {
        LanguageDetection.DetectionResult.Builder lddrBuilder = new LanguageDetection.DetectionResult.Builder(LanguageCode.ESTONIAN);
        lddrBuilder.encoding("aes");
        lddrBuilder.script(ISO15924.Armn);
        lddrBuilder.confidence(Double.MIN_VALUE);
        LanguageDetection.DetectionResult dr1 = lddrBuilder.build();
        dr1.hashCode();
        assertTrue(dr1.equals(dr1));

        lddrBuilder = new LanguageDetection.DetectionResult.Builder(LanguageCode.ESTONIAN);
        lddrBuilder.encoding("aes");
        lddrBuilder.script(ISO15924.Armn);
        // no confidence
        LanguageDetection.DetectionResult dr2 = lddrBuilder.build();
        assertNull(dr2.getConfidence());
        assertFalse(dr1.equals(dr2));
        assertFalse(dr2.equals(dr1));
        assertFalse(dr1.hashCode() == dr2.hashCode());
    }

    @Test
    public void testArabicMorphoBuilderReuse() throws Exception {
        // COMN-101
        ArabicMorphoAnalysis.Builder builder = new ArabicMorphoAnalysis.Builder();
        ArabicMorphoAnalysis analysis1 = builder.build();
        Token token = new Token.Builder(0, 10, "token").build();
        builder.addComponent(token);
        ArabicMorphoAnalysis analysis2 = builder.build();
        assertFalse(analysis1.equals(analysis2));
    }

    @Test
    public void testArabicMorphoToStringIncludesBaseClass() throws Exception {
        // COMN-101
        ArabicMorphoAnalysis.Builder builder = new ArabicMorphoAnalysis.Builder();
        Token token = new Token.Builder(0, 10, "token").build();
        builder.addComponent(token);
        ArabicMorphoAnalysis analysis = builder.build();
        assertTrue(analysis.toString().contains("components"));
    }
}