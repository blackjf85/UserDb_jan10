package com.example.userdb_jan10

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.userdb_jan10.model.local.user.UserDatabase
import com.example.userdb_jan10.model.repository.UserRepository
import com.example.userdb_jan10.viewmodel.UserViewModel

class UserFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels {
        UserViewModel.Factory(
            UserRepository(
                UserDatabase.getDatabase(requireActivity().applicationContext).userDao()
            )
        )
    }


}