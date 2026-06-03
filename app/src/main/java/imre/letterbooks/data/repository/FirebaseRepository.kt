package imre.letterbooks.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import imre.letterbooks.data.modul.User

class FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    fun getUser(onResult: (User?) -> Unit) {

        val uid = auth.currentUser?.uid ?: run {
            onResult(null)
            return
        }

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                onResult(user)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }


    fun observeUser(onUpdate: (User?) -> Unit) {

        val uid = auth.currentUser?.uid ?: run {
            onUpdate(null)
            return
        }

        db.collection("users")
            .document(uid)
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) {
                    onUpdate(null)
                    return@addSnapshotListener
                }

                val user = snapshot.toObject(User::class.java)
                onUpdate(user)
            }
    }
}