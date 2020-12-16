package com.mahmoud_ashraf.currencyconverter.presentation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object Navigation {

    fun replaceFragment(
        supportFragmentManager: FragmentManager, @IdRes idResContainer: Int,
        fragment: Fragment
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idResContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}