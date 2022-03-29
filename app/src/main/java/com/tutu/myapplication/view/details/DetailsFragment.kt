package com.tutu.myapplication.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.tutu.myapplication.databinding.DetailsFragmentBinding
import com.tutu.myapplication.model.data.DataModel

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val KEY = "details"

        fun newInstance(data: DataModel) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY, data)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderData()
    }

    private fun renderData() {

        val data = arguments?.getParcelable<DataModel>(KEY)
        with(binding) {
            data?.let {
                Glide.with(binding.image).load(it.avatar_url).into(image)
                userId.text = it.id.toString()
                userLogin.text = it.login
                userType.text = it.type
            }
        }
    }
}