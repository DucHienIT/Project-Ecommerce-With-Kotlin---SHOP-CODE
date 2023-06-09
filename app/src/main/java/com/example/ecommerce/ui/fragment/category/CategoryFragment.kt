package com.example.ecommerce.ui.fragment.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.ui.adapter.CategoryListAdapter
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


/**
 * Fragment hiển thị danh sách các danh mục sản phẩm.
 */
class CategoryFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Quan sát sự thay đổi của danh sách các danh mục sản phẩm và hiển thị lên giao diện.
        homeViewModel.categoryLiveDate.observe(viewLifecycleOwner) { categories ->
            val categoryAdapter: CategoryListAdapter by inject { parametersOf(categories) }
            category.adapter = categoryAdapter
        }

        // Thiết lập tiêu đề và ẩn nút back trên thanh toolbar.
        image_back.visibility = View.GONE
        text_toolbar.text = getString(R.string.category)

        println("Page List Category")

        // Quan sát sự thay đổi của trạng thái của progressbar và hiển thị lên giao diện.
        homeViewModel.progressbarLiveData.observe(viewLifecycleOwner) { visible ->
            progress(visible)
        }

        // Thiết lập layout cho danh sách các danh mục sản phẩm.
        category.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}
