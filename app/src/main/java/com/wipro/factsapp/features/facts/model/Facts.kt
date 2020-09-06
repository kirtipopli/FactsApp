package com.wipro.factsapp.features.facts.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Facts(
    @get:JsonProperty("title")
    @set:JsonProperty("title")
    @JsonProperty("title")
    var title: String? = null,
    @get:JsonProperty("description")
    @set:JsonProperty("description")
    @JsonProperty("description")
    var description: String? = null,
    @get:JsonProperty("imageHref")
    @set:JsonProperty("imageHref")
    @JsonProperty("imageHref")
    var imageHref: String? = null
) : Serializable {
}