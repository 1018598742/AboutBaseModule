package com.fta.base.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.verticalLayout

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return  AboutFragmentUI().createView(AnkoContext.create(context!!,this))
//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    class AboutFragmentUI: AnkoComponent<AboutFragment>{

        override fun createView(ui: AnkoContext<AboutFragment>): View = ui.apply {
            nestedScrollView {
                verticalLayout {

                }
            }
        }.view

    }
}