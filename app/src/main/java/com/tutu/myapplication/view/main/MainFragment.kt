package com.tutu.myapplication.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutu.myapplication.R
import com.tutu.myapplication.databinding.MainFragmentBinding
import com.tutu.myapplication.model.data.AppState
import com.tutu.myapplication.model.data.DataModel
import com.tutu.myapplication.view.base.BaseFragment
import com.tutu.myapplication.view.details.DetailsFragment
import com.tutu.myapplication.view.main.adapter.MainAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<AppState, MainInteractor>() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                parentFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.activity_container, DetailsFragment.newInstance(data))
                    .commit()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniViewModel()
        initViews()
    }

    private fun initViews() {
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    private fun iniViewModel() {
        val viewModel: MainViewModel by viewModel()

        model = viewModel
        model.subscribe().observe(viewLifecycleOwner, { renderData(it) })
        model.getData(isNetworkAvailable)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }
}