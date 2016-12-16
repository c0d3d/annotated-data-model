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

package com.basistech.rosette.dm.jackson.array;

import com.basistech.rosette.dm.RegionalDialectDetection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Map;

/**
 * {@link com.basistech.rosette.dm.RegionalDialectDetection}
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder(alphabetic = true)
public abstract class RegionalDialectDetectionArrayMixin {

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonPropertyOrder(alphabetic = true)
    public abstract static class DialectDetectionResultMixin {

        @JsonCreator
        public DialectDetectionResultMixin(@JsonProperty("countryCode") String countryCode,
                                           @JsonProperty("countryName") String countryName,
                                           @JsonProperty("score") double score,
                                           @JsonProperty("relativeError") double relativeError,
                                           @JsonProperty("extendedProperties") Map<String, Object> extendedProperties) {
            //
        }
    }

    @JsonCreator
    RegionalDialectDetectionArrayMixin(@JsonProperty("startOffset") int startOffset,
                                       @JsonProperty("stopOffset") int endOffset,
                                       @JsonProperty("dialectResults")
                                               List<RegionalDialectDetection.DialectDetectionResult> dialectResults,
                                       @JsonProperty("extendedProperties")
                                               Map<String, Object> extendedProperties) {
        //
    }
}
