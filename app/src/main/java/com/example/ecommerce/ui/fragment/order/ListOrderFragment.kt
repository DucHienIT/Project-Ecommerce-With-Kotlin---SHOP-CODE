package com.example.ecommerce.ui.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.ui.adapter.AdapterListOrder
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.viewmodel.ListOrderViewModel
import kotlinx.android.synthetic.main.fragment_list_order.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ListOrderFragment : Fragment() {

    private val listOrderViewModel: ListOrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listOrderViewModel.listOrder(
            "Bearer ${TokenHolder.access_token}"
        )

        // Quan sát sự thay đổi của danh sách các danh mục sản phẩm và hiển thị lên giao diện.
        listOrderViewModel.listOrderLiveData.observe(viewLifecycleOwner) { orders ->
            val listOrderAdapter: AdapterListOrder by inject { parametersOf(orders) }
            listOrderFragment.adapter = listOrderAdapter
        }

        // Thiết lập tiêu đề và ẩn nút back trên thanh toolbar.
        image_back.visibility = View.GONE
        text_toolbar.text = "List Order"

        println("Page List Category")

        // Quan sát sự thay đổi của trạng thái của progressbar và hiển thị lên giao diện.
        listOrderViewModel.progressbarLiveData.observe(viewLifecycleOwner) { visible ->
            progress(visible)
        }

        // Thiết lập layout cho danh sách các danh mục sản phẩm.
        listOrderFragment.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}
