package com.poc.androidassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.poc.androidassignment.R
import com.poc.androidassignment.activities.MainActivity
import com.poc.androidassignment.adapters.CountryDataDetailsAdapter
import com.poc.androidassignment.databinding.FragmentLivedataBinding
import com.poc.androidassignment.utils.NetworkUtils
import com.poc.androidassignment.utils.getStringPreference
import com.poc.androidassignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_livedata.*

class LiveDataFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    var networkUtils: NetworkUtils = NetworkUtils()
    var isSwipe = false;

    /**
     * function to initialise view models and network
     * ***/
    private fun initVM() {
        networkUtils = NetworkUtils()
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.isProgessDisplay.observe(viewLifecycleOwner, {
            it?.let { loader ->
                if (loader) {
                    if (!isSwipe) {
                        loadingprogress.visibility = View.VISIBLE
                    } else {
                        isSwipe = false
                        loadingprogress.visibility = View.GONE
                    }

                } else {
                    loadingprogress.visibility = View.GONE
                }
            }
        })

        viewModel.strTitle.observe(viewLifecycleOwner, {
            it?.let { title ->
                if (!title.isEmpty()) {
                    (activity as MainActivity).supportActionBar?.title =
                        activity?.getStringPreference("title")
                }
            }
        })
        (activity as MainActivity).supportActionBar?.title = activity?.getStringPreference("title")
        if (networkUtils.isOnline(requireContext())) {
            viewModel.makeApiCall()
        } else {
            viewModel.gerAllUsers()
            Toast.makeText(requireContext(), R.string.string_internet, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * function to refresh data after swipe
     * ****/
    fun refreshList() {
        swipeToRefresh?.setOnRefreshListener {
            isSwipe = true
            if (networkUtils.isOnline(requireContext())) {
                viewModel.makeApiCall()
                Toast.makeText(requireContext(), R.string.data_refreshed, Toast.LENGTH_SHORT).show()
            } else {
                viewModel.gerAllUsers()
                Toast.makeText(requireContext(), R.string.string_internet, Toast.LENGTH_SHORT)
                    .show()
            }
            Toast.makeText(requireContext(), R.string.data_refreshed, Toast.LENGTH_SHORT).show()
            swipeToRefresh.isRefreshing = false
        }
    }


    /**
     * override function view created
     *
     * ****/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshList()
        displayProgress()
        visibleNodataText()
    }


    /**
     * function to display progress
     * ***/
    private fun displayProgress() {
        if (!networkUtils.isOnline(requireContext())) {
            loadingprogress.visibility = View.GONE
        }
    }

    /**
     *function to display no data
     * ***/
    private fun visibleNodataText() {
        viewModel.countryResponseData.observe(viewLifecycleOwner, {
            it?.let { data ->
                if (data.isEmpty()) {
                    txtnodataavail.visibility = View.VISIBLE
                } else {
                    txtnodataavail.visibility = View.GONE
                }
            }
        })
    }

    /***
     *
     * overide function to create view and binding view to the fragment
     * ***/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentLiveDataBinding: FragmentLivedataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_livedata, container, false)
        refreshList()
        val objCustomListeners = object : BindingCustomDependencies {
            override fun getcountryListAdapter(): CountryDataDetailsAdapter =
                CountryDataDetailsAdapter(requireContext())

            override fun getLayoutManager(): LinearLayoutManager =
                LinearLayoutManager(requireContext())

        }
        initVM()
        with(fragmentLiveDataBinding) {
            lifecycleOwner = this@LiveDataFragment
            countryListViewModel = viewModel
            bindadapter = objCustomListeners
        }
        return fragmentLiveDataBinding.root
    }
}

/**
 * inteface for adapter class and layout manager
 * **/

interface BindingCustomDependencies {
    fun getcountryListAdapter(): CountryDataDetailsAdapter
    fun getLayoutManager(): LinearLayoutManager
}