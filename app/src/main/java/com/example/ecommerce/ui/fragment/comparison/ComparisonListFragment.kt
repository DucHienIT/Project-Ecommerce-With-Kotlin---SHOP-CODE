package com.example.ecommerce.ui.fragment.comparison

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.model.Software
import com.example.ecommerce.ui.adapter.ComparisonSoftwareListAdapter
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.viewmodel.ComparisonListViewModel
import kotlinx.android.synthetic.main.fragment_comparison_list.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.ArrayList


class ComparisonListFragment : Fragment() {
    private val comparisonListViewModel: ComparisonListViewModel by viewModel { parametersOf(id) }
    var args: ComparisonListFragmentArgs? = null
    var id: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comparison_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = arguments?.let { ComparisonListFragmentArgs.fromBundle(it) }
        id = args?.software?.category
        "Comparison Software List".also { text_toolbar.text = it }
        image_back.setOnClickListener {
            it.findNavController().popBackStack()
        }
        comparisonListViewModel.comparisonListSoftwareLiveData.observe(viewLifecycleOwner) {
            val comparisonSoftwareListAdapter: ComparisonSoftwareListAdapter by inject()
            comparisonSoftwareListAdapter.software = it as ArrayList<Software>
            recyclerview_comparison.adapter = comparisonSoftwareListAdapter
        }
        comparisonListViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            progress(it)
        }
        recyclerview_comparison.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


}