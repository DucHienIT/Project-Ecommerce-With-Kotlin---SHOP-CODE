package com.example.ecommerce.ui.fragment.category

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.ui.adapter.AdapterCategoryDetail
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.viewmodel.CategoryDetailViewModel
import kotlinx.android.synthetic.main.fragment_category_list.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


// Định nghĩa Fragment hiển thị danh sách sản phẩm theo danh mục
class CategoryListFragment : Fragment() {
    // Khởi tạo ViewModel để xử lý logic và truy vấn dữ liệu cho Fragment
    private val categoryDetailSoftwareViewModel: CategoryDetailViewModel by viewModel { parametersOf(id) }
    var args: CategoryListFragmentArgs? = null
    var id: Int? = null

    // Khởi tạo View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Gán layout cho Fragment
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    // Khởi tạo và xử lý sự kiện trên View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Lấy dữ liệu truyền vào qua Navigation
        args = arguments?.let { CategoryListFragmentArgs.fromBundle(it) }
        id = args?.id?.id

        // Xử lý sự kiện khi người dùng click vào nút quay lại
        image_back.setOnClickListener {
            findNavController().popBackStack()
        }

        // Đặt tiêu đề cho Toolbar
        text_toolbar.text = getString(R.string.category_list)

        // Hiển thị ProgressBar khi đang tải dữ liệu
        categoryDetailSoftwareViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            progress(it)
        }

        // Hiển thị danh sách sản phẩm trong danh mục
        categoryDetailSoftwareViewModel.categoryDetailLiveData.observe(viewLifecycleOwner) {
            // Khởi tạo Adapter hiển thị danh sách sản phẩm
            val adapterCategoryDetail: AdapterCategoryDetail by inject { parametersOf(it) }
            // Đặt Adapter cho RecyclerView
            category_list.adapter = adapterCategoryDetail
        }
        // Đặt kiểu hiển thị danh sách là dạng dọc
        category_list.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }
}