package com.wipro.factsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.util.*
import javax.inject.Singleton

@Singleton
class NetworkHelper constructor(private val context: Context) {

    companion object {
        private const val TAG = "NetworkHelper"
    }

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }

    fun castToNetworkError(throwable: Throwable): NetworkError {
        val defaultNetworkError = NetworkError()
        var httpException: HttpException? = null
        var errorMessage: String = ""
        var noContentExceptionCode = 201

        try {
            if (throwable is ConnectException) return NetworkError(0, "0")
            if (throwable is HttpException) {
                httpException = throwable
                if (httpException.code() == 400) {
                    errorMessage = "BAD Request"
                    return NetworkError(httpException.code(), errorMessage)
                } else if (httpException.code() == 403)
                    return NetworkError(httpException.code(), errorMessage)
                else if (httpException.code() == 404) {
                    errorMessage = "Not Found"
                    return NetworkError(httpException.code(), errorMessage)
                } else if (httpException.code() == 500) {
                    errorMessage = "Internal Server Error"
                    return NetworkError(httpException.code(), errorMessage)
                } else if (httpException.code() == 502) {
                    errorMessage = "BAD Gateway"
                    return NetworkError(httpException.code(), errorMessage)
                } else if (httpException.code() == 204) {
                    errorMessage = "No Content"
                    return NetworkError(httpException.code(), errorMessage)
                } else return defaultNetworkError
            }

            if (throwable is NoSuchElementException) {
                errorMessage = "No Content"
                return NetworkError(noContentExceptionCode, errorMessage)
            }
            val error = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .fromJson(throwable.message.toString(), NetworkError::class.java)
            return httpException?.code()
                ?.let { NetworkError(it, error.statusCode, errorMessage) }!!
        } catch (e: IOException) {
            Log.e(TAG, e.toString())
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, e.toString())
        } catch (e: NullPointerException) {
            Log.e(TAG, e.toString())
        }
        return defaultNetworkError
    }
}