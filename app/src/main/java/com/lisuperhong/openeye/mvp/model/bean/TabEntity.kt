package com.lisuperhong.openeye.mvp.model.bean

import com.flyco.tablayout.listener.CustomTabEntity

class TabEntity(val title: String, private val selectedIcon: Int, private val unSelectedIcon: Int) :
    CustomTabEntity {
    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }
}