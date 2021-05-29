package com.rsschool.android2021

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var min: EditText? = null
    private var max: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        min = view.findViewById(R.id.min_value)
        max = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)

        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {

            val minValue = min?.text?.toString() ?: ""
            val maxValue = max?.text?.toString() ?: ""

             if(minValue != ""  &&  maxValue != "" &&
                    minValue.toInt() < maxValue.toInt()){
                val mainActivity = activity as MainActivity
                mainActivity.openSecondFragment(minValue.toInt(), maxValue.toInt())
            } else previousResult?.text = "Write correct data"

        }
    }

    interface ListenerFirstFragment{
        fun openFirstFragment(previousNumber: Int)
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }


}