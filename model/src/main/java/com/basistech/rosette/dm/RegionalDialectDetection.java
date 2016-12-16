/*
* Copyright 2014 Basis Technology Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.basistech.rosette.dm;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RegionalDialectDetection extends Attribute implements Serializable {

    /**
     * A single result from a dialect detection. The dialect identifier will produce multiple
     * identifications, each with a score and relative error.
     */
    public static class DialectDetectionResult extends BaseAttribute {
        private String countryCode;
        private String countryName;
        private double score;
        private double relativeError;

        protected DialectDetectionResult(String countryCode,
                                         String countryName,
                                         double score,
                                         double relativeError,
                                         Map<String, Object> extendedProperties) {
            super(extendedProperties);
            this.countryCode = countryCode;
            this.countryName = countryName;
            this.score = score;
            this.relativeError = relativeError;


        }

        /**
         * Gets the country code for this detection.
         *
         * @return The country code.
         */
        public String getCountryCode() {
            return countryCode;
        }

        /**
         * Gets the country name for this detection.
         *
         * @return The country name.
         */
        public String getCountryName() {
            return countryName;
        }

        /**
         * Gets the score for this detection.
         *
         * @return The score.
         */
        public double getScore() {
            return score;
        }

        /**
         * Gets the relative error for this detection.
         *
         * @return The relative error.
         */
        public double getRelativeError() {
            return relativeError;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }

            DialectDetectionResult that = (DialectDetectionResult) o;

            if (Double.compare(that.score, score) != 0) {
                return false;
            }
            if (Double.compare(that.relativeError, relativeError) != 0) {
                return false;
            }
            if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) {
                return false;
            }
            return countryName != null ? countryName.equals(that.countryName) : that.countryName == null;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            long temp;
            result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
            result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
            temp = Double.doubleToLongBits(score);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(relativeError);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        protected Objects.ToStringHelper toStringHelper() {
            return super.toStringHelper()
                    .add("countryCode", countryCode)
                    .add("countryName", countryName)
                    .add("score", score)
                    .add("relativeError", relativeError);
        }

        /**
         * Builder for {@link DialectDetectionResult}.
         */
        public static class Builder extends BaseAttribute.Builder<RegionalDialectDetection.DialectDetectionResult, RegionalDialectDetection.DialectDetectionResult.Builder> {


            private double relativeError;
            private double score;


            private String countryCode;
            private String countryName;

            /**
             * Construct a builder initialized with all the default values.
             */
            public Builder() {
                // Nothing
            }

            /**
             * Construct a builder initialized from the existing {@link DialectDetectionResult}
             *
             * @param toCopy the result to copy
             * @adm.ignore
             */
            public Builder(DialectDetectionResult toCopy) {
                super(toCopy);
                relativeError = toCopy.getRelativeError();
                score = toCopy.getScore();
                countryCode = toCopy.getCountryCode();
                countryName = toCopy.getCountryName();
            }

            /**
             * Specifies the relative error
             *
             * @param relativeError the relative error
             * @return this
             */
            public Builder relativeError(double relativeError) {
                this.relativeError = relativeError;
                return this;
            }

            /**
             * Specifies the score
             *
             * @param score the score
             * @return this
             */
            public Builder score(double score) {
                this.score = score;
                return this;
            }

            /**
             * Specifies the country code
             *
             * @param countryCode the country code
             * @return this
             */
            public Builder countryCode(String countryCode) {
                this.countryCode = countryCode;
                return this;
            }

            /**
             * Specifies the countryName
             *
             * @param countryName the country name
             * @return this
             */
            public Builder countryName(String countryName) {
                this.countryName = countryName;
                return this;
            }

            /**
             * Build an immutable {@link DialectDetectionResult} from the current state of the builder
             *
             * @return the dialect detection result.
             */
            public DialectDetectionResult build() {
                return new DialectDetectionResult(countryCode, countryName, score, relativeError, buildExtendedProperties());
            }

            @Override
            protected Builder getThis() {
                return this;
            }
        }

    }

    private final List<DialectDetectionResult> dialectResults;

    protected RegionalDialectDetection(int startOffset,
                                       int endOffset,
                                       List<DialectDetectionResult> dialectResults,
                                       Map<String, Object> extendedProperties) {
        super(startOffset, endOffset, extendedProperties);
        this.dialectResults = listOrNull(dialectResults);
    }

    /**
     * Gets this detection's individual {@link DialectDetectionResult}. The returned results will be ordered from
     * highest to lowest score.
     *
     * @return This detection's results, or null if there are none.
     */
    public List<DialectDetectionResult> getDialectResults() {
        return dialectResults;
    }

    @Override
    protected Objects.ToStringHelper toStringHelper() {
        return super.toStringHelper()
                .add("dialectResults", dialectResults);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        RegionalDialectDetection that = (RegionalDialectDetection) o;

        return dialectResults != null ? dialectResults.equals(that.dialectResults) : that.dialectResults == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dialectResults != null ? dialectResults.hashCode() : 0);
        return result;
    }

    /**
     * A builder for {@link RegionalDialectDetection}
     */
    public static class Builder extends Attribute.Builder<RegionalDialectDetection, RegionalDialectDetection.Builder> {
        private List<DialectDetectionResult> dialectResults;

        /**
         * Construct a builder from the required properties
         *
         * @param startOffset The start offset of the region in characters.
         * @param endOffset   The stop offset of the region in characters.
         * @param results     The list of {@link DialectDetectionResult}
         */
        public Builder(int startOffset, int endOffset, List<DialectDetectionResult> results) {
            super(startOffset, endOffset);
            this.dialectResults = results;
        }

        /**
         * Construct a builder by copying its initial values from the given {@link RegionalDialectDetection}.
         *
         * @param toCopy The detection to copy from.
         */
        public Builder(RegionalDialectDetection toCopy) {
            super(toCopy);
            dialectResults = Lists.newArrayList();
            addAllToList(dialectResults, toCopy.getDialectResults());
        }

        /**
         * Construct an immutable {@link RegionalDialectDetection} from the current state of this builder.
         *
         * @return the detection.
         */
        public RegionalDialectDetection build() {
            return new RegionalDialectDetection(startOffset, endOffset, dialectResults, buildExtendedProperties());
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }


}
