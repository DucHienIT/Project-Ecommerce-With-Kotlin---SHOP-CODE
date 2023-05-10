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
    // Khởi tạo ViewModel để xử lý logic và truy vấn dữ liệu cho Fragment
    private val comparisonListViewModel: ComparisonListViewModel by viewModel { parametersOf(id) }
    var args: ComparisonListFragmentArgs? = null
    var id: Int? = null

    // Khởi tạo View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Gán layout cho Fragment
        return inflater.inflate(R.layout.fragment_comparison_list, container, false)
    }

    // Xử lý sự kiện trên View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lấy dữ liệu truyền vào qua Navigation
        args = arguments?.let { ComparisonListFragmentArgs.fromBundle(it) }
        id = args?.software?.category?.id

        // Đặt tiêu đề cho Toolbar
        "Comparison Software List".also { text_toolbar.text = it }

        // Xử lý sự kiện khi người dùng click vào nút quay lại
        image_back.setOnClickListener {
            it.findNavController().popBackStack()
        }

        // Hiển thị danh sách các phần mềm có thể so sánh
        comparisonListViewModel.comparisonListSoftwareLiveData.observe(viewLifecycleOwner) {
            // Khởi tạo Adapter hiển thị danh sách phần mềm
            val comparisonSoftwareListAdapter: ComparisonSoftwareListAdapter by inject()
            // Gán dữ liệu cho Adapter
            comparisonSoftwareListAdapter.software = it as ArrayList<Software>
            // Đặt Adapter cho RecyclerView
            recyclerview_comparison.adapter = comparisonSoftwareListAdapter
        }

        // Hiển thị ProgressBar khi đang tải dữ liệu
        comparisonListViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            progress(it)
        }

        // Đặt kiểu hiển thị danh sách là dạng dọc
        recyclerview_comparison.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}
