package com.example.guru_start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import kotlin.io.path.fileVisitor

class MyPostsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val MenuMyposts:ImageView=view.findViewById(R.id.menu_myposts)
        MenuMyposts.setOnClickListener{
            showMyPostsMenu(it)
        }
    }
    private fun showMyPostsMenu(view: View){
        val popupMenu=PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.menu_myposts)
        popupMenu.setOnMenuItemClickListener {
            true
        }
        popupMenu.show()
    }
}