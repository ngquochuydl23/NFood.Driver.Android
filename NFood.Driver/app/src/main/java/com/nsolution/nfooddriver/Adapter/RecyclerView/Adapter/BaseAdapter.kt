package com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nsolution.nfooddriver.Utils.Image

abstract class BaseAdapter<T : RecyclerView.ViewHolder?> : RecyclerView.Adapter<T>() {

  lateinit var context: Context
  val EmptyList: Int = 0

  open fun getView(parent: ViewGroup, viewType: Int, layout: Int): View {
    context = parent.context
    return LayoutInflater.from(context).inflate(layout, parent, false)
  }

  fun setImage(imageView: ImageView, url: String?) {
    Image(context).setImage(imageView, url)
  }

  fun <Model> getSizeList(list: ArrayList<Model>?): Int {
    if (!list.isNullOrEmpty())
      return list.size
    return EmptyList
  }

  fun <Model> removeItemAt(list: ArrayList<Model>?, position: Int) {
    list?.removeAt(position)
    notifyDataSetChanged()
  }

  fun <Model> addNewItem(list: ArrayList<Model>?, newItem: Model) {
    list?.add(newItem)
    notifyDataSetChanged()
  }
}