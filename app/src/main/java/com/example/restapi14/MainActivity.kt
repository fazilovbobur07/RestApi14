package com.example.restapi14

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.restapi14.adapter.MyRetAdapter
import com.example.restapi14.databinding.ActivityMainBinding
import com.example.restapi14.databinding.ItemDialogBinding
import com.example.restapi14.models.MyTodo
import com.example.restapi14.models.PostTodoRequest
import com.example.restapi14.retrofit.MyApiClient
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var myRetAdapter: MyRetAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MyApiClient.apicl.getAllTodo()
            .enqueue(/* callback = */ object : Callback<List<MyTodo>>{
                override fun onResponse(
                    call: retrofit2.Call<List<MyTodo>>,
                    response: Response<List<MyTodo>>
                ) {
                    if (response.isSuccessful) {
                        val list = response.body() as ArrayList
                        myRetAdapter = MyRetAdapter(list)
                        binding.rv.adapter = myRetAdapter

                    }
                }

                override fun onFailure(call: retrofit2.Call<List<MyTodo>>, t: Throwable) {
                    Toast.makeText(this@MainActivity , "Error ${t.message}" , Toast.LENGTH_LONG).show()
                }
            })

        binding.btnFloatingAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
            itemDialogBinding.btnSave.setOnClickListener {
                val postTodoRequest = PostTodoRequest(
                    itemDialogBinding.edtOhirgiMuddat.text.toString(),
                    itemDialogBinding.edtSarlavha.text.toString(),
                    itemDialogBinding.edtHolat.text.toString(),
                    itemDialogBinding.edtMatn.text.toString()
                )
                MyApiClient.apicl.addTodo(postTodoRequest)
                    .enqueue(object : Callback<MyTodo>{
                        override fun onResponse(call: retrofit2.Call<MyTodo>, response: Response<MyTodo>) {
                            Toast.makeText(this@MainActivity, "${response.body()?.sarlavha} saqlandi", Toast.LENGTH_SHORT).show()

                        }

                        override fun onFailure(call: retrofit2.Call<MyTodo>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "Error ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
                dialog.cancel()
            }
            dialog.setView(itemDialogBinding.root)
            dialog.show()
        }
    }
}