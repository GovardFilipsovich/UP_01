package com.example.retrfot_task3

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Decoration: RecyclerView.ItemDecoration() {
    private val left = 0
    private val top = 50
    private val right = 0
    private val bottom = 0
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(left,top,right, bottom)
        //super.getItemOffsets(outRect, view, parent, state)
    }
}