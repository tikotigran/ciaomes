package tik.jan.ciao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import tik.jan.ciao.activities.RegisterActivity
import tik.jan.ciao.databinding.ActivityMainBinding
import tik.jan.ciao.ui.fragments.ChatFragment
import tik.jan.ciao.ui.objacts.AppDrawer
import tik.jan.ciao.utilites.AUTH
import tik.jan.ciao.utilites.initFirebase
import tik.jan.ciao.utilites.replaceActivity
import tik.jan.ciao.utilites.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
            lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if(AUTH.currentUser!=null){
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatFragment(),false)

        }else{
            replaceActivity(RegisterActivity())

        }
    }

    private fun initFields() {
        mToolbar = mBinding.maintoolbar
        mAppDrawer = AppDrawer(this,mToolbar)
        initFirebase()

    }
}
