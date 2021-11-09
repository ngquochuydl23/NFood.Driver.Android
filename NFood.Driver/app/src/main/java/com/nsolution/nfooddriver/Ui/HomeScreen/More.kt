package com.nsolution.nfooddriver.Ui.HomeScreen

import android.content.Intent
import android.os.Bundle
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.Authentication.Authentication
import com.nsolution.nfooddriver.Ui.Base.BaseActivity
import com.nsolution.nfooddriver.Ui.Document.DocumentManagement
import com.nsolution.nfooddriver.Ui.Earnings.Earnings
import com.nsolution.nfooddriver.Ui.Helps.HelpCenter
import com.nsolution.nfooddriver.Ui.History.TripHistory
import com.nsolution.nfooddriver.Ui.Notification.Notification
import com.nsolution.nfooddriver.Ui.Profile.Profile
import com.nsolution.nfooddriver.Ui.Settings.Settings
import com.nsolution.nfooddriver.Ui.Payment.Payment
import kotlinx.android.synthetic.main.activity_more.*

class More : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_more)

    initialView()

  }

  private fun initialView() {
    getCloseActionBar(header, getString(R.string.more))

    documentManagementButton.setOnClickListener {
      navigateTo(DocumentManagement::class.java)
    }

    driverInformationContainer.setOnClickListener {
      navigateTo(Profile::class.java)
    }

    settingButton.setOnClickListener {
      navigateTo(Settings::class.java)
    }

    notificationButton.setOnClickListener {
      navigateTo(Notification::class.java)
    }

    signOutButton.setOnClickListener {
      navigateToAuthScreen()
    }

    tripHistoryButton.setOnClickListener {
      navigateTo(TripHistory::class.java)
    }

    earningsButton.setOnClickListener {
      navigateTo(Earnings::class.java)
    }

    helpsButton.setOnClickListener {
      navigateTo(HelpCenter::class.java)
    }

    paymentButton.setOnClickListener {
      navigateTo(Payment::class.java)
    }
  }

  private fun navigateToAuthScreen() {
    val intent = Intent(this, Authentication::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
    finish()
  }
}
