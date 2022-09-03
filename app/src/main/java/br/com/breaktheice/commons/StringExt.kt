package br.com.breaktheice.commons

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import java.io.Reader

inline fun <reified A> ResponseBody.asError(): A? {
    return runCatching { charStream().fromJson<A>() }.getOrNull()
}

inline fun <reified A> Reader.fromJson(): A {
    return Gson().fromJson(this, object : TypeToken<A>() {}.type)
}
