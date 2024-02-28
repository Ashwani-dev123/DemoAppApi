package com.ashwani.demoapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashwani.demoapp.Adapter.ItemAdapter
import com.ashwani.demoapp.Model.Data
import com.ashwani.demoapp.Model.ResponseModel
import com.ashwani.demoapp.Repository.PostRepository
import com.ashwani.demoapp.Room.DataRepository
import com.ashwani.demoapp.ViewModel.PostViewModel
import com.ashwani.demoapp.ViewModel.PostViewModelFactory
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var etSearch :EditText
    lateinit var ivSearch :ImageView
    lateinit var ivClose :ImageView
    private lateinit var postRepository: PostRepository
    private lateinit var postViewModelFactory: PostViewModelFactory
    private lateinit var postViewModel: PostViewModel
    lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter:ItemAdapter
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var currentPage: Int = 0

    lateinit var dataListMain: ArrayList<Data>
    lateinit var dataListTemp: ArrayList<Data>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerV)
        etSearch=findViewById(R.id.etSearch)
        etSearch.visibility= View.GONE
        ivSearch=findViewById(R.id.ivSearch)
        ivClose=findViewById(R.id.ivClose)

        ivClose.visibility= View.GONE
        val repository = DataRepository(this)


        ivSearch.setOnClickListener {
            etSearch.visibility= View.VISIBLE
            ivSearch.visibility= View.GONE
            ivClose.visibility= View.VISIBLE

            val dataListJson = Gson().toJson(dataListMain)
            val data = com.ashwani.demoapp.Room.Data(dataListJson = dataListJson)

            // AV save data
//            lifecycleScope.launch {
//                repository.insertData(data)
//            }
        }

        ivClose.setOnClickListener {
            etSearch.setText("")
            etSearch.visibility= View.GONE
            ivSearch.visibility= View.VISIBLE
            ivClose.visibility= View.GONE


            //AV retrieve data
//            lifecycleScope.launch {
//                val allData = repository.getAllData()
//                val dataListMain = mutableListOf<Data>()
//                for (data in allData) {
//                    val dataListJson = data.dataListJson
//                    val dataList: ArrayList<Data> = Gson().fromJson(dataListJson, object : TypeToken<ArrayList<Data>>() {}.type)
//                    dataListMain.addAll(dataList)
//                }
//
//                Log.e("dashkgdfaysdv", "onCreate: "+dataListMain.size)
//            }

        }

//        initRecyclerView(it.body() as ArrayList<ResponseModel>)
        dataListMain= ArrayList()
        dataListTemp= ArrayList()

        postRepository= PostRepository()
        postViewModelFactory= PostViewModelFactory(postRepository)
        postViewModel= ViewModelProvider(this,postViewModelFactory).get(PostViewModel::class.java)

        postViewModel.getPost(currentPage,5)

        itemAdapter= ItemAdapter(ArrayList(),this)

        val layoutManager= LinearLayoutManager(this)
        initRecyclerView(ArrayList(),layoutManager)


        postViewModel.myResponse.observe(this, Observer {
//            Log.e("asdasdasda","sdfdsfs   "+ it.body()!!.data)
            Log.e("asdasdasda","currentPage   "+ currentPage)

            itemAdapter.setPostData((it.body()!!.data))
            isLoading = false
            currentPage++

            dataListMain.clear()
            dataListMain.addAll(itemAdapter.dataList)

        })

        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
    //                getMoreItems()
                Log.e("asdasdasda","onScrolled   ")
                postViewModel.getPost(currentPage,5)
            }
        })

        etSearch.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                dataListTemp.clear()
            }
        } )

    }

    private fun initRecyclerView(
        responseModels: ArrayList<ResponseModel>,
        layoutManager: LinearLayoutManager
    ) {
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter=itemAdapter
    }
}
