package com.example.alankituniverse.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alankituniverse.R
import com.example.alankituniverse.data.local.AppPreference
import com.example.alankituniverse.databinding.ActivityErrorBinding
import com.example.alankituniverse.ui.activity.ehrms.EhrmsMainActivity
import com.example.alankituniverse.util.extns.LottieType
import com.example.alankituniverse.util.extns.launchActivity
import com.example.alankituniverse.util.extns.set
import com.example.alankituniverse.util.extns.textColor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ErrorActivity : AppCompatActivity() {

    @Inject
    lateinit var appPreference: AppPreference

    private var isTransactionApi = false

    private lateinit var type: String
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var action: String
    private lateinit var binding: ActivityErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra(TYPE)!!
        title = intent.getStringExtra(TITLE)!!
        description = intent.getStringExtra(DESCRIPTION)!!
        action = intent.getStringExtra(ACTION) ?: "default_action"

        when (type) {
            ROOT -> setUpViewForRootCondition()
            NETWORK_EXCEPTION -> setUpViewForNetworkException()
        }

        binding.let {
            it.tvTitle.text = title
            it.tvDescription.text = description
        }
    }

    private fun setUpViewForNetworkException() {

        val networkExceptionType = intent.getStringExtra(NetworkExceptions.NETWORK_EXCEPTION_TYPE)
        binding.tvTitle.textColor(this, R.color.red)
        binding.lottieView.set(LottieType.ALERT)

        if (isTransactionApi) {
            binding.tvTitle.textColor(this, R.color.purple_500)
            binding.lottieView.set(LottieType.PENDING)
            //   binding.tvTitle.text = getString(R.string.please_check_transaction_report)
            //  binding.tvDescription.text = getString(R.string.transaction_error_message)
        } else when (networkExceptionType) {
            NetworkExceptions.NO_INTERNET -> binding.lottieView.set(LottieType.NO_INTERNET)
            NetworkExceptions.INTERNAL_SERVER_ERROR -> binding.lottieView.set(LottieType.SERVER)
            NetworkExceptions.TIME_OUT_EXCEPTION -> binding.lottieView.set(LottieType.TIME_OUT)
        }
    }

    private fun setUpViewForRootCondition() {
        binding.lottieView.set(LottieType.WARNING)
        binding.tvTitle.textColor(this, R.color.red)
    }

    override fun onBackPressed() {
        when (action) {
            NAVIGATE_TO_LOGIN ->
                launchActivity(EhrmsMainActivity::class.java, forgotAll = true)
            NAVIGATE_TO_MAIN ->
                launchActivity(DashboardActivity::class.java, forgotAll = true)
            else -> {
                if (type == NETWORK_EXCEPTION && isTransactionApi)
                    launchActivity(EhrmsMainActivity::class.java, forgotAll = true)
                else super.onBackPressed()
            }
        }
    }

    companion object {
        //TAGS
        const val TYPE = "type"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val ACTION = "action"

        //TYPES
        const val ROOT = "root"
        const val NETWORK_EXCEPTION = "network_exception"

        //ACTIONS
        const val NAVIGATE_TO_MAIN = "navigate_to_main"
        const val NAVIGATE_TO_LOGIN = "navigate_to_login"
    }

    class NetworkExceptions {
        companion object {
            const val NETWORK_EXCEPTION_TYPE = "network_exception_type"
            const val INTERNAL_SERVER_ERROR = "internal_server_error"
            const val TIME_OUT_EXCEPTION = "time_out_exception"
            const val UNAUTHORIZED = "unauthorized"
            const val NO_INTERNET = "no_internet"
            const val OTHER = "other"
        }
    }
}
