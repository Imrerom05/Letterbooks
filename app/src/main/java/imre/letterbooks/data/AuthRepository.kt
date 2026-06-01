package imre.letterbooks.data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import imre.letterbooks.data.modul.User



class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(
                email,
                password
            ).await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(
        email: String,
        password: String,
        username: String
    ): Result<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(
                email,
                password
            ).addOnSuccessListener {

                val uid = auth.currentUser!!.uid

                val user = User(
                    uid = uid,
                    username = username,
                    email = email
                )

                db.collection("users")
                    .document(uid)
                    .set(user)
            }

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun currentUser() = auth.currentUser
}