package a3.student.finder.datasource

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.util.Objects

object FirebaseHelper {
    suspend fun getDoc(collection: String, doc: String): MutableMap<String, Any>? {
        return try {
            Firebase.firestore.collection(collection).document(doc).get().await().data
        } catch (e: Exception) {
            null
        }
    }
}