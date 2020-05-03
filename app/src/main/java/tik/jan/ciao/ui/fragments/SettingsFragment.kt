package tik.jan.ciao.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import tik.jan.ciao.MainActivity
import tik.jan.ciao.R
import tik.jan.ciao.activities.RegisterActivity
import tik.jan.ciao.utilites.AUTH
import tik.jan.ciao.utilites.replaceActivity


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }

        return true
    }
}
