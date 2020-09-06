package com.wipro.factsapp.data.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ServerResponse<T> : Serializable {
    var success = false
    var data: T? = null
        private set
    val status = 0

    fun setData(data: T) {
        this.data = data
    }

    override fun toString(): String {
        return "ServerResponse{" +
                ", data=" + data +
                "success=" + success +
                ", status=" + status +
                '}'
    }
}
