package com.example.ecommerce.ui.fragment.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.ui.adapter.PropertySoftwareAdapter
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.viewmodel.PropertySoftwareViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_property.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class PropertyFragment : Fragment() {
    private val propertySoftwareViewModel: PropertySoftwareViewModel by viewModel { parametersOf(id) }
    var args: PropertyFragmentArgs? = null
    var id: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_property, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = arguments?.let { PropertyFragmentArgs.fromBundle(it) }
        id = args?.software?.id
        "Technical Specifications".also { text_toolbar.text = it }
        image_back.setOnClickListener {
            it.findNavController().popBackStack()
        }
        propertySoftwareViewModel.propertySoftwareLiveData.observe(viewLifecycleOwner) {
            val propertySoftwareAdapter: PropertySoftwareAdapter by inject { parametersOf(it) }
            recyclerview_property.adapter = propertySoftwareAdapter
        }
        recyclerview_property.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        propertySoftwareViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            progress(it)
        }
    }
}








