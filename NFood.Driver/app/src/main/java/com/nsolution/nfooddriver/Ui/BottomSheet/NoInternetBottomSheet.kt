package com.nsolution.nfooddriver.Ui.BottomSheet

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nsolution.nfooddriver.R
import kotlinx.android.synthetic.main.layout_bottom_sheet_no_internet.*

class NoInternetBottomSheet : BaseBottomSheet() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return LayoutInflater.from(context).inflate(R.layout.layout_bottom_sheet_no_internet, container)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initialView()
  }

  private fun initialView(){
    Glide
      .with(requireContext())
      .load(R.drawable.illustration_no_internet)
      .override(300, 200)
      .centerCrop()
      .into(image)

    settingButton.setOnClickListener {
      startActivity(Intent(Settings.ACTION_SETTINGS))
    }
  }
}