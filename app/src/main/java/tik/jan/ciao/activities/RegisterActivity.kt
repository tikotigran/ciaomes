package tik.jan.ciao.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import tik.jan.ciao.R
import tik.jan.ciao.databinding.ActivityRegisterBinding
import tik.jan.ciao.ui.fragments.EnterphoneNumberFragment
import tik.jan.ciao.utilites.initFirebase
import tik.jan.ciao.utilites.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()

    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registertoolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterphoneNumberFragment(), false)
    }
}
