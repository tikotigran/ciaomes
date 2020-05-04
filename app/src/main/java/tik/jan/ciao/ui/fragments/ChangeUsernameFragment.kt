package tik.jan.ciao.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.fragment_change_username.*
import tik.jan.ciao.MainActivity
import tik.jan.ciao.R
import tik.jan.ciao.utilites.*
import java.util.*


class ChangeUsernameFragment : BaseFragment(R.layout.fragment_change_username) {

    lateinit var mNewusername: String


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        settings_input_username.setText(USER.username)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> change()
        }
        return true
    }

    private fun change() {
        mNewusername = settings_input_username.text.toString().toLowerCase(Locale.getDefault())
        if (mNewusername.isEmpty()){
            showToast("Поле пустое")

        }else {
            REF_DATABASE_ROOT.child(NODE_USERNAME)
                .addListenerForSingleValueEvent(AppValueEventListener{
                    if (it.hasChild(mNewusername)){
                        showToast("Такой пользователь уже существует")
                    }else{
                        changeUsername()
                    }
                })

    }
    }
    private fun changeUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAME).child(mNewusername).setValue(UID)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    updateCurrentUsername()

                }

            }
    }

    private fun updateCurrentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_USERNAME)
            .setValue(mNewusername)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast(getString(R.string.toast_data_update))
                    deleteOldUsername()
                }else{
                    showToast(it.exception?.message.toString())
                }
            }
    }

    private fun deleteOldUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAME).child(USER.username).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast(getString(R.string.toast_data_update))
                    fragmentManager?.popBackStack()
                    USER.username = mNewusername
                }else{
                    showToast(it.exception?.message.toString())
                }
            }
    }
}
