package com.example.ecommerce.ui.fragment.chat

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.model.Chat
import com.example.ecommerce.network.OpenAIClient
import com.example.ecommerce.ui.adapter.CartListAdapter
import com.example.ecommerce.ui.adapter.MessageAdapter
import com.example.ecommerce.ui.fragment.detail.DetailSoftwareFragmentArgs
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.viewmodel.AddCartViewModel
import com.example.ecommerce.viewmodel.CartViewModel
import com.example.ecommerce.viewmodel.LoginViewModel
import com.example.ecommerce.viewmodel.SubCartViewModel
import com.example.ecommerce.utils.Constants
import com.example.ecommerce.utils.RecyclerItemDecoration
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart_detail.*
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_chat.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.io.IOException
import java.util.ArrayList

class ChatFragment : Fragment() {

    private var openAIClient: OpenAIClient? = null
    private var messageLists: ArrayList<Chat> = ArrayList()
    private var mAdapter: MessageAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_back.setOnClickListener {
            it.findNavController().popBackStack()
        }

        // Initialize Adapter and RecyclerView
        mAdapter  = MessageAdapter(requireContext(), messageLists)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.stackFromEnd = true
        rvChats.layoutManager = layoutManager
        rvChats.addItemDecoration(RecyclerItemDecoration())
        rvChats.adapter = mAdapter


        iv_send.setOnClickListener {
            val prompt = et_text.text.toString()
            if (prompt == "" || prompt.isEmpty()) {
                addText(Constants.TYPE_AI, Constants.EMPTY_AI_MESSAGE)
            } else {
                userPrompt(prompt, Constants.TYPE_USER)
                if (checkForInternet(requireContext())) {
                    sentRequest()
                }
            }
            println(prompt)

        }

        // Initialize OpenAI
        setupOpenAI()

        savedInstanceState?.let {
            messageLists = it.getParcelableArrayList("messageLists") ?: ArrayList()
            if (messageLists.size >= 1){
                tv_intro.visibility = View.GONE
            }
            mAdapter?.updateList(messageLists)
            mAdapter?.notifyDataSetChanged()
        }



    }



    private fun setupOpenAI() {
        openAIClient = OpenAIClient()
        openAIClient!!.setModel(Constants.MODEL)
        openAIClient!!.setApiUrl(Constants.BASE_URL)
        openAIClient!!.setApiKey(Constants.API_KEY)
        val enable: Boolean =
            openAIClient!!.getModel().equals(OpenAIClient.GPT_3_5_TURBO)
        openAIClient!!.setMaxTokensEnabled(enable)
        openAIClient!!.setMaxTokens(Constants.MAX_TOKENS.toDouble())
        openAIClient!!.setTemperature(Constants.TEMPERATURE.toDouble())
    }

    private fun addText(type: Int, message: String) {
        val chat = Chat(type, type, message)
        requireActivity().runOnUiThread {
            messageLists.add(chat)
            mAdapter!!.notifyItemInserted(messageLists.size - 1)
            rvChats.smoothScrollToPosition(messageLists.size - 1)

        }
    }

    private fun userPrompt(message: String, type: Int) {
        when (type) {
            2 -> {
                val chatMessage = Chat(Constants.TYPE_AI, Constants.TYPE_AI, "typing...")
                requireActivity().runOnUiThread {
                    tv_intro.visibility = View.GONE
                    messageLists.add(chatMessage)
                    mAdapter!!.notifyItemInserted(messageLists.size - 1)
                    rvChats.smoothScrollToPosition(messageLists.size - 1)
                }
            }
            1 -> {
                val chatMessage = Chat(type, type, message)
                requireActivity().runOnUiThread {
                    tv_intro.visibility = View.GONE
                    messageLists.add(chatMessage)
                    mAdapter!!.notifyItemInserted(messageLists.size - 1)
                    rvChats.smoothScrollToPosition(messageLists.size - 1)

                    if (!checkForInternet(requireContext())) {
                        addText(Constants.TYPE_AI, Constants.NO_AI_INTERNET)
                        et_text.setText("")
                    }
                }
            }
            else -> {
                updateText(type, message)
            }
        }
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun updateText(type: Int, message: String) {

        requireActivity().runOnUiThread {

            messageLists[type].message = message
            mAdapter!!.notifyItemChanged(messageLists.size - 1)
            rvChats.smoothScrollToPosition(messageLists.size - 1)

        }
    }

    private fun sentRequest() {
        val userInput: String = et_text.text.toString()
        if (!TextUtils.isEmpty(userInput)) {
            userPrompt(userInput, 2)
            et_text.setText("")
            try {
                openAIClient!!.setPrompt(userInput)
                openAIClient!!.generateResponse(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e(Constants.TAG, e.message.toString()) }
                    @Throws(IOException::class)
                    override fun onResponse(call: Call, response: Response) {
                        val responseBody = response.body!!.string()
                        try {
                            updateText(messageLists.size - 1, openAIClient!!.getResponse(responseBody)!!)
                        } catch (e: JSONException) {
                            println("Lỗi rồi")
                            try {
                                removeMessage(messageLists.size - 1)
                            } catch (ex: JSONException) {
                                // Handle errors
                                Log.e(Constants.TAG, ex.message!!)
                            }
                        }
                    }
                })
            } catch (e: Exception) {
                // Handle errors
                Log.e(Constants.TAG, e.message!!)
            }
        }
    }
    private fun removeMessage(position: Int) {
        requireActivity().runOnUiThread {
            messageLists.removeAt(position)
            mAdapter!!.notifyItemRemoved(position)
            rvChats.smoothScrollToPosition(messageLists.size - 1)

        }
    }

}