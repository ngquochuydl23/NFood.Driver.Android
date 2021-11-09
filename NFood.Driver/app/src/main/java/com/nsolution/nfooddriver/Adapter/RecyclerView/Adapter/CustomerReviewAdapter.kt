package com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter


import android.view.ViewGroup
import com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder.CustomerReviewHolder
import com.nsolution.nfooddriver.Models.ItemCustomerReviewDto
import com.nsolution.nfooddriver.R

class CustomerReviewAdapter(val listReview: ArrayList<ItemCustomerReviewDto>?) :
  BaseAdapter<CustomerReviewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerReviewHolder {
    val view = getView(parent, viewType, R.layout.item_customer_review)
    return CustomerReviewHolder(view)
  }

  override fun onBindViewHolder(holder: CustomerReviewHolder, position: Int) {
    val itemReview = listReview?.get(position)

    holder.commentContent.text = itemReview?.commentContent
    holder.createdOn.text = itemReview?.createdOn
  }

  override fun getItemCount(): Int {
    return getSizeList(listReview)
  }
}