package ru.geekbrain.android.myloginapp.data.userrepo

import ru.geekbrain.android.mvp_mvvm.domain.UserRepo
import ru.geekbrain.android.myloginapp.domain.entities.UserProfile

class RoomUserRepoImpl : UserRepo {
    override fun addUser(user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): List<UserProfile> {
        TODO("Not yet implemented")
    }

    override fun changeUser(id: String, user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}