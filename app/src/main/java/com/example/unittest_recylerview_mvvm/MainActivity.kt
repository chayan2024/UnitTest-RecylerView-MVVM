package com.example.unittest_recylerview_mvvm
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unittest_recylerview_mvvm.adapter.ItemAdapter
import com.example.unittest_recylerview_mvvm.viewmodel.ItemViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        itemAdapter = ItemAdapter(itemViewModel.items.value ?: emptyList())

        recyclerView.adapter = itemAdapter

        itemViewModel.items.observe(this) { items ->
            itemAdapter.updateItems(items)
        }
    }
}

