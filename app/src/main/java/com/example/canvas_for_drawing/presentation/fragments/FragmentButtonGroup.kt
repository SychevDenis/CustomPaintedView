package com.example.canvas_for_drawing.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.presentation.MainActivity

class FragmentButtonGroup() : Fragment() {
    private lateinit var buttonBack: Button
    private lateinit var buttonNext: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_button_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("log", "OnCreateBG")
        buttonBack = view.findViewById(R.id.button_back)
        buttonNext = view.findViewById(R.id.button_next)
        buttonBack.setOnClickListener(){
            MainActivity().onClickBack()
        }
        buttonNext.setOnClickListener(){
            MainActivity().onClickNext()
        }
    }

}
