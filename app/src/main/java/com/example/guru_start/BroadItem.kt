package com.example.guru_start

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


data class BoardItem(val SemesterTextView: String, val QuestionTextView: String)

class BoardAdapter(val itemList: ArrayList<BoardItem>) :
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.question_itemlist, parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.SemesterTextView.text = itemList[position].SemesterTextView
        holder.QuestionTextView.text = itemList[position].QuestionTextView

        // 콘스트레인 클릭 이벤트 처리
        holder.constraintLayout.setOnClickListener {
            val context = holder.itemView.context
            // 하나의 질문답변 페이지로 전환
            val intent = Intent(context, A_page::class.java)

            // 데이터 전달
            intent.putExtra("content", currentItem.QuestionTextView)
            intent.putExtra("period", currentItem.SemesterTextView)

            // 화면 전환
            context.startActivity(intent)
        }

        // 답변하기 버튼 클릭 이벤트 처리
//        holder.AnswerButton.setOnClickListener {
//            val context = holder.itemView.context
//            val intent = Intent(context, A_regist::class.java)
//
//            // 데이터 전달
//            intent.putExtra("content", currentItem.QuestionTextView)
//            intent.putExtra("period", currentItem.SemesterTextView)
//
//            // 화면 전환
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }


    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val SemesterTextView = itemView.findViewById<TextView>(R.id.SemesterTextView)
        val QuestionTextView = itemView.findViewById<TextView>(R.id.QuestionTextView)
        val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.constraintLayout)
//        val AnswerButton: Button = itemView.findViewById(R.id.AnswerButton7)
    }
}
