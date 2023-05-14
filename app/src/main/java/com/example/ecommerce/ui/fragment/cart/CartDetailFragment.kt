package com.example.ecommerce.ui.fragment.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.ui.adapter.CartListAdapter
import com.example.ecommerce.ui.fragment.detail.DetailSoftwareFragmentArgs
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.viewmodel.AddCartViewModel
import com.example.ecommerce.viewmodel.CartViewModel
import com.example.ecommerce.viewmodel.LoginViewModel
import com.example.ecommerce.viewmodel.SubCartViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart_detail.*
import kotlinx.android.synthetic.main.fragment_category.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CartDetailFragment : Fragment() {
    private var args: CartDetailFragmentArgs? = null
    private val addCartViewModel: AddCartViewModel by viewModel()
    private val subCartViewModel: SubCartViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    var id: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = args?.cart?.id
        args = arguments?.let { CartDetailFragmentArgs.fromBundle(it) }
        text.text=args?.cart?.software?.name
        Picasso.get().load(args?.cart?.software?.image_url).into(image);
        quantity.text=args?.cart?.quantity.toString()
        println(id)
        add.setOnClickListener{
            if (loginViewModel.checkLoginStatus.value == false) {
                it.findNavController().navigate(R.id.action_detailSoftwareFragment_to_loginFragment2)
            } else {
                args?.cart?.software?.id?.let { it1 ->
                    addCartViewModel.addCart(
                        it1,"Bearer ${TokenHolder.access_token}")
                }
                Toast.makeText(context,"Add successfully", Toast.LENGTH_SHORT).show()

            }

        }

        sub.setOnClickListener{
            if (loginViewModel.checkLoginStatus.value == false) {
                it.findNavController().navigate(R.id.action_detailSoftwareFragment_to_loginFragment2)
            } else {
                args?.cart?.software?.id?.let { it1 ->
                    subCartViewModel.subCart(
                        it1,"Bearer ${TokenHolder.access_token}")
                }
                Toast.makeText(context,"Sub cart successfully", Toast.LENGTH_SHORT).show()

            }
        }

    }
}