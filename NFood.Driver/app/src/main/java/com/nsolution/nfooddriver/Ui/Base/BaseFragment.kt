package com.nsolution.nfooddriver.Ui.Base

import android.annotation.SuppressLint
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Authentication.SignInFragment

abstract class BaseFragment : Fragment() {


  @SuppressLint("CommitTransaction")
  fun addFragmentToStack(fragment: Fragment) {
    if (!fragment.isAdded) {

      fragmentManager?.commit {
        add(R.id.childFragmentContainer, fragment)
        addToBackStack(fragment.tag)
      }
    }
  }

  fun navigateTo(activity: Class<*>){
    val intent = Intent(context,activity)
    startActivity(intent)
  }
}