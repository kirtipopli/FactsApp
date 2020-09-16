package com.wipro.factsapp.features.facts.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class FactsResponse(
    @get:JsonProperty("title")
    @set:JsonProperty("title")
    @JsonProperty("title")
    var title: String? = null,
    @get:JsonProperty("rows")
    @set:JsonProperty("rows")
    @JsonProperty("rows")
    var rows: MutableList<Facts?>? = null
) : Serializable {
}