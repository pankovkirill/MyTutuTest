package com.tutu.myapplication.view.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tutu.myapplication.R
import com.tutu.myapplication.databinding.LoadingLayoutBinding
import com.tutu.myapplication.model.data.AppState
import com.tutu.myapplication.model.data.DataModel
import com.tutu.myapplication.utils.network.isOnline
import com.tutu.myapplication.viewmodel.BaseViewModel
import com.tutu.myapplication.viewmodel.Interactor

abstract class BaseFragment<T : AppState, I : Interactor<T>> : Fragment() {

    private var _binding: LoadingLayoutBinding? = null
    private val binding get() = _binding!!
    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isNetworkAvailable = isOnline(requireContext())
        _binding = LoadingLayoutBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        _binding = LoadingLayoutBinding.inflate(layoutInflater)

        isNetworkAvailable = isOnline(requireContext())
        if (!isNetworkAvailable) {
            showNoInternetConnectionToast()
        }
    }

    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showToast(
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showToast(appState.error.message)
            }
        }
    }

    protected fun showNoInternetConnectionToast() {
        showToast(getString(R.string.dialog_message_device_is_offline))
    }

    protected fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = View.VISIBLE
    }

    abstract fun setDataToAdapter(data: List<DataModel>)
}