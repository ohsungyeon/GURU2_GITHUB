package com.example.guru_start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.fragment.app.Fragment

class QnAFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_qna, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val MenuQnA: ImageView =view.findViewById(R.id.menu_qna)
        MenuQnA.setOnClickListener{
            showQnAMenu(it)
        }
    }
    private fun showQnAMenu(view:View){
        val popupMenu= PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.menu_qna)
        popupMenu.setOnMenuItemClickListener {
            true
        }
        popupMenu.show()
    }
}