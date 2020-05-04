package tik.jan.ciao.utilites

enum class AppStates(val state:String) {
    ONLINE("в сети"),
    OFFLINE("был недавно"),
    TYPING("печатает");

    companion object{
        fun updateState(appStates: AppStates){

        }
    }
}