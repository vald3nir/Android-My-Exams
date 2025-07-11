package com.vald3nir.android.firebase.data

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import org.json.JSONObject

object FirebaseDB {

    fun enableOfflineMode() {
        Firebase.database.setPersistenceEnabled(true)
    }

    fun insertOrUpdate(path: String, data: Any) {
        println("Firebase: Writing data to path: $path")
        println("Firebase: Data: $data")
        Firebase.database.getReference(path).setValue(data)
    }

    suspend fun readList(path: String): List<String?> {
        println("Firebase: Reading data from path: $path")
        val myRef = Firebase.database.getReference(path)
        val response = arrayListOf<String?>()
        val children = myRef.get().await().children
        children.forEach { item ->
            val data = item.value as Map<*, *>
            val json = JSONObject(data).toString()
            println("Firebase: Item: $json")
            response.add(json)
        }
        println("Firebase: Response: $response")
        return response
    }

    suspend fun readObject(path: String): String {
        println("Firebase: Reading data from path: $path")
        val myRef = Firebase.database.getReference(path)
        val item = myRef.get().await().value
        val data = item as Map<*, *>
        val response = JSONObject(data).toString()
        println("Firebase: Response: $response")
        return response
    }
}