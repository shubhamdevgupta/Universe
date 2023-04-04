package com.example.alankituniverse.util.nav_drawer

class NavMenuModel {
    var menuTitle: String
    var menuIconDrawable: Int = 0

    constructor(menuTitle: String, menuIconDrawable: Int) {
        this.menuTitle = menuTitle
        this.menuIconDrawable = menuIconDrawable
    }

    constructor(menuTitle: String, menuIconDrawable: Int, subMenu: ArrayList<SubMenuModel>) {
        this.menuTitle = menuTitle
        this.menuIconDrawable = menuIconDrawable
    }

    class SubMenuModel(var subMenuTitle: String)
}