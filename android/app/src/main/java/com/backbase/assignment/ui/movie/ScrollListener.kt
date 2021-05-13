package com.backbase.assignment.ui.movie

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.ui.MainActivityViewModel

class ScrollListener(var rv: RecyclerView, val vm : MainActivityViewModel) : RecyclerView.OnScrollListener() {
    var load = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if(dy>0){
            val visibleItemCount = rv.layoutManager!!.childCount
            val totalItemCount = rv.layoutManager!!.itemCount
            val pastVisibleItems = (rv.layoutManager!! as LinearLayoutManager).findLastVisibleItemPosition()
            if(vm.dataLoading.value == false){
                if((visibleItemCount+pastVisibleItems)>= totalItemCount){
                    load = false
                    vm.getMostPopular()
                }
            }
        }
    }
}