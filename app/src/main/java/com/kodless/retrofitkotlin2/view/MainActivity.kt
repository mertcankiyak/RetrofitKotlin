package com.kodless.retrofitkotlin2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kodless.retrofitkotlin2.R
import com.kodless.retrofitkotlin2.adapter.RecyclerViewAdapter
import com.kodless.retrofitkotlin2.model.CryptoModel
import com.kodless.retrofitkotlin2.service.CryptoAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// at okhttp3.internal.Util.<clinit>(Util.java:87)
// Buna benzer hata alınıyorsa Module build.gradle app dosyasına
// compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
// android { altına eklenmesi gerekiyor.


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {
    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModelListesi: ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    //Disposable
    private var compositeDisposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Recyclerview
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        compositeDisposable = CompositeDisposable()
        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(
            retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(cryptoList: List<CryptoModel>) {
        cryptoModelListesi = ArrayList(cryptoList)
        recyclerViewAdapter = RecyclerViewAdapter(cryptoModelListesi!!, this@MainActivity)
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(applicationContext, cryptoModel.currency, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}